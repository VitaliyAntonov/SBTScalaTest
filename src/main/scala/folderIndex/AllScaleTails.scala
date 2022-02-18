package folderIndex

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer





/**
 *  Класс создаёт пирамиду тайлов для крупных масштабов
 *  к примеру, если upScale = 10, то используя файлы рисунков из папки 10
 *  создаём папку 9, сливая по 4 рисунка и приводя их размер к 256 x 256 пикселей
 *  затем переходим на уровень вверх и из папки 9 создаём масштаб 8.
 *  Так продолжаем до 0-й папки. В 0-й папке создаём последний рисунок
 * @param pyramidPath      - путь к исходной пирамиде тайлов
 * @param upScale   - масштаб(номер папки) от которого строим пирамиду тайлов вверх
 */
class AllScaleTails(pyramidPath: String, upScale: Int) extends FileSystem {



  createPyramydScale // Создаём пирамиду тайлов от текущего масштаба до 0-го

  /** Функция строит всю пирамиду от заданного масштаба до 0-го */
  def createPyramydScale = {
    for(num <- upScale to 1 by -1){
      mergeImagesAndSave(num)
    }
  }

  /**
   * Функция создаёт слой пирамиды тайлов крупных масштабов
   * @param actualPath - Номер сканируемого масштаба(номер/имя папки)
   */
  def mergeImagesAndSave(actualPath: Int) = {

    /** Путь к папке с масштабом actualPath */
    val upScalePath: String = {
      if (pyramidPath.endsWith(dm)) pyramidPath + actualPath.toString
      else pyramidPath + dm + actualPath.toString
    }


    /** Создаём папку для сохранения верхнего слоя пирамиды */
    val savePath = pyramidPath + dm + (actualPath - 1).toString
    newPathCreate(savePath)

    /** сканируем папку с масштабом upScale */
    val scanUpScale = new FolderTileScan(upScalePath)  // inFoldersTilesNames(upScalePath)

    /** Номер первой папки(строки) в цикле всегда чётный */
    val honestNumber = {
      if((scanUpScale.foldersList.head.toInt % 2) == 0){   // номер первой папки чётный
        scanUpScale.foldersList.head.toInt
      }
      else scanUpScale.foldersList.head.toInt - 1
    }

    logCount = 0 // обнуляем счётчик шкалы прогресса
    println("")

    /** Используем диапазон строк - нимимальный и максимальный номера папок в папке scanUpScale */
    val maxNumRow = scanUpScale.foldersList(scanUpScale.foldersList.length - 1).toInt

    /** цикл по строкам */
    for(numRow <- honestNumber to maxNumRow by 2){
      /** путь к папке ВЕРХНЕЙ СТРОКИ по номеру */
      val upRow = upScalePath + dm + numRow.toString
      /** путь к папке нижней строки по номеру */
      val downRow = upScalePath + dm + (numRow + 1).toString

      /** сканируем папки - списки файлов */
      val upScan = new FolderTileScan(upRow)
      val downScan = new FolderTileScan(downRow)

      /** Путь к пустому прозрачному рисунку */
      val emptyPath = "."+ dm + "src/main/scala/folderIndex".split("/").mkString(dm)

      /** путь к папке для сохранения строки масштаба */
      val divRowPath: String = savePath + dm + (numRow/2).toString
      newPathCreate(divRowPath) // создаём дирректорию

      /** Путь для сохранения рисунка при тестировании */
      val outPath = "."+ dm + "src/test/scala".split("/").mkString(dm)

      /** создаём общий список номеров файлов в верхней и нижней папках(строках)
       * чтобы использовать минимальный и максимальный номера файлов*/
      val numList = (upScan.filesNum.toSet ++ downScan.filesNum.toSet).toArray.sortBy(w => w)

      /** номер первого рисунка(столбец/тайл) всегда чётный */
      val honestColumn = {
        if((numList(0) % 2) == 0) numList(0)
        else numList(0) - 1 // если в папке нечётный уменьшаем номер на 1
      }

      /** цикл по столбцам(тайлам в папке) */
      for(column <- honestColumn to numList.last by 2){
        val leftUpPath = upRow + dm + column.toString + ".png"
        val rightUpPath = upRow + dm + (column + 1).toString + ".png"
        val leftDownPath = downRow + dm + column.toString + ".png"
        val rightDownPath = downRow + dm + (column + 1).toString + ".png"
        /** проверка наличия файла по указанному пути
         * Если файл присутствует, загружаем его,
         * если файла нет, загружаем прозрачный файл empty.png */

        val images = ArrayBuffer[BufferedImage]() /** Буфер для 4-х рисунков */
        /** Проверяем существует ли файл и загружаем в буфер по соответствующему пути */
        if(new File(leftUpPath).isFile) images += ImageIO.read(new java.io.File(upRow, column.toString + ".png"))
        else images += ImageIO.read(new java.io.File(emptyPath, "empty.png"))

        if(new File(rightUpPath).isFile) images += ImageIO.read(new java.io.File(upRow, (column + 1).toString + ".png"))
        else images += ImageIO.read(new java.io.File(emptyPath, "empty.png"))

        if(new File(leftDownPath).isFile) images += ImageIO.read(new java.io.File(downRow, column.toString + ".png"))
        else images += ImageIO.read(new java.io.File(emptyPath, "empty.png"))

        if(new File(rightDownPath).isFile) images += ImageIO.read(new java.io.File(downRow, (column + 1).toString + ".png"))
        else images += ImageIO.read(new java.io.File(emptyPath, "empty.png"))

        val combined = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB) // исходный для resize
        val g = combined.getGraphics
        g.drawImage(images(0), 0, 0, null)
        g.drawImage(images(1), 256, 0, null)
        g.drawImage(images(2), 0, 256, null)
        g.drawImage(images(3), 256, 256, null)
        g.dispose() // освобождение памяти объекта

        val sizeImage256 = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB)
        val resizeG = sizeImage256.getGraphics
        /** отрисовка в буфер sizeImage256 рисунка combined с уменьшенными размерами  */
        resizeG.drawImage(combined, 0, 0, 256, 256, null)
        resizeG.dispose() // освобождение памяти объекта

        /** РЕЗУЛЬТАТ sizeImage256 - рисунок для сохранения
         * определяем имя рисунка */
          val imageName = (column/2).toString + ".png"
        /** Сохраняем рисунок */
        ImageIO.write(sizeImage256, "PNG", new java.io.File(divRowPath, imageName))

        logCount = logProgress(logCount) // шкала прогресса в логе
      }
    }








    // отладка
//    println("Source Pyramid ")
//    for (x <- scanSource.foldersList) println(x) // скан исходной пирамиды
//    for (x <- scanUpScale.foldersList) println(x) // скан исходной папки для построения пирамиды вверх
//    println(minFolderPath) // путь к папке с минимальным номером
//    for(x <- inFoldersNames(activePathUp)._2) println(x) // список рисунков в папке
//    println("minNumRow = " + minNumRow)
//    println("maxNumRow = " + maxNumRow)
//    println("numColumnMin = " + numColumnMin)
//    println("numColumnMax = " + numColumn)


    // ------------------
  }


}






object TestFineScaleTails extends App{


//  C:\D\maps\osm_test\2
//  val tFileScale = new FineScaleTails("C:\\D\\maps\\rTails\\m200\\GoogleMapsCompatible", 10)
  val tFileScale = new AllScaleTails("E:\\resultTiles", 10)

//  val tFileScale = new FineScaleTails("C:\\D\\maps\\osm_test", 2)


//  C:\D\maps\rTails\m200\GoogleMapsCompatible\12
// C:\D\maps\rTails\m200\GoogleMapsCompatible\12\1221

//  println(tFileScale.dm)
//  println(tFileScale.upScalePath)
//
//  val list2 = tFileScale.inFoldersNames("C:\\D\\maps\\rTails\\m200\\GoogleMapsCompatible\\12\\1221")
//  for(name <- list2._1) println(name + " "); println("")
//  for(name <- list2._2){
//    print(name.slice(0,name.indexOf(".")).toInt + " ")
//    println(name)
//  }


//  val words = "55 22 33 44 31 22".split(" ")
//  val sorted = words.sortBy(w => w.length -> w.toLowerCase)
//
//  for(s <- sorted) print(s + " ")


//  tFileScale.mergeImagesAndSave(10)

}















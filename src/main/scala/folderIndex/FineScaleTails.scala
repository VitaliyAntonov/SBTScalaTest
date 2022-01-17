package folderIndex

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
class FineScaleTails(pyramidPath: String, upScale: Int) extends FileSystem {

  /** delimiter Разделитель в пути к файлу в зависимости от Операционной системы */
  val dm: String = {
    if (Util.getOS == Util.OS.WINDOWS) "\\"
    else "/"
  }

  /** Путь к папке с масштабом upScale */
  val upScalePath: String = {
    if (pyramidPath.endsWith(dm)) pyramidPath + upScale.toString
    else pyramidPath + dm + upScale.toString
  }

  /** Функция создаёт пирамиду тайлов крупных масштабов */
  def mergeImagesAndSave = {

    /** Сканируем директорию исходной пирамиды */
    val scanSource = new FolderTileScan(pyramidPath)  // inFoldersTilesNames(pyramidPath)

    /** сканируем папку с масштабом upScale */
    val scanUpScale = new FolderTileScan(upScalePath)  // inFoldersTilesNames(upScalePath)

    /** Текущий файл - левый верхний */
    var numRow = 0          // Номер строки(имя папки)
    var numColumn = 0       // Номер столбца(имя файла)

    var activePathUp = ""     /** путь к обрабатываемой папке - ВЕРХНЯЯ СТРОКА */
    var activePathDown = ""   /** путь к обрабатываемой папке - Нижняя строка */






    /** номер обрабатываемой папки(строки)  */
    numRow = scanUpScale.foldersList(0).toInt

    /** путь к папке с минимальным номером, содержащей тайлы - первая строка */
    val minFolderPath = {
      /** Ищем первую НЕ пустую папку */
      var fExit = 0
      val max = scanUpScale.foldersList.maxBy(x => x.toInt).toInt
      while(fExit == 0 && numRow <= max){
        activePathUp = upScalePath + dm + numRow.toString
        if(inFoldersNames(activePathUp)._2.nonEmpty) fExit = 1
        else numRow += 1
      }
      activePathUp
    }
    /** Номер первой НЕПУСТОЙ папки с тайлами */
    val firstRowNum = minFolderPath.slice(minFolderPath.lastIndexOf(dm) + 1, minFolderPath.length).toInt
    /** Используем диапазон строк - нимимальный и максимальный номера папок в папке scanUpScale */
    val maxNumRow = scanUpScale.foldersList(scanUpScale.foldersList.length - 1).toInt

    /** цикл по строкам */
    for(numRow <- firstRowNum until maxNumRow by 2){
      /** путь к папке ВЕРХНЕЙ СТРОКИ по номеру */
      val upRow = upScalePath + dm + numRow.toString
      /** путь к папке нижней строки по номеру */
      val downRow = upScalePath + dm + (numRow + 1).toString

      /** сканируем папки - списки файлов */
      val upScan = new FolderTileScan(upRow)
      val downScan = new FolderTileScan(downRow)

      /** создаём общий список номеров файлов в верхней и нижней папках(строках)
       * чтобы использовать минимальный и максимальный номера файлов*/
      val numList = (upScan.filesNum.toSet ++ downScan.filesNum.toSet).toArray.sortBy(w => w)

      /** цикл по столбцам(тайлам в папке) */
      print(s"Row = $numRow Column = ")
      for(column <- numList(0) until numList.last by 2){
        val leftUpPath = upRow + dm + column.toString
        val rightUpPath = upRow + dm + (column + 1).toString
        val leftDownPath = downRow + dm + column.toString
        val rightDownPath = downRow + dm + (column + 1).toString
        /** проверка наличия файла по указанному пути
         * Если файл присутствует, загружаем его,
         * если файла нет, загружаем прозрачный файл empty.png */




      }





    }








    // отладка
    println("Source Pyramid ")
    for (x <- scanSource.foldersList) println(x) // скан исходной пирамиды
    for (x <- scanUpScale.foldersList) println(x) // скан исходной папки для построения пирамиды вверх
    println(minFolderPath) // путь к папке с минимальным номером
    for(x <- inFoldersNames(activePathUp)._2) println(x) // список рисунков в папке
//    println("minNumRow = " + minNumRow)
//    println("maxNumRow = " + maxNumRow)
//    println("numColumnMin = " + numColumnMin)
//    println("numColumnMax = " + numColumn)


    // ------------------
  }


  /**
   * Функция возвращает путь, сформированный из pyramidPath и номера папки
   * @param num  - номер папки, которую необходимо создать
   */
  def pathNumberName(num: Int): String ={
    if (pyramidPath.endsWith(dm)) pyramidPath + num.toString
    else pyramidPath + dm + num.toString
  }

  /**
   * Создание директории с указанным путём, если директория ещё не создана
   * @param newPath номер директории, от которой идём вверх по пирамиде тайлов
   */
  def newPathCreate(newPath: String): Unit ={
    /** Проверка, существует ли директория с полученным путём */
    val pathTab = new java.io.File(newPath)
//    import java.nio.file.Files
//    import java.nio.file.Paths
    if(!pathTab.isDirectory) {    // директория не существует
//      Files.createDirectory(Paths.get(newPath))  // Создание директории(импорты для этой команды)
      /** Создаём директорию */
      val f = new java.io.File(newPath)
      f.mkdirs()
    }
  }




  /** Метод определяет пути к файлам рисунков, которые объединяются */
  def setPathsImages = {

  }

}






object TestFineScaleTails extends App{
  val tFileScale = new FineScaleTails("C:\\D\\maps\\rTails\\m200\\GoogleMapsCompatible", 10)

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


  tFileScale.mergeImagesAndSave

}















package folderIndex

import java.awt.image.BufferedImage
import java.io.File
import java.lang.Integer.parseInt
import java.nio.file.Files
import javax.imageio.ImageIO
import scala.collection.immutable.StringOps
import scala.collection.mutable


/**
 * задача N40, N41
 *
 *
 * Panorama
 *   Класс для
 * - пакетного переименования рисунков в пирамиде тайлов
 *   и
 * - Слияния пирамид тайлов разных карт
 *
 * @param sourcePathName - путь к папке, содержащей пирамиды тайлов разных карт
 * @param outPathName - путь к папке, в которой создаётсся слияние исходных пирамид тайлов
 *
 * - */
class TailsRefactor(sourcePathName: String, outPathName: String) {

  /** сканируем папки исходных пирамид тайлов */
  val scanPyramids = new FolderIndex(sourcePathName)

  /** Отладка - вывод в лог всех файлов с путями */
//  scanPyramids.fileNameRoadToLog

  /** формируем список дублированных имён файлов из массива */
  val doubleNames: Set[String] = {
    val retSet = mutable.Set[String]()
    scanPyramids.doubleFileNames.foreach( row => retSet += row.fileName )
    retSet.toSet
  }

  /** Слияние дублированных тайлов */
  mergeDoubleFiles

  /** Переименовываем и копируем файлы в итоговую директорию */
  copyAndRename

  /**
   * Копирование файлов в директорию результата,
   * с переименованием файлов
   */
  def copyAndRename ={

    println("+++++++ Копирование файлов в директорию результата ++++++")
    val filesQua = scanPyramids.fileNameRoad.size - scanPyramids.doubleFileNames.length
    println("Всего файлов для копирования: " + filesQua )
    println("Progress: ")

    /** Счётчик обработанных файлов для вывода лога */
    var fileCount = 0

    /** Обходим список файлов, исключая дублированные имена */
    for((fName, road) <- scanPyramids.fileNameRoad){
      if( ! doubleNames.contains(fName)){ //если имя отсутствует в списке дублей

        /** Путь к исходному файлу */
        val sourceImagePath = sourcePathName + "/" + road.mkString("/") + "/" + fName

        /** папка масштаба */
        val folderScale = road.init.last
        /** папка строки */
        val folderRow = road.last
        /** имя файла, как номер столбца */
        val resultFileName = getColumnName(fName)
        /** Путь к файлу рисунка результата */
        val resultImagePath = outPathName + "/" + folderScale + "/" + folderRow + "/" + resultFileName

        /** Проверка, существует ли директория с полученным путём */
        val pathTab = new java.io.File(outPathName + "/" + folderScale + "/" + folderRow)
        if(pathTab.isDirectory){ // директория существует

          /** Проверяем существует ли файл */
          val fileYes = new File(resultImagePath).isFile

          if(! fileYes){ // Файл не существует
            /** копируем файл в путь результата */
            copyFile(sourceImagePath, resultImagePath)
          }

        }
        else { // директория ещё не создана
          /** Создаём директорию */
          val f = new java.io.File(outPathName + "/" + folderScale + "/" + folderRow)
          f.mkdirs()
          /** копируем файл в путь результата */
          copyFile(sourceImagePath, resultImagePath)
        }

      }
      fileCount = logProgress(fileCount)
    }
    println("")
  }


  /**
   * Функция копирования файлов
   * путь к месту копирования уже должен существовать
   * @param source - путь к исходному файлу
   * @param dest   - путь к новому месту файла
   * @return
   */
  def copyFile(source: String , dest: String) = {
    val fSource = new File(source)
    val fDest = new File(dest)
    Files.copy(fSource.toPath, fDest.toPath)
  }

  /**
   * Функция слияния дублированных файлов
   */
  def mergeDoubleFiles = {
    println("+++++++ Слияние дублированных тайлов на границах листов карты ++++++")
    println("Всего дублей: " + scanPyramids.doubleFileNames.length )

    println("Progress: ")

    /** Счётчик обработанных файлов для вывода лога */
    var fileCount = 0

    /** Обходим список дублей, для слияния рисунков */
    scanPyramids.doubleFileNames.foreach{nFileRoad =>

      /** путь(как массив папок пути) к исходному рисунку из Map соответствии fileNameRoad */
      val iSourceRoad = scanPyramids.fileNameRoad(nFileRoad.fileName)

      /** Полный путь к исходному рисунку из Map соответствии fileNameRoad */
      val imageSourcePath = scanPyramids.inClassPath + "/" + iSourceRoad.mkString("/") + "/" + nFileRoad.fileName

      /** Полный путь к файлу дубля */
      val imageDoublePath = scanPyramids.inClassPath + "/" + nFileRoad.road.mkString("/") + "/" + nFileRoad.fileName

      /** папка масштаба */
      val folderScale = iSourceRoad.init.last
      /** папка строки */
      val folderRow = iSourceRoad.last
      /** имя файла, как номер столбца */
      val resultFileName = getColumnName(nFileRoad.fileName)
      /** Путь для сохранения нового рисунка */
      val resultImagePath = outPathName + "/" + folderScale + "/" + folderRow + "/" + resultFileName

      /** Проверка, существует ли директория с полученным путём */
      val pathTab = new java.io.File(outPathName + "/" + folderScale + "/" + folderRow)
      if(pathTab.isDirectory){ // директория существует

        /** Проверяем существует ли файл */
        val fileYes = new File(resultImagePath).isFile

        if(fileYes){ // Файл существует
          /** Сливаем дубль с рисунком результата */
          mergeTails(resultImagePath, imageDoublePath, resultImagePath)
        }
        else{ // файл не существует
          /** Сливаем исходный рисунок с дублем и размещаем на место результата */
          mergeTails(imageSourcePath, imageDoublePath, resultImagePath)
        }

      }
      else { // директория ещё не создана
        /** Создаём директорию */
        val f = new java.io.File(outPathName + "/" + folderScale + "/" + folderRow)
        f.mkdirs()
        /** Сливаем рисунки и размещаем на место результата */
        mergeTails(imageSourcePath, imageDoublePath, resultImagePath)
      }

      /** Выводим лог */
      fileCount = logProgress(fileCount)

    }

    println("")
  }


  /**
   * Печать в лог шкалы прогресса преобразования тайлов
   * печатаем в строку 100 точек, затем обнуляем счётчик точек
   * и переходим на новую строку
   * @param count  - счётчик
   * @return       - изменённый счётчик
   */
  def logProgress(count: Int): Int = {
    if(count >= 99){
      println(".")
      0
    }
    else{
      print(".")
      count + 1
    }
  }




  /**
   * Функция слияния двух рисунков
   * накладывает рисунки тайлов, оказавшиеся на границе карт
   * @param imagePath1 - путь к первому рисунку
   * @param imagePath2 - путь ко второму рисунку
   * @param resultPath - путь к итоговому рисунку, полученному слиянием исходных рисунков
   */
  def mergeTails(imagePath1: String, imagePath2: String, resultPath: String): Unit ={
    /** Пути к файлам рисунков - без имён файлов */
    val folder1 = new java.io.File(folderFromFilePath(imagePath1)) // base path of the images тип java.io.File
    val folder2 = new java.io.File(folderFromFilePath(imagePath2))

    val resultFolder = new java.io.File(folderFromFilePath(resultPath))

    // load source images
    val image = ImageIO.read(new java.io.File(folder1, fileNameFromPath(imagePath1)))

    val overlay = ImageIO.read(new java.io.File(folder2, fileNameFromPath(imagePath2)))

    // create the new image, canvas size is the max. of both image sizes
    val w: Int = Math.max(image.getWidth(), overlay.getWidth());
    val h: Int = Math.max(image.getHeight(), overlay.getHeight());
    val combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    // paint both images, preserving the alpha channels
    val g = combined.getGraphics;
    g.drawImage(image, 0, 0, null);
    g.drawImage(overlay, 0, 0, null);

    g.dispose()

    // Save as new image
    ImageIO.write(combined, "PNG", new java.io.File(resultFolder, fileNameFromPath(resultPath)));
  }





  /**
   * Функция возвращает имя тайла, состоящее только из значащих
   * цифр номера столбца.
   * Из имени: 0000000161_0000000328.png
   * получаем имя: 328.png
   *
   * @param tailName - имя тайла
   * @return
   */
  def getColumnName(tailName: String): String = {
    val nameSplit = tailName.split("_" )
    val column = nameSplit.last.split("\\.")(0)
    val extName = nameSplit.last.split("\\.")(1)

    val nameInt = parseInt(column)

    nameInt.toString + "." + extName
  }

  /**
   * Функция возвращает имя файла, выделенное из полного пути
   * @param path
   * @return
   */
  def fileNameFromPath(path: String): String ={
    val pathSplit = path.split("/+" )
    pathSplit(pathSplit.length - 1)
  }

  /**
   * Функция возвращает путь к файлу, без имени самого файла, из полного пути
   * @param path  - полный путь к файлу
   * @return
   */
  def folderFromFilePath(path: String): String ={
    val pathSplit = path.split("/+" )
    pathSplit.init.mkString("/")
  }

}


object refactorTailsTest extends App {


  /** Проверка слияния рисунков */
//  val imagePath1 = "/home/vitaliy/develop/project/scala_student/SBTScalaTest/src/test/scala/pack_tails/3901/GoogleMapsCompatible/12/1284/0000001284_0000002605.png"
//  val imagePath2 = "/home/vitaliy/develop/project/scala_student/SBTScalaTest/src/test/scala/pack_tails/3902/GoogleMapsCompatible/12/1284/0000001284_0000002605.png"
//  val mergePath = "/home/vitaliy/develop/project/scala_student/SBTScalaTest/src/test/scala/pack_tails/mergePack/result.png"
//  val pyramid = new TailsRefactor(
//    "/home/vitaliy/develop/project/scala_student/SBTScalaTest/src/test/scala/pack_tails",
//    mergePath
//  )
//  pyramid.mergeTails(imagePath1, imagePath2, mergePath)
//  println("name = " + pyramid.getColumnName("0000000161_0000000328.png"))
  /** ---------------------------------------------------------------------------- */


  /** Проверка рефакторинга пирамиды тайлов */

  /** Путь к исходной пирамиде тайлов */
  val sourcePyramidFolder = "/home/vitaliy/develop/GDAL_Panorama/Maps/sourceTails_NO_38_39/"

  /** Путь к результату -  пирамиде тайлов */
  val resultPyramidFolder = "/home/vitaliy/develop/GDAL_Panorama/Maps/resultTails_NO_38_39/"

  /** производим рефакторинг пирамиды тайлов */
  val refactor = new TailsRefactor(sourcePyramidFolder, resultPyramidFolder)


}










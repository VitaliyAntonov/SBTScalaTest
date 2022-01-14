package folderIndex

import scala.collection.mutable.ArrayBuffer


/** Класс создаёт пирамиду тайлов для мелких масштабов
 * */

/**
 *
 * @param path - путь к исходной пирамиде тайлов
 */

/**
 *  Класс создаёт пирамиду тайлов для крупных масштабов
 *  к примеру, если upScale = 10, то используя файлы рисунков из папки 10
 *  создаём папку 9, сливая по 4 рисунка и приводя их размер к 256 x 256 пикселей
 *  затем переходим на уровень вверх и из папки 9 создаём масштаб 8.
 *  Так продолжаем до 0-й папки. В 0-й папке создаём последний рисунок
 * @param pyramidPath      - путь к исходной пирамиде тайлов
 * @param upScale   - масштаб(номер папки) от которого строим пирамиду тайлов вверх
 */
class FineScaleTails(pyramidPath: String, upScale: Int) {



  /** delimiter Разделитель в пути к файлу в зависимости от Операционной системы */
  val dm = {
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

    /** Текущий файл - левый верхний */
    var numRow = 0          // Номер строки(имя папки)
    var numColumn = 0       // Номер столбца(имя файла)

    var activePath = ""     /** путь к обрабатываемой папке */

    var upLeftPath = ""     // Путь к файлу верхний левый
    var upRightPath = ""    // верхний правый
    var downLeftPath = ""   // нижний левый
    var downRightPath = ""  // нижний правый


    /** Сканируем директорию исходной пирамиды */
    val scanSource = inFoldersNames(pyramidPath)

    /** сканируем папку с масштабом upScale */
    val scanUpScale = inFoldersNames(upScalePath)

    /** номер обрабатываемой папки(строки)  */
    numRow = scanUpScale._1.minBy(x => x.toInt).toInt

    /** путь к папке с минимальным номером, содержащей тайлы */
    val minFolderPath = {
      /** Ищем первую НЕ пустую папку */
      var fExit = 0
      val max = scanUpScale._1.maxBy(x => x.toInt).toInt
      while(fExit == 0 && numRow <= max){
        activePath = upScalePath + dm + numRow.toString
        if(inFoldersNames(activePath)._2.nonEmpty) fExit = 1
        else numRow += 1
      }
      activePath
    }





    // отладка
    for (x <- scanSource._1) println(x) // скан исходно пирамиды
    for (x <- scanUpScale._1) println(x) // скан исходной папки для построения пирамиды вверх
    println(minFolderPath) // путь к папке с минимальным номером
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


  /**
   * Функция создаёт список имён папок и файлов, расположенных внутри указанной директории
   * @param inPath   - директория для сканирования
   * @return      - Список имён папок
   */
  def inFoldersNames(inPath: String): (Array[String], Array[String]) = {
    val pathsNames = ArrayBuffer[String]() // список имён вложенных папок
    val filesNames = ArrayBuffer[String]() // список имён файлов в папке

    /** создание экземпляра File для директории */
    val pathTab = new java.io.File(inPath)
    if(pathTab.isDirectory) { // если папка существует
      /** обход объектов в папке */
      for (obj ← pathTab.listFiles){
        /** обработка файлов */
        if(obj.isFile) filesNames += obj.getName
        /** обработка папок */
        if(obj.isDirectory) pathsNames += obj.getName
      }
    }

    Tuple2(pathsNames.toArray.sortBy(w => w.toInt), filesNames.toArray.sortBy(w => w.slice(0,w.indexOf(".")).toInt ))
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















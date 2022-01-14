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
 * @param path      - путь к исходной пирамиде тайлов
 * @param upScale   - масштаб(номер папки) от которого строим пирамиду тайлов вверх
 */
class FineScaleTails(path: String, upScale: Int) {

  /** Текущий файл - левый верхний */
  var numRow = 0          // Номер строки(имя папки)
  var numColumn = 0       // Номер столбца(имя файла)

  var upLeftPath = ""     // Путь к файлу верхний левый
  var upRightPath = ""    // верхний правый
  var downLeftPath = ""   // нижний левый
  var downRightPath = ""  // нижний правый

  /** delimiter Разделитель в пути к файлу в зависимости от Операционной системы */
  val dm = {
    if (Util.getOS == Util.OS.WINDOWS) "\\"
    else "/"
  }

  /** Путь к папке с масштабом upScale */
  val upScalePath: String = {
    if (path.endsWith(dm)) path + upScale.toString
    else path + dm + upScale.toString
  }

  newPathCreate(pathNumberName(9))

  val list2 = inFoldersNames(path)
  for(name <- list2._1) print(name + " "); println("")
  for(name <- list2._2) print(name + " "); println("")


  /**
   * Функция возвращает путь, сформированный из path и номера папки
   * @param num  - номер папки, которую необходимо создать
   */
  def pathNumberName(num: Int): String ={
    if (path.endsWith(dm)) path + num.toString
    else path + dm + num.toString
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
   * Функция создаёт список имён папок, расположенных внутри указанной директории
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

}





object TestFineScaleTails extends App{
  val tFileScale = new FineScaleTails("C:\\D\\maps\\rTails\\m200\\GoogleMapsCompatible", 10)

//  C:\D\maps\rTails\m200\GoogleMapsCompatible\12
// C:\D\maps\rTails\m200\GoogleMapsCompatible\12\1221

  println(tFileScale.dm)
  println(tFileScale.upScalePath)

  val list2 = tFileScale.inFoldersNames("C:\\D\\maps\\rTails\\m200\\GoogleMapsCompatible\\12\\1221")
  for(name <- list2._1) println(name + " "); println("")
  for(name <- list2._2){
    print(name.slice(0,name.indexOf(".")).toInt + " ")
    println(name)
  }


  val words = "55 22 33 44 31 22".split(" ")
  val sorted = words.sortBy(w => w.length -> w.toLowerCase)

  for(s <- sorted) print(s + " ")
}















package folderIndex

import java.io.File
import java.nio.file.Files
import scala.collection.mutable.ArrayBuffer



/** Трэйт содержит функции для работы с файловой системой */
trait FileSystem {


  var logCount = 0

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


  /** delimiter Разделитель в пути к файлу в зависимости от Операционной системы */
  val dm: String = {
    if (Util.getOS == Util.OS.WINDOWS) "\\"
    else "/"
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

    Tuple2(pathsNames.toArray, filesNames.toArray)
  }

  /**
   * Функция возвращает имя файла, выделенное из полного пути
   * @param path
   * @return
   */
  def fileNameFromPath(path: String): String ={
    path.slice(path.lastIndexOf(dm) + 1, path.length)
  }

  /**
   * Функция возвращает путь к файлу, без имени самого файла, из полного пути
   * @param path  - полный путь к файлу
   * @return
   */
  def folderFromFilePath(path: String): String ={
    path.slice(0, path.lastIndexOf(dm))
  }

  /**
   * Создание директории с указанным путём, если директория ещё не создана
   * @param newPath  - путь к создаваемой дирректории
   */
  def newPathCreate(newPath: String): Unit ={
    /** Проверка, существует ли директория с полученным путём */
    val pathTab = new java.io.File(newPath)
    if(!pathTab.isDirectory) {    // директория не существует
      /** Создаём директорию */
      val f = new java.io.File(newPath)
      f.mkdirs()
    }
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


  /** класс для фиксации имени файла и массива имён папок на пути к нему */
  case class nameFileRoad(fileName: String, road: Array[String]){
    /** путь к файлу, начинающийся с разделителя */
    def path: String = {
      var s = dm
      for(x <- road.indices){
        if(x < (road.length - 1)) s += road(x) + dm
        else s += road(x) + dm + fileName
      }
      s
    }
  }

}

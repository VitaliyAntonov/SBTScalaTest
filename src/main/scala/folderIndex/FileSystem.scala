package folderIndex

import scala.collection.mutable.ArrayBuffer



/** Трэйт содержит функции для работы с файловой системой */
trait FileSystem {

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



}

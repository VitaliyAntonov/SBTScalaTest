package folderIndex

import scala.collection.mutable.ArrayBuffer

/** Класс для сканирования папки с файлами */
class ScanFolder(val inClassPath: String) extends FileSystem {

  /** Полный список файлов, включая вложенные директории, в сканируемой папке */
  def fileList = fileListBuffer.toArray
  private val fileListBuffer = ArrayBuffer[nameFileRoad]()

  println(s"\nСканирование папки: $inClassPath")

  /** Сканирование корневой папки */
  scanDir(Array[String](), "")

  println(s"\nВсего файлов в $inClassPath : ${fileList.length}")

  /**
   * Функция сканирует папку с путём,
   * сложенным из пути папки верхнего уровня и имени вложенной папки
   *
   * при обнаружении имён файлов - имя файла с записью пути к нему
   * добавляется к fNameRoadMap
   *
   * Если имя файла уже есть в fNameRoadMap,
   * имя файла и массив пути добавляется в массив doubleFilesBuffer
   *
   * Если папка пуста, путь к ней добавляется в массив путей к пустым папкам emptyPathsBuffer
   *
   * @param pathUp       - путь к корневой папке(inClassPath) - массив имён вложенных папок
   * @param nameFolder   - имя сканируемой папки
   *
   * @return Boolean
   */
  private def scanDir(pathUp: Array[String], nameFolder: String): Boolean = {

    /** Возвращаемое значение */
    var retValue: Boolean = true

    /** Определяем путь к папке */
    val path: String = {
      var s = inClassPath
      if(pathUp.nonEmpty) s += dm + pathUp.mkString(dm)
      if(nameFolder != "") s += dm + nameFolder
      s
    }

    /** создание экземпляра File для директории */
    val pathTab = new java.io.File(path)

    if(pathTab.isDirectory){  // если папка существует

      /** добавляем имя папки к новому пути, если nameFolder не пустой */
      val newPathUp = {  // эта проверка для начала сканирования с корневой папки
        // позволяет сканировать с пустым путём саму корневую папку
        if(nameFolder != "") pathUp :+ nameFolder
        else pathUp
      }

      var numPin = 0

      /** обход объектов в папке */
      for (i ← pathTab.listFiles){

        /** обработка файлов */
        if(i.isFile) {
          val nameScan = i.getName
          fileListBuffer += nameFileRoad(nameScan, newPathUp)
          //          println("Файл: " + newPathUp.mkString("/") + "/" + nameScan)
        }

        /** обработка папок */
        if(i.isDirectory){
          val nameScan = i.getName
          //          println("Папка: " + newPathUp.mkString("/") + "/" + nameScan)
          /** Рекурсивный вызов */
          scanDir(newPathUp, nameScan)
        }

        if(numPin >= 100) {
          logCount = logProgress(logCount) // шкала прогресса
          numPin = 0
        }
        else numPin += 1

      }

      retValue = true
    }
    else{
      println(s" Отсутствует папка: $path")
      retValue = false
    }

    retValue
  }


  /** Отладка - вывод в лог списка файлов и путей к ним */
  def fileListToLog = {
    println("\nВсего файлов в списке fileList: " + fileList.length)
    var num = 0
    for (nfr <- fileList){
      print(num + " " + nfr.fileName  + " -> ")
      num += 1
      print( nfr.road.mkString(dm))
      println("")
    }
  }


}

object iScanFolderTest extends App {
  //  val fIndex = new FolderIndex("/home/vitaliy/develop/project/scala_student/SBTScalaTest/src/test/scala/pack_tails")
  val fIndex = new ScanFolder("E:\\resultTiles\\m200\\GoogleMapsCompatible")

  fIndex.fileListToLog




  println("breakPoint")

}

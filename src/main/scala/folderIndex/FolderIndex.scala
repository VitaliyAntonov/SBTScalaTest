package folderIndex

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


/**
 * класс java.io.File описание: http://proglang.su/java/io-and-files-and-directories-file-class
 * Инструментарий для индексирования файловой системы
 *
 * Класс строит
 * Map соответствие Имя файла -> Массив имён папок в порядке вложенности, начиная от path
 * в которых лежит файл в порядке вложенности от корневого пути сканирования.
 * Например для файла:
 * /home/vitaliy/develop/project/scala_student/SBTScalaTest/src/test/scala/pack_tails/3901/GoogleMapsCompatible/10/318/0000000318_0000000648.png
 * запись выглядит так:
 *
 * 0000000318_0000000648.png -> (3901, GoogleMapsCompatible, 10, 318)
 */
class FolderIndex(val inClassPath: String) {

  /** Map соответствие Имя файла -> Путь, как Массив имён папок в порядке вложенности, начиная от path */
  def fileNameRoad = fNameRoadMap.toMap
  private val fNameRoadMap = mutable.Map[String, Array[String]]()

  /** Массив для фиксации файлов с одинаковыми именами */
  def doubleFileNames = doubleFilesBuffer.toArray
  private val doubleFilesBuffer = ArrayBuffer[nameFileRoad]()

  /** Массив путей к пустым папкам */
  def emptyRoad = emptyRoadBuffer.toArray
  private val emptyRoadBuffer = ArrayBuffer[Array[String]]()

  /** Сканирование корневой папки */
  scanFolder(Array[String](), "")


  /** Отладка - вывод в лог списка файлов и путей к ним */
  def fileNameRoadToLog = {
    println("Всего файлов в списке fileNameRoad: " + fileNameRoad.size)
    var num = 0
    for ((fName, road) <- fileNameRoad){
      print(num + " " + fName + " -> ")
      num += 1
      print(road.mkString("/"))
      println("")
    }
  }



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
  def scanFolder(pathUp: Array[String], nameFolder: String): Boolean = {

    /** Возвращаемое значение */
    var retValue: Boolean = true

    /** Определяем путь к папке */
    val path: String = {
      var s = inClassPath
      if(pathUp.nonEmpty) s += "/" + pathUp.mkString("/")
      if(nameFolder != "") s += "/" + nameFolder
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

      /** количество обнаруженных объектов в папке */
      var objetsCount = 0

      /** обход объектов в папке */
      for (i ← pathTab.listFiles){

        /** обработка файлов */
        if(i.isFile) {
          objetsCount += 1
          val nameScan = i.getName

          /** проверяем наличие имени файла в списке */
          fNameRoadMap.get(nameScan) match {
            case Some(x) => { // файл уже есть в списке Map
              /** добавляем запись о файле в массив имён дублей */
              doubleFilesBuffer += nameFileRoad(nameScan, newPathUp)
            }
            case _ => { // файла нет в списке Map
              /** Добавляем запись о файле в Map соответствие fNameRoadMap */
              fNameRoadMap += nameScan -> newPathUp
            }

          }

//          println("Файл: " + newPathUp.mkString("/") + "/" + nameScan)
        }

        /** обработка папок */
        if(i.isDirectory){
          objetsCount += 1
          val nameScan = i.getName
//          println("Папка: " + newPathUp.mkString("/") + "/" + nameScan)
          /** Рекурсивный вызов */
          scanFolder(newPathUp, nameScan)
        }

      }

      /** добавление пустых папок в массив */
      if(objetsCount == 0){
        emptyRoadBuffer += newPathUp
      }

      retValue = true
    }
    else{
      println(s" Отсутствует папка: $path")
      retValue = false
    }

    retValue
  }
}

/** класс для фиксации имени файла и массива имён папок на пути к нему */
case class nameFileRoad(fileName: String, road: Array[String])


object iFolderIndexTest extends App {
//  val fIndex = new FolderIndex("/home/vitaliy/develop/project/scala_student/SBTScalaTest/src/test/scala/pack_tails")
  val fIndex = new FolderIndex("C:\\Users\\user\\IdeaProjects\\SBTScalaTest\\src\\test\\scala\\pack_tails")

  fIndex.fileNameRoadToLog


  println("breakPoint")

}



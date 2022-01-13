package folderIndex

import scala.collection.mutable.ArrayBuffer


/**  для ОС WINDOWS
 *  Класс строит список фалов с расширением .sitx в виде:
 *    <Map Path="C:\D\maps\O38\O3801.sitx"/>
 *    <Map Path="C:\D\maps\O38\O3802.sitx"/>
 *    <Map Path="C:\D\maps\O38\O3803.sitx"/>
 *    .
 *    .
 *    .
 *    для размещения в файле проекта Imagery Creator Panorama imagerycreator.xml
 *    файл imagerycreator.xml в Windows находится в папке C:\ProgramData\Panorama\imagerycreator
 *
 * @param rootPath  - корневая папка для поиска файлов карт с расширением .sitx
 */
class ImageryFileList(val rootPath: String) {

  /** Массив путей к файлам с расширением .sitx */
  def fileSitxArr = fileSitxArrBuffer
  private val fileSitxArrBuffer = ArrayBuffer[String]()

  /** строки со списком файлов .sitx для добавления в файл проекта */
  var strFileListBuffer = ""

  /** Конструктор */
  imageryCreateList

  /** функция для создания списков класса */
  def imageryCreateList = {
    val fIndex = new FolderIndex(rootPath) // создаём индекс

    for(field <- fIndex.fileNameRoad){ // обход списка файлов
      if(field._1.toLowerCase().endsWith(".sitx")){ // расширение файла .sitx
        // формируем путь к файлу
        val filePath: String = rootPath + "\\" + field._2.mkString("\\") + "\\" + field._1
        fileSitxArrBuffer += filePath  // путь в список найденных файлов
        val fstr = s"""<Map Path="$filePath"/>\n"""
        strFileListBuffer += fstr
      }
    }

  }



}

object TestImageryFileList extends App {
  val testClass = new ImageryFileList("C:\\D\\maps\\vSus")
//  val testClass = new ImageryFileList("C:\\D\\maps\\200000\\200000")

//  val testClass = new ImageryFileList("C:\\D\\maps\\200000\\200000\\G44")
  println(testClass.strFileListBuffer)
}



package folderIndex

import scala.collection.mutable.ArrayBuffer


/**  для ОС WINDOWS
 *  Класс строит список файлов с расширением .sitx в виде:
 *    <Map Path="C:\D\maps\O38\O3801.sitx"/>
 *    <Map Path="C:\D\maps\O38\O3802.sitx"/>
 *    <Map Path="C:\D\maps\O38\O3803.sitx"/>
 *    .
 *    .
 *    .
 *    для размещения в файле проекта Imagery Creator Panorama imagerycreator.xml
 *    файл imagerycreator.xml в Windows находится в папке C:\ProgramData\Panorama\imagerycreator
 *
 * @param pathsList  - список папок для поиска файлов карт с расширением .sitx
 */
class ImageryFileList(val pathsList: Array[String]) {

  /** Массив путей к файлам с расширением .sitx */
  def fileSitxArr = fileSitxArrBuffer
  private val fileSitxArrBuffer = ArrayBuffer[String]()

  /** строки со списком файлов .sitx для добавления в файл проекта */
  var strFileListBuffer = ""

  /** Конструктор */
  for(path <- pathsList){
    imageryCreateList(path)
  }

  /** функция для создания списков класса */
  def imageryCreateList(path: String) = {
    val fIndex = new FolderIndex(path) // создаём индекс

    for(field <- fIndex.fileNameRoad){ // обход списка файлов
      if(field._1.toLowerCase().endsWith(".sitx")){ // расширение файла .sitx
        // формируем путь к файлу
        val filePath: String = path + "\\" + field._2.mkString("\\") + field._1
        fileSitxArrBuffer += filePath  // путь в список найденных файлов
        val fstr = s"""<Map Path="$filePath"/>\n"""
        strFileListBuffer += fstr
      }
    }

  }



}

object TestImageryFileList extends App {

//  val testClass = new ImageryFileList(Array(
//    "E:\\tiles\\200000\\200000\\N38",
//    "E:\\tiles\\200000\\200000\\N39",
//    "E:\\tiles\\200000\\200000\\N40",
//    "E:\\tiles\\200000\\200000\\N41",
//    "E:\\tiles\\200000\\200000\\O38",
//    "E:\\tiles\\200000\\200000\\O39",
//    "E:\\tiles\\200000\\200000\\O40",
//    "E:\\tiles\\200000\\200000\\O41"
//  ))

//  val testClass = new ImageryFileList(Array(
//    "E:\\tiles\\200000\\200000\\M38",
//    "E:\\tiles\\200000\\200000\\M39",
//    "E:\\tiles\\200000\\200000\\M40",
//    "E:\\tiles\\200000\\200000\\M41",
//  ))

//  val testClass = new ImageryFileList(Array(
//    "E:\\tiles\\200000\\200000\\N42",
//    "E:\\tiles\\200000\\200000\\O42",
//    "E:\\tiles\\200000\\200000\\M42"
//  ))

//    val testClass = new ImageryFileList(Array(
//      "E:\\tiles\\200000\\200000\\O36",
//      "E:\\tiles\\200000\\200000\\O37",
//      "E:\\tiles\\200000\\200000\\N36",
//      "E:\\tiles\\200000\\200000\\N37",
//      "E:\\tiles\\200000\\200000\\M36",
//      "E:\\tiles\\200000\\200000\\M37"
//    ))

//  val testClass = new ImageryFileList(Array(
//    "E:\\tiles\\200000\\200000\\O34",
//    "E:\\tiles\\200000\\200000\\O35",
//    "E:\\tiles\\200000\\200000\\N34",
//    "E:\\tiles\\200000\\200000\\N35",
//    "E:\\tiles\\200000\\200000\\M34",
//    "E:\\tiles\\200000\\200000\\M35"
//  ))

//  val testClass = new ImageryFileList(Array(
//    "E:\\tiles\\200000\\200000\\R35",
//    "E:\\tiles\\200000\\200000\\R36",
//    "E:\\tiles\\200000\\200000\\Q35",
//    "E:\\tiles\\200000\\200000\\Q36",
//    "E:\\tiles\\200000\\200000\\P35",
//    "E:\\tiles\\200000\\200000\\P36"
//  ))

//  val testClass = new ImageryFileList(Array(
//    "E:\\tiles\\200000\\200000\\L34",
//    "E:\\tiles\\200000\\200000\\L35",
//    "E:\\tiles\\200000\\200000\\K34",
//    "E:\\tiles\\200000\\200000\\K35",
//    "E:\\tiles\\200000\\200000\\J34",
//    "E:\\tiles\\200000\\200000\\J35"
//  ))

//  val testClass = new ImageryFileList(Array(
//    "E:\\tiles\\200000\\200000\\L36",
//    "E:\\tiles\\200000\\200000\\L37",
//    "E:\\tiles\\200000\\200000\\L38",
//    "E:\\tiles\\200000\\200000\\K36",
//    "E:\\tiles\\200000\\200000\\K37",
//    "E:\\tiles\\200000\\200000\\K38"
//  ))

//  val testClass = new ImageryFileList(Array(
//    "E:\\tiles\\200000\\200000\\O43",
//    "E:\\tiles\\200000\\200000\\O44",
//    "E:\\tiles\\200000\\200000\\N43",
//    "E:\\tiles\\200000\\200000\\N44",
//    "E:\\tiles\\200000\\200000\\M43",
//    "E:\\tiles\\200000\\200000\\M44"
//  ))

  val testClass = new ImageryFileList(Array(
    "E:\\tiles\\200000\\200000\\L39",
    "E:\\tiles\\200000\\200000\\K39"
  ))


  println("Всего файлов .sitx " + testClass.fileSitxArr.length)
  println(testClass.strFileListBuffer)
}



import scala.collection.mutable

/**
 * Переименование пирамиды тайлов, сгененерированных в программе
 * Panorama Imagery Creator
 * задача:
 * имена тайлов представляют собой только номер столбца карты,
 * без номера строки и без незначащих нулей слева от номера столбца
 *
 * Путь к папке с тайлами:
 * /home/vitaliy/develop/project/scala_student/SBTScalaTest/src/test/scala/pack_tails
 *
 */






object PackTailsRename {

  /** Map соответствие имя файла -> массив имён папок,
   * в которых лежит файл в порядке вложенности от корневого пути сканирования.
   * Например для файла:
   * /home/vitaliy/develop/project/scala_student/SBTScalaTest/src/test/scala/pack_tails/3901/GoogleMapsCompatible/10/318/0000000318_0000000648.png
   * массив выглядит так:
   *
   * 3901
   * GoogleMapsCompatible
   * 10
   * 318
   *
   * */
  val fNameRoad = mutable.Map[String, Array[String]]()



}

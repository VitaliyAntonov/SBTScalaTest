package folderIndex

import java.io.File


/**
 * Класс для слияния пирамиды тайлов части карты
 * с результирующей общей пирамидой тайлов
 *
 * @param resultPyramid  - путь к папке с итоговой(результат) пирамидой тайлов
 */
class MergePyramidTiles(resultPyramid: String) extends FileSystem with GraphicsOption {

  /**
   * Проверка наличия папки с указанным путём resultPyramid
   * если папка отсутстует, то создаём её */
  newPathCreate(resultPyramid)

  /**
   * Функция сливает указанную sourcePyramid пирамиду тайлов
   * с пирамидой результата
   *
   * файлы, отсутствующие в пирамиде результата копируются
   * файлы с одинаковыми именами, номерами строк и столбцов(тайлы границ карт) сливаются
   *
   * @param sourcePyramid  - путь к пирамиде, присоединяемой к результату
   */
  def addPyramidToResult(sourcePyramid: String): Unit ={

    val indexSource = new ScanFolder(sourcePyramid) // исходная(присоединяемая) пирамида

    var numPin = 0 // счётчики шкалы прогресса
    logCount = 0
    var countFiles = 0

    /** обход списка тайлов исходной пирамиды только для .png файлов */
    for(field <- indexSource.fileList if field.fileName.toLowerCase.endsWith(".png") ){

      val fName = field.fileName
      val fRow = field.road.last
      val fScale = field.road.init.last

      /** проверка наличия папки масштаба, создание, если папки нет */
      newPathCreate(resultPyramid + dm + fScale)

      /** проверка наличия папки строки, создание, если папки нет */
      newPathCreate(resultPyramid + dm + fScale + dm + fRow)

      /** Путь к файлу в исходной пирамиде */
      val pathInSource = sourcePyramid + field.path

      /** путь к файлу в пирамиде результата */
      val pathInResult = resultPyramid + dm + fScale + dm + fRow + dm + fName

      /** проверка наличия файла в результирующей пирамиде */
      if(new File(pathInResult).isFile){
        /** файл обнаружен - сливаем рисунки */
        mergeTails(pathInSource, pathInResult, pathInResult)
      }
      else{
        /** файл отсутствует - копируем его в результирующую пирамиду */
        copyFile(pathInSource, pathInResult)
      }

      if(numPin >= 9) {
        logCount = logProgress(logCount) // шкала прогресса
        numPin = 0
      }
      else numPin += 1

      if(numPin == 0 & logCount == 0) print(countFiles)
      countFiles += 1

    }

  }

}


object iMergePyramidTilesTest extends App {

  val tPyramid = new MergePyramidTiles("E:\\resultTiles")

  tPyramid.addPyramidToResult("E:\\oneProject")

}


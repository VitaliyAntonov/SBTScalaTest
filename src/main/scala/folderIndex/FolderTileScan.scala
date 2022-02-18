package folderIndex

import scala.language.postfixOps


/**
 * Класс сканирует папку для последующего слияния рисунков тайлов
 * Представляет списки файлов и папок, отсортированных в порядке нумерации
 * @param folderPath
 */
class FolderTileScan(val folderPath: String) extends FileSystem {
  /** Список папок и файлов */
  private val dirLists = inFoldersNames(folderPath)

  //(pathsNames.toArray.sortBy(w => w.toInt), filesNames.toArray.sortBy(w => w.slice(0,w.indexOf(".")).toInt ))

  /** Список папок отсортированных в порядке нумерации */
  val foldersList = dirLists._1.sortBy(w => w.toInt)
  /** Список файлов отсортированных в порядке нумерации */
  val filesList = dirLists._2.sortBy(w => w.slice(0, w.indexOf(".")).toInt)
  /** Список номеров файлов в папке */
  val filesNum = filesList.map(field => field.slice(0, field.indexOf(".")).toInt)


  /** Диапазон столбцов - нимимальный и максимальный номера файлов в папке scanUpScale */
  /** Имя файла с минимальным номером */
  val fNameMin = {
    if(filesList nonEmpty) filesList(0)
    else ""
  }
  /** Номер файла с минимальным номером */
  val numColumnMin = {
    if(fNameMin != "") fNameMin.slice(0, fNameMin.indexOf(".")).toInt
    else 0
  }

  /** Имя файла с максимальным номером */
  val fNameMax = {
    if(filesList nonEmpty) filesList(filesList.length - 1)
    else ""
  }
  /** Номер файла с максимальным номером */
  val numColumnMax = if(fNameMax != "") fNameMax.slice(0, fNameMax.indexOf(".")).toInt

}


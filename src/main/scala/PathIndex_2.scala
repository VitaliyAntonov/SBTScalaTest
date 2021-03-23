

/** Определяет путь и содержимое папки */
trait Folder {
  val fullPathName: String  /** Полный путь к папке */
  val pathOK: Boolean  /** Флаг наличия дирректории - если папка существует = True */
  val inFoldersNames: List[String]  /** Список вложенных дирректорий */
  val inFilesNames: List[String]  /** Список вложенных файлов */
}

/** Дерево каталога папок */
trait TreeFolder{
  val branchNesting: Int /** Удалённость ветви от корня дерева папок. В корне = 0 */

}


/** Индексирование вложенных файлов и папок
 * корневой класс */
abstract class Iif extends Folder

/** Создание индексов папки */
class IndexFolder(path: String) extends Iif{
  import scala.collection.mutable.ListBuffer
  val (fullPathName, pathOK, inFoldersNames, inFilesNames) = indexer(path)

  /** Проверка наличия директории и создание списков */
  private def indexer(path: String): (String, Boolean, List[String],List[String]) = {
    val pathTab = new java.io.File(path) // Таблица вложенных
    var fullPathName = path // полный путь к директории
    val pathYes: Boolean = pathOK(pathTab) // Флаг наличия дирректории
    val inFoldersNames = ListBuffer[String]() // Список вложенных папок
    val inFilesNames = ListBuffer[String]() // Список вложенных файлов
    if(pathYes){
      fullPathName = pathTab.getCanonicalPath // При наличии директории читаем полное имя пути
      for (i ← pathTab.listFiles){
        if(i.isDirectory) inFoldersNames += i.getName // Заполняем список папок
        if(i.isFile) inFilesNames += i.getName // Заполняем список файлов
      }
      (fullPathName, pathYes, inFoldersNames.toList, inFilesNames.toList)
    }
    else (fullPathName, pathYes, List.empty, List.empty)
  }

  private def pathOK(pathTab: java.io.File): Boolean = {
    if(pathTab.isDirectory) true
    else false
  }
}

/** Проверка объекта - компаньона */
object IndexFolder {
  /** метод apply даёт возможность упрощённого создания объекта
   * без слова new */
  def apply(path: String) = new IndexFolder(path)
}


object testIndexFolder{

  val nf = new IndexFolder(".")
  val nf2: IndexFolder = IndexFolder(".")


  println(nf.inFoldersNames.mkString(" "))
  println(nf2.inFilesNames.mkString(" "))


}




/** Работа с таймером */
import java.util.{Timer, TimerTask}

object MyTask {
  var i = 0
}

class MyTask extends TimerTask {
  override def run(): Unit = {
    System.out.println("timer iteration: " + {MyTask.i += 1; MyTask.i})

    /** topic ДЕЛАТЬ ! Выборка из буфера обмена с обработкой исключения */
    val x = clipboardString.get /** Текст из буфера обмена */




    println(x)
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    val timer = new Timer
    val task = new MyTask
    timer.schedule(task, 0, 2000) // 2000 - delay (can set to 0 for immediate execution), 5000 is a frequency.
  }
}


//object clipboardString {
//  @throws[IOException]
//  @throws[UnsupportedFlavorException]
//  @throws[InterruptedException]
//  def get: String = {
//    val clipboard = Toolkit.getDefaultToolkit.getSystemClipboard
//    /** Забираем строку из буфера обмена * */
//    val flavor = DataFlavor.stringFlavor
//    if (clipboard.isDataFlavorAvailable(flavor)) clipboard.getData(flavor).asInstanceOf[String]
//    else ""
//  }
//}





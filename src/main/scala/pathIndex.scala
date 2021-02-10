


import scala.collection.mutable.ListBuffer

/**
 * Инструментарий для индексирования файловой системы
 *
 * Связанные индексы для файлов
 *
 * 1 - ID файла
 * 2 - имя файла
 * 3 - ID папки, в которой находится файл
 *
 * Связанные индексы для папок
 * 1 - ID папки
 * 2 - имя папки
 * 3 - ID папки,в которую вложена
 *
 * ID файла или папки - это номер в массиве, полученный при генерации индекса
 *
 * Служебные массивы для контроля индесации
 *
 * массив флагов создания индекса
 * createIndexFlags
 * 1 - ID папки
 * 2 - флаги построения индекса папок - выставляется при построении индекса для всех вложенных папок
 * 3 - флаги построения индекса файлов - выставляется при построении индекса для всех вложенных файлов
 *
 * Переменные процесса индексации
 * 1 - startPath - путь стартовой папки, с которой начато построение индекса
 * 1 - currentPath - путь текущей папки, для которой строится индекс
 * 2 - newFolderID - ID следующей папки(свободный ID) при индексации(последний номер в массиве папок)
 * 3 - newFileID - ID следующего файла(свободный ID) при индксации(последний номер в массиве файлов)
 * 4 - timeDateIndex - время и дата построения индекса
 *
 * Устройство файлов для фиксации построенных индексов
 *
 * Вариант индекса вниз от заданной папки
 *  Директория, в которой находятся файлы индексов
 *  Основной файл <IndexHead.index> , в котором прописаны имена всех файлов созданных индексов
 *
 * ***  Формировать имя файла индекса от полного пути к индексируемой папке
 * - менять слэш на другой символ(например <subSlash> = "_")
 * - при наличии в пути знака _ заменять его на два таких знака <????>
 *
 * задать время обновления индекса
 * при устаревании индекса формировать второй файл с новым именем
 * по окончании формирования нового файла индекса предыдущий удалять.
 *
 * Формат фиксации индекса из массивов в файл
 *
 * каждая строка содержит одну запись из массива, поля разделены пробелом
 *
 * Индекс, файлов:
 * ключевое слово начала индекса <StartFilesIndex>
 * ID файла <пробел> имя файла <пробел> ID папки, в которой находится файл <enter>
 * ключевое слово окончания индекса <EndFilesIndex>
 *
 *
 * ID папки <пробел> имя папки <пробел> ID папки,в которую вложена
 */


/**
 * базовый класс Ifs Индекс файловой системы
 */
abstract  class Ifs



/** Создание списков вложенных файлов и папок внутри директории
 * с проверкой корректности входного пути <path>
 * - При многократном вызове использовать один экземпляр класса
 * чтобы сократить потребление памяти
 * @param path  для создания экземпляра используем полный путь к дирректории */
final class Ifolder(path: String) extends Ifs {

  /** создание экземпляра File для директории */
  private val pathTab = new java.io.File(path)
  /** Список вложенных папок */
  private var inFoldersNames = ListBuffer[String]()
  /** Список вложенных файлов */
  private var inFilesNames = ListBuffer[String]()

  /** Проверка существует ли директория
   * и создание списков вложенных папок и файлов
   * @return Boolean */
  val pathYes: Boolean = {
    if(pathTab.isDirectory){
      for (i ← pathTab.listFiles){
        if(i.isDirectory) inFoldersNames += i.getName
        if(i.isFile) inFilesNames += i.getName
      }
      true
    }
    else{
      inFoldersNames.clear()
      inFilesNames.clear()
      false
    }
  }

  /** Возврат флага корректного чтения или ошибки пути */
  def pathOk: Boolean = pathYes

  /** Возврат полного пути сканируемой папки */
  def fullPathName: String = pathTab.getCanonicalPath

  /** Возврат списка вложенных папок */
  def foldersNames: List[String] = inFoldersNames.toList

  /** Возврат списка вложенных файлов */
  def filesNames: List[String] = inFilesNames.toList

  /** Окончание индексации - обнуление списков - освобождение памяти
   * Применить после построения индекса*/
  def indexEnd: Unit ={
    inFoldersNames.clear()
    inFilesNames.clear()
  }

}


// topic Индекс файловой системы
class onFolderList(path: String) extends Ifs{
  /** Создание списков - экземпляр сканирования*/
  val iPath = new Ifolder(path)
  /** Флаг корректности пути */
  val pathOk: Boolean = iPath.pathOk
  /** Полный путь к папке */
  val fullPathName: String = iPath.fullPathName
  /** Списо вложеных файлов */
  val inFilesNames: List[String] = iPath.filesNames
  /** Список вложенных папок */
  val inFoldersNames: List[String] = iPath.foldersNames

  /** Очищаем экземпляр сканироваия */
  iPath.indexEnd

  /** Отладочные методы */
  def show: Unit = {
    if(pathOk){
      println("Директория :   " + fullPathName)
      showFoldersNames
      showFilesNames
    }
    else println("Error Path")
  }

  def showFoldersNames: Unit = {
    print("Список папок : ")
    if(inFoldersNames.nonEmpty){
      println()
      for(i <- inFoldersNames) println(i)}
    else println("No folders in directory")
  }

  def showFilesNames: Unit = {
    print("Список файлов : ")
    if(inFilesNames.nonEmpty){
      println()
      for(i <- inFilesNames) println(i)}
    else println("No files in directory")
  }

}

// topic Тестирование
// Название класса важно - должно быть в форме MyClassTest




object IfolderDemo {

  val t1_ns: Long = System.nanoTime()  // Измерение времени в наносекундах

  var i1 = new onFolderList(".")

  val duration: Long = (System.nanoTime() - t1_ns)/1000

  i1.show

  i1 = new onFolderList("/home/vitaliy/develop/SBTScalaTest/TestEmptyDirectory")

  i1.show

  println("время создания списков :  " + duration + " микросекунд")

  // topic создание пустой дирректории
  val f = new java.io.File("/home/vitaliy/Desktop/a3425566777");
  f.mkdirs()


}



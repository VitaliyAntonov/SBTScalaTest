package workSxfRsc.HeaderTables

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/9/21
  **/
class Ht11Fonts {

}

/** 11 Структура таблицы  шрифтов. Префикс   0x00545854  // .TXT
  * Записи таблицы шрифтов постоянной длины 72 байта */
object Ht11Fonts{
  /** Название шрифта */
  val fontFileName = TabOFL(0, 32)  // Имя файла, где находится шрифт
  /** Условное название */
  val fontShortName = TabOFL(32, 32) // Уникальное символьное имя (ANSI)
  /** Код. Идентификационный номер шрифта в классификаторе*/
  val fontId = TabOFL(64, 4)
  /** Номер символа для уточнения высоты шрифта **/
  val numSimbolHeight = TabOFL(68, 1) // Номер символа шрифта, высота которого точно
  // равняется заданной высоте (по умолчанию “0”)
  /** Кодовая страница **/
  val codeWinPage = TabOFL(69, 1)  // Кодовая страница Windows
  /** Резерв. НЕ ИСПОЛЬЗУЕТСЯ **/
  val noUsing = TabOFL(70, 1)  //  0

}



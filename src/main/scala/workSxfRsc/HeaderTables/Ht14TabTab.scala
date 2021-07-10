package workSxfRsc.HeaderTables

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/9/21
  **/
class Ht14TabTab {

}


/** 14 Структура таблицы таблиц. Префикс  0x00424154  .TAB */
object Ht14TabTab {

  /** Смещение на таблицу цветов печати От начала файла */
  val colorPrintTabOffset = TabOFL(0, 4)  // От начала файла

  /** Длина таблицы */
  val colorPrintTabSize = TabOFL(4, 4)    // В байтах

  /** Число записей */
  val colorPrintTabRecQua = TabOFL(8, 4)  // Запись постоянной длины

  /** Резерв НЕ ИСПОЛЬЗОВАТЬ */
  val noUsing = TabOFL(12, 60)

}


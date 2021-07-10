package workSxfRsc.HeaderTables

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/9/21
  **/
class Ht10Palette {

}

/** 10  Структура Таблицы палитр. Префикс 0x004C4150  // .PAL */
object Ht10Palette {
  /** Палитра в формате RGB */
  val colorsPalette = TabOFL(0, 1024)  // 4 байта на цвет
  /** Название палитры */
  val colorPalName = TabOFL(1024, 32) // Уникальное символьное имя (ANSI)
}



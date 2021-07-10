package workSxfRsc.Graphic

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/7/21
  **/



/** 13. Текст, код типа примитива  142. */
class Gr13Text {

}






/** 13. Текст, код типа примитива  142. */
object Gr13Text {

  /** Цвет текста */
  val tTextColor = TabOFL(0, 4)

  /** Цвет фона. 0x0FFFFFFFF – прозрачный */
  val tBgColor = TabOFL(4, 4)

  /** Цвет тени. 0x0FFFFFFFF – прозрачный */
  val tShadowColor = TabOFL(8, 4)

  /** Высота. Если 0 – высота будет рассчитываться по метрике выводимого текста */
  val tHeight = TabOFL(1, 4)

  /** Толщина контура шрифта.  Примечание 1. */
  val tDepth = TabOFL(16, 4)

  /** Выравнивание 0 */
  val tAlignment = TabOFL(20, 2)

  /** Резерв 0 */
  val noUsing = TabOFL(22, 2)

  /** Ширина символа +24 1 Примечание 2. */
  val tSymbolHeight = TabOFL(24, 1)

  /** Признак горизонтального расположения. 1- шрифт располагается горизонтально */
  val tHorizontalFlag = TabOFL(25, 1)

  /** Признак наклона символа. 1 - курсив */
  val tItalicsFlag = TabOFL(26, 1)

  /** Признак подчеркивания. 1 - подчеркивание */
  val tUnderlineFlag = TabOFL(27, 1)

  /** Признак перечеркивания. 1- перечеркивание */
  val tStrikeThroughFlag = TabOFL(28, 1)

  /** Тип шрифта. Номер шрифта в таблице шрифтов классификатора */
  val tFontType = TabOFL(29, 1)

  /** Кодовая страница. Примечание 3 */
  val tCodePage = TabOFL(30, 1)

  /** Флаг. 1 - признак масштабирования по метрике */
  val tScaleFlag = TabOFL(31, 1)


  /** Итого:  32 байта  */
}

/**
  * Примечания:
  * 1.  Возможные значения для толщины контура шрифта:
  *     THIN            - 100;
  *     EXTRALIGHT      - 200;
  *     LIGHT           - 300;
  *     NORMAL          - 400;
  *     MEDIUM          - 500;
  *     SEMIBOLD        - 600;
  *     BOLD            - 700;
  *     EXTRABOLD       - 800;
  *     HEAVY           - 900;
  *
  * 2.  Возможные значения для ширины символа
  *     Нормальный    – 0;
  *     Суженый       – 1;
  *     Широкий       – 2;
  *
  * 3.  Для учета кодовой страницы используются Windows константы.
  *     Для России
  *     (ANSY/WINDOWS) –  RUSSIAN_CHARSET,
  *     (OEM/866/DOS)  -  OEM_CHARSET.
  *     Возможны другие Windows константы.
  *
  */




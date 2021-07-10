package workSxfRsc.Graphic

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/6/21
  **/
class Gr1Line {

}

/** -------------------------------------------------- **/
/** 1. Простая линия, код типа примитива  128 */

/** Структура таблицы */
object Gr1Line{
  /** Цвет линии RGB или индекс  **/
  val tColor = TabOFL(0, 4)

  /** Толщина линии В микронах  */
  val tDepth = TabOFL(4, 4)

  /** Итого:   8 байт **/
}


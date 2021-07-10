package workSxfRsc.Graphic

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/6/21
  **/
class Gr2DotLine {

}

/** -------------------------------------------------- **/

/** 2. Пунктирная линия, код типа примитива  129 **/
object Gr2DotLine{
  /** Цвет линии RGB или индекс  **/
  val tColor = TabOFL(0, 4)

  /** Толщина линии В микронах  */
  val tDepth = TabOFL(4, 4)

  /** Длина штриха В микронах  **/
  val tStroke = TabOFL(8, 4)

  /** Длина пробела в микронах **/
  val tSpace = TabOFL(12, 4)

  /** Итого:   16 байт **/
}


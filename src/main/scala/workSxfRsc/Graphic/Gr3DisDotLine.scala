package workSxfRsc.Graphic

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/6/21
  **/
class Gr3DisDotLine {

}

/** -------------------------------------------------- **/

/** 3. Смещенный пунктир, код типа примитива  148 **/
object Gr3DisDotLine{
  /** Цвет линии RGB или индекс  **/
  val tColor = TabOFL(0, 4)

  /** Толщина линии В микронах  */
  val tDepth = TabOFL(4, 4)

  /** Длина штриха В микронах  **/
  val tStroke = TabOFL(8, 4)

  /** Длина пробела в микронах **/
  val tSpace = TabOFL(12, 4)

  /** Направление и величина сдвига линии
    * = 0 пунктир лежит справа вплотную к оси
    * > 0 сдвигаемся вправо от оси, иначе влево. В микронах
    */
  val tDirAmount = TabOFL(16, 4)

  /** Итого:   20 байт **/
}


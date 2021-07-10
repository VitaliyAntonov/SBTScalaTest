package workSxfRsc.Graphic

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/8/21
  **/
class Gr19PointSymbol {

}



/** 19. Точечный знак _ графическое изображение, код типа примитива  165. */
object Gr19PointSymbol {

  /** Длина. В байтах */
  val tLength = TabOFL(0, 4)

  /** Идентификатор изображения.  0 */
  val tImageId = TabOFL(4, 4)

  /** Имя файла изображения. Полное имя файла */
  val tImageName = TabOFL(8, 256)

  /** Тип файла изображения.   1-BMP */
  val tImageType = TabOFL(264, 4)

  /** Габариты знака (высота). В микрометрах */
  val tSymbolHeight = TabOFL(268, 4)

  /** Габариты знака (ширина). В микрометрах */
  val tSymbolWidth = TabOFL(272, 4)

  /** Привязка знака от начала габаритов (высота). В микрометрах */
  val tSymAnchorHeight = TabOFL(276, 4)

  /** Привязка знака от начала габаритов (ширина). В микрометрах */
  val tSymAnchorWidth = TabOFL(280, 4)

  /** Смещение тени. В микрометрах */
  val tShadowOffset = TabOFL(284, 4)

  /** Параметры контура. Параметры линии */
  val tBorderParam = TabOFL(288, 8)

  /** Флаг наличия контура. 1- рисовать контур */
  val tBorderFlag = TabOFL(296, 1)

  /** Флаг наличия тени. 1- есть тень */
  val tShadowFlag = TabOFL(297, 1)

  /** Резерв */
  val noUsing = TabOFL(298, 14)


  /** Итого: 312 байт */

}













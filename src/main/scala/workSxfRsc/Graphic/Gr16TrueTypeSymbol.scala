package workSxfRsc.Graphic

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/8/21
  **/
class Gr16TrueTypeSymbol {

}

/** 16. Знак True-Type шрифта, код типа примитива  151. */
object Gr16TrueTypeSymbol {

  /** Параметры шрифта */
  val tFontParam = TabOFL(0, 32)

  /** Условное название шрифта */
  val tFontName = TabOFL(32, 32)

  /** Номер знака в шрифте */
  val tFontSymbolNum = TabOFL(64, 4)

  /** Габариты знака относительно точки привязки. ( 4 значения по 4 байта )
    * Последовательно – максимальное отклонение от точки привязки
    * вверх, влево, вниз, вправо – все отклонения в микрометрах
    */
  val tSymbolSize = TabOFL(68, 16)

  /** Точка привязки знака по вертикали. В микрометрах */
  val tAnchorPinVertical = TabOFL(84, 4)

  /** Точка привязки знака по горизонтали */
  val tAnchorPinHorizontal = TabOFL(88, 4)

  /** Итого:  92 */

}




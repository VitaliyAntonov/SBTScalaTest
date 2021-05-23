package siteCreator.component

/**
 * @author Виталий Антонов @date 5/14/21
 *         kaligraf@yandex.ru
 * */


trait Sizes {
  /** Размеры контейнеров DIV */
  class BoxSize {
    /** ----------------------------------- */
    /** Параметры анимации при развёртке контейнера */
    var boxTransition: String = "transition: all 50ms ease-in-out;"

    /** ----------------------------------- */

    /** Параметры, задаваемые в виде строк - для CSS ордеров */

    /** ширина контейнера, заданная в виде строки */
    var boxWidthAsString: String = ""

    /** высота контенера, заданная в виде строки */
    var boxHeightAsString: String = ""

    /** бордюр контейнера, заданный в виде строки  */
    var divBorder: String =s"""  border: 1px solid #FFFFFF;"""

    /** ----------------------------------- */

    /** Начальная Координата контейнера left */
    var boxLeft: Int = 20

    /** Начальная Координата контейнера top */
    var boxTop: Int = 20

    /** ширина контейнера до прорисовки */
    var boxWidth0: Int = 0

    /** ширина прорисованного контейнера */
    var boxWidth: Int = 330

    /** высота контенера до прорисовки */
    var boxHeight0: Int = 30

    /** высота контенера  */
    var boxHeight: Int = 30

    /** зазор между контейнерами по высоте */
    var GapY: Int = 10

    /** радиус бордера бокса */
    var boxBorderRadius: Int = 8

    /** Размер шрифта до прорисовки */
    var boxFontSize0: Int = 0

    /** Размер шрифта */
    var boxFontSize: Int = 18

    /** ----------------------------------- */

    /** ширина контейнера при hover */
    var boxHoverWidth: Int = 250

    /** высота контенера при hover */
    var boxHoverHeight: Int = 70

    /** Размер шрифта тега при hover */
    var boxHoverFontSize: Int = 25

    /** ----------------------------------- */
  }





}


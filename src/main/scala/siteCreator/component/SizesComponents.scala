package siteCreator.component

/**
 * @author Виталий Антонов @date 5/14/21
 *         kaligraf@yandex.ru
 * */



/** Размеры контейнеров DIV */
class BoxSize {
  /** ----------------------------------- */
  /** Параметры анимации при развёртке контейнера */
  var boxTransition: String = "transition: all 50ms ease-in-out;"
  /** ----------------------------------- */
  /** Начальная Координата контейнера left */
  var boxLeftOffset: Int = 20
  /** Начальная Координата контейнера top */
  var boxTopOffset: Int = 20
  /** ширина контейнера до прорисовки */
  var boxWidth0: Int = 0
  /** ширина прорисованного контейнера */
  var boxWidth: Int = 330
  /** высота контенера */
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




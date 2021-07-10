package siteCreator.component

/**
 * @author Виталий Антонов @date 6/24/21
 *         kaligraf@yandex.ru
 * */


/** HTML Component Code класс для формирования HTML кода компонента */
class HtmlC {
  /** буфер html кода компонента */
  var buffer: String = ""

  /** уроаень вложенности тегов друг в друга
   * по нему определяем табуляцию в начале строки */
  private var insertLevel = 0
  def inLevel = insertLevel

  /** открытие HTML тега */
  def <<<(tag: String): HtmlC = {
    buffer += ("  " * insertLevel) + tag + "\n"
    insertLevel += 1
    this
  }

  /** Закрытие HTML тега < /div> */
  def >>>(tag: String): HtmlC  = {
    if(insertLevel > 0) {insertLevel -= 1; buffer += ("  " * insertLevel) + s"</${tag}>\n"}
    else println(s" !!!!!!!!!!!  Попытка закрыть неоткрытый тег  !!!!!!!!!!!")
    this
  }

  def enter: HtmlC  = { buffer += "\n" ; this }

  /** Добавление html кода в тот же уровень вложенности */
  def ++(tag: String) = { buffer += ("  " * insertLevel) + tag; this }
}

/** CSS Component Code класс для формирования CSS кода компонента */
class CssC {
  var buffer: String = ""

  def enter = { buffer += "\n" ; this }

  def +(tag: String) = { buffer += tag; this }
}

/** Js Component Code класс для формирования JS(java script) кода компонента */
class JsC {
  var buffer: String = ""

  def enter = { buffer += "\n" ; this }

  def +(tag: String) = { buffer += tag; this }
}


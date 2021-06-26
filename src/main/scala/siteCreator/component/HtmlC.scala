package siteCreator.component

/**
 * @author Виталий Антонов @date 6/24/21
 *         kaligraf@yandex.ru
 * */


/** HTML Component класс для формирования HTML кода компонента */
class HtmlC {
  var buffer: String = ""

  def enter = { buffer += "\n" ; this }

  def +(tag: String) = { buffer += tag; this }
}

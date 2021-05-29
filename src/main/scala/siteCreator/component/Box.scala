package siteCreator.component

/**
 * @author Виталий Антонов @date 5/27/21
 *         kaligraf@yandex.ru
 * */


/**
 * Класс для создания контейнеров html
 * @param sName  - Имя для селектора класса CSS кода
 * @param width
 * @param height
 * @param top
 * @param left
 */
class Box(val sName: String, var width: Int, var height: Int, var top: Int = 0, var left: Int = 0) {

  /** Имя селектора класса с номером */
  def sNameN(num: Int): String = sName + "-" + num.toString

  /** HTML Открытие контейнера с именем класса Selector Class */
  def htmlOpenBoxWithSClass: String = s"""<div class="${sName}">"""

  /** HTML Открытие контейнера с двумя именами класса: имя и имя с номером */
  def htmlOpenBoxWith2SClassN(num: Int): String = s"""<div class="${sName} ${sNameN(num)}">"""

  /** HTML Закрытие контейнера */
  def htmlCloseBox: String = s"""</div>"""

}

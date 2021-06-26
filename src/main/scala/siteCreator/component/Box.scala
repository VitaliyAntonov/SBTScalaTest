package siteCreator.component

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @author Виталий Антонов @date 5/27/21
 *         kaligraf@yandex.ru
 * */


/**
 * Класс для создания контейнеров(div) html
 * @param sName  - Имя для селектора класса CSS кода
 * @param width
 * @param height
 * @param top
 * @param left
 */
class Box(val sName: String, var width: Int, var height: Int, var top: Int = 0, var left: Int = 0) {

  /** Список значений css свойств компонента
   * _1  Свойство
   * _2  Значение
   * */
  val cssList = new mutable.ListBuffer[(String, String)]()

  cssList += (
    ("width", s"${width.toString}px"),
    ("height", s"${height.toString}px"),
    ("left", s"${left.toString}px"),
    ("top", s"${top.toString}px")
  )

  val cssListHover = new ListBuffer[(String, String)]()

  /** CSS код компонента */
  def CSS: String = {
    var s=""
    s += s".${sName} {\n"
    for(arg <- cssList){
      s += arg._1 + ": " + arg._2 + ";\n"
    }
    s += "}\n"
    s
  }

  /** CSS код при hover компонента */
  def CSSHover: String = {
    var s=""
    s += s".${sName}:hover {\n"
    for(arg <- cssListHover){
      s += arg._1 + ": " + arg._2 + ";\n"
    }
    s += "}\n"
    s
  }

  /** CSS код при hover компонента для добавления контекста */
  def CSSHoverContent(content: String): String = {
    val s =
      s"""
         |.${sName}:hover span::after {
         |  content: "${content}";
         |}
         |""".stripMargin
    s
  }

  /** Имя селектора класса с номером */
  def sNameN(num: Int): String = sName + "-" + num.toString

  /** HTML Открытие контейнера с именем класса Selector Class */
  def htmlOpenBoxWithSClass: String = s"""<div class="${sName}">"""

  /** HTML Открытие контейнера с двумя именами класса: имя и имя с номером */
  def htmlOpenBoxWith2SClassN(num: Int): String = s"""<div class="${sName} ${sNameN(num)}">"""

  /** HTML Закрытие контейнера */
  def htmlCloseBox: String = s"""</div>"""


}

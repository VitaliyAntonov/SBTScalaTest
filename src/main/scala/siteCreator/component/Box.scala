package siteCreator.component

import scala.collection.mutable

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
class Box(val sName: String,
          var width: Int,
          var height: Int,
          var top: Int,
          var left: Int) {

  /** Список значений CSS свойств с атрибутами компонента */
  val cssMapList: mutable.Map[String, String] = mutable.Map(
    "width" -> s"${width}px",
    "height" -> s"${height}px",
    "left" -> s"${left}px",
    "top" -> s"${top}px"
  )

  /** список CSS свойств с атрибутами для всех элементов с именем Name0 на странице,
   *  то есть например для всех markBox всех списков страницы с селектором сласса .markBox
   * */
  val cssForPageName0:mutable.Map[String, String] = mutable.Map()

  /** список CSS свойств с атрибутами при hover на компоненте */
  val cssMapListHover: mutable.Map[String, String] =  mutable.Map()

  /** список дополнительных имён класса контейнера */
    /** TODO Изменить тип на MAP и переделать метод добавки дополнительных имён классов */
  val sClassNames = mutable.Map.empty[String, String]

  /** Строка из всех имён классов */
  def getClassNames: String = {
    var s = ""
    /** дополнительные имена классов, если заданы **/
    if(sClassNames.nonEmpty) sClassNames.foreach(arg => s += arg + " ")
    s += sName // заданное имя для экземпляра контейнера
    s
  }

  /** строка вида class = "name0 name1 ... nameN" */
  def classes: String = {
    var s = ""
    if(sClassNames.nonEmpty) s += s""" class = "${getClassNames}""""
    s
  }

  /** CSS код компонента */
  def CSS: String = {
    var s=""
    s += s".${sName} {\n"
    for((tag, arg) <- cssMapList){
      s += "  " + tag + ": " + arg + ";\n"
    }
    s += "}\n"
    s
  }

  /** CSS код при hover на компоненте */
  def cssHover: String = {
    var s=""
    s += s".${sName}:hover {\n"
    for((tag, arg) <- cssMapListHover){
      s += "  " + tag + ": " + arg + ";\n"
    }
    s += "}\n"
    s
  }

  /** CSS код при hover компонента для добавления контента
   * после тега span внутри контейнера
   * */
  def cssHoverContent(content: String): String = {
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

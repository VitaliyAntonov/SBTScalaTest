package siteCreator.pages.component

import siteCreator.pages.HtmlPC


/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */


/**
 * Компонент контейнер
 * @param nameDiv имя данного вида контейнера для генерации селектора CSS класса и CSS id
 * @param args вложенные элементы
 */
class Divs(nameDiv: String = "")(args: Component*)(cssProperty: String*) extends Component("Div" + nameDiv) {

  var numDefHtml = 0

  override def html(page: HtmlPC)  = {
    val id = componentId /** Фиксируем id */

    /** атрибуты селектора класса добавляем 1 раз при numDefHtml = 0*/
    if(numDefHtml == 0) addCssClassProperty(page, componentName)(cssProperty: _*)
    numDefHtml += 1

    /** атрибуты к селектору id добавляем при каждом вызове */
    addCss(page, cssTemplate(id))

    /** открытие тега */
    val htmlHead =
      s"""    <div id=${s""""$id""""} class=${s""""$componentName""""}>
         |""".stripMargin

    /** закрытие тега */
    val htmlFooter =
      s"""    </div>
         |""".stripMargin


    addHml(page, htmlHead)
    addArgs(page)(args: _*)
    addHml(page, htmlFooter)
  }

  def cssTemplate(id:String = ""): String =
    s"""
       |#$id {
       |  width: 200px;
       |  height: 200px;
       |  background-color: hsl(220, 34%, 50%);
       |}""".stripMargin + "\n"

  def jsTemplate(id:String = ""): String = ""

  var attrCssClass =""

  /** Добавление CSS параметров к элементу селектор по имени класса */
  def setCssClassAttr(args: String*) = {
    attrCssClass += "\n." + componentName + " {\n"
    for(i <- 0 until args.length) attrCssClass += "  " + args(i) + "\n"
    attrCssClass += "}\n"
  }
}

object Divs {
  def apply(name: String)(args: Component*)(cssProperty: String*) = new Divs(name)(args: _*)(cssProperty: _*)

  /** Пробный контейтер */
  val divBox = Divs("Box")()("border: 1px solid yellow;",
                             "border-radius: 5px;")



}



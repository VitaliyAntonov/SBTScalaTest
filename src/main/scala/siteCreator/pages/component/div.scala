package siteCreator.pages.component

import siteCreator.pages.HtmlPC


/**
 * Компонент контейнер
 *
 * @param nameDiv имя данного вида контейнера
 * @param args вложенные элементы
 */
class div(nameDiv: String = "")(args: Component*) extends Component("Div" + nameDiv) {

  override def html(page: HtmlPC)  = {
    val id = componentId
    addCss(page, cssTemplate(id))
    val htmlTemplate = s"""
       |    <div id=${s""""$id""""} class=${s""""$componentName""""}>
       |      ${addArgs(page)(args: _*);""}
       |    </div>
       |""".stripMargin
    addHml(page, htmlTemplate)
  }

  def cssTemplate(id:String = ""): String =
    s"""
       |#$id {
       |  width: 200px;
       |  height: 200px;
       |  background-color: hsl(220, 34%, 50%);
       |  border: 1px solid #00FF00;
       |}
       |""".stripMargin

  def jsTemplate(id:String = ""): String = ""

}

package siteCreator.pages.component

import siteCreator.pages.HtmlPC


/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */


/**
 * Компонент - шаблон HTML страницы
 *
 * @param namePage Имя страницы
 * @param args список вложенных компонентов
 */
class HtmlPage(namePage: String)(args: Component*)
  extends Component("HtmlPage" + namePage) {

  override def html(page: HtmlPC)  = {
  lazy val header =
    s"""|<!DOCTYPE html>
        |<html>
       |  <head>
       |    <meta charset="utf-8">
       |    <title>${page.namePage}</title>
       |    <link rel="stylesheet" href=${s""""${page.namePage + "style.css"}""""}>
       |  </head>
       |  <body>
       |""".stripMargin

  lazy val footer =
  s"""|    <script defer src=${s""""${page.namePage + "script.js"}""""}></script>
       |  </body>
       |</html>
       |""".stripMargin

    addCss(page, cssTemplate) /** Добавляем CSS шаблон */
    addHml(page, header) /** Добавляем верх страницы */
    addArgs(page)(args: _*) /** Создание HTML последовательности для вложенных элементов */
    addHml(page, footer) /** Добавляем низ страницы */
  }

  def cssTemplate =
    s"""
       |${HtmlCom.root.cssTemplate}
       |
       |body {
       |    background-color: var(--bodyBgColor);
       |    color: var(--bodyTextColor);
       |}
       |""".stripMargin

  def jsTemplate(id:String = ""): String =
    s"""
       |
       |""".stripMargin
}

object HtmlPage{
  val csshelp = new HtmlPage("csshelp")()

}


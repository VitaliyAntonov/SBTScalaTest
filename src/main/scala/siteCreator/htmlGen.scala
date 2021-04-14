
package siteCreator

import siteCreator.pages.component.{HtmlCom, HtmlPage}

import java.io.PrintWriter


object htmlGen{
  /** */
  val colorClass = "themeColor"
  val sizeClass = "elementSize"
  val testClass = "test"
  val id = "newID"
  val text = "Это пробная сгенерированная страница"
  val htmlTemplate = {
    <html>
      <head>
        <meta charset="utf-8"></meta>
        <title>form_v0</title>
        <link rel="stylesheet" href="butFix.css"></link>
      </head>
      <body>
        {text}
        <div id={id} class={colorClass + " " + sizeClass}>
        {HtmlCom.fixButton.htmlTemplate}
        {HtmlCom.fixButton.htmlTemplate}
      </div>
      </body>
    </html>
  }
//  val cssTemplate = HtmlCom.fixButton.cssTemplate

  /** Сохранение полученного шаблона в файл */
  def saveHtmlTemplate(fileName: String) = {
    val folder = "PublicHtml/"
    val out = new PrintWriter(folder + fileName)
    out.println(htmlTemplate)
    out.close()
  }

  /** Сохранение CSS шаблона в файл */
  def saveCssTemplate(fileName: String) = {
    val folder = "PublicHtml/"
    val out = new PrintWriter(folder + fileName)
//    out.println(cssTemplate)
    out.close()
  }


  val testPage = new HtmlPage("Test")(
    HtmlCom.divBox,
    HtmlCom.divBox)




  saveHtmlTemplate("index.html")
  saveCssTemplate("butFix.css")

  var x = <p></p>
  val y = <form></form>
  val z = x :+ y
  println(z.mkString(""))


}

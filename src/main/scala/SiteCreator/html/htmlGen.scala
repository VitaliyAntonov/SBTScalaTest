

package SiteCreator.html


import java.io.PrintWriter
import scala.xml.Elem


/**
 * Класс позволяет определять конкретные типы компонентов
 */

/**
 * Класс позволяет определять конкретные типы компонентов в HTML и CSS
 * @param componentName  Имя компонента
 */
abstract class Component(val componentName: String) extends Enumeration {
  /** Шаблоны для копирования в файлы PublicHtml */
  /** HTML шаблон компонента */
  def htmlTemplate: Elem
  /** CSS шаблон компонента */
  def cssTemplate: String
  /** =================================================== */

  /** Генерация ID в виде componentName + ID */
  def componentId = {
    lazy val x = Value(componentName)
    x.toString + x.id
  }
  /** Генерация ID в виде name + ID */
  def cNameId(name: String = "") = {
    lazy val x = Value(name)
    x.toString + x.id
  }
  /** Множество имён класса */
  def cNames(overClass1: String = "") = {
    componentName + " " + overClass1
  }
  /** Добавляем к имени компонента имя вложенного под-компонента */
  def cNamePlus(plus: String) = componentName + plus
}


/** Единичная кнопка с фиксацией с подложенным чекбоксом */
class FixButton(nameButton: String = "") extends Component("fixButton" + nameButton){

  /** Имя чекбокса(класса), используемое при генерации ID для привязки label */
  def inCompName = cNamePlus("Check")

  /** Часть CSS кода, привязанного к ID элемента(уникальные параметры кнопки) */
  var top: Int = 10
  var cssIdButton = ""
  def pastedCss(id: String) = {
    cssIdButton +=
      s"""
         |#${id} {
         |    position: absolute;
         |    top: ${top}px;    /** Отступ от верхнего края формы **/
         |    left: 100px;
         |    offText: "NO" ; /** текст на ВЫключенной кнопке **/
         |    onText: "YES !" ; /** текст на включенной кнопке **/
         |    width: 300px;
         |    height: 100px;
         |    border: 1px solid #0000FF;
         |}
         |""".stripMargin
  }

  /** HTML шаблон компонента */
  override def htmlTemplate = {
    /** ID чекбокса  используемый в пределах компонента для формирования label */
    val inCompId = cNameId(inCompName)
    /** Фиксируем ID кнопки */
    val idButton = componentId
    /** Определяем индивидуальные CSS параметры кнопки */
    pastedCss(idButton)
    top += 110 /** Позиция по вертикали каждой новой кнопки */

    <div id={idButton} class={cNames("colorTheme")}>
      <input type="checkbox" id={inCompId} class={inCompName} ></input>
      <label for={inCompId}> <!-- label выставляется для ID -->
        <div class={cNamePlus("Tap")}>
          <p class={cNamePlus("Text")}>1 _ </p>
        </div>
      </label>
    </div>
  }

  /** CSS шаблон компонента */
  override def cssTemplate: String =
    s"""
       |body {
       |  background-color: #000000;
       |  color: #FF0000;
       |}
       |""".stripMargin + cssIdButton  /** Добавляем уникальные параметры бокса кнопки **/
}

/** Перечень ссылок на компоненты */
object HtmlComponents extends Enumeration {
  lazy val fixButton = new FixButton("Test")


}



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
        {HtmlComponents.fixButton.htmlTemplate}
        {HtmlComponents.fixButton.htmlTemplate}
      </div>
      </body>
    </html>
  }
  val cssTemplate = HtmlComponents.fixButton.cssTemplate

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
    out.println(cssTemplate)
    out.close()
  }




  saveHtmlTemplate("index.html")
  saveCssTemplate("butFix.css")

}

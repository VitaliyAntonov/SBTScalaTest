package siteCreator.component

import siteCreator.HtmlPC


/** Единичная кнопка с фиксацией с подложенным чекбоксом */
class FixButton(nameButton: String = "") extends Component("fixButton" + nameButton){

  override def html(page: HtmlPC) = {}

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
  def htmlTemplate = {
    /** ID чекбокса  используемый в пределах компонента для формирования label */
    val inCompId = cNameId(inCompName)
    /** Фиксируем ID кнопки */
    val idButton = componentId
    /** Определяем индивидуальные CSS параметры кнопки */
    pastedCss(idButton)
    top += 110 /** Позиция по вертикали каждой новой кнопки */
    (
    s"""
       |    <div id=${idButton} class=${cNames("colorTheme")}>
       |      <input type="checkbox" id=${inCompId} class=${inCompName} ></input>
       |      <label for=${inCompId}> <!-- label выставляется для ID -->
       |        <div class=${cNamePlus("Tap")}>
       |          <p class=${cNamePlus("Text")}>1 _ </p>
       |        </div>
       |      </label>
       |    </div>
       |""".stripMargin
    )
  }

  /** CSS шаблон компонента */
  def cssTemplate(id:String = ""): String =
    s"""
       |body {
       |  background-color: #000000;
       |  color: #FF0000;
       |}
       |""".stripMargin

  /** Добавляем уникальные параметры бокса кнопки * */

  /** JS шаблон компонента */
  def jsTemplate(id:String = ""): String = {""}

}




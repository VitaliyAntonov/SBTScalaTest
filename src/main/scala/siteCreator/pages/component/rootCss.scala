package siteCreator.pages.component

import siteCreator.pages.HtmlPC

/** Класс для создания root сектора страниц с основными CSS настройками HTML страницы.
 * Включает переменные, например  --bodyBackColor, --bodyPadding, для основных цветовых
 * и визуальных настроек */

class RootCss( colorThemeName: ColorTheme,
                        gridName: String = "",
                        sizePreference: String = "") extends Component("RootCss") {

  /** Цвет основного фона страницы */
  def bodyBackgroundColor = s"""--bodyBackgroundColor: ${colorThemeName.toRGB(colorThemeName.bodyBackgroundColor)};"""
  /** Основной цвет текста страницы */
  def bodyTextColor = s"""--bodyTextColor: ${colorThemeName.toRGB(colorThemeName.bodyTextColor)};"""

  /** HTML шаблон компонента */
  override def html(page: HtmlPC)  = ""

  /** CSS шаблон компонента */
  def cssTemplate(id:String = ""): String =
    s"""|root {
        |  $bodyBackgroundColor
        |  $bodyTextColor
        |}""".stripMargin

  /** JS шаблон компонента */
  def jsTemplate(id:String = ""): String = ""
}





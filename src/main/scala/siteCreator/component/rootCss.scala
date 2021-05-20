package siteCreator.component

import siteCreator.HtmlPC

/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */


/** Класс для создания root сектора страниц с основными CSS настройками HTML страницы.
 * Включает переменные, например  --bodyBackColor, --bodyPadding, для основных цветовых
 * и визуальных настроек */
class RootCss(actTheme: ColorTheme,
              gridName: String = "",
              sizePreference: String = "") extends Component("RootCss") {

  /** HTML шаблон компонента */
  override def html(page: HtmlPC)  = ""

  /** CSS шаблон компонента */
  def cssTemplate: String =
    s"""|:root {
        |   --bodyBgColor: ${actTheme.bodyBgColor};
        |   --bodyTextColor: ${actTheme.bodyTextColor};
        |}""".stripMargin

}





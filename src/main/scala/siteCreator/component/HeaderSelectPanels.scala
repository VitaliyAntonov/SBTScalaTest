package siteCreator.component

import siteCreator.HtmlPC

/**
 * @author Виталий Антонов @date 5/13/21
 *         kaligraf@yandex.ru
 * */

/**
 * Select Tabs Panel
 * Класс позволяет создавать Панель с переключающимися вкладками
 * @param name
 * @param panels
 */
class SelectPanels(name: String)(panels: String*) extends Component(name + "STabPanels") {

  override def html(page: HtmlPC): Unit = {

    /** html - панели вкладок */
    var html: String = ""

    addCss(page, CCss.cssHeadPBox) // добавляем CSS основного контейнера
    addCss(page, CCss.cssForAllBut) // CSS всех кнопок
    addCss(page, CCss.CssRadioCheck(SClass.radioClass)) // CSS кнопок при Check
    addCss(page, CCss.cssButHover) // CSS кнопок при Hover
    addCss(page, CCss.cssPanelTabs) // CSS для всех вкладок панели

    /** html контейнера для всех элементов */
    html +=
      s"""
         |  <div class="${componentName}">
         |""".stripMargin

    var radioId = ""    /** ID для radio-button */
    var sRadioClass = ""   /** Имя селектора класса для radio-button */

    if(panels.nonEmpty){
      /** Создаём html кнопок */
      for(i <- 0 until panels.size){
        radioId = componentId + "-" + i.toString /** Фиксируем id */
        sRadioClass = SClass.radioClass + "-" + i.toString    /** Имя селектора класса для radio-button */
        html +=
          s"""
             |    <input type="radio" name="${SClass.radioName}" id="${radioId}" class="${SClass.radioClass} ${sRadioClass}" ${if(i == 0)"checked" else ""} >
             |    <label for="${radioId}" class="${SClass.lClass}" ><div class="${SClass.inLabel} ${SClass.inLabel + "-" + i.toString}">${panels(i)}</div></label>
             |""".stripMargin

        addCss(page, CCss.cssForButN((SClass.inLabel + "-" + i.toString), i)) // CSS - расположение кнопок

      }
      /** Создаём html вкладок */
      for(i <- 0 until panels.size) {
        html +=
          s"""
             |    <div class="${SClass.tabBox} ${SClass.tabBox + "-" + i.toString}">
             |      <p>${panels(i)}</p>
             |    </div>
             |""".stripMargin

        addCss(page, CCss.cssTabCheck(i)) // CSS показываем выбранную вкладку
      }
    }

    /** Закрываем html контейнера для всех элементов панели */
    html +=
      s"""
         |  </div>
         |""".stripMargin

    addHtml(page, html)
  }

  /** Object Selectors Class назначенные имена классов для селекторов */
  object SClass{
    /** имя(name) для всех radio-button */
    lazy val radioName: String = s"${name}STabs"
    /** Имя селектора класса для radio-button */
    lazy val radioClass: String = s"${name}Btn"
    /** Имя селектора класса для label */
    lazy val lClass: String = s"${name}LabelBut"
    /** Имя класса, вложенного в label */
    lazy val inLabel: String = s"""${name}InLabel"""
    /** Имя класса контейнеров вкладок */
    lazy val tabBox: String = s"""${name}TabBox"""
  }

  /** Component CSS - CSS код, используемый для формирования компонента на странице */
  object CCss {
    /** CSS контейнера для всех элементов */
    def cssHeadPBox: String = {
      lazy val s =
        s"""
           |.${componentName} {
           |  position: absolute;
           |  top: 0px;
           |  left: 0px;
           |  width: 100vw;
           |  height: 100vh;
           |  background-color: #384952;
           |  color: #f7f57a;
           |}
           |
           |.${componentName} input {
           |    opacity: 0;
           |    display: none;
           |    visibility: hidden;
           |}
           |""".stripMargin
      s
    }

    /** CSS общие настройки всех кнопок */
    def cssForAllBut: String = {
      lazy val s =
        s"""
           |.${SClass.inLabel} {
           |  position: absolute;
           |  display: flex;
           |  align-items: center;
           |  justify-content: center;
           |  width: ${CSize.butWidth}px;
           |  height: ${CSize.butHeight}px;
           |  border: ${CSize.borderBut};
           |  border-radius: ${CSize.borderRadius}px;
           |  top: ${CSize.butTop}px;
           |  background-color: #384952;
           |  color: #808080;
           |  user-select: none;
           |  cursor: pointer;
           |  transition: all 50ms ease-in-out;
           |}
           |""".stripMargin
      s
    }

    /** CSS расположение кнопок */
    def cssForButN(selector: String, number: Int): String = {
      lazy val left = (CSize.butWidth + CSize.butGapW) * number + CSize.butBlockLeft
      lazy val s =
        s""".${selector} { left: ${left}px; }\n"""
      s
    }

    /** CSS checked для кнопки */
    def CssRadioCheck(selector: String): String = {
      lazy val s =
        s"""
          |.${selector}:checked + label .${SClass.inLabel} {
          |  background-color: ${CColors.butBgCheckColor};
          |  color: ${CColors.butCheckColor};
          |  width: ${CSize.butWidthCheck}px;
          |  height: ${CSize.butHeightCheck}px;
          |  transform: translateY(${CSize.butCheckTranslate}px) translateX(${CSize.butCheckTranslate}px);
          |  border-bottom: none;
          |  border-left: 1px solid ${CColors.butColor};
          |  border-top: 1px solid ${CColors.butColor};
          |  border-right: 1px solid ${CColors.butColor};
          |  border-bottom-left-radius: 0px;
          |  border-bottom-right-radius: 0px;
          |  font-size: 28px;
          |  z-index: 1;
          |}
          |""".stripMargin
      s
    }

    /** CSS hover для кнопки */
    def cssButHover: String = {
      lazy val s =
        s"""
           |.${SClass.inLabel}:hover {
           |  background-color: ${CColors.butBgCheckColor};
           |  color: ${CColors.butCheckColor};
           |  border-color: ${CColors.butCheckColor};
           |}
           |""".stripMargin
      s
    }

    /** CSS для всех контейнеров вкладок */
    def cssPanelTabs: String = {
      lazy val s =
        s"""
           |.${SClass.tabBox} {
           |  position: absolute;
           |  top: ${CSize.topTabPanel}px;
           |  left: ${CSize.leftTabPanel}px;
           |  width: ${CSize.widthTabPanel};
           |  height: ${CSize.heightTabPanel};
           |  background-color: ${CColors.tabPBgColor};
           |  border: 1px solid ${CColors.tabPBorderColor};
           |  visibility: hidden;
           |}
           |""".stripMargin
      s
    }

    /** CSS для контейнера выбранной вкладки */
    def cssTabCheck(num: Int): String = {
      /** селектор radio-button */
      lazy val selRB = SClass.radioClass + "-" + num.toString
      /** селектор контейнера вкладки */
      lazy val selBoxTab = SClass.tabBox + "-" + num.toString
      lazy val s =
        s"""
           |.${selRB}:checked ~ .${selBoxTab} {
           |  visibility: visible;
           |}
           |""".stripMargin
      s
    }
  }

  /** Component Size - размеры компонента и его составляющих */
  object CSize {
    /** ширина кнопки вкладок */
    lazy val butWidth = 100
    /** высота кнопки вкладок */
    lazy val butHeight = 25
    /** отступ от верха панели для кнопок */
    lazy val butTop = 15
    /** отступ блока кнопок слева */
    lazy val butBlockLeft = 15
    /** Расстояние между кнопками */
    lazy val butGapW = 20
    /** Бордюр кнопки неактивной вкладки */
    lazy val borderBut = s"1px solid ${CColors.butColor}"
    lazy val borderRadius = 4

    /** увеличение размеров в пикселях при Check */
    lazy val butAddSize = butGapW
    lazy val butWidthCheck = butWidth + butAddSize
    lazy val butHeightCheck = butHeight + butAddSize
    /** сдвиг координат top и left при Check */
    lazy val butCheckTranslate = -(butAddSize/2)

    /** контейнеры вкладок панелей */
    lazy val topTabPanel = butTop + butHeight + butAddSize/2 - 1 // -1 пиксель для бордюра
    lazy val leftTabPanel = 3
    lazy val widthTabPanel = "calc(100vw - 6px)"
    lazy val heightTabPanel = s"calc(100vh - ${topTabPanel + leftTabPanel}px)"
  }

  /** Component Colors - цвета компонента и его составляющих */
  object  CColors {
    /** цвета кнопок */
    lazy val butColor = "#808080"
    lazy val butCheckColor = "#f7f57a"
    lazy val butBgCheckColor = "#0d3153"

    /** цвета вкладок панели */
    lazy val tabPBgColor = "#252e33"
    lazy val tabPBorderColor = butColor
  }


}





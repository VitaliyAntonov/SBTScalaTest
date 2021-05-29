package siteCreator.component
import siteCreator.HtmlPC

/**
 * @author Виталий Антонов @date 5/26/21
 *         kaligraf@yandex.ru
 * */

/** Класс позволяет создать вертикальный список с увеличением
 * нескольких элементов при hover
 * элементы:
 * - контейнер окна видимости с вертикальным скроллингом
 * - контейнер, содержащий разметку и остальные элементы, размером больше окна видимости
 * Scale Alphabet (sab)*/
class WindowVisible(name: String)(tags: String*)(alphabet: String = "")
  extends Component(name + "WinVisible") {

  override def html(page: HtmlPC): Unit = {
    /** HTML */
    var html: String = ""
    /** CSS */
    var css: String = ""

    html += CElem.visibleBox.htmlOpenBoxWithSClass + enter    /** открываем html контейнер области видимости */
    css += CCss.cssVisibleBox                                 /** CSS контейнера области видимости */
    html += CElem.mapBox.htmlOpenBoxWithSClass + enter        /** открываем контейнер mapBox */
    css += CCss.cssMapBox                                     /** CSS контейнера mapBox */


    /** html контейнеры вертикальной разметки */
    if(alphabet.nonEmpty){
      css += CCss.cssMarkBoxes // общий CSS для контейнеров вертикальной разметки
      for(i <- 0 until alphabet.length){
        css +=  CCss.cssMarkBoxN(i)
        css += CCss.cssMarkBoxHover(i)

        html += CElem.markBox.htmlOpenBoxWith2SClassN(i) + i + CElem.markBox.htmlCloseBox + enter

      }
    }

    html += CElem.mapBox.htmlCloseBox + enter       /** закрываем контейнер mapBox */
    html += CElem.visibleBox.htmlCloseBox + enter   /** Закрываем html контейнер области видимости */
    addCss(page, css)   // Добавляем сформированный CSS
    addHtml(page, html) // Добавляем сформированный HTML
  }


  /** Встроенные элементы Component Elements */
  object CElem{
    /** контейнер области видимости - в нём помещается больший контейнер mapBox */
    lazy val visibleBox = new Box(s"${name}VisibleBox", 400, 600, 50, 0)
    /** контейнер mapBox - отображает базу данных */
    lazy val mapBox = new Box(s"${name}MapBox", 1000, 1000, -70, 0)
    /** контейнер разметки */
    lazy val markBox = new Box(s"${name}MarkBox", CSize.widthMarkBox, CSize.heightMarkBox, CSize.topMarkBoxes, 0)
  }

  /** Object Selectors Class назначенные имена классов для селекторов CSS */
  object SClass{
    /** контейнер области видимости - в нём помещается больший контейнер mapBox */
    lazy val visibleBoxName = s"${name}VisibleBox"
    /** контейнер для отображения базы данных */
    lazy val mapBoxName = s"${name}MapBox"
    /** общее имя для контейнеров вертикальной разметки */
    lazy val markBoxName = s"${name}MarkBox"
  }

  /** Component Size - размеры компонента и его составляющих */
  object CSize {
    /** контейнер области видимости */
    lazy val widthVisibleBox = 400
    lazy val heightVisibleBox = 600
    lazy val topVisibleBox = 50
    lazy val leftVisibleBox = 0

    /** контейнер mapBox */
    lazy val widthMapBox = 1000
    lazy val heightMapBox = 1000

    /** контейнеры разметки */
    lazy val widthMarkBox = 200
    lazy val heightMarkBox = sizeLiteraBox0 + gapLiteraBox
    lazy val topMarkBoxes = 100


    lazy val heightMarkBox0 = sizeLiteraBox0 + gapLiteraBox
    lazy val heightMarkBox1 = 35
    lazy val heightMarkBox2 = 50
    lazy val heightMarkBox3 = 65

    /** координата left контейнеров литер */
    lazy val leftLiteraBox = 30
    /** контейнеры литер имеют по четыре размера width = height */
    lazy val sizeLiteraBox0 = 15
    lazy val sizeLiteraBox1 = 35
    lazy val sizeLiteraBox2 = 50
    lazy val sizeLiteraBox3 = 65
    /** Зазор между контейнерами литер */
    lazy val gapLiteraBox = 6
    /** размер шрифта литер имеют по четыре размера */
    lazy val literaFontSize0 = 10
    lazy val literaFontSize1 = 25
    lazy val literaFontSize2 = 35
    lazy val literaFontSize3 = 45
  }

  /** Component CSS - CSS код, используемый для формирования компонента на странице */
  object CCss {

    /** CSS для контейнера области видимости */
    def cssVisibleBox: String = {
      val s =
        s"""
          |.${CElem.visibleBox.sName} {
          |   position: absolute;
          |   width: ${CElem.visibleBox.width}px;
          |   height: ${CElem.visibleBox.height}px;
          |   top: ${CElem.visibleBox.top}px;
          |   left: ${CElem.visibleBox.left}px;
          |   border: 1px solid yellow;
          |   overflow: auto;
          |}
          |""".stripMargin
      s
    }

    /** CSS контейнера mapBox */
    def cssMapBox: String = {
      val s =
        s"""
           |.${CElem.mapBox.sName} {
           |  position: relative;
           |  width: ${CElem.mapBox.width}px;
           |  height: ${CElem.mapBox.height}px;
           |  top: ${CElem.mapBox.top}px;
           |  left: ${CElem.mapBox.left}px;
           |  border: 1px solid red;
           |}
           |""".stripMargin
      s
    }

    /** общий CSS для контейнеров вертикальной разметки */
    def cssMarkBoxes: String = {
      val s =
        s"""
           |.${CElem.markBox.sName} {
           |  position: absolute;
           |  width: ${CElem.markBox.width}px;
           |  height: ${CElem.markBox.height}px;
           |  border: 1px solid #808080;
           |  z-index: 1;
           |}
           |
           |""".stripMargin
      s
    }
    /** CSS координата top контейнеров вертикальной разметки */
    def cssMarkBoxN(num: Int): String = {
      val s =
        s""".${CElem.markBox.sNameN(num)} {top: calc(${num} * ${CElem.markBox.height}px + ${CElem.markBox.top}px);}\n"""
      s
    }

    /** CSS hover на контейнере разметки */
    def cssMarkBoxHover(num: Int): String = {
      val trans: Int = (CSize.heightMarkBox3 - CSize.heightMarkBox) / 2
      val trans1: Int = trans + (CSize.heightMarkBox2 - CSize.heightMarkBox) / 2
      val s =
        s"""
           |.${CElem.markBox.sNameN(num)}:hover {
           |  transform: translateY(-${trans}px);
           |  height: ${CSize.heightMarkBox3}px;
           |  border: 1px solid #5FEFFF;
           |}
           |
           |.${CElem.markBox.sNameN(num)}:hover ~ .${CElem.markBox.sNameN(num+1)} {
           |  transform: translateY(${trans}px);
           |  height: ${CSize.heightMarkBox2}px;
           |  border: 1px solid red;
           |}
           |""".stripMargin
      s
    }

  }

  /** Component Colors - цвета компонента и его составляющих */
  object  CColors {

  }





}

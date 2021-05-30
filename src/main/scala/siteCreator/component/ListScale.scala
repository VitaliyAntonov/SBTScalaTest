package siteCreator.component
import siteCreator.HtmlPC

/**
 * @author Виталий Антонов @date 5/24/21
 *         kaligraf@yandex.ru
 * */


/** Класс позволяет создать вертикальный список с увеличением
 * нескольких элементов при hover
 * элементы:
 * - контейнер окна видимости с вертикальным скроллингом
 * - контейнер, содержащий разметку и остальные элементы, размером больше окна видимости
 * Scale Alphabet (sab)*/
class ListScale(name: String)(tags: String*)(alphabet: String = "")
  extends Component(name + "ListScale") {

  override def html(page: HtmlPC): Unit = {

    /** html текущей страницы */
    var html: String = ""
    /** CSS текущей страницы */
    var css: String = ""

    css +=  CCss.cssMarkBoxes


    /** html контейнеры вертикальной разметки */
    if(alphabet.nonEmpty){
      val LenN = alphabet.length - 1
      for(i <- 0 until alphabet.length){
        i match {     // формируем высоты и координату top разметочных контейнеров
          case 0 => CElem.markBox.height = CSize.sizeLiteraBox3; CElem.markBox.top = CSize.topMarkBoxes
          case LenN => {  CElem.markBox.height = CSize.sizeLiteraBox3
                          CElem.markBox.top = CSize.topMarkBoxes + CSize.sizeLiteraBox3 + (i - 1) * CSize.heightMarkBox
                        }
          case _ => {
            CElem.markBox.height = CSize.heightMarkBox
            CElem.markBox.top = CSize.topMarkBoxes + CSize.sizeLiteraBox3 + (i - 1) * CSize.heightMarkBox
          }
        }
        css +=  CCss.cssMarkBoxN(i) + enter

        html += CElem.markBox.htmlOpenBoxWith2SClassN(i) + i + CElem.markBox.htmlCloseBox + enter
      }
    }

    /** Открываем контейнер для вертикального FLEX */
    html += s"""    <div id="${SClass.idFlexVboxName}" class="${SClass.flexVboxName}">\n"""

    css +=  CCss.cssVFlexBox
    css +=  CCss.cssLiteraBoxes
    css +=  CCss.cssLiteraBoxHover


    /** Контейнеры литер */
    if(alphabet.size > 0) {
      val LenN = alphabet.size - 1
      for (i <- 0 until alphabet.size) {
        css +=  CCss.cssMarkBoxHover(i) // CSS для hover на разметочных боксах
        html += s"""      <div class="${CElem.literaBox.sName} ${CElem.literaBox.sNameN(i)}" onmouseover="${SClass.jsLiteraHoverN}(${i})" onmouseout="${SClass.jsLiteraEndHoverN}(${i})">${alphabet(i)}</div>\n"""
      }
    }

    /** Закрытие контейнера для вертикального FLEX */
    html += s"""    </div>\n"""




    addJs(page, CJS.jsLiteraHoverN)
    addJs(page, CJS.jsLiteraEndHoverN)            // Добавляем сформированный JS

    addCss(page, css)                             // Добавляем сформированный CSS
    addHtml(page, html)                           // Добавляем сформированный HTML
  }

  /** Встроенные элементы Component Elements */
  object CElem{
    /** контейнер области видимости - в нём помещается больший контейнер mapBox */
    val visibleBox = new Box(s"${name}VisibleBox", 400, 600, 50, 0)
    /** контейнер mapBox - отображает базу данных */
    val mapBox = new Box(s"${name}MapBox", 1000, 1000, -70, 0)
    /** контейнер разметки */
    lazy val markBox = new Box(s"${name}MarkBox", CSize.widthMarkBox, CSize.heightMarkBox, CSize.topMarkBoxes, 0)
    /** контейнер flexBox */
    val flexBox = new Box(s"${name}FlexVBox", CSize.widthFlexBox, CSize.heightFlexBox,
                              CSize.topFlexBox, 0 )
    /** контейнеры литер */
    val literaBox =  new Box(s"${name}LiteraInFlex", CSize.sizeLiteraBox0,
                              CSize.sizeLiteraBox0, 0, CSize.leftLiteraBox )

  }

  /** Object Selectors Class назначенные имена классов для селекторов */
  object SClass{
    /** общее имя для контейнеров вертикальной разметки */
    lazy val markBoxName = s"${name}MarkBox"

    /** контейнер для литер/тегов с FLEX разметкой */
    lazy val flexVboxName = s"${name}FlexVBox"
    /** id контейнера  с FLEX разметкой */
    lazy val idFlexVboxName = cNameId(s"id${name}FlexVboxName")

    /** контейнеры литер */
    val literaBoxName = s"${name}LiteraInFlex"
    /** JS Имя функции при hover на литере - параметр номер литеры */
    val jsLiteraHoverN = "jsLiteraHoverN"
    /** JS Имя функции при ОКОНЧАНИИ hover на литере  - параметр номер литеры */
    val jsLiteraEndHoverN = "jsLiteraEndHoverN"
  }

  /** Component Size - размеры компонента и его составляющих */
  object CSize {
    /** контейнеры разметки */
    val widthMarkBox = 200
    val heightMarkBox = sizeLiteraBox0 + gapLiteraBox
    val topMarkBoxes = 60

    /** вертикальный FLEX контейнер */
    val widthFlexBox = 100
    val heightFlexBox = (alphabet.size - 2) * (sizeLiteraBox0 + gapLiteraBox) + sizeLiteraBox3 * 2
    val topFlexBox = topMarkBoxes
    val topFlexBoxHoverOnLitera = topFlexBox + sizeLiteraBox3 / 2
    val heightFlexBoxHoverOnLitera = heightFlexBox - sizeLiteraBox3

    /** координата left контейнеров литер */
    val leftLiteraBox = 40
    val leftLiteraBoxHover = 50
    val leftLiteraBoxHover3 = 60

    /** контейнеры литер имеют по три размера width = height */
    lazy val sizeLiteraBox0 = 18
    lazy val sizeLiteraBox2 = 50
    lazy val sizeLiteraBox3 = 65
    /** Зазор между контейнерами литер */
    lazy val gapLiteraBox = 1
    /** размер шрифта литер имеют по четыре размера */
    lazy val literaFontSize0 = 13
    lazy val literaFontSize2 = 35
    lazy val literaFontSize3 = 45
  }

  /** Component CSS - CSS код, используемый для формирования компонента на странице */
  object CCss {
    /** общий CSS для контейнеров вертикальной разметки */
    def cssMarkBoxes: String = {
      lazy val s =
        s"""
           |.${SClass.markBoxName} {
           |  position: absolute;
           |  width: ${CSize.widthMarkBox}px;
           |  border: 1px solid #808080;
           |  z-index: 1;
           |}
           |""".stripMargin
      s
    }
    /** CSS координата top контейнеров вертикальной разметки */
    def cssMarkBoxN(num: Int): String = {
      lazy val s =
      s""".${CElem.markBox.sNameN(num)} {top: ${CElem.markBox.top}px; height: ${CElem.markBox.height}px;}"""
      s
    }

    /** CSS для контейнера с вертикальным FLEX */
    def cssVFlexBox: String = {
      lazy val s =
        s"""
           |.${SClass.flexVboxName} {
           |  display: flex;
           |  flex-direction: column;
           |  justify-content: space-around;
           |  position: absolute;
           |  left: 0px;
           |  top: ${CElem.flexBox.top}px;
           |  width: ${CSize.widthFlexBox}px;
           |  height: ${CSize.heightFlexBox}px;
           |  border: 1px solid yellow;
           |  transition: all 50ms ease-in-out;
           |}
           |""".stripMargin
      s
    }

    /** общий CSS для контейнеров литер */
    def cssLiteraBoxes: String = {
      lazy val s =
        s"""
           |.${SClass.literaBoxName} {
           |  position: relative;
           |  left: ${CSize.leftLiteraBox}px;
           |  display: flex;
           |  align-content: center;
           |  justify-content: center;
           |  width: ${CSize.sizeLiteraBox0}px;
           |  height: ${CSize.sizeLiteraBox0}px;
           |  font-size: ${CSize.literaFontSize0}px;
           |  border: 1px solid red;
           |  transition: all 50ms ease-in-out;
           |}
           |""".stripMargin
      s
    }

    /** CSS для hover на контейнере разметки */
    def cssMarkBoxHover(num: Int): String = {
      val s =
        s"""
         |.${CElem.markBox.sNameN(num)}:hover ~ .${SClass.flexVboxName} .${SClass.literaBoxName + "-" + num.toString} {
         |  width: ${CSize.sizeLiteraBox3}px;
         |  height: ${CSize.sizeLiteraBox3}px;
         |  font-size: ${CSize.literaFontSize3}px;
         |  left: ${CSize.leftLiteraBoxHover3}px;
         |  z-index: 2;
         |}
         |
         |.${SClass.markBoxName + "-" + num.toString}:hover ~ .${SClass.flexVboxName} .${SClass.literaBoxName + "-" + (num - 1).toString} {
         |  width: ${CSize.sizeLiteraBox2}px;
         |  height: ${CSize.sizeLiteraBox2}px;
         |  font-size: ${CSize.literaFontSize2}px;
         |  left: ${CSize.leftLiteraBoxHover}px;
         |}
         |
         |.${SClass.markBoxName + "-" + num.toString}:hover ~ .${SClass.flexVboxName} .${SClass.literaBoxName + "-" + (num + 1).toString} {
         |  width: ${CSize.sizeLiteraBox2}px;
         |  height: ${CSize.sizeLiteraBox2}px;
         |  font-size: ${CSize.literaFontSize2}px;
         |  left: ${CSize.leftLiteraBoxHover}px;
         |}
         |""".stripMargin
      s
    }

    /** CSS для hover на контейнере Литеры */
    def cssLiteraBoxHover: String = {
      lazy val s =
        s"""
           |.${SClass.literaBoxName}:hover {
           |  width: ${CSize.sizeLiteraBox3}px;
           |  height: ${CSize.sizeLiteraBox3}px;
           |  font-size: ${CSize.literaFontSize3}px;
           |  left: ${CSize.leftLiteraBoxHover3}px;
           |  z-index: 2;
           |}
           |""".stripMargin
      s
    }


  }

  /** Component Java Script - JS скрипты компонента */
  object CJS {

    /** изменение размеров соседних литер при hover на литере
     * @num - номер текущей литеры */
    def jsLiteraHoverN: String = {
      val s =
        s"""
           |function ${SClass.jsLiteraHoverN}(num) {
           |  let numUp = num - 1;
           |  let numDown = num + 1;
           |  let classUpName = "${SClass.literaBoxName + "-"}" + numUp;
           |  let classDownName = "${SClass.literaBoxName + "-"}" + numDown;
           |  let classUp = document.getElementsByClassName(classUpName);
           |  let classDown = document.getElementsByClassName(classDownName);
           |
           |  if(num > 0){
           |	  classUp[0].style.width = "${CSize.sizeLiteraBox2}px";
           |    classUp[0].style.height = "${CSize.sizeLiteraBox2}px";
           |    classUp[0].style.fontSize = "${CSize.literaFontSize2}px";
           |    classUp[0].style.left = "${CSize.leftLiteraBoxHover}px";
           |  }
           |  if(num < ${alphabet.length - 1}){
           |	  classDown[0].style.width = "${CSize.sizeLiteraBox2}px";
           |    classDown[0].style.height = "${CSize.sizeLiteraBox2}px";
           |    classDown[0].style.fontSize = "${CSize.literaFontSize2}px";
           |    classDown[0].style.left = "${CSize.leftLiteraBoxHover}px";
           |  }
           |}
           |""".stripMargin
      s
    }

    /** изменение размеров соседних литер при ОКОНЧАНИИ hover на литере
     * @num - номер текущей литеры */
    def jsLiteraEndHoverN: String = {
      val s =
        s"""
           |function ${SClass.jsLiteraEndHoverN}(num) {
           |  let numUp = num - 1;
           |  let numDown = num + 1;
           |  let classUpName = "${SClass.literaBoxName + "-"}" + numUp;
           |  let classDownName = "${SClass.literaBoxName + "-"}" + numDown;
           |  let classUp = document.getElementsByClassName(classUpName);
           |  let classDown = document.getElementsByClassName(classDownName);
           |
           |  if(num > 0){
           |	  classUp[0].style.width = null;
           |    classUp[0].style.height = null;
           |    classUp[0].style.fontSize = null;
           |    classUp[0].style.left = null;
           |  }
           |  if(num < ${alphabet.length - 1}){
           |	  classDown[0].style.width = null;
           |    classDown[0].style.height = null;
           |    classDown[0].style.fontSize = null;
           |    classDown[0].style.left = null;
           |  }
           |}
           |""".stripMargin
      s
    }


  }

  /** Component Colors - цвета компонента и его составляющих */
  object  CColors {

  }

}

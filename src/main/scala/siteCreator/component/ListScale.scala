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
    val htmlCod = new HtmlC // объект HTML кода компонента
    /** CSS текущей страницы */
    var css: String = ""
    /** JS текущей страницы */
    var js: String = ""

    css +=  CCss.cssMarkBoxes + CCss.cssMarkBoxesHover + CElem.markBox.CSSHoverContent(">>>>>")


    /** html контейнер области видимости */
    htmlCod + CElem.visibleBox.htmlOpenBoxWithSClass + enter


    /** html контейнеры вертикальной разметки */
    if(alphabet.nonEmpty){
      val LenN = alphabet.length - 1
      for(i <- 0 until alphabet.length){
        // Первый и последний разметочные контейнеры больше по высоте
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

        // HTML для использования JS
        htmlCod + s"""    <div class="${CElem.markBox.sName} ${CElem.markBox.sNameN(i)}" onmouseover="${SClass.jsFnMarkBoxHoverN}(${i})" onmouseout="${SClass.jsFnMarkBoxEndHoverN}(${i})"><span></span></div>""" + enter

      }
    }

    /** Открываем контейнер для вертикального FLEX */
    htmlCod + s"""    <div id="${SClass.idFlexVboxName}" class="${SClass.flexVboxName}">\n"""

    css +=  CCss.cssScrollbar
    css +=  CCss.cssVFlexBox
    css +=  CCss.cssLiteraBoxes
    css +=  CCss.cssLiteraBoxHover
    css +=  CCss.cssVisibleBox

    /** Контейнеры литер */
    if(alphabet.nonEmpty) {
      for (i <- 0 until alphabet.length) {
        htmlCod + s"""      <div class="${CElem.literaBox.sName} ${CElem.literaBox.sNameN(i)}" onmouseover="${SClass.jsFnLiteraHoverN}(${i})" onmouseout="${SClass.jsFnLiteraEndHoverN}(${i})">${alphabet(i)}</div>\n"""
      }
    }

    /** Закрытие контейнера для вертикального FLEX */
    htmlCod + s"""    </div>\n"""

    /** Закрытие контейнера области видимости */
    htmlCod + CElem.visibleBox.htmlCloseBox + enter

    /**  Добавляем JS */
    js += CJS.jsMarkBoxHover + CJS.jsLiteraHoverN + CJS.jsLiteraEndHoverN
    addJs(page, js)
    /** Добавляем сформированный CSS */
    addCss(page, css)
    /** Добавляем сформированный HTML */
    addHtml(page, htmlCod.buffer)

  }

  /** Встроенные элементы компонента Component Elements */
  object CElem{
    /** контейнер области видимости списка */
    val visibleBox = new Box(s"${name}VisibleBox", CSize.visibleBoxWidth, 400, 50, 0)
    /** контейнер mapBox - отображает базу данных */
    val mapBox = new Box(s"${name}MapBox", 1000, 1000, -70, 0)
    /** контейнер разметки */
    val markBox = new Box(s"${name}MarkBox", CSize.widthMarkBox, CSize.heightMarkBox, CSize.topMarkBoxes, 0)
    /** контейнер flexBox */
    val flexBox = new Box(s"${name}FlexVBox", CSize.widthFlexBox, CSize.heightFlexBox,
                              CSize.topFlexBox, 0 )
    /** контейнеры литер */
    val literaBox =  new Box(s"${name}LiteraInFlex", CSize.sizeLiteraBox0,
                              CSize.sizeLiteraBox0, 0, CSize.leftLiteraBox )
  }

  /** Object Selectors Class назначенные имена классов для селекторов и имён функций JS */
  object SClass{
    /** общее имя для контейнеров вертикальной разметки */
    lazy val markBoxName = s"${name}MarkBox"
    /** JS Function Name - Имя функции при hover на контейнере разметки - параметр номер литеры */
    val jsFnMarkBoxHoverN = "jsFnMarkBoxHoverN"
    /** JS Function Name - Имя функции при ОКОНЧАНИИ hover на контейнере разметки - параметр номер литеры */
    val jsFnMarkBoxEndHoverN = "jsFnMarkBoxEndHoverN"

    /** контейнер для литер/тегов с FLEX разметкой */
    lazy val flexVboxName = s"${name}FlexVBox"
    /** id контейнера  с FLEX разметкой */
    lazy val idFlexVboxName = cNameId(s"id${name}FlexVboxName")

    /** контейнеры литер */
    val literaBoxName = s"${name}LiteraInFlex"
    /** JS Function Name - Имя функции при hover на литере - параметр номер литеры */
    val jsFnLiteraHoverN = "jsLiteraHoverN"
    /** JS Имя функции при ОКОНЧАНИИ hover на литере  - параметр номер литеры */
    val jsFnLiteraEndHoverN = "jsLiteraEndHoverN"
  }

  /** Component Size - размеры компонента и его составляющих */
  object CSize {
    /** контейнеры разметки */
    val widthMarkBox = 100
    val heightMarkBox = sizeLiteraBox0 + gapLiteraBox
    val topMarkBoxes = 0

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
    /** размер шрифта литер имеют по три размера */
    lazy val literaFontSize0 = 13
    lazy val literaFontSize2 = 35
    lazy val literaFontSize3 = 45

    /** Ширина visibleBox */
    lazy val visibleBoxWidth = leftLiteraBoxHover3 + sizeLiteraBox3 + 1

  }

  /** Component CSS - CSS код, используемый для формирования компонента на странице */
  object CCss {
    /** Настройки полосы прокрутки */
    def cssScrollbar: String = {
      s"""
         |::-webkit-scrollbar {
         |    width: 0px;
         |    height: 0px;
         |}
         |::-webkit-scrollbar-track {
         |    background-color: #43626d;
         |    box-shadow: inset 0 0 3px #ffffff;
         |    border-radius: 10px;
         |}
         |::-webkit-scrollbar-thumb {
         |    border-radius: 10px;
         |    box-shadow: inset 0 0 3px #ffffff;
         |    background-color: #384952;
         |}
         |""".stripMargin
    }


    /** CSS контейнера области видимости списка */
    def cssVisibleBox: String = {
      CElem.visibleBox.cssList += (
        ("position", "relative"),
        ("overflow-y", "scroll"),
        ("background-color", "#182922" )
      )
      CElem.visibleBox.CSS
    }

    /** общий CSS для контейнеров вертикальной разметки - z-index 1 размещает разметку ВЫШЕ флекс бокса */
    def cssMarkBoxes: String = {
      lazy val s =
        s"""
           |.${SClass.markBoxName} {
           |  position: absolute;
           |  width: ${CSize.widthMarkBox}px;
           |  border: 1px dotted #808080;
           |  display: flex;
           |  align-items: center;
           |  z-index: 1;
           |}
           |""".stripMargin
      s
    }
    /** CSS координата top контейнеров вертикальной разметки */
    def cssMarkBoxN(num: Int): String = {
      val s =
      s""".${CElem.markBox.sNameN(num)} {top: ${CElem.markBox.top}px; height: ${CElem.markBox.height}px;}"""
      s
    }

    /** CSS hover на контейнере вертикальной разметки */
    def cssMarkBoxesHover: String = {
      CElem.markBox.cssListHover += (
        ("background-color", "#283942")
      )
      CElem.markBox.CSSHover
    }


    /** CSS для контейнера с вертикальным FLEX */
    def cssVFlexBox: String = {
      val s =
        s"""
           |.${SClass.flexVboxName} {
           |  display: flex;
           |  flex-direction: column;
           |  justify-content: space-around;
           |  position: absolute;
           |  overflow: visible;
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
      val s =
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

    /** CSS для hover на контейнере Литеры */
    def cssLiteraBoxHover: String = {
      val s =
        s"""
           |.${SClass.literaBoxName}:hover {
           |  background-color: #384952;
           |}
           |""".stripMargin
      s
    }




  }

  /** Component Java Script - JS скрипты компонента */
  object CJS {

    /** Изменение размеров при hover на контейнере разметки */
    def jsMarkBoxHover: String = {

      /** Функция вызывается при hover на контейнере разметки
       * - увеличивает до 3-го размера привязанную к разметке литеру
       * - увеличивает до 2-го размера соседние литеры
       * */
      var s = s"""
           |function ${SClass.jsFnMarkBoxHoverN}(num) {
           |  class3SetStyle(getClass(num));
           |  let iUpDown = classUpDown(num);
           |  if(num > 0) class2SetStyle(iUpDown.classUp);
           |  if(num < ${alphabet.length - 1}) class2SetStyle(iUpDown.classDown);
           |}
           |""".stripMargin

      /** Функция вызывается при окончании hover на контейнере разметки
       * - сбрасывает стили привязанной к разметке литеры
       * - сбрасывает стили соседних литер
       * */
      s += s"""
           |function ${SClass.jsFnMarkBoxEndHoverN}(num) {
           |  classResetStyle(getClass(num));
           |  let iUpDown = classUpDown(num);
           |  if(num > 0) classResetStyle(iUpDown.classUp);
           |  if(num < ${alphabet.length - 1}) classResetStyle(iUpDown.classDown);
           |}
           |""".stripMargin

      /** Функция возвращает два идентификатора для верхней и нижней литер
       * по именам классов литер */
      s += s"""
           |function classUpDown(num) {
           |  let classUpName = "${SClass.literaBoxName + "-"}" + (num - 1);
           |  let classDownName = "${SClass.literaBoxName + "-"}" + (num + 1);
           |  let iClUp = document.getElementsByClassName(classUpName);
           |  let iClDown = document.getElementsByClassName(classDownName);
           |  return {
           |    classUp: iClUp[0],
           |    classDown: iClDown[0],
           |  };
           |}
           |""".stripMargin

      /** Функция возвращает идентификатор для литеры привязанной к разметке
       * по имени класса литеры */
      s += s"""
           |function getClass(num) {
           |  let className = "${SClass.literaBoxName + "-"}" + num;
           |  let iClass = document.getElementsByClassName(className);
           |  return iClass[0];
           |}
           |""".stripMargin

      /** Функция выставляет стили 3-го размера для компонента
       * с идентификатором iclName */
      s += s"""
           |function class3SetStyle(iclName) {
           |	  iclName.style.width = "${CSize.sizeLiteraBox3}px";
           |    iclName.style.height = "${CSize.sizeLiteraBox3}px";
           |    iclName.style.fontSize = "${CSize.literaFontSize3}px";
           |    iclName.style.left = "${CSize.leftLiteraBoxHover3}px";
           |    iclName.style.zIndex = 2;
           |}
           |""".stripMargin

      /** Функция выставляет стили 2-го размера для компонента
       * с идентификатором iclName */
      s += s"""
           |function class2SetStyle(iclName) {
           |	  iclName.style.width = "${CSize.sizeLiteraBox2}px";
           |    iclName.style.height = "${CSize.sizeLiteraBox2}px";
           |    iclName.style.fontSize = "${CSize.literaFontSize2}px";
           |    iclName.style.left = "${CSize.leftLiteraBoxHover}px";
           |}
           |""".stripMargin

      /** Функция сбрасывает стили для компонента
       * с идентификатором iclName */
      s += s"""
           |function classResetStyle(iclName) {
           |	  iclName.style.width = null;
           |    iclName.style.height = null;
           |    iclName.style.fontSize = null;
           |    iclName.style.left = null;
           |    iclName.style.zIndex = null;
           |}
           |""".stripMargin
      s
    }

    /** изменение размеров при hover на литере
     * @num - номер текущей литеры */
    def jsLiteraHoverN: String = {
      val s =
        s"""
           |function ${SClass.jsFnLiteraHoverN}(num) {
           |  class3SetStyle(getClass(num));
           |  let iUpDown = classUpDown(num);
           |  if(num > 0) class2SetStyle(iUpDown.classUp);
           |  if(num < ${alphabet.length - 1}) class2SetStyle(iUpDown.classDown);
           |}
           |""".stripMargin
      s
    }

    /** изменение размеров соседних литер при ОКОНЧАНИИ hover на литере
     * @num - номер текущей литеры */
    def jsLiteraEndHoverN: String = {
      val s =
        s"""
           |function ${SClass.jsFnLiteraEndHoverN}(num) {
           |  classResetStyle(getClass(num));
           |  let iUpDown = classUpDown(num);
           |  if(num > 0) classResetStyle(iUpDown.classUp);
           |  if(num < ${alphabet.length - 1}) classResetStyle(iUpDown.classDown);
           |}
           |""".stripMargin
      s
    }


  }

  /** Component Colors - цвета компонента и его составляющих */
  object  CColors {

  }

}

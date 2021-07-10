package siteCreator.component
import siteCreator.HtmlPC

import scala.collection.mutable.ArrayBuffer

/**
 * @author Виталий Антонов @date 6/27/21
 *         kaligraf@yandex.ru
 * */

/**
 * ДЕЛАТЬ:
 * - по количеству тегов определять высоту flex контейнера
 * - по максимально длинному тегу(количество символов) определять ширину контейнеров всех тегов
 * - добавить контейнер заголовка списка
 * - добавить контейнеры, подсказывающие возможность скроллинга списка, снизу и сверху списка
 */



/** Класс позволяет создать вертикальный список с увеличением
 * нескольких элементов при hover
 */
class TagsList(name: String, width: Int, height: Int, left: Int, top: Int)(tags: String*)
  extends Component(name + "TagsList") {

  override def html(page: HtmlPC): Unit = {

    /** html текущей страницы */
    val htmlCod = new HtmlC // HTML код компонента
    /** CSS текущей страницы */
    val cssCod = new CssC // CSS код компонента
    /** JS текущей страницы */
    val jsCod = new JsC  // JS код компонента

    /** Формируем встроенные в компонент элементы */
    componentsCreate

    /** -----------  Формируем HTML  ------------ */

    /** Контейнер заголовка */
    htmlCod ++ CElem.headerTagsList.htmlOpenBoxWithSClass ++ "<span></span>"
      CElem.headerTagsList.htmlCloseBox + enter

    /** Контейнер индикатор скроллинга вверх */
    htmlCod ++ CElem.upScrollIndicator.htmlOpenBoxWithSClass ++ "<span></span>"

    /** - OPEN html контейнер области видимости */
    htmlCod <<< CElem.visibleBox.htmlOpenBoxWithSClass ++ enter

    /** html контейнеров разметки */
    if(tags.nonEmpty){
      for(i <- 0 until tags.length) {
        htmlCod ++ s"""<div class="${CElem.markBoxTab(i).getClassNames}" onmouseover="${SClass.jsFnMarkBoxHoverN}(${name}, ${i})" onmouseout="${SClass.jsFnMarkBoxEndHoverN}(${name}, ${i})"><span></span></div>""" ++ enter
      }
    }

    /** -OPEN html контейнера flexBox */
    htmlCod <<< s"""<div id="${SClass.idFlexVboxName}" class="${SClass.flexVboxName}">\n"""

    /** Контейнеры тегов */
    if(tags.nonEmpty){
      for(i <- 0 until tags.length){
        htmlCod ++ s"""<div class="${CElem.tagBoxTab(i).classes}" onmouseover="${SClass.jsFnLiteraHoverN}(${name}, ${i})" onmouseout="${SClass.jsFnLiteraEndHoverN}(${name}, ${i})">${tags(i)}</div>\n"""
      }
    }

    /** -CLOSE html контейнера flexBox */
    htmlCod >>> CElem.flexBox.htmlCloseBox + enter

    /** - CLOSE html контейнер области видимости */
    htmlCod >>> CElem.visibleBox.htmlCloseBox + enter

    /** ---------------  Формируем CSS  ----------------- */

    /** общий CSS для контейнеров вертикальной разметки - z-index 1 размещает разметку ВЫШЕ флекс бокса */



  }

  /** Встроенные элементы компонента Component Elements */
  object CElem{
    /** Контейнер ЗАГОЛОВОК списка */
    val headerTagsList = new Box(s"${name}-headerTagsListBox", width, 15, top, left)

    /** Контейнер верхнего индикатора скроллинга */
    val upScrollIndicator = new Box(s"${name}-upScrollIndicator", width, 10,
                                    top + headerTagsList.height, left)

    /** контейнер области видимости списка */
    val visibleBox = new Box(s"${name}VisibleBox", width, height,
      upScrollIndicator.top + upScrollIndicator.height , left)

    /** массив контейнеров разметки */
    val markBoxTab: ArrayBuffer[Box] = new ArrayBuffer[Box]()

    /** контейнер flexBox */
    val flexBox = new Box(s"${name}FlexVBox", CSize.widthFlexBox, CSize.heightFlexBox,
      CSize.topFlexBox, 0 )

    /** массив контейнеров тегов */
    val tagBoxTab = new ArrayBuffer[Box]()

  }


  /** Object Selectors Class назначенные имена классов для селекторов и имён функций JS */
  object SClass{

    /** Имена классов для контейнеров вертикальной разметки */
    val markBoxName0 = s"MarkBox"               // имя класса для всех MarkBox на странице
    val markBoxName1 = s"${name}MarkBox"        // все MarkBox -ы компонента

    /** Имена классов контейнера тега */
    val tagBoxName0 = "tagBoxInFlex"            // все на странице
    val tagBoxName1 = s"${name}tagBoxInFlex"    // все для компонента

    /** JS Function Name - Имя функции при hover на контейнере разметки - параметр номер литеры */
    val jsFnMarkBoxHoverN = "jsFnMarkBoxHoverN"
    /** JS Function Name - Имя функции при ОКОНЧАНИИ hover на контейнере разметки - параметр номер литеры */
    val jsFnMarkBoxEndHoverN = "jsFnMarkBoxEndHoverN"

    /** контейнер для литер/тегов с FLEX разметкой */
    lazy val flexVboxName = s"${name}FlexVBox"
    /** id контейнера  с FLEX разметкой */
    lazy val idFlexVboxName = cNameId(s"id${name}FlexVboxName")

    /** JS Function Name - Имя функции при hover на литере - параметр номер литеры */
    val jsFnLiteraHoverN = "jsLiteraHoverN"
    /** JS Имя функции при ОКОНЧАНИИ hover на литере  - параметр номер литеры */
    val jsFnLiteraEndHoverN = "jsLiteraEndHoverN"
  }

  /** Component Size - размеры компонента и его составляющих */
  object CSize {
    /** Заголовок списка */
    val headerTagsListBoxHeight = 15 // высота контейнера заголовка

    /** индикаторы скроллинга */
    val scrollIndicatorHeight = 10 // высота индикаторов скроллинга

    /** контейнеры разметки */
    lazy val widthMarkBox = leftTagBoxHover2 + 2
    lazy val heightMarkBox = heightTagBox0 + gapTagBox
    lazy val topMarkBoxes = 0
    lazy val leftMarkBoxes = 0

    /** вертикальный FLEX контейнер */
    lazy val widthFlexBox = 100
    lazy val heightFlexBox = (tags.size - 2) * (heightTagBox0 + gapTagBox) + heightScaleTagBox2  * 2
    lazy val topFlexBox = topMarkBoxes

    /** координата left контейнеров тегов */
    lazy val leftTagBox = 40
    lazy val leftTagBoxHover1 = 50
    lazy val leftTagBoxHover2 = 60

    /** контейнеры тегов имеют по три размера width и height */
    // ширина контейнера при создании (15)
    lazy val widthTagBox0 = literaFontSize0 * maxLengthTags + 5
    // ширина соседних при hover (50)
    lazy val widthScaleTagBox1 = literaFontSize1 * maxLengthTags + 15
    // ширина выделенного при hover (65)
    lazy val widthScaleTagBox2 = literaFontSize2 * maxLengthTags + 20

    lazy val heightTagBox0 = 18       // высота контейнера при создании
    lazy val heightScaleTagBox1 = 50  // высота соседних при hover
    lazy val heightScaleTagBox2 = 65  // высота выделенного при hover

    /** Зазор по вертикали между контейнерами тегов */
    lazy val gapTagBox = 1

    /** размер шрифта литер имеют по три размера */
    lazy val literaFontSize0 = 13
    lazy val literaFontSize1 = 35
    lazy val literaFontSize2 = 45

    /** Ширина visibleBox */
    lazy val visibleBoxWidth = leftTagBoxHover2 + heightScaleTagBox2 + 1

    /** Максимальная длина тега в пришедшем списке */
    def maxLengthTags: Int = {
      var maxLength = 0
      for(arg <- tags){
        if(arg.length > maxLength) maxLength = arg.length
      }
      maxLength
    }

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
      CElem.visibleBox.cssMapList += (
        ("position", "relative"),
        ("overflow-y", "scroll"),
        ("background-color", CColor.visibleBoxBackgroundColor )
      )
      CElem.visibleBox.CSS
    }

    /** общий CSS для контейнеров вертикальной разметки - z-index 1 размещает разметку ВЫШЕ флекс бокса */
    def cssMarkBoxes: String = {
      lazy val s =
        s"""
           |.${CElem.markBoxTab(0).sClassNames("name1")} {
           |  position: absolute;
           |  width: ${CSize.widthMarkBox}px;
           |  border: 1px dotted ${CColor.markBoxBorderColor};
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
        s""".${CElem.markBoxTab(num).sName} {top: ${CElem.markBoxTab(num).top}px; height: ${CElem.markBoxTab(num).height}px;}"""
      s
    }

    /** CSS hover на контейнере вертикальной разметки
     * окрашивает контейнер разметки */
    def cssMarkBoxesHover(num: Int): String = {
      CElem.markBoxTab(num).cssMapListHover += (
        ("background-color", "#283942")
        )
      CElem.markBoxTab(num).cssHover
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

    /** общий CSS для контейнеров тегов */
    def cssTagBoxes: String = {
      val s =
        s"""
           |.${SClass.tagBoxName1} {
           |  position: relative;
           |  left: ${CSize.leftTagBox}px;
           |  display: flex;
           |  align-content: center;
           |  justify-content: center;
           |  width: ${CSize.widthTagBox0}px;
           |  height: ${CSize.heightTagBox0}px;
           |  font-size: ${CSize.literaFontSize0}px;
           |  border: 1px solid red;
           |  transition: all 50ms ease-in-out;
           |}
           |""".stripMargin
      s
    }

    /** CSS для hover на контейнере ТЕГА */
    def cssLiteraBoxHover: String = {
      val s =
        s"""
           |.${SClass.tagBoxName1}:hover {
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
       * - увеличивает до 2-го размера привязанную к разметке литеру
       * - увеличивает до 1-го размера соседние литеры
       *
       * Аргументы:
       * pref - имя компонента, заданное при создании экземпляра TagList
       * num  - номер тега в списке
       * */
      var s = s"""
                 |function ${SClass.jsFnMarkBoxHoverN}(pref, num) {
                 |  class2SetStyle(getClass(pref, num));
                 |  let iUpDown = classUpDown(pref, num);
                 |  if(num > 0) class1SetStyle(iUpDown.classUp);
                 |  if(num < ${tags.length - 1}) class2SetStyle(iUpDown.classDown);
                 |}
                 |""".stripMargin

      /** Функция вызывается при окончании hover на контейнере разметки
       * - сбрасывает стили привязанной к разметке литеры
       * - сбрасывает стили соседних литер
       * */
      s += s"""
              |function ${SClass.jsFnMarkBoxEndHoverN}(pref, num) {
              |  classResetStyle(getClass(pref, num));
              |  let iUpDown = classUpDown(pref, num);
              |  if(num > 0) classResetStyle(iUpDown.classUp);
              |  if(num < ${tags.length - 1}) classResetStyle(iUpDown.classDown);
              |}
              |""".stripMargin

      /** Функция возвращает два идентификатора для верхней и нижней литер
       * по именам классов литер */
      s += s"""
              |function classUpDown(pref, num) {
              |  let classUpName = "pref + ${SClass.tagBoxName0 + "-"}" + (num - 1);
              |  let classDownName = "pref + ${SClass.tagBoxName0 + "-"}" + (num + 1);
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
              |function getClass(pref, num) {
              |  let className = "pref + ${SClass.tagBoxName0 + "-"}" + num;
              |  let iClass = document.getElementsByClassName(className);
              |  return iClass[0];
              |}
              |""".stripMargin

      /** Функция выставляет стили 2-го размера для компонента
       * с идентификатором iclName */
      s += s"""
              |function class2SetStyle(iclName) {
              |	  iclName.style.width = "${CSize.heightScaleTagBox2}px";
              |    iclName.style.height = "${CSize.heightScaleTagBox2}px";
              |    iclName.style.fontSize = "${CSize.literaFontSize2}px";
              |    iclName.style.left = "${CSize.leftTagBoxHover2}px";
              |    iclName.style.zIndex = 2;
              |}
              |""".stripMargin

      /** Функция выставляет стили 1-го размера для компонента
       * с идентификатором iclName */
      s += s"""
              |function class1SetStyle(iclName) {
              |	  iclName.style.width = "${CSize.widthScaleTagBox1}px";
              |    iclName.style.height = "${CSize.heightScaleTagBox1}px";
              |    iclName.style.fontSize = "${CSize.literaFontSize1}px";
              |    iclName.style.left = "${CSize.leftTagBoxHover1}px";
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
           |function ${SClass.jsFnLiteraHoverN}(pref, num) {
           |  class2SetStyle(getClass(pref, num));
           |  let iUpDown = classUpDown(pref, num);
           |  if(num > 0) class2SetStyle(iUpDown.classUp);
           |  if(num < ${tags.length - 1}) class2SetStyle(iUpDown.classDown);
           |}
           |""".stripMargin
      s
    }

    /** изменение размеров соседних литер при ОКОНЧАНИИ hover на литере
     * @num - номер текущей литеры */
    def jsLiteraEndHoverN: String = {
      val s =
        s"""
           |function ${SClass.jsFnLiteraEndHoverN}(pref, num) {
           |  classResetStyle(getClass(pref, num));
           |  let iUpDown = classUpDown(pref, num);
           |  if(num > 0) classResetStyle(iUpDown.classUp);
           |  if(num < ${tags.length - 1}) classResetStyle(iUpDown.classDown);
           |}
           |""".stripMargin
      s
    }


  }

  /** Component Colors - цвета компонента и его составляющих */
  object  CColor {
    val visibleBoxBackgroundColor = "#182922"

    val markBoxBorderColor = "#808080"

  }


  /** Формируем встроенные в компонент элементы */
  def componentsCreate: Unit = {
    markBoxesCreate /** контейнеры вертикальной разметки - создание списка */
    tagBoxesCreate  /** контейнеры тегов - создание списка */
  }

  /** контейнеры вертикальной разметки - создание списка */
  def markBoxesCreate: Unit = {
    if(tags.nonEmpty) {
      val LenN = tags.length - 1 // константа - номер последнего тега списка(от 0)
      var mbHeight: Int = 0 // Изменяющаяся высота
      var mbTop: Int = 0 // Изменяющаяся top координата

      /** обход пришедших тегов */
      for(i <- 0 until tags.length){

        /** Имя класса с индексом */
        val markBoxName2: String = SClass.markBoxName1 + "-" + i   // MarkBox компонента с индексом

        // Первый и последний разметочные контейнеры больше по высоте
        i match {     // формируем высоты и координату top разметочных контейнеров
          case 0 => mbHeight = CSize.heightScaleTagBox2; mbTop = CSize.topMarkBoxes
          case LenN => {  mbHeight = CSize.heightScaleTagBox2
            mbTop = CSize.topMarkBoxes + CSize.heightScaleTagBox2 + (i - 1) * CSize.heightMarkBox
          }
          case _ => {
            mbHeight = CSize.heightMarkBox
            mbTop = CSize.topMarkBoxes + CSize.heightScaleTagBox2 + (i - 1) * CSize.heightMarkBox
          }
        }
        /** Контейнер разметки с полученными параметрами */
        val markBox = new Box(markBoxName2, CSize.widthMarkBox, mbHeight, mbTop, CSize.leftMarkBoxes)

        /** Имена дополнительных классов контейнера */
        markBox.sClassNames += ("name0" -> SClass.markBoxName0, "name1" -> SClass.markBoxName1)

        /** Добавляем контейнер markBox в список разметочных контейнеров */
        CElem.markBoxTab += markBox
      }
    }
  }

  /** контейнеры тегов - создание списка */
  def tagBoxesCreate: Unit = {
    if(tags.nonEmpty) {
      /** обход пришедших тегов */
      for(i <- 0 until tags.length){
        /** Имя класса контейнера тега */
        val name2 = s"${name}tagBoxInFlex-${i}"

        /** Контейнер тега */
        val tagBox = new Box(name2, CSize.widthTagBox0, CSize.heightTagBox0, 0,  CSize.leftTagBox)

        /** Дополнительные имена классов контейнера
         * name2 задано как имя экземпляра и является основным(не является дополнительным именем) */
        tagBox.sClassNames += (
          "name0" -> SClass.tagBoxName0,
          "name1" -> SClass.tagBoxName1
        )

      }
    }
  }


}

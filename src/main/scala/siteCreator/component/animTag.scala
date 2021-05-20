

package siteCreator.component

import CSSv01.Tags.tagsCSS
import HtmlCom.actColorTheme
import animTags.animationTagGenerator
import siteCreator.HtmlPC

import scala.collection.mutable.ArrayBuffer

/**
 * @author Виталий Антонов @date 5/2/21
 *         kaligraf@yandex.ru
 * */


/**
 * Набор тегов, заключённых в контейнеры, расположенные друг под другом
 * Теги с анимацией при наведении по hover
 * здесь создаём селекторы с координатой top
 */
class animTags(
               /** имя компонента */
               val nameTagsBlock: String = ""
             )(
               /** перечень тегов для размещения */
               tags: String*
              ) extends Component("AnimTag" + nameTagsBlock) {

  /** Ссылки на основные данные класса */
  /** Selectors Class  - перечень классов для селекторов CSS */
  val SClass = OSClass
  /** Component Css  -  определение блоков CSS кода компонента */
  val CCss = OCCss
  /** Component Size - размеры компонента и его составляющих */
  val CSize = new BoxSize
  /** Component Colors - цвета компонента и его составляющих */
  val CColors = OCColors

  var numDefHtml: Int = 0 // счётчик обращений к методу html

  /**
   * @param page имя страницы, которая формируется
   */
  override def html(page: HtmlPC): Unit = {

  }



  /** Счётчик обращений к методу literaTagsBox */
  var literaTagsBoxGoCount: Int = 0

  /**
   * Если список тегов в базе НЕ ПУСТ
   * Функция создаёт контейнер с перечнем тегов, анимируемых по hover для CSS property(свойств CSS)
   * @param page  -  страница, к которой добавляем элементы
   * @param litera   - литера, для которой создаём контейнер с тегами
   * @param className  -  имя класса идентификации контейнера для hover из literaBoxes
   * @param top  - координата top контейнера
   * @param left  - координата left контейнера
   * @return   - количество найденных тегов в базе для изменения цветов контейнера буквы при 0
   */
  def literaTagsBox(page: HtmlPC, litera: Char, className: String, top: Int, left: Int): Int = {
    /** массив тегов для литеры */
    val tags = animTags.tagsForLitera(litera)
    /** Если список тегов НЕ пуст */
    if(tags.nonEmpty){
      /** Размещаем код, который размещается ОДНОКРАТНО */
      if(literaTagsBoxGoCount == 0) {
        addCss(page, CCss.cssTemplate) /** CSS, общий для всех контейнеров с анимированными литерами */
        addCss(page, animationTagGenerator(SClass.allTagsClass, 30, 1500)) /** CSS для анимации букв тега */
      }
      literaTagsBoxGoCount += 1 // увеличиваем счётчик обращений

      addHtml(page, s"""    <div class="${SClass.tagsBoxClass} ${className}">\n""") /** +HTML открытие тега для контейнера */
      addCss(page, CCss.literaTagsBoxCss(className, top, left)) /** CSS контейнера с перечнем тегов литеры */

      /** Изменяющийся от тега к тегу координата top контейнера тега*/
      var tagBoxTop: Int = 0
      /** перебор всех найденных тегов */
      for(i <- tags.indices){

        /** Для каждого найденного в базе тега создаём контейнер,
         * оборачиваем буквы CSS тега в span и добавляем в html */
        addHtml(page,tagInSpan(tags(i)))

        /** Формируем CSS с координатой top */
        tagBoxTop = i * (CSize.boxHeight + CSize.GapY) + CSize.boxTopOffset
        val tagBoxTopCss: String =
          s"""\n.${SClass.tagBoxClassName(tags(i))} {top: ${tagBoxTop}px ;}"""
        addCss(page, tagBoxTopCss) // Добавили CSS с координатой TOP


      }
      addCss(page, "\n") // отступ снизу блока top координат
      addHtml(page, s"""    </div>\n""") /** +HTML закрытие тега для контейнера */
    }

    tags.length // количество найденных тегов в базе для изменения цветов контейнера буквы при 0
  }

  /**
   * Метод создаёт контейнер div и заключает каждый символ тега в "span".
   * Каждому "span" присваивается класс tl0...tlx, где
   * x - количество символов тега минус 1
   * @param tag - Тег, символы которого необходимо обернуть в "span"
   * @return - текст для вставки в буфер html
   */
  def tagInSpan(tag: String): String = {
    var s: String = s"""      <div class="${SClass.tagBoxClassName(tag)} ${SClass.allTagsClass}">\n  """
    s += "      " // пробелы для выравнивания строки
    for(i <- 0 until tag.length){
      s += s"""<span class="tl${i.toString}">${tag(i)}</span> """
    }
    s += s"""\n      </div>\n"""
    s
  }

  /** Метод позволяет сменить настройки цвета для контейнеров анимированных тегов */
  def tagBoxSetColor(
                      tagBoxBgColorNew: String = actColorTheme.tagBoxBgColor ,
                      tagBoxTextColorNew: String = actColorTheme.tagBoxTextColor,
                      tagBoxBgTextColorNew: String = actColorTheme.tagBoxBgTextColor,
                      tagBoxBorderColorNew: String = actColorTheme.tagBoxBorderColor,
                      tagBoxBgHoverColorNew: String = actColorTheme.tagBoxBgHoverColor,
                      tagBoxTextHoverColorNew: String = actColorTheme.tagBoxTextHoverColor,
                      tagBoxBorderHoverColorNew: String = actColorTheme.tagBoxBorderHoverColor
                    ) ={
    CColors.tagBoxBgColor = tagBoxBgColorNew
    CColors.tagBoxTextColor = tagBoxTextColorNew
    CColors.tagBoxBgTextColor = tagBoxBgTextColorNew
    CColors.tagBoxBorderColor = tagBoxBorderColorNew
    CColors.tagBoxBgHoverColor = tagBoxBgHoverColorNew
    CColors.tagBoxTextHoverColor = tagBoxTextHoverColorNew
    CColors.tagBoxBorderHoverColor = tagBoxBorderHoverColorNew
  }

  /** Component Colors - цвета компонента и его составляющих */
  object OCColors{
    /** ====================== Цветовая схема ================================= */
    /** Цвет фона боксов анимированных по hover тегов */
    var tagBoxBgColor: String = actColorTheme.tagBoxBgColor
    /** Цвет текста анимированных по hover тегов */
    var tagBoxTextColor: String = actColorTheme.tagBoxTextColor
    /** Цвет фона под символами анимированного тега */
    var tagBoxBgTextColor: String = actColorTheme.tagBoxBgTextColor
    /** Цвет бордюра боксов анимированных по hover тегов */
    var tagBoxBorderColor: String = actColorTheme.tagBoxBorderColor
    /** Цвет фона боксов при hover */
    var tagBoxBgHoverColor: String = actColorTheme.tagBoxBgHoverColor
    /** Цвет текста тегов при hover */
    var tagBoxTextHoverColor: String = actColorTheme.tagBoxTextHoverColor
    /** Цвет бордюра боксов при hover */
    var tagBoxBorderHoverColor: String = actColorTheme.tagBoxBorderHoverColor
    /** ======================================================================= */
  }

  /** Object Selectors Class назначенные имена классов для селекторов */
  object OSClass{
    /** Формирование имени класса для селектора контейнера(div) тега. Пример: helpCssTags-box-tag-align-content */
    def tagBoxClassName(tag: String): String = {s"${nameTagsBlock}-box-tag-${tag}"}

    /** имя класса для всех сформированных контейнеров(div) с анимированными литерами. Пример helpCssTags-all-boxes */
    def allTagsClass: String = s"${nameTagsBlock}-all-boxes"

    /** Общее имя класса для всех контейнеров любой литеры. Пример: tags-box  */
    def tagsBoxClass = "tags-box"

  }

  /** transition: transition-property transition-duration transition-timing-function transition-delay; */

  /** Component CSS - CSS код, используемый для формирования компонента на странице */
  object OCCss {
    /** ----------------------------------------------------------------------- */
    /** CSS для общего контейнера тегов литеры */
    def literaTagsBoxCss(className: String, top: Int, left: Int): String =
      s"""
         |.${className} {
         |  position: absolute;
         |  top: ${top}px;
         |  left: ${left}px;
         |  width: 0px;
         |  height: 600px;
         |  border-radius: 8px;
         |  background-color: #384952;
         |  transition: all 200ms linear 0ms;
         |}
         |
         |.${className}:hover {
         |  width: 600px;
         |}
         |""".stripMargin
    /** ----------------------------------------------------------------------- */

    /** CSS однократно размещаемый для всех тегов CSS Property */
    def cssTemplate: String = {
      var s = ""
      /** Селектор для всех контейнеров тегов до их прорисовки */
      s +=
        s""".${SClass.allTagsClass} {
           |  position: absolute;
           |  display: flex;
           |  align-items: center;
           |  justify-content: center;
           |  width: ${CSize.boxWidth0}px;
           |  height: ${CSize.boxHeight}px;
           |  color: ${CColors.tagBoxTextColor};
           |  background-color: ${CColors.tagBoxBgColor};
           |  border-radius: ${CSize.boxBorderRadius}px;
           |  font-size: ${CSize.boxFontSize0}px;
           |  left: ${CSize.boxLeftOffset}px;
           |  margin-top: ${CSize.GapY}px;
           |  ${CSize.boxTransition}
           |  user-select: none;
           |  cursor: pointer;
           |}
           |""".stripMargin
      /** Селектор для контейнеров тегов при hover */
      s +=
        s"""
           |.${SClass.allTagsClass}:hover {
           |  z-index: 1;
           |  height: 70px;
           |  transform: matrix(1.3, 0, 0, 1, 50, -20);
           |  color: rgb(255, 221, 169);
           |}
           |
           |""".stripMargin
      s
    }
    /** ----------------------------------------------------------------------- */

    /** CSS для развёртки контейнеров с анимированными тегами при hover на литере */
    def cssForHoverVisualTagsBoxes(literaClass: String, tagsBoxClass: String): String = {
      var s = ""
      s +=  // Развёртка тегов литеры при hover на литере
        s"""
           |.${literaClass}:hover ~ .${tagsBoxClass} .${SClass.allTagsClass} {
           |  width: ${CSize.boxWidth}px;
           |  font-size: 18px;
           |  border: 1px solid rgb(101, 115, 53);
           |  padding: 0px 10px;
           |}
           |
           |.${tagsBoxClass}:hover .${SClass.allTagsClass} {
           |  width: ${CSize.boxWidth}px;
           |  border: 1px solid green;
           |  padding: 0px 10px;
           |  font-size: 18px;
           |}
           |""".stripMargin
      s
    }
    /** ----------------------------------------------------------------------- */

  }

}
object animTags{
  def apply(nameTagsBlock: String = "")(tags: String*) = {
    new animTags(nameTagsBlock)(tags: _*)
  }

  /** Генерация массива с тегами(Property) для одного символа алфавита из базы тегов CSS */
  def tagsForLitera(litera: Char): Array[String] = {
    val s = ArrayBuffer[String]()
    for(i <- tagsCSS.pro.indices){          // Перебор всех массивов базы
      if(tagsCSS.pro(i)(0)(0) == litera){   // Соответствие первой буквы первого элемента массива аргументу
        s += tagsCSS.pro(i)(0)
      }
    }
    s.toArray
  }

  /**
   * Функция генерирует CSS, необходимый для анимации тега CSS
   * @param classNameBox  -  имя класса, используемое для контейнеров тегов
   * @param litersQua  -  количество анимируемых литер
   * @param timeMs     -  общее время цикла анимации, миллисекунд
   * @return           -  CSS, который ОДНОРАТНО включаем в файл стилей
   */
  def animationTagGenerator(classNameBox: String, litersQua: Int, timeMs: Int): String ={
    var s = ""
    val added = timeMs / litersQua // надбавка к задержке каждой литеры
    var delay: Int = 0             // задержка начала анимации для каждой литеры своя
    for(i <- 1 to litersQua){
      s += s""".${classNameBox}:hover :nth-child(${i}){animation: pn-scale ${timeMs}ms ease-in-out ${delay}ms infinite alternate none;}\n"""
      delay += added
    }
    s += s"""
         |@keyframes pn-scale {
         |  50%{
         |    font-size: 35px;
         |    background-color:  red;
         |  }
         |  100%{
         |    font-size: 8px;
         |  }
         |}
         |""".stripMargin
    s
  }

}

object testerAnimTag{
  val a = animTags("litera-a")()
  println(a.tagInSpan("animation-timing-function"))
  println(a.SClass.allTagsClass)
  println(a.SClass.tagBoxClassName("align-items"))
  println(a.CCss.cssTemplate)

  println(animTags.tagsForLitera('a').mkString("\n"))

}




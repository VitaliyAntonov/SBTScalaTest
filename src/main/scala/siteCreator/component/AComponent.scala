package siteCreator.component

import siteCreator.HtmlPC


/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */



/** Перечень ссылок на компоненты */
object HtmlCom extends Enumeration {


  /** Здесь можно изменить тему, передав в RootCss другое имя темы, например colorThemeWhite */
  lazy val actColorTheme = ColorTheme.colorBasicTheme
  lazy val root = new RootCss(actColorTheme)

  /** Пробный шаблон страницы */
  lazy val pageTemplate = new HtmlPage("TemplateTest")(Divs.divBox, Divs.divBox)

  /** ----------------------------------------------------------------------- */
  /** Шаблон страницы помощи по CSS тегам */
  lazy val helpCss = new HtmlPage("helpCss")(
    /** создание контейнеров букв алфавита */
//    literaBoxes // Контейнеры букв алфавита
//    tabPanel  // панель переключаемых вкладоок
    listSAB // Вертикальный список с увеличением масштаба Scale Alphabet
//    winVisible
  )

  lazy val winVisible = new WindowVisible("winV")()("abcdefghijklmnopqrstuvwxyz")

  /** Вертикальный список с увеличением масштаба Scale Alphabet */
  lazy val listSAB = new ListScale("sab")()("abcdefghijklmnopqrstuvwxyz")

  /** Панель переключаемых вкладоок */
  lazy val tabPanel = new SelectPanels("headPBox")("CSS","HTML","JS")

  /** Контейнеры букв алфавита для страницы helpCss анимированных тегов */
  lazy val literaBoxes = new literaBoxes

  /** Анимированные теги для страницы helpCss */
  lazy val aTags = new animTags("helpCssTags")()
  /** ----------------------------------------------------------------------- */

}

/**
 * Класс позволяет создавать конкретные типы компонентов в HTML, CSS и JS файлах
 * @param componentName  Имя компонента
 */
abstract class Component(val componentName: String) extends Enumeration with Sizes {
  /** ============================ */
  /** ABSTRACT
   * Метод строит HTML шаблон компонента и добавляет шаблоны компонента в CSS и JS файлы
   * @param page имя страницы, которая формируется
   */
  def html(page: HtmlPC)
  /** ============================ */
  /**
   * добавление компонента в буфер страницы
   * @param page - буферы создаваемой HTML страницы
   * @param x - стринг HTML, который необходимо добавить
   */
  def addHtml(page: HtmlPC, x: String) = {
    page.htmlFile.html += x
  }
  /** ============================ */
  /**
   * Добавление CSS шаблона компонента в буфер страницы
   * @param page  - буферы создаваемой HTML страницы
   * @param x - стринг CSS, который необходимо добавить
   */
  def addCss(page: HtmlPC, x: String) = {
    page.cssFile.css += x
  }
  /** ============================ */
  /**
   * Добавление JS шаблона компонента в буфер страницы
   * @param page  - буферы создаваемой HTML страницы
   * @param x - стринг JS, который необходимо добавить
   */
  def addJs(page: HtmlPC, x: String) = {
    page.jsFile.js += x
  }
  /** ============================ */
  /**
   * Добавление массива вложенных компонентов в буфер страницы.
   * Для каждого компонента вызываем его метод html.
   * @param page  - буферы создаваемой HTML страницы
   * @param args - массив компонентов, которые необходимо добавить
   */
  def addArgs(page: HtmlPC)(args: Component*) = {
    if (args.nonEmpty) {
      for(i <- 0 until args.length) args(i).html(page)
    }
  }
  /** =================================================== */
  /**
   * Добавление массива свойств CSS с селектором класса
   * @param page - буферы создаваемой HTML страницы
   * @param componentName - имя компонента = селектору CSS класса
   * @param CssProperty - массив свойств CSS к селектору класса
   */
  def addCssClassProperty(page: HtmlPC, componentName: String)(CssProperty: String*) = {
    if (CssProperty.nonEmpty) {
      page.cssFile.css += s"\n.$componentName {"
      for(i <- 0 until CssProperty.length) page.cssFile.css += "\n  " + CssProperty(i)
      page.cssFile.css += s"\n}\n"
    }
  }
  /** =================================================== */


  /** Генерация ID в виде componentName + ID */
  def componentId = {
    lazy val x = Value(componentName)
    x.toString + x.id
  }
  /** Генерация ID в виде name + ID */
  def cNameId(name: String = "") = {
    val x = Value(name)
    x.toString + x.id
  }
  /** Множество имён класса */
  def cNames(overClass1: String = "") = {
    componentName + " " + overClass1
  }

  /** Enter */
  def enter: String = "\n"

  /** Добавляем к имени компонента имя вложенного под-компонента */
  def cNamePlus(plus: String) = componentName + plus

  /** Замена символа - на символ _ в тегах CSS */
  def nameToScala(name: String): String = {
    var s = ""
    for(i <- 0 until name.length){
      val sim = name.slice(i, i+1)
      if(sim != "-") s += sim
      else s += "_"
    }
    s
  }

}




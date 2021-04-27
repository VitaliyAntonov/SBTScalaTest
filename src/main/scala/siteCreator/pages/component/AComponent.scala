package siteCreator.pages.component

import siteCreator.pages._
import siteCreator.pages.component.Divs._


/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */



/** Перечень ссылок */
object HtmlCom extends Enumeration {
  lazy val fixButton = new FixButton("Test")

  /** Здесь можно изменить тему, передав в RootCss другое имя темы, например colorThemeWhite */
  lazy val actColorTheme = ColorTheme.colorBasicTheme
  lazy val root = new RootCss(actColorTheme)

  /** Пробный шаблон страницы */
  lazy val pageTemplate = new HtmlPage("TemplateTest")(divBox, divBox)
}

/**
 * Класс позволяет создавать конкретные типы компонентов в HTML, CSS и JS файлах
 * @param componentName  Имя компонента
 */
abstract class Component(val componentName: String) extends Enumeration {
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
  def addHml(page: HtmlPC, x: String) = {
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
    lazy val x = Value(name)
    x.toString + x.id
  }
  /** Множество имён класса */
  def cNames(overClass1: String = "") = {
    componentName + " " + overClass1
  }

  /** Добавляем к имени компонента имя вложенного под-компонента */
  def cNamePlus(plus: String) = componentName + plus


}




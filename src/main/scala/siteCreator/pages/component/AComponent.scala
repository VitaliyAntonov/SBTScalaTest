package siteCreator.pages.component

import siteCreator.pages.HtmlPC


/** Перечень ссылок на компоненты */
object HtmlCom extends Enumeration {
  lazy val fixButton = new FixButton("Test")
  lazy val divBox = new div("Box")()
  /** Можно добавить новую тему, переопределив класс ColorTheme */
  lazy val colorBasicTheme = new ColorBasicTheme
  /** Здесь можно изменить тему, передав в RootCss другое имя темы */
  lazy val root = new RootCss(colorBasicTheme)
  /** Пробный шаблон страницы */
  lazy val pageTemplate = new HtmlPage("TemplateTest")(divBox, divBox)
}

/**
 * Класс позволяет создавать конкретные типы компонентов в HTML, CSS и JS файлах
 * Каждый новый компонент - это метод соответствующего класса
 * @param componentName  Имя компонента
 */
abstract class Component(val componentName: String) extends Enumeration {
  /** ============================ */
  /** ABSTRACT
   * Метод строит HTML шаблон компонента и добавляет свои шаблоны в CSS и JS файлы
   * @param page
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


//  /** Добавляем HTML шаблон. CSS и JS добавляются внутри компонентов */
//  def addComponent(x: Component) = {
//    HtmlPC.Page.htmlFile.html += x.htmlTemplate
//  }
//  /** Функция вызывается из компонентов, для добавления частей шаблонов */
//  def addHtmlSequence(x: String) = {
//    HtmlPC.Page.htmlFile.html += x
//  }
//  def addCssSequence(x: String) = {
//    HtmlPC.Page.cssFile.css += x
//  }

}




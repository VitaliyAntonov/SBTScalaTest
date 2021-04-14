package siteCreator.pages

import siteCreator.pages.component.{Component, HtmlCom}

/**
 * Класс для создания HTML страницы
 *
 * @param filename - имя html страницы
 */
class HtmlFile(val fileName: String) {var html: String = ""}
/**
 * Класс для создания CSS файла, связанного с html страницей
 * @param filename - имя CSS файла
 */
class CssFile(val fileName: String) {var css: String = ""}
/**
 * Класс для создания JS файла, связанного с html страницей
 * @param filename - имя JS файла
 */
class JsFile(val fileName: String) {var js: String = ""}

/**
 * Класс для создания 3-х файлов: CSS, JS и HTML одной страницы. (HtmlPageCreator)
 * @param namePage - имя страницы
 */
class HtmlPC(val namePage: String){
  val htmlFile = new HtmlFile(namePage)

  val cssFileName = namePage + "style"
  val cssFile = new CssFile(cssFileName)

  val jsFileName = namePage + "script"
  val jsFile = new JsFile(jsFileName)

  /**
   * Добавление компонента в буфер
   * @param x Добавляемый в буфер компонент
   * @return
   */
  def +(x: Component) = {x.html(this); this}


}



/** Тестирование класса создания файлов HTML страницы */
object testPC{
  /** Тестовая страница сайта */
  val page = new HtmlPC("myPageTest")
  page + HtmlCom.pageTemplate


  println(page.namePage)
  println(page.htmlFile.html)

  println(page.cssFileName)
  println(page.cssFile.css)

  println(page.jsFileName)
  println(page.jsFile.js)


}



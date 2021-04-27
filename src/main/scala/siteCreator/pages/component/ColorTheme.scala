package siteCreator.pages.component

import java.awt.Color


/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */


abstract class ColorTheme{
  def toRGB(x: Color): String = {s"""rgb(${x.getRed}, ${x.getGreen}, ${x.getBlue})"""}
  def toHex(x: Color): String = {
    val watch = x.getRGB // Здесь байт прозрачности первый
    var a = ((watch >>> 24) | ((watch & 0xFFFFFF) << 8)).toHexString // Ставим прозрачность последним байтом
    val i = 8 - a.length
    if(i > 0) for(k <- 0 until i) { a = "0" + a } // Дополняем нулями до 8-ми знаков
    "#" + a
  }

  lazy val white = new Color(0xFFFFFF)
  lazy val black = new Color(0x000000)
  lazy val red = new Color(0xFF0000)
  lazy val orange = new Color(0xFFB100)
  lazy val yellow = new Color(0xFFF401)
  lazy val green = new Color(0x00FF00)
  lazy val bluelite = new Color(0x05ECFF)
  lazy val blue = new Color(0x0000FF)
  lazy val purple = new Color(0xC100FF)
  lazy val gray = new Color(180, 180, 180)
  lazy val myCol = new Color(0x010000)

  def bodyBgColor: String
  def bodyTextColor: String
}

/**  */
object ColorTheme {
  /** Можно добавить новую тему, переопределив класс ColorTheme */
  lazy val colorBasicTheme = new ColorBasicTheme
  lazy val colorThemeWhite = new ColorThemeWhite
}


/** Базовая цветовая схема - для добавления темы создать новый класс, наследующий ColorTheme
 * ссылку на новую тему добавить в объект HtmlCom */
class ColorBasicTheme extends ColorTheme{

  /** Цвет фона страницы */
  override def bodyBgColor: String = toRGB(black)
  /** Основной цвет текста страницы */
  override def bodyTextColor = toRGB(white)

}

/** Светлая тема */
class ColorThemeWhite extends ColorTheme{

  /** Цвет фона страницы */
  override def bodyBgColor: String = toRGB(white)
  /** Основной цвет текста страницы */
  override def bodyTextColor = toRGB(black)

}



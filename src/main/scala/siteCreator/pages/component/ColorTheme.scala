package siteCreator.pages.component

import java.awt.Color

abstract class ColorTheme {
  def toRGB(x: Color): String ={s"""rgb(${x.getRed}, ${x.getGreen}, ${x.getBlue})"""}

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

  def bodyBackgroundColor: Color
  def bodyTextColor: Color
}


/** Базовая цветовая схема - для добавления темы создать новый класс, наследующий ColorTheme
 * ссылку на новую тему добавить в объект HtmlCom */
class ColorBasicTheme extends ColorTheme{

  /** Цвет фона страницы */
  override def bodyBackgroundColor = black
  /** Основной цвет текста страницы */
  override def bodyTextColor = white

}





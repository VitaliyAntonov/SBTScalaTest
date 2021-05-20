package siteCreator.component

import java.awt.Color


/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */


abstract class ColorTheme{
  /** Цвет в CSS формат RGB */
  def toRGB(x: Color): String = {s"""rgb(${x.getRed}, ${x.getGreen}, ${x.getBlue})"""}
  /** Цвет в CSS формат HexA с указанием прозрачности - длина 4 байта */
  def toHexA(x: Color): String = {
    val watch = x.getRGB // Здесь байт прозрачности первый
    var a = ((watch >>> 24) | ((watch & 0xFFFFFF) << 8)).toHexString // Ставим прозрачность последним байтом
    val i = 8 - a.length
    if(i > 0) for(k <- 0 until i) { a = "0" + a } // Дополняем нулями до 8-ми знаков
    "#" + a
  }
  /** Цвет в CSS формат Hex - длина 3 байта */
  def toHex(x: Color): String = {
    val watch = x.getRGB // Здесь байт прозрачности первый
    var a = (watch & 0xFFFFFF).toHexString // Убираем прозрачность
    val i = 6 - a.length
    if(i > 0) for(k <- 0 until i) { a = "0" + a } // Дополняем нулями до 6-ти знаков
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
  /** Здесь Можно добавить новую тему */
  lazy val colorBasicTheme = new ColorBasicTheme
  lazy val colorThemeWhite = new ColorThemeWhite
}


/** Базовая цветовая схема - для добавления темы создать новый класс, наследующий ColorTheme
 * ссылку на новую тему добавить в объект HtmlCom */
class ColorBasicTheme extends ColorTheme{

  /** Цвет фона страницы */
  override def bodyBgColor: String = toHex(black)
  /** Основной цвет текста страницы */
  override def bodyTextColor = toHex(white)

  /** ================================================ */
  /** Цвет фона боксов анимированных по hover тегов */
  def color1d2731 = new Color(0x1d2731)
  def tagBoxBgColor: String = toHex(color1d2731)

  /** Цвет текста анимированных по hover тегов */
  def colorFdc879 = new Color(0xfdc879)
  def tagBoxTextColor: String = toHex(colorFdc879)

  /** Цвет фона под символами анимированного тега */
  def tagBoxBgTextColor: String = toHex(red)

  /** Цвет бордюра боксов анимированных по hover тегов */
  def color6df58d = new Color(0x6df58d)
  def tagBoxBorderColor: String = toHex(color6df58d)

  /** Цвет фона боксов при hover */
  def color1D2833 = new Color(0x1D2833)
  def tagBoxBgHoverColor: String = toHex(color1d2731)

  /** Цвет текста тегов при hover */
  def colorFED599 = new Color(0xFED599)
  def tagBoxTextHoverColor: String = toHex(colorFED599)

  /** Цвет бордюра боксов при hover */
  def colorB0FCC1 = new Color(0xB0FCC1)
  def tagBoxBorderHoverColor: String = toHex(colorB0FCC1)
  /** ================================================== */



}

/** Светлая тема */
class ColorThemeWhite extends ColorTheme{

  /** Цвет фона страницы */
  override def bodyBgColor: String = toHex(white)
  /** Основной цвет текста страницы */
  override def bodyTextColor = toHex(black)

}

object testerColorTheme{
  val x = ColorTheme.colorBasicTheme.bodyBgColor
  println(x)
  println(ColorTheme.colorBasicTheme.bodyTextColor)
}




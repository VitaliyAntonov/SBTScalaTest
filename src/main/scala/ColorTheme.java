
/**
 * @author Виталий Антонов @date 12.03.21
 *
 * Цвета проекта Altius
 *
 **/


import java.awt.Color;

public abstract class ColorTheme extends Color{
  /** при наследовании затребован конструктор **/
  public ColorTheme(int r, int g, int b) {
    super(r, g, b);
  }

  /** Основной фон **/
  static Color backBaseColor = new Color(33,41,44);
  /** Основной цвет текста **/
  static Color textBaseColor = white;

  /** Меню **/
  /** Фон неактивных ссылок меню **/
  static Color linkMenuBackColor = new Color(0x384952);
  /** Текст неактивных ссылок меню **/
  static Color linkMenuTextColor = new Color(0xCDD1D4);
  /** Фон наведённых(hover) ссылок меню **/
  static Color linkHoverMenuBackColor = new Color(0x29363C);
  /** Текст наведённых(hover) ссылок меню **/
  static Color lincHoverMenuTextColor = new Color(0x00F8E0);
  /** Фон вкладок(панелей) меню **/


}







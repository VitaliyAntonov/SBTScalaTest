package workSxfRsc.HeaderTables

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/6/21
  **/
class Ht8DisplayParam {

}

/** ------------------------------------------------------------- */


/** 8 Структура Таблицы параметров экрана. Префикс 0x00524150 .PAR */
object Ht8DisplayParam{

  /** Длина записи */
  val recordSize = TabOFL(0, 4)  // В байтах, с учетом длины кодов семантик

  /** Внутренний код объекта */
  val innerCode = TabOFL(4, 2)  // Порядковый номер объекта

  /** Номер функции отображения */
  val primitiveType = TabOFL(6, 2) // Код типа примитива.

  /** Параметры отображения. Длина определяется по типу примитива primitiveType */
  val visualParameters = TabOFL(8, 0)  // Параметры примитива соответствующие типу. Приложение А
}


/** ============================================================= */
/**    Приложение А Типы графических примитивов и их параметры    */

/** Цвет графических примитивов может задаваться двумя способами:
  * цветом в формате RGB либо номером цвета в палитре классификатора.
  * Если цвет выбирается из палитры, его значение записывается
  * в  следующем  виде: 0x0F0000XX,
  * где XX – номер цвета в палитре (с 0).
  * В формате RGB значение записывается в виде 0x00RRGGBB.
  *
  * Типы примитивов:
  * 1. Простая линия, код типа примитива  128            class  Gr1Line
  * 2. Пунктирная линия, код типа примитива  129         class  Gr2DotLine
  * 3. Смещенный пунктир, код типа примитива  148               Gr3DisDotLine
  * 4. Площадь, код типа примитива  135                         Gr4Area
  * 5. Штрихованная площадь, код типа примитива  153            Gr5ShadedArea
  * 6. Точечный объект, код типа примитива  143                 Gr6PointSprite
  * 7. Площадь, заполненная знаками, код типа примитива  144    Gr7AreaWithSprites
  * 8. Окружность, код типа примитива  140                      Gr8Circle
  * 9. Освещение участка, код типа примитива  154               Gr9LightingArea
  * 10. Векторный знак, код типа примитива  149                 Gr10VectorSymbol
  * 11. Площадь, заполненная векторными знаками, код 155
  * 12. Декорированная линия, код типа примитива  157
  * 13. Текст, код типа примитива  142
  * 14. Шрифт пользователя, код типа примитива  152
  * 15. Шаблон, код типа примитива  150
  * 16. Знак True-Type шрифта, код типа примитива  151
  * 17. Набор примитивов, код типа примитива  147
  * 18. Наборная штриховая линия, код типа примитива  158
  * 19. Точечный знак _ графическое изображение, код типа примитива  165
  * 20. Объект пользователя, код типа примитива  250
  * */









/** -------------------------------------------------- **/



/** ============================================================= */












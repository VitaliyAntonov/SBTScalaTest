package workSxfRsc.Graphic

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/7/21
  **/




class Gr12DecoratedLine {

}





/** 12. Декорированная линия, код типа примитива  157
  * Служит для создания сложных изображений линейных объектов,
  * таких как линии электропередач, водопроводы и т.д. */
object Gr12DecoratedLine {

  /** Длина параметров. В байтах (N1) **/
  val tParamLength = TabOFL(0, 4)

  /** Длина, занимаемая знаком  в начале линии. В микронах **/
  val tSymLineStartLength = TabOFL(4, 4)

  /** Длина, занимаемая знаком в конце линии. В микронах **/
  val tSymLineEndLength = TabOFL(8, 4)

  /** Длина, занимаемая знаком в начале отрезка. В микронах **/
  val tSymLineSegStartLength = TabOFL(12, 4)

  /** Длина, занимаемая знаком в конце отрезка. В микронах **/
  val tSymLineSegEndLength = TabOFL(16, 4)

  /** Длина, занимаемая первым заполняющим знаком. В микронах **/
  val tSymFirstLength = TabOFL(20, 4)

  /** Длина, занимаемая вторым заполняющим знаком. В микронах **/
  val tSymSecondLength = TabOFL(24, 4)

  /** Флаг равномерного размещения заполняющих знаков.  В микронах **/
  val tSymbolPlaceFlag = TabOFL(28, 1)

  /** Резерв 0 **/
  val noUsing = TabOFL(32, 3)

  /** ---------------    Примитивы     --------------  */

  /** базовая линия **/
  val tBaseLine = TabGP(
    TabOFL(36, 2),  /** Длина параметров базовой линии. Длина параметров в байтах + 4 **/
    TabOFL(38, 2),  /** Номер примитива базовой линии. Любой линейный примитив **/
    TabOFL(40, 0)   /** Параметры по типу примитива **/
  )
  /** ============== */

  /** Символ, начинающий линию */
  val tStartLineSymbol = TabGP(
    TabOFL(0, 2),
    TabOFL(2, 2),  /** Номер примитива. 0 или 149 **/
    TabOFL(4, 0)
  )
  /** ============== */

  /** Символ, заканчивающий линию */
  val tEndLineSymbol = TabGP(
    TabOFL(0, 2),
    TabOFL(2, 2),  /** Номер примитива. 0 или 149 **/
    TabOFL(4, 0)
  )
  /** ============== */

  /** Символ на точке метрики СЛЕВА */
  val tMPLeftSymbol = TabGP(
    TabOFL(0, 2),
    TabOFL(2, 2),  /** Номер примитива. 0 или 149 **/
    TabOFL(4, 0)
  )

  /** ============== */

  /** Символ на точке метрики СПРАВА */
  val tMPRightSymbol = TabGP(
    TabOFL(0, 2),
    TabOFL(2, 2),  /** Номер примитива. 0 или 149 **/
    TabOFL(4, 0)
  )
  /** ============== */

  /** Первый символ, заполняющий отрезки метрики */
  val tMSegFirstFillSymbol = TabGP(
    TabOFL(0, 2),
    TabOFL(2, 2),  /** Номер примитива. 0 или 149 **/
    TabOFL(4, 0)
  )
  /** ============== */

  /** ВТОРОЙ символ, заполняющий отрезки метрики */
  val tMSegSecondFillSymbol = TabGP(
    TabOFL(0, 2),
    TabOFL(2, 2),  /** Номер примитива. 0 или 149 **/
    TabOFL(4, 0)
  )
  /** ============== */


  /** Итого:   N1 **/
}


/**
  * Примечания:
  * 1.  Для линии, у которой векторные знаки расположены в начале и конце
  *     каждого отрезка, заполните параметры примитивов для векторного отрезка.
  *     Такие линии используются, например, для изображения линий   электропередач.
  *
  *     Если начало или конец линии объекта должны быть выделены (мост, туннель),
  *     заполните параметры примитивов для начало или конца линии.
  *
  *     Для объектов расположенных равномерно вдоль линии введите параметры
  *     заполняющих объектов. Можно ввести как один, так и два
  *     чередующихся между собой объекта, находящихся  на заданном расстоянии
  *     друг от друга (используется для изображения нефтепроводов, линий  связи, насыпей).
  *
  *     Все остальные примитивы должны иметь номер примитива 127 – пустой примитив,
  *     Длина параметров 4 байта. Параметров для пустого примитива нет.
  */





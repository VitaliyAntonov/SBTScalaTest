package CSSv01.Tags





/**
 * Иерархия CSS тегов
 */

abstract class RulesCSS

/** ----------   Верхние трейты  ----------------- */
trait NoneTags extends RulesCSS {
  var order: String = "{"
  protected var row = ""
  /** Здесь после закрытия ордера */
  protected def hearNoneTags: this.type = { this}
}
trait EndOrder extends NoneTags {
  /** здесь ждём тег endOrder */
  protected def hearAlignEndOrder = {order += "" ; this}
  def endOrder = {order += "}"; hearNoneTags}
}
trait RowTags extends EndOrder{
  /** Здесь ждём символ <| окончание строки  */
  protected def hearEnter = {this}
  /** Окончание строки */
  def <| ={order += row + ";\n" ; row = ""; this}
}

/** --------------  Иерархия классов  -----------------  */
/**
 * Иерархия классов - определяет последовательность
 * ввода тегов в цепочке методов
 */

/** После закрытия ордера */
class AfterEndOrder extends RulesCSS with NoneTags
/** Буфер ордера */
class IOrder extends AfterEndOrder with EndOrder{
  override def toString = order
}
/** Буфер строки. Enter в ордере */
class Row extends IOrder with RowTags
/** Атрибут - завершение строки */
class Attribute extends Row with AlignAttribute
/** Свойство - начало строки */
class Property extends Attribute with AlignProperty
/** Здесь создаём новую строку и новый ордер */
class OrderRow extends Property{
  override def toString = row
}





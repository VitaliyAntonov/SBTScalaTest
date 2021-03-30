package CSSv1





import CSSv1.Tags.{AlignOrder, AlignVRules}

import scala.language.postfixOps /** постфиксная нотация **/


/** обощающий класс для всего пакета CSS */
abstract class CSS

/** трейт для значений общего доступа класса Order **/
trait OrderVRules extends CSS with AlignVRules{
  /** Строка(буфер) для формирования ордера  */

  var thisType:String = ""
  var nextType:String = ""
  /** Типы всех элементов строк CSS */

  val CSSNewOrder = "CSSNewOrder"     /** Начало ордера */
  val CSSNewRow = "CSSNewRow"         /** Начало строки */
  val CSSEndRow = CSSNewRow           /** Окончание строки */
  val CSSEndOrder = "CSSEndOrder"     /** Окончание ОРДЕРА */
  val CSSProperty = "CSSProperty"     /** Свойства - пишутся в начале строки - после них двоеточие */
  val CSSAttribute = "CSSAttribute"   /** Атрибуты - теги пишутся после свойств - после них точка с запятой */
  val CSSValue = "CSSValue"           /** Числа - пишутся после свойств - после них есть или нет единицы измерения */
  val CSSDefVar = CSSProperty         /** Определение константы - пишется в начале строки */
  val CSSUseVar = "CSSUseVar"         /** Использование константы - пишется после нескольких видов тегов */
}

/** Трейт служит корнем для всех CSS тегов */
trait ReferencesCSS extends CSS with AlignOrder

/** Order Формирует несколько строк CSS кода, заключённые в фигурные скобки */
class Order(var order:String = "") extends CSS with OrderVRules with ReferencesCSS{
//  override var order = ""
  override def toString: String = order

  /** Метод проверяет пришедшие теги CSS */
  def tagSelect(tag: String, thisT: String, nextT: String) = {
    if(nextType != thisT) throw new NoSuchElementException(s"Тег: $tag \n Ожидаемый тип: $thisType \n Пришедший тип: $thisT")
    else {
      thisType = thisT; nextType = nextT  // следующий тег
      thisType match {
        case CSSNewRow =>  endRow
        case CSSEndRow => endRow
        case CSSProperty => tagProperty(tag)
        case CSSAttribute => tagAttribute(tag)
        case _ => throw new NoSuchElementException(s"Не определён тип CSS тега $thisType")
      }
    }

    tagProperty(tag)
    this
  }

  /** implicit def int2Fraction(n: Int) = Fraction(n, 1) */
  def tagProperty(name: String) = {order += name + ": "; this}
  def tagAttribute(name: String) = {order += name + ";"; this}
  def endRow = {order += "\n"; nextType = CSSProperty; this}
  def newRow = {order += "\n"; nextType = CSSProperty; this}
  def newOrder = {order += "{"; nextType = CSSProperty; this}
  def endOrder = {order += "}"}

}
object Order extends CSS with OrderVRules with ReferencesCSS{
  /** Предыдущий, текущий и ожидаемый типы тегов */
  def apply = new Order
}






object myCSSOrderTest{



  println("Hello CSS!")
  val order1 = Order





  println(order1)

}



package CSSv1

/** постфиксная нотация **/
import scala.language.postfixOps

abstract class CSS

/** Основные типы для сочетания тегов **/
trait CSSRules extends CSS{
  object Property /** тег начинает строку ордера */
  type Property
  object Attribute /** после Property */
  type Attribute
  object CSSValue
  type CSSValue
  object NoType
  type NoType
  object StartOrder
  type StartOrder
  object EndOrder
  type EndOrder
  object Selector
  type Selector

  def select(s:Any): Any = s match {
    case _ : Property => Property
    case _ : Attribute => Attribute
    case _ : CSSValue => CSSValue
    case _ : NoType => NoType
    case _ => throw new NoSuchElementException("Не определён тип в правилах CSSRules")
  }
}

/** по типу определяем корректность последовательности тегов
 * от класса TypeRules наследуем подтипы */
class TypeRules
/** каждому тегу CSS назначаем тип **/
abstract class CSSTag extends CSS{
  type TagType <: TypeRules
  def get(tag: TagType)
  /**  Типы определяют правила для построения CSS **/

  /** Имя тега в CSS коде */
  val name:String
  /** Тип допустимого предыдущего тега */
  val preType:Any
  /** Тип текущего тега */
  val thisType:Any
  /** Тип допустимого следующего тега */
  val nextType:Any
}

/** Формирует несколько строк CSS кода, заключённые в кавычки */
class Order(var str:String ="{") extends CSS{
  override def toString: String = str

}

trait CSSTags extends CSS with Outline{

}

trait OutlineRules extends CSS{
  object OutlineAttribute
  type OutlineAttribute
}

trait Outline extends CSS with CSSRules{

//  def outline_style = new CSSTag("outline-style", Selector, Property, OutlineAttribute)
//  def none = new CSSTag("display", Property, OutlineAttribute, Selector)
}


















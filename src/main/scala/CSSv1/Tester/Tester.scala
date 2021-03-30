package CSSv1


/** Тест синтаксиса цепочек **/

/** Цепочка с переопределением значения строки с каждым вызовом метода add **/
case class Chain(x: String){
  val str = x
  override def toString: String = str
  def :<< (s: String):Chain = { /** метод применяется к ПРАВОМУ аргументу **/
    val nc = Chain(this + s) // Экземпляр класса Chain с добавлением пришедшего аргумента String
    nc
  }
  def <<: (s: String):Chain = { /** метод применяется к ЛЕВОМУ аргументу **/
    val nc = Chain(s + this) // Экземпляр класса Chain с добавлением пришедшего аргумента String
    nc
  }
  /** За методом закреплён определённый string **/
  def outline_style = Chain(this + "outline-style ")
  def solid = Chain(this + "solid ")
}

/** трейт для переменных общего доступа **/
trait TestOrderVar extends CSS{
  var order = ""
}

class TestOrder extends CSS with TestOrderVar{
  def new_property = {order += "new-property "; this}
}



/** Цепочка со встроенным буфером **/
//object ChainVar
class ChainVar(var str:String ="") extends CSS{
  override def toString: String = str
  val myValue = 2021
  /** За методом закреплён определённый string **/
  def new_property = {this.str += "new-property "; this}
  def new_attribute = {this.str += "new-attribute "; this}
  def new_value = {this.str += s"(var--${myValue})"}
}

object ChainTest{
  val myValue = 2021
  /** Цепочка с буфером **/
  val chBuf = new ChainVar
  chBuf.new_property.new_attribute.new_value
  println(chBuf)
  println(classOf[ChainVar].getName ) /** Извлечение имени класса с цепочкой от корня пакета **/
  println(classOf[ChainVar].getSimpleName ) /** Извлечение имени класса */

  /** Цепочка с переопределением значения строки с каждым вызовом add методов **/
  println("I'm here")
  val ch = new Chain(";")
  println(ch :<< " 111 " :<< " 222 " :<< " 333 ")
  println(ch.<<:("111 ").<<:("222 ").<<:("333 "))
  println("111 " <<: "222 " <<: "333 " <<: ch)
  /** цепочка методов с закреплёнными String **/
  println(ch.outline_style.solid)
}

//Листинг 19.5. Невариантный (жесткий) класс Cel
class Cell[T](init: T) {
  private[this] var current = init
  def get = current
  def set(x: T) = { current = x }
}

/** Основные типы для сочетания тегов **/
trait CSSRulesOldVersion extends CSS{
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

/** Селекция типов SCALA профессиональное программмирование стр.611 */
class Food
abstract class Animal {
  type SuitableFood <: Food
  def eat(food: SuitableFood)
}

// Листинг 20.10. Реализация абстрактного типа в подклассе
class Grass extends Food
class Cow extends Animal {
  type SuitableFood = Grass
  override def eat(food: Grass) = {}
}

/** пример правильного полиморфизма **/
/** При вызове сработает тот метод, к классу которого принадлежит обрабатываемый объект
 * при вызове принадлежность объекта неизвестна   */
class Base{
  def printType = println("Base")
}

class Base1 extends Base{
  override def printType: Unit = println("Base1")
}

class Base2 extends Base1{
  override def printType:Unit = println("Base2")
}




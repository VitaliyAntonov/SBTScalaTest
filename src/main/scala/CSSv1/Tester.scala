package CSSv1

import CSSv1.Tags.Align


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

trait OrderVar extends CSS{
  var order = ""
}

class Order extends CSS with OrderVar{
  def new_property = {order += "new-property "; this}
}



/** Цепочка со встроенным буфером **/
//object ChainVar
class ChainVar(var str:String ="") extends CSS with Align{
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
//  chBuf.new_property.new_attribute.new_value
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






package CSS


/** Рабочий вриант с селективным доступом к следующему тегу  */

class Order{
  var buffer = ""
  /** Начало CSS Ордера */
  def <<|  =  {Order.buffer = "{ "; Order.row}
}
object Order{
  var buffer: String = ""
  /** Добавляем в Order свойство */
  def proToOrder(name: String) = {Order.buffer += name + ": "}
  /** Добавляем в Order атрибут */
  def attToOrder(name: String) = {Order.buffer += name + " ;"}
  /** Enter и Табуляция в начале строки */
  def enTab = {Order.buffer += "\n  "}
  /** Якорь. Вызовом метода Order.row.hear входим в низ(класс Row) классов свойств CSS */
  val row = new Row
  /** Якорь для обращения к текущему Ордеру. Пример: in.buffer */
  var in: Order = null /** TODO создать якорь */
  /** Сщздание нового Ордера */
  def create = {val actualOrder = new Order; in = actualOrder }
}

class align_items extends{
  def align_items = {
  }
}

class align_content extends align_items {
  object Attribute{val x = new Attribute}
  class Attribute{
    def hear = {this}

    def flex_start = {Order.attToOrder("flex-start"); Order.row.hear }
    def center = {Order.attToOrder("center"); Order.row.hear }
    def stretch = {Order.attToOrder("stretch"); Order.row.hear }
  }

  def align_content = { Order.proToOrder("align-content"); Attribute.x.hear }

}

/** Класс для входа в Свойства CSS - начало строки CSS кода */
class Row extends align_content{
  def hear = {this}
  /** Enter и Табуляция в начале строки */
  def > = {Order.enTab; Order.row.hear}
  /** Окончание CSS Ордера */
  def |>>  =  {Order.buffer += "}"}
}





object CSS_Test5{

  /** Получение имени класса */
  def name = this.getClass.getSimpleName
  def nameToCSS(name: String): String = {
    var s = ""
    for(i <- 0 until name.length){
      val sim = name.slice(i, i+1)
      if(sim != "_") s += sim
      else s += "-"
    }
    s
  }

  val ord = new Order
  ord.<<|.align_content.flex_start.
    >.align_content.flex_start.|>>

  println(Order.buffer)

}
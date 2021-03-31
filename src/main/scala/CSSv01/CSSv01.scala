package CSSv01



object Order{
  protected class Order{var buffer = ""}
  /** Добавляем в Order свойство */
  def proToOrder(name: String) = {in.buffer += "  " + name + ": "}
  /** Добавляем в Order атрибут */
  def attToOrder(name: String) = {in.buffer += name + " ;"}
  /** Enter */
  def enter = {in.buffer += "\n"}
  /** Якорь. Вызовом метода Order.row.hear входим в низ(класс Row) классов свойств CSS */
  val row = new Row
  /** Якорь для обращения к текущему Ордеру. Пример: in.buffer */
  var in: Order = null /** TODO создать якорь */
  /** Создание нового Ордера */
  def create = {in = new Order; row.hear; row} /** TODO ЯКОРЬ Создан */

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
  def > = {Order.enter; Order.row.hear}
  /** Начало CSS Ордера */
  def <<|  =  {Order.in.buffer = "{ "; Order.row.hear}
  /** Окончание CSS Ордера */
  def |>>  =  {Order.in.buffer += "}"}
}





object HierarchyTest5{

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

  val ord = Order.create
  ord.<<|.align_content.flex_start.
    >.>.align_content.stretch.>.|>>

  println(Order.in.buffer)

}



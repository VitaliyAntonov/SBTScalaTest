package CSSv01

import scala.language.postfixOps /** постфиксная нотация **/
/** Схемы наследования */

class Order
object Order{
  var buffer: String = "{"
  def endOrder = buffer += "}"
  def Enter = {Order.buffer += Row.str; this} /**  Order.buffer += Row.str; this */
}



trait Enteralign_content{
  def <|  = {Order.Enter}
}

trait ToEnteralign_content extends Enteralign_content{
  protected def hearEnter = {this}
}



trait EndRowalign_content /** Верхний класс Атрибутов */
trait stretch extends EndRowalign_content with ToEnteralign_content{
  def stretch = {hearEnter}
}
trait center extends stretch{
  def center = {}
}
trait flex_start extends center{
  def flex_start = {}
}
trait Ialign_content extends flex_start{
  protected def hearalign_content = {this}
}


class EndProperty
class align_items extends EndProperty{
  def align_items = {}
}
class align_content extends align_items with Ialign_content{
  def align_content = {hearalign_content}
}
/** Класс для входа в Свойства CSS - начало строки CSS кода */
class Row extends align_content
object Row{
  var str = ""
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

  val ord = new Order
  val a = new Row
  a

  println(Order.buffer)


}




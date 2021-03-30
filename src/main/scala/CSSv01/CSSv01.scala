package CSSv01




import CSSv01.Tags.{OrderRow}

import scala.language.postfixOps /** постфиксная нотация **/




object CSSv01_Test{
  println("Hello CSSv01")
  val x = new OrderRow
  x.align_items.stretch.<|
  x.align_self.stretch.<|
  x.align_items.flex_start.<|



//  x.align_self.center





//  x.align_self.space_around.<|.endOrder


  println(x.order)

  /** Получение имени метода */
  def name = this.getClass.getSimpleName

  println(name)



}


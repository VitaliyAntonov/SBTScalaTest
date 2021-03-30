package CSSv00




import scala.language.postfixOps /** постфиксная нотация **/


/**
 * Иерархия CSS тегов
 *
 *  Верхний класс
 *  - EndOrder -    содержит метод EndOrder
 *  - ENTER -       Метод ENTER можно вызвать после любого тега в конце строки
 *  - Attribute -   Класс содержит методы для тегов(атрибуиы, константы CSS, расположенных в конце строки
 *  - Property -    Класс содержит методы для свойств
 *  - NewRow  -     Нижний класс для доступа к свойствам с начала строки
 *  - NewOrder -    Создание нового ордера
 *  Нижний класс
 */

abstract class Order

  class OrderBufer extends Order{
    var order = ""
    override def toString: String = order
  }
  class EndOrder extends OrderBufer {
    /** фиксация уровня иерархии */
    def hearOrderEnd = {}

    def OrderEnd = {order += "}"; this}
  }
  class Enter extends EndOrder{
    /** фиксация уровня иерархии */
    def hearEnter = {order += ""; this}

//    def <<::(x: NewOrder) = {  NewRow.enterTap(x)}
  }
  class Attribute extends Enter{
    /** фиксация уровня иерархии */
    def hearAttribute = { this}

    def AttrToOrder(name: String) = {order += name + ";"; hearEnter}

    def stretch = {AttrToOrder("stretch")}


  }
  class Property extends Attribute {

    /** фиксация уровня иерархии */
    def hearProperty = {this}
    /** Добавление свойства в ордер */
    def PropToOrder(name: String) = {order += name + " "; hearAttribute}


    def align_items = {PropToOrder("align_items")}
  }
  class NewRow extends Property{
    /** фиксация уровня иерархии */
    def hearNewRow = {}
    def create = {order += "{"; hearProperty}

  }
  object NewRow{
//    def enterTap(sb: NewOrder) = {sb.order += "\n" ; this}

  }
//  class NewOrder extends CSSv01








object CSSIeTest{






//  val ord  = new NewOrder
//  ord.create.align_items.stretch.OrderEnd
//  println(ord)




}
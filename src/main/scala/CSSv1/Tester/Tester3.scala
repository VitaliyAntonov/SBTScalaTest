package CSSv1.Tester



import scala.language.postfixOps /** постфиксная нотация **/


/** Селектор типа */
object selectTest{
  sealed abstract class Furniture{
    def tip:String
  }
  case class Couch() extends Furniture{
    override def tip = "Couch"
  }
  case class Chair() extends Furniture{
    override def tip = "Chair"
  }


  def findPlaceToSit(tip: Furniture): String = {
    tip match{
      case _: Couch => "Lie on the couch"
      case _: Chair => "Sit on the chair"
    }
  }

  class Tags
  class NewRow extends Tags
  class Property extends NewRow
  class Attribute extends Property
  class EndRow extends Attribute

  class RowTag(name:String){override def toString: String = name}
  object RowTag{def apply(name:String) = new RowTag(name)}

  abstract class Tag{
    type TagType <: NewRow
  }

  class TagToOrder extends Tag
  object TagToOrder{
    def ToRow[TagType](name: String, x: TagType) = { this}
  }

  case class Prop() extends Tag{
    type TagType <: NewRow
    def toOrder[TagType](name: String, x: TagType) = {
      TagToOrder.ToRow(name, x) }
  }
  case class Attr() extends Tag{
    type TagType <: Property
  }


  class overType{
    def overTypeMetod = {this}
  }

  /** Метод должен возвращать свой тип */
  class ReturnType extends overType{

    def metod1 = this
    def metodOver = {overTypeMetod}
  }

    /** Свой тип имеет экземпляр класса - создание экземпляра через new */
  val np = new ReturnType
  /** Метод того же класса возвращает тот-же тип что и экземпляр этого класса */
  val np1 = np.overTypeMetod
  /** методы  класса могет вызвать только собственные методы и методы вышестоящего класса*/

  /** При вызове метода верхнего класса тип цепочки методов соответствует типу верхнего класса
   * и собственные методы экземпляра становятся недоступны */

  val na = Attr()



  val a = Chair()
  println(a.tip)

  println(findPlaceToSit(a))

  /** Получение имени метода */
  def name = this.getClass.getSimpleName



}





package CSS

/** постфиксная нотация **/
import scala.language.postfixOps

//import java.lang.Number

/** topic CSS типы **/

/** order - свойство(приказ) **/
/** Тег <order> используем для готового
 * выражения CSS свойств без указания селектора, например:
 *    { font-size: 5vw;
 *    padding: .5em;
 *    background: rgb(170, 46, 164);
 *    text-align: center;}
 */

/** Класс <CSStag>
 * для обозначения всех тегов, имеющихся в CSS
 * является верхним ограничителем для типов <Property> и <Attribute>
 */

/** Класс(тип) <Property>
 * для обозначения свойств , например <font-size>
 */

/**
 * Корневой класс для пакета CSS
 */


abstract class CSS {

  /**
   * Прверка принадлежности типа тега к типу Property -> Boolean
   * @param tag
   * @return Boolean
   */
  def isProperty(tag: Any): Boolean = tag match {
    case _: Property => true
    case _ => false
  }

}


/**
 * Property
 * Свойство CSS, это тег, с которого начинается строка CSS <order>.
 * Перед ним фигурная скобка или точка с запятой
 * После тега типа <Property> обычно ставится двоеточие
 */


/**
 *    Трейт  CSSProperty
 *    для перечисления всех свойств CSS
 *    def display: this.type = {new Property("display"); this} - для цепочки вызовов
 */


/** объединяет все теги и правила CSS */
trait CSSTags extends CSS with CSSProperty  with CSSValue with outline

/** Хранит все возможные аттрибуты CSS */


/** Хранит все возможные переменные CSS */



/**
 *   Класс(тип) <Attribute>
 *   для обозначения атрибутов свойств, например:
 *   text-align: center ; center - это атрибут с типом Attribute
 *   после атрибута могут быть параметры или точка с запятой,
 *   как завершение строки
 */


/**
 *  SubUnit
 *  Объединяет допустимые виды числовых или цветовых единиц CSS
 *  Параметр(например 5px или rgb(10,10,15)), который ставится
 *  после тегов типа Attribute или Property
 */
abstract class SubUnit extends CSS

/**
 * Value
 * Часть числового или цвтового параметра, представляет из себя число
 */


/**
 * Unit
 * Единицы изммерения, размещаемые после или до числовых значений
 * Например px, vw, vh, fr или rgb
 */
abstract class Unit extends CSS

/**
 * CSSRules содержит правила формирования CSS кода
 */
trait CSSRules extends CSS {

}



/**
 * Order
 * набор строк CSS типа RowOrder, заключённый в фигурные скобки
 */

trait CSSValue extends CSS

class Value(tag: String) extends CSS{
  override def toString: String = tag
  def apply = new Value(tag)
}

class Attribute(tag: String) extends CSS{
  override def toString: String = tag
  def apply = new Attribute(tag)
}

class Property(tag: String) extends CSS {
  override def toString: String = tag
  def apply = new Property(tag)
}
object Property{ /** Компаньон **/
  def apply(tag: String) = new Property(tag) /** Фабричный метод конструктор **/
}

trait CSSProperty extends CSS{
  def display = new Property("display")
  def grid_template_columns = new Property("grid-template-columns")
}
/** Создаёт строку CSS кода **/
case class RowOrder(prop: Property, attr: Attribute)
                                    extends CSS with CSSTags {
  var rowOrder = prop.toString + ": " + attr.toString  + ";"
}


class CSSTag
/** Создание цепочки вызовов **/
/** def set(obj: Title.type): this.type = { useNextArgAs = obj; this } */
class CSSOrder(tag: CSSTag) extends CSS with CSSTags{
  var order = tag.toString
  override def toString: String = order

  def tag1(tag: CSSOrder): CSSOrder = {
    this.order += tag.toString + " "
    this
  }

//  def <<: (x: CSSTag): CSSOrder = {
//    new CSSOrder(this)
//    this
//  }

}



object testCssTypes extends CSSTags {

//  val pr = new CSSOrder("{")
//  pr tag1
//
//  println(pr.order)


  val ord = RowOrder(outline_style, solid )
//  ord.grid_template_columns
  println(ord.rowOrder)

  /** Пример синтаксиса цепочек **/
 val doc = new Document
  doc set Title to "Scala for the Programmers"
  println(doc.title)

  /**
   * пример синтаксиса цепочек методов с пробелами
   * SCALA для нетерпеливых  Глава 19.1
   */
  object Title
  class Document {
    var title = ""
    private var useNextArgAs: Any = null
    def set(obj: Title.type): this.type = { useNextArgAs = obj; this }
    def to(arg: String) = if (useNextArgAs == Title) title = arg
  }


  def grid_column_start = new Attribute("grid-column-start")

  println("display isProperty =" + isProperty(display))
  println("grid_column_start isProperty =" + isProperty(grid_column_start))
  println("display = " + display)
  println("grid_template_columns = " + grid_template_columns)
//  println(grid_template_columns.to)

//  val row = grid_template_columns.to + grid_template_columns.to

  /**
   * Сопоставление с типами
   */
  def findType(x: Any, s: Any) = x  match {
    case x: Int => x
    case s: String => Integer.parseInt(s)
    case _: BigInt => Int.MaxValue
    case _ => 0
  }


  /**
   * Листинг 15.5. Поиск по шаблону с использованием шаблонов-констант
   */
  def describe(x: Any) = x match {
  case 5 => "five"
  case true => "truth"
  case "hello" => "hi!"
  case Nil => "the empty list"
  case _ => "something else"
  }

//  Листинг 15.6. Поиск по шаблону с использованием шаблона-
//    переменной
  def findVar(x: Any) = x match {
    case 0 => "zero"
    case somethingElse => "notzero:"+ somethingElse
  }

  case class BinOp(value: "+", e: Any, value1: Any){

  }




}




/**
 * Simple Scala class that demonstrates pattern match using classes in Scala.
 *
 * @author Eugene Suleimanov
 **/
object PatternMatchDemo {
  def main(args: Array[String]) {
    println(specialtyTest("Java"));
    println(specialtyTest("C++"));
    println(specialtyTest("Scala"));
    println(specialtyTest("PHP"));
  }

  def specialtyTest(specialty: String) = specialty match {
    case "Scala" => "Scala Developer"
    case "Java" => "Java Developer"
    case "C++" => "C++ Developer"
    case _ => "Unsupported specialty"
  }
}




/**
 * Пример селекции типов из книги
 */
abstract class CurrencyZone {
  type Currency <: AbstractCurrency
  def make(x: Long): Currency
  abstract class AbstractCurrency {
    val amount: Long
    def designation: String
    def + (that: Currency): Currency = make(this.amount + that.amount)
    def * (x: Double): Currency = make((this.amount * x).toLong)
    def - (that: Currency): Currency = make(this.amount - that.amount)
    def / (that: Double) = make((this.amount / that).toLong)
    def / (that: Currency) = this.amount.toDouble / that.amount
    def from(other: CurrencyZone#AbstractCurrency): Currency =
      make(math.round(other.amount.toDouble *
        Converter.exchangeRate(other.designation)(this.designation)))
    private def decimals(n: Long): Int = if (n == 1) 0 else 1 + decimals(n / 10)
    override def toString = ((amount.toDouble / CurrencyUnit.amount.toDouble)
      formatted ("%." + decimals(CurrencyUnit.amount) + "f") + " " + designation)
  }
  val CurrencyUnit: Currency
}

object Converter {
  var exchangeRate = Map(
    "USD" -> Map("USD" -> 1.0, "EUR" -> 0.7596, "JPY" -> 1.211 , "CHF" -> 1.223),
    "EUR" -> Map("USD" -> 1.316 , "EUR" -> 1.0, "JPY" -> 1.594 , "CHF" -> 1.623),
    "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272, "JPY" -> 1.0, "CHF" -> 1.018),
    "CHF" -> Map("USD" -> 0.8108, "EUR" -> 0.6160, "JPY" -> 0.982 , "CHF" -> 1.0)
  )
}


object US extends CurrencyZone {
  abstract class Dollar extends AbstractCurrency {
    def designation = "USD"
  }
  type Currency = Dollar
  def make(cents: Long) = new Dollar {
    val amount = cents
  }
  val Cent = make(1)
  val Dollar = make(100)
  val CurrencyUnit = Dollar
}

object Europe extends CurrencyZone {
  abstract class Euro extends AbstractCurrency {
    def designation = "EUR"
  }
  type Currency = Euro
  def make(cents: Long) = new Euro {
    val amount = cents
  }
  val Cent = make(1)
  val Euro = make(100)
  val CurrencyUnit = Euro
}

object Japan extends CurrencyZone {
  abstract class Yen extends AbstractCurrency {
    def designation = "JPY"
  }
  type Currency = Yen
  def make(yen: Long) = new Yen {
    val amount = yen
  }
  val Yen = make(1)
  val CurrencyUnit = Yen
}








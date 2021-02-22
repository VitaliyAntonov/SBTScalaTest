


object Demo {
  val x = {
    println("initializing x");
    "done"
  }
}

object Demo1 {
  lazy val x = { println("initializing x"); "done" }
}

/** Листинг 20.8. Инициализация трейта с ленивыми val-переменными */
trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  lazy val numer = numerArg / g
  lazy val denom = denomArg / g
  override def toString = numer + "/" + denom
  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

//class Food
//abstract class Animal {
//  def eat(food: Food)
//}

/** topic  20.7. Типы, зависящие от пути */
//Листинг 20.9. Моделирование подходящего питания с помощью абстрактных типов
class Food

abstract class Animal {
  type SuitableFood <: Food  /** SuitableFood  ПСЕВДОНИМ ТИПА для подклассов
                              Тип SuitableFood является подтипом класса(ТИПА) Food */
  def eat(food: SuitableFood) /** Метод будет работать только с типом SuitableFood */
}

//Листинг 20.10. Реализация абстрактного типа в подклассе
class Grass extends Food /** Название КЛАССА определяет название ТИПА Grass */
class Cow extends Animal {
  type SuitableFood = Grass /** для класса Cow определяем тип Grass */
  override def eat(food: Grass) = {} /** Метод будет работать только с типом Grass
                              тип SuitableFood определен в качестве псевдонима для класса Cow */
}

class DogFood extends Food /** Название КЛАССА определяет название ТИПА DogFood */
class Dog extends Animal {
  type SuitableFood = DogFood /** для класса Dog определяем тип DogFood
                              тип SuitableFood определен в качестве псевдонима для класса DogFood */
  override def eat(food: DogFood) = {}
}


/** topic Типы внутренних классов */

class Outer {
  class Inner
}

/** topic Перечисленния */
object Color0 extends Enumeration {
  val Red = Value
  val Green = Value
  val Blue = Value
}

object Color extends Enumeration {
  val Red, Green, Blue = Value
}

object Direction extends Enumeration {
  val North = Value("North")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
}


object typeTest{
  Demo
  Demo1.x

  class Fish extends Food
  val bessy: Animal = new Cow

  val lassie = new Dog

  /** Не скомпилируется, так как для объекта класса(ТИПА) Dog допустим только тип DogFood */
  // lassie eat (new bessy.SuitableFood)

  val bootsie = new Dog
  lassie eat (new bootsie.SuitableFood)


  /** topic Типы внутренних классов */
  /** Здесь o1.Inner и o2.Inner являются двумя типами, зависящими от пути, и это РАЗНЫЕ типы */
  val o1 = new Outer
  val o2 = new Outer

//  val o3: Outer#Inner = null
  val o3 = new o1.Inner /** Внутренний класс(ТИП) можно использовать только через ОПРЕДЕЛЁННЫЙ
                        объект внешнего класса(ТИПА) val o1 = new Outer */

  /** topic Перечисления */
  for (d <- Direction.values) print(d + " ")
  println()
  for (d <- Direction.values) print(d.id + " ")
  println()
  println("Direction.North.id = " + Direction.North.id)
  println("Direction(1) = " + Direction(1))

  // Define a new enumeration with a type alias and work with the full set of enumerated values
  object WeekDay extends Enumeration {
    type WeekDay = Value
    val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
  }
  import WeekDay._

  def isWorkingDay(d: WeekDay) = ! (d == Sat || d == Sun)

  WeekDay.values filter isWorkingDay foreach println

  // Example of adding attributes to an enumeration by extending the Enumeration.Val class
  object Planet extends Enumeration {
    protected case class Val(mass: Double, radius: Double) extends super.Val {
      def surfaceGravity: Double = Planet.G * mass / (radius * radius)
      def surfaceWeight(otherMass: Double): Double = otherMass * surfaceGravity
    }
    import scala.language.implicitConversions
    implicit def valueToPlanetVal(x: Value): Val = x.asInstanceOf[Val]

    val G: Double = 6.67300E-11
    val Mercury = Val(3.303e+23, 2.4397e6)
    val Venus   = Val(4.869e+24, 6.0518e6)
    val Earth   = Val(5.976e+24, 6.37814e6)
    val Mars    = Val(6.421e+23, 3.3972e6)
    val Jupiter = Val(1.9e+27, 7.1492e7)
    val Saturn  = Val(5.688e+26, 6.0268e7)
    val Uranus  = Val(8.686e+25, 2.5559e7)
    val Neptune = Val(1.024e+26, 2.4746e7)
  }

  println(Planet.values.filter(_.radius > 7.0e6))
  // output:
  // Planet.ValueSet(Jupiter, Saturn, Uranus, Neptune)

}


/** topic 20.10. Практический пример: работа с валютой */
abstract class CurrencyZone {
  type Currency <: AbstractCurrency /** Абстрактный тип, тип валюты */
  def make(x: Long): Currency
  abstract class AbstractCurrency {
    val amount: Long
    def designation: String
    override def toString = amount + " " + designation
    def + (that: Currency): Currency =
      make(this.amount + that.amount)
    def * (x: Double): Currency =
      make((this.amount * x).toLong)
  }
}

/** Листинг 20.11. Зона валюты США */
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










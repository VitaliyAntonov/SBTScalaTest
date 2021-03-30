package CSSv1.SortCats



import java.io.File
import scala.io.Source
import scala.language.implicitConversions/** убираем предупреждения компилятора об implicit */





/** для примера класс рациональных чисел */
class Fraction(n: Int, d: Int) {
  private val num = n
  private val den = d
  override def toString: String = s"$num/$den"
  def *(other: Fraction) = new Fraction(num * other.num, den * other.den)
}
object Fraction {
  def apply(n: Int, d: Int) = new Fraction(n, d)
}

/** Чтение файла  объявляем неявное преобразование в тип RichFile **/
/**  */
class RichFile(val from: File) {
  def read = Source.fromFile(from.getPath).mkString
}




object ImplicitCodeTest{
  implicit def int2Fraction(n: Int) = Fraction(n, 1); println("implicit def int2Fraction(n: Int) = Fraction(n, 1)...")


  val result = 3 * Fraction(4, 5) // Вызовет int2Fraction(3)
  println(result)


  /** Чтение файла **/
  implicit def file2RichFile(from: File) = new RichFile(from)


}



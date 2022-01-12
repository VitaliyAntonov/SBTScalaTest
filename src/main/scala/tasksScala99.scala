
import java.util.NoSuchElementException


object tasksScala0 {

  /** P01 (*) Найти последний элемент списка. */
  def myLast[T](l: List[T]): T = l.last

  println("myLast(List(1,1,2,3,5,8)) = " + myLast(List(1, 1, 2, 3, 5, 8)))
  //  println(myLast(List()))

  /** P02 (*) Найдите предпоследний элемент списка. */
  def penultimate[T](l: List[T]): T = l match {
    case Nil => throw new NoSuchElementException("penultimate on empty List!")
    case _ :: Nil => throw new NoSuchElementException("penultimate one item in a List!")
    case _ => l.takeRight(2).head // l.reverse(1)
  }

  println("penultimate(List(1,1,2,3,5,8)) = " + penultimate(List(1, 1, 2, 3, 5, 8)))
  //  println(penultimate(List(1)))

  /** P03 (*) Найдите K- й элемент списка. */
  def nth[T](n: Int, l: List[T]): T = {
    /** Базовые методы - моё решение */
    //    if(l.length > n) l.drop(n).head
    //    else throw new NoSuchElementException("List is less than the searched number !")

    /** Базовые методы - решение с сайта */
    if (n >= 0) l(n)
    else throw new NoSuchElementException("List is less than the searched number !")
  }

  println("nth(List(1,1,2,3,5,8)) = " + nth(5, List(1, 1, 2, 3, 5, 8)))

  /** P04 (*) Найдите количество элементов в списке. */
  def myLength[T](l: List[T]): Int = l.length /** встроенное <length> */

  /** рекурсивное с сайта */
  def lengthRecursive[T](l: List[T]): Int = l match {
    case Nil => 0
    case _ :: tail => 1 + lengthRecursive(tail)
  }

  /** P05 (*) Перевернуть список. */
  def myReverse[T](l: List[T]): List[T] = l match {
    case _ :: Nil => l
    case Nil => l
    case _ => l.reverse
  }

  def reverseRecursive[T](l: List[T]): List[Any] = l match {
    case Nil => l
    case _ :: Nil => l
    // case _ => ReverseRecursive(l.tail) ::: (l.head :: Nil)
    case golova :: hvost => reverseRecursive(hvost) ::: List(golova)
  }

  def reverseFunctional[A](ls: List[A]): List[A] =
    ls.foldLeft(List[A]()) { (r, h) => h :: r }

  println("myReverse(List(1, 1, 2, 3, 5, 8)) =" + myReverse(List(1, 1, 2, 3, 5, 8)))
  println("ReverseRecursive(List(1, 1, 2, 3, 5, 8)) =" + reverseRecursive(List(1, 1, 2, 3, 5, 8)))

  /** P06 (*) Узнайте, является ли список палиндромом. */
  def isPalindrome[T](l: List[T]): Boolean = l match {
    /** базовые методы(моё) */
    case Nil => true
    case _ :: Nil => true
    case _ => {
      val divLength = l.length / 2

      /** для <drop> при нечётном средний элемент не хэшируется */
      l.reverse.drop(divLength) == l.drop(divLength)
    }
  }

  def isPalindromeR[T](l: List[T]): Boolean = {
    /** с рекурсией(моё) */
    def compare[T](l: List[T], n: List[T]): Boolean = l match {
      case Nil => true
      case _ :: Nil => true
      case h :: t => h == n.head && compare(t, n.tail)
    }

    val x = l.length / 2
    compare(l.reverse.drop(x), l.drop(x))
  }

  /** Базовый метод с сайта - самый быстрый */
  def isPalindromeS[A](ls: List[A]): Boolean = ls == ls.reverse

  /** Метод - обёртка для измерения времени работы кода(с вызовом функции по имени)
   * пример вызова:  fixTime(isPalindromeS(l)) */
  def fixTime[T](f: => T) = {
    val t1_ns: Long = System.nanoTime()
    f
    val t2_ns: Long = System.nanoTime()
    println(f + " время работы метода : " + (t2_ns - t1_ns) / 1000 + " микросекунд")
  }

  /**
   * Усреднённое время работы функции
   * @param f - тестируемая функция
   * @param nCycle - количество повторов тестируемой функции
   * @tparam T
   */
  def fixTimeInCycle[T](f: => T, nCycle: Int) = {
    val t1_ns: Long = System.nanoTime()
    for(i <- 0 until nCycle)  f
    val t2_ns: Long = System.nanoTime()
    println(f + " время работы функции : " + (t2_ns - t1_ns)  / nCycle + " наносекунд")
  }
  def nonFu(a: Int): Int = a + 1

  /** Возврат функции из метода */
  val retFunc = (a:Int, b: Int) => a * b




//  fixTimeInCycle(isPalindromeS(List(1,2,2,2,2,21,34,56)),100000)
  fixTimeInCycle(nonFu(1),1000)

  /** Вызов по имени */
  /** https://docs.scala-lang.org/ru/tour/by-name-parameters.html */
  def whileLoop(condition: => Boolean)(body: => Unit): Unit =
    if (condition) {
      body
      whileLoop(condition)(body)
    }
  var i = 2
  whileLoop(i > 0) { println(i)
    i -= 1
  }  // выведет 2 1


  val x = List.fill(5)(2)
  println(x)

  /** P07 (**) Сглаживание структуры вложенного списка.
   * scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
   * res0: List[Any] = List(1, 1, 2, 3, 5, 8)
   */
    /** пример с сайта */
  def flatten(ls: List[Any]): List[Any] = ls flatMap {
    case ms: List[_] => flatten(ms)
    case e => List(e)
  }

  println("==================  flatten  ======================")
  val as = flatten(List(List(1, 1), 2, List(3, List(5, 8))))
  println(as)

  /** P08 (**) Устранение последовательных дубликатов элементов списка.  -мой */
  def compress(ls: List[Any]): List[Any] = {
    import scala.collection.mutable.ListBuffer
    if (ls.nonEmpty) {
      val lb = ListBuffer[Any]()
      var lbnum = 0
      lb += ls(0)
      for (i <- 0 until ls.size) {
        if (lb(lbnum) != ls(i)) {
          lb += ls(i); lbnum += 1
        }
      }
      lb.toList
    }
    else Nil
  }
  /** Рекурсивный с сайта */
  def compressTailRecursive[A](ls: List[A]): List[A] = {
    def compressR(result: List[A], curList: List[A]): List[A] = curList match {
      case h :: tail => compressR(h :: result, tail.dropWhile(_ == h))
      case Nil       => result.reverse
    }
    compressR(Nil, ls)
  }


  val rlb = compress (List ('a','a','a','a','b','c','c','a','a','d','e','e','e','e'))
  val rlb1 = compress (List ())
  println(rlb1.mkString(" "))
  println(compress(Nil).mkString(""))

}





object testTasks99 extends App{

  /** Проверка возврата функции из метода */
  println("retFunc(3,7) = " + tasksScala0.retFunc(3,7))

  /** Создание сокращённой записи функционального литерала */
  println("Создание сокращённой записи функционального литерала ")
  def sum(a: Int, b: Int, c: Int) = a + b + c
  sum(1, 2, 3)
  val a = sum _   /** частично применяемая функция */
  println("a(1, 2, 3) = " + a(1, 2, 3))
  /** можно выразить частично применяемую функцию,
   * предоставив ей только некоторые из требуемых аргументов.
   */
  val b = sum(1, _: Int, 3)
  println("b(2) = " + b(2))
  println("b(5) = " + b(5))
  println("\n")

}








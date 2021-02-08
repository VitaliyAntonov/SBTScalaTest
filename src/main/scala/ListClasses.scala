

import scala.collection.immutable.List

// topic 16. Работа со списками


object literalLists {
  // topic Литералы списков
  val fruit = List("apples", "oranges", "pears")
  val nums = List(1, 2, 3, 4)
  val diag3 = List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )
  val empty = List()

  // topic Пример первой разновидности шаблона выглядит следующим образом:
//  val List(a, b, c) = fruit

  val a :: b :: rest = fruit

  val mas1 = Array("test","one","two","three")
  val mas: Array[String] = new Array[String](10)
    for(i <- 0 to 9) mas(i) = "test"
  for(i <- mas) print(i + " ")
  println("\n" + mas.mkString(" "))

  // topic Алгоритм вставочой сортировки

  def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))

  def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)

  // topic flatten ---- Линеаризация списка списков: flatten
  val nl = List(List(1, 2), List(3), List(-2), List(4, 5)).flatten
  println(nl.mkString(" "))

  val abcde = List("a","b","c","d","e")
  val charAbcd = List('a','b','c','d')

  val x = (charAbcd take 3).mkString("")
  println(x)

  println((abcde take 2).mkString(" "))

  val emptyList = List()

  val buf = new StringBuilder
  println(abcde addString (buf, "(", ";", ")"))
  val it = abcde.iterator
  println("it.next() = " + it.next())

  // topic Сортировка методом слияния

  // topic Листинг 16.1. Функция сортировки методом слияния для List-объектов
  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }

  // topic Сортировка методом слияния
  println(msort((x: Int, y: Int) => x < y)(List(5, 7, 1, 3)).mkString(" "))


  // topic 16.7. Методы высшего порядка, определенные в классе List
  /** <map>     xs map f получает в качестве операндов список xs типа List[T]
                и функцию f типа T => U. Она возвращает список,
                получающийся в результате применения f к каждому элементу списка xs */

  println("List(1, 2, 3) map (_ + 1) = " + (List(1, 2, 3) map (_ + 1)))

  val words = List("the", "quick", "brown", "fox")
  println(words map (_.length))
  println(words map (_.toList.reverse.mkString))

  /** <flatMap> похож на map, но в качестве правого
                операнда получает функцию, возвращающую список элементов. Он
                применяет функцию к каждому элементу списка и возвращает
                объединение всех результатов выполнения функции. */
  /** Сравнение <map> и <flatMap> */
  println(words map (_.toList)) // список списков
  println(words flatMap (_.toList)) // слияние в один список

  /** <range>     является вспомогательным методом, создающим
                список из всех целых чисел в некотором диапазоне */
  println(List.range(1, 5) flatMap (i => List.range(1, i) map (j => (i, j))) )
  val list4 = List(1,2,3,4,5,6)
  println(list4.map(i => i + 1))

  // topic Фильтрация списков
  /** <filter>  Операция xs filter p получает в качестве операндов список xs
                типа List[T] и функцию-предикат p, относящуюся к типу T =>
                Boolean. Эта операция выдает список всех элементов x из списка
                xs, для которых p(x) вычисляется в true */
  println( list4 filter (i => i % 2 == 0) )

  /**  <partition> похож на метод filter, но возвращает пару
                списков. Один список содержит все элементы, для которых
                предикат вычисляется в true, а другой — все элементы, для
                которых предикат вычисляется в false */
  println(list4 partition (_ % 2 == 0))

  /** <find>      также похож на метод filter, но возвращает только первый
                элемент, удовлетворяющий условию заданного предиката */
  println(list4 find (i => i%2 == 0))

  /** <takeWhile >  Операция xs takeWhile p получает самый длинный префикс списка xs,
                в котором каждый элемент удовлетворяет условию предиката p */

  println(List(1, 2, 3, -4, 5, 6, 7, 8) takeWhile (_ > 0))


}


object findList {
  // topic поиск по шаблону и вставочная сортировка
  def isort[T](xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: xs1 => insert(x, isort(xs1))
  }
  def insert(x: Int, xs: List[Int]): List[Int] = xs
  match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs
                    else y :: insert(x, ys)
  }

}







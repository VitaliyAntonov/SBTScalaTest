


import scala.collection.immutable.{List, TreeMap, TreeSet}
import scala.collection.mutable


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
  println("=============================  map and flatMap ========================")
  println(words map (_.toList)) // список списков
  println(words flatMap (_.toList)) // слияние в один список

  /** <range>     является вспомогательным методом, создающим
                список из всех целых чисел в некотором диапазоне */
  println(List.range(1, 5) flatMap (i => List.range(1, i) map (j => (i, j))) )
  val list4 = List(1,2,3,4,5,6)
  println(list4.map(i => i + 1))
  list4.size

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

  /** <dropWhile> операция xs dropWhile p удаляет самый длинный префикс из списка xs, в
                котором каждый элемент удовлетворяет условию предиката p */
  println(List(1, 2, 3, -4, 5, 6, 7, 8) dropWhile (_ > 0))

  /** <span>      объединяет takeWhile и dropWhile в одну операцию точно так же,
                как метод splitAt объединяет stake и drop. Он возвращает пару из двух списков */
  println(List(1, 2, 3, -4, 5, 6, 7, 8) span (_ > 0))
  val(al, bl) = List(1, 2, 3, -4, 5, 6, 7, 8) span (_ > 0)
  println("al = " + al)
  println("bl = " + bl)

  /** <forall>  xs forall p получает в качестве аргументов список xs и
                предикат p. Она возвращает результат true, если все элементы
                списка удовлетворяют условию предиката p */
  println( bl forall (i => i > 0) )
  /** <exists>  xs exists p возвращает true, если в xs имеется элемент,
                удовлетворяющий условию предиката p */
  println(bl.exists(_ == -4))



  def sum(xs: List[Int]): Int = (0 /: xs) (_ + _)

  println(sum(bl))
  println(("" /: words) (_ + " " + _))
  println((words.head /: words.tail) (_ + " " + _))

  val m: Array[String] = Array.fill(5)("a")

  // topic Сортировка списков
  /** <sortWith>  Операция xs sortWith before, где xs является списком, а
                before — функцией, которая может использоваться для сравнения
                двух элементов, выполняет сортировку элементов списка xs. */
  println( List(1, -3, 4, 2, 6) sortWith (_ < _) )
  println( words sortWith (_.length > _.length) )

  // topic 16.8. Методы объекта List
  /** <List.apply> Создание списков из их элементов */
  println(List.apply(1, 2, 3))

  val squares = List.tabulate(5)(n => n * n)
  println("squares = " + squares)
  println(List.concat(List('a', 'b'), List('c')))

// topic Строки, реализуемые посредством StringOps
  def hasUpperCase(s: String) = s.exists(_.isUpper)
  println(hasUpperCase("Robert Frost"))
  println(hasUpperCase("e e cummings"))


}


// topic 17.2. Наборы и отображения
object demoSet {

  // topic Использование наборов
  val text = "See Spot run. Run, Spot. Run!"
  val wordsArray = text.split("[ !,.]+")
  val words = mutable.Set.empty[String]
  for (word <- wordsArray) words += word.toLowerCase
  println("words = " + words)
  println("words = " + words.mkString(" "))

  // topic Применение отображений
  def countWords(text: String) = {
    val counts = mutable.Map.empty[String, Int]
    for (rawWord <- text.split("[ ,!.]+")) {
      val word = rawWord.toLowerCase
      val oldCount =
        if (counts.contains(word))
          counts(word)
        else 0
      counts += (word -> (oldCount + 1))
    }
    counts
  }

  println(countWords(text))

  // topic Отсортированные наборы и отображения
  /** Отсортированные наборы */
  val ts = TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)
  println(ts)
  val cs = TreeSet('f', 'u', 'n')
  println(cs)

  val mts = mutable.TreeSet(9,8,7,6,5,0)
  println(mts)
  mts += 2
  mts += 0
  println(mts)
  /** Отсортированные отображения */
  var tm = TreeMap(3 -> 'x', 1 -> 'x', 4 -> 'x')
  println(tm)
  tm += (2 -> 'x')

  // topic 17.5. Кортежи
  /**  поиск самого длинного слова в коллекции и
      возвращающий наряду с ним его индекс */
  def longestWord(words: Array[String]) = {
    var word = words(0)
    var idx = 0
    for (i <- 1 until words.length)
      if (words(i).length > word.length) {
        word = words(i)
        idx = i
      }
    (word, idx)
  }

  val longest = longestWord("The quick brown fox".split(" "))
  println(longest._1 + " " + longest._2)
  /** Кроме того, значение каждого элемента кортежа может быть присвоено своей собственной переменной */
  val (word, idx) = longest
  println(word + " " + idx)

  Timer(500) { println("Timer went off") }

}

object Timer {
  def apply(interval: Int, repeats: Boolean = true)(op: => Unit) {
    val timeOut = new javax.swing.AbstractAction() {
      def actionPerformed(e : java.awt.event.ActionEvent) = op
    }
    val t = new javax.swing.Timer(interval, timeOut)
    t.setRepeats(repeats)
    t.start()
  }
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

// topic Листинг 19.1. Базовая функциональная очередь
class Queue[T] private (
                         private val leading: List[T],
                         private val trailing: List[T]
                       ) {
  private def mirror =
    if (leading.isEmpty)
      new Queue(trailing.reverse, Nil)
    else
      this

  def head = mirror.leading.head

  def tail = {
    val q = mirror
    new Queue(q.leading.tail, q.trailing)
  }

  def enqueue(x: T) =
    new Queue(leading, x :: trailing)
}

// Листинг 19.3. Фабричный метод apply в объекте-спутнике
object Queue {
  // создает очередь с исходными элементами xs
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
}


// topic








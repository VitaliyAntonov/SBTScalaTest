



import scala.collection.mutable

/** topic 24. Углубленное изучение коллекций */

/**
 * topic Иерархия коллекций
 * <Traversable>
 *   <Iterable>
 *     <Seq>
 *       <IndexedSeq>
 *          <Vector>
 *          <ResizableArray>
 *          <GenericArray>
 *       <LinearSeq>
 *          <MutableList>
 *          <List>
 *          <Stream>
 *       <Buffer>
 *          <ListBuffer>
 *          <ArrayBufer>
 *     <Set>
 *       <SortedSet>
 *           <TreeSet>
 *       <HashSet(mutable)>
 *       <LinkedHashSet>
 *       <HashSet(immutable)>
 *       <BitSet>
 *       <EmptySet, Set1, Set2, Set3, Set4>
 *     <Map>
 *       <SortedMap>
 *         <TreeMap>
 *       <HashMap(mutable)>
 *       <LinkedHashMap(mutable)>
 *       <HashMap(immutable)>
 *       <EmptyMap, Map1, Map2, Map3, Map4>
 */


/** <Вопрос> как посмотреть тип mutable.Map или Map */

object CollectionDetail{}

object mapTest{
  val y: Map[Int, Char] = Map(1 ->'a', 2->'b', 3->'c', 4->'d')
  val x: mutable.Map[Int, Char] = mutable.Map(1 ->'a', 2->'b', 3->'c', 4->'d')
  println(x)
  x(2)='D'
  println(x)

  /** Создание immutable.Map из mutable.Map */
  val q: Map[Int, Char] = x.toMap
  println("q = " + q)
//  q(2) = 'S' /** это не работает, если immutable */

  /** Создание mutable.Map из immutable.Map */
  val z: mutable.Map[Int, Char] = mutable.Map() ++ y
  println(z)

  val e: mutable.Map[Int, Char] = y.to(mutable.Map)
  e(3)='G'
  println(e)

  /** Диапазоны */
  val diapazon: Range = 1 to 5
  println("diapazon = " + diapazon)

  /** topic Потоки */
  val str0 = 1 #:: 2 #:: 3 #:: Stream.empty
  val str = 1 #:: 2 #:: 3 #:: LazyList.empty
  println(str)
  /** обход элементов потока */
  for(i <- 0 until str.size) println(str(i))
  str foreach print; println()
  str.foreach(print); println()
  str.foreach(arg => print(arg + " ")); println()

  /** topic 24.10 МАССИВЫ */
  /** способ создания обобщенных массивов
   * Метод evenElems возвращает новый массив, состоящий из тех
   * элементов вектора xs, которые находятся на четных позициях
   */
  import scala.reflect.ClassTag
  def evenElems[T: ClassTag](xs: Vector[T]): Array[T] = {
    val arr = new Array[T]((xs.length + 1) / 2)
    for (i <- 0 until xs.length by 2)
      arr(i / 2) = xs(i)
    arr
  }

  val tegVector = evenElems(Vector(1, 2, 3, 4, 5))
  tegVector.foreach(arg => print(arg + " ")); println()

  /** topic 24.11 СТРОКИ */
  val st = "HelloWorld"
  println("\n=================   СТРОКИ   ===================")
  println(st.reverse)
  println("st.map(_ . toUpper) = " + st.map(_ . toUpper))
  println(st drop 3)
  println(st slice(1, 6))
  val s: Seq[Char] = st
  println("s = " + s.toString() )

  /** topic 24.14. Представления */
  /**
   * реализация ленивой операции map
   * @param coll
   * @param f
   * @tparam T
   * @tparam U
   * @return
   */
//  def lazyMap[T, U](coll: Iterable[T], f: T => U) =
//    new Iterable[U] {
//      def iterator = coll.iterator map f }
  val v = Vector(1 to 10: _ * )
  println(v)
  println( v map (_ + 1) map (_ * 2) )
  val v2 = (v.view map (_ + 1) map (_ * 2)).force
  val vv = v.view
  val vvv = vv map (_ + 1)
  vv.foreach(arg => print(arg + " ")); println
  vvv.foreach(arg => print(arg + " ")); println

  /** Это ПРЕДСТАВЛЕНИЕ не работает ??? */
//  val arr = (0 to 9).toArray
//  val subarr = arr.view.slice(3, 6)
//  def negate(xs: collection.mutable.Seq[Int]) = {
//    for (i <- 0 until xs.length)  -xs(i)
//  }
//  negate(subarr)
//  println(arr.mkString(" "))


  /** Инверсия части элементов массива */
  val arr = (0 to 9).toArray
  def negate(xs: Array[Int], start: Int, end: Int) = {
      for (i <- 0 until xs.length; if i >= start && i < end )  xs(i) = -xs(i)
  }

  println(arr.mkString(" "))
  negate(arr,3,6)
  println(arr.mkString(" "))
  println



//  val it = Iterator(arr)
//  val it = Iterator("a", "number", "of", "words")
//  it foreach println

//  while (it.hasNext) {
//    println(it.next().toString)
//  }

  /** Буферезированный итератор добавляет метод head
   * позволяющий взять первый элемент итератора, без перехода к следующему элементу */
  val it = Iterator(1, 2, 3, 4)
  val bit = it.buffered
  println(bit.head)
  println(bit.next)

  /** dropWhile - обрезать пока верно условие */
  /** takeWhile - взять пока верно условие */
  val words = List("the", "quick", "brown", "fox")
  val t1 = List ('a','a','a','a','b','c','c','a','a','d','e','e','e','e')
  val w1 = t1 dropWhile (_ == 'a')
  val w2 = t1 takeWhile (_ == 'a')
  println(w1.mkString(" "))
  println(w2.mkString(" "))

  /** Экстракторы */
  /** Листинг 26.1. Объект извлечения Email-строк */
  object EMail {
    // Метод вставки (необязательный)
    def apply(user: String, domain: String) = user + "@" + domain
    // Метод извлечения (обязательный)
    def unapply(str: String): Option[(String, String)] = {
      val parts = str split "@"
      if (parts.length == 2) Some(parts(0), parts(1)) else None
    }
  }

  val adr = EMail.apply("user", "domain")
  println(adr)
  println(EMail.unapply(adr))

  /** 26.3. Шаблоны без переменных или с одной переменной */
  /** Листинг 26.2. Объект извлечения дважды встречающейся строки */
  object Twice {
    def apply(s: String): String = s + s
    def unapply(s: String): Option[String] = {
      val length = s.length / 2
      val half = s.substring(0, length)
      if (half == s.substring(length)) Some(half)
      else None
    }
  }

  /** Например, объект-экстрактор, показанный в листинге 26.3, определяет строки,
      состоящие только из символов в верхнем регистре. */
  /** Листинг 26.3. Объект-экстрактор UpperCase */
  object UpperCase {
    def unapply(s: String): Boolean = s.toUpperCase == s }

  /** функция userTwiceUpper в своем коде поиска по
   * шаблону применяет вместе все ранее определенные экстракторы: */
  def userTwiceUpper(s: String) = s match {
    case EMail(Twice(x @ UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ =>
      "no match"
  }

  println(userTwiceUpper("DIDI@hotmail.com"))




}





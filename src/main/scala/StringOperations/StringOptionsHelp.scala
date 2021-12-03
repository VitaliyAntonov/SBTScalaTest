package StringOperations


/**
 * Методы работы со строками  https://data-flair.training/blogs/scala-string-method/
 */
object StringOptionsHelp extends App {

  val st = "Ayushi"

  /** 1. char charAt(int index) - Символ, по его номеру в строке */
  println("1. char charAt(int index)")
  val simAtIndex = st.charAt(1)
  println(simAtIndex)
  println("")

  /** 2. int compareTo(Object o) - при равенстве строк возвращает 0 */
  println("2. int compareTo(Object o)")
  val rez0 = st.compareTo("Ayushi")
  println(rez0)
  println("")

  /** 3. int compareTo(String anotherString) */
  /**
   * Это похоже на предыдущий, за исключением того, что он лексикографически
   * сравнивает две строки. Если они совпадают, возвращается 0.
   * В противном случае возвращается разница между ними
   * (количество символов меньше в более короткой строке или
   * максимальная разница ASCII между ними).
   */
  println("3. int compareTo(String anotherString)")
  println("Ayushi".compareTo("Ayushi "))
  println("Ayushi".compareTo("Ayushi"))
  println("Ayushi".compareTo("ayushi"))
  println("")

  /**
   * 4. int compareToIgnoreCase(String str)
   * int compareToIgnoreCase Scala String Method сравнивает две строки лексикографически,
   * игнорируя различия в регистре.
   */
  println("4. int compareToIgnoreCase(String str)")
  println("Ayushi".compareToIgnoreCase("ayushi"))
  println("Ayushi".compareToIgnoreCase("ayushi "))
  println("Ayushi".compareToIgnoreCase("aYushi"))
  println("")

  /** 5. String concat(String str) - слияние строк */
  println("5. String concat(String str) - слияние строк")
  println("Ayushi".concat("Sharma"))
  println("")

  /** 6. Boolean contentEquals(StringBuffer sb) - сравнение строк
   * contentEquals сравнивает строку с содержимым StringBuffer.
   * Если равно, возвращается true; в противном случае - false.*/
  println("6. Boolean contentEquals(StringBuffer sb) - сравнение строк")
  val a = new StringBuffer("Ayushi")
  println("Ayushi".contentEquals(a))
  println("ayushi".contentEquals(a))
  println("")

  /**
   * 7. Boolean endsWith(String suffix) - соответствие окончания строки
   * Этот метод Scala String возвращает true,
   * если строка заканчивается указанным суффиксом;
   * в противном случае - false.
   */
  println("7. Boolean endsWith(String suffix)")
  println("Ayushi".endsWith("shi"))
  println("Ayushi".endsWith("sha"))
  println("")


  println("qwe.asdf.png".lastIndexOfSlice("png"))
  println("qwe.asdf.png".lastIndexOfSlice("asd"))
  println("qwe.asdf.png".lastIndexOfSlice("frt"))


}





package StringOperations


/**  HELP
 * Методы работы со строками  https://data-flair.training/blogs/scala-string-method/
 */
object StringOptionsHelp extends App {

  val st = "Ayushi"

  /** 1. char charAt(int index) - Символ, по его номеру в строке */
  println("1. char charAt(int index)")
  val simAtIndex = st.charAt(1)
  println(simAtIndex)
  println("")

  /** 2. int compareTo(Object o)  - Сравнение
   *  - при равенстве строк возвращает 0 */
  println("2. int compareTo(Object o)")
  val rez0 = st.compareTo("Ayushi")
  println(rez0)
  println("")

  /** 3. int compareTo(String anotherString) - Сравнение */
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
   * 4. int compareToIgnoreCase(String str) - Сравнение
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
  println("7. Boolean endsWith(String suffix) - соответствие окончания строки")
  println("Ayushi".endsWith("shi"))
  println("Ayushi".endsWith("sha"))
  // проверка расширения в имени файла
  val fName = "0000000161_000000000328_.PNG"
  println(fName.toLowerCase.endsWith(".png"))
  // обрезка расширения в имени файла
  val fNameNoExt = fName.slice(0, fName.toLowerCase.lastIndexOfSlice(".png"))
  println(fNameNoExt)
  println("")


  /** 8. Boolean equals(Object anObject) - Сравнение
   * Этот метод Scala String возвращает true, если строка и объект равны;
   * в противном случае - ложь.
   */
  println("8. Boolean equals(Object anObject) - если равны, то true")
  val barr0 = Array('A','y','u','s','h','i')
  // b: Array[Char] = Array(A, y, u, s, h, i)
  println("Ayushi".equals(barr0))
  // res34: Boolean = false
  println("Ayushi".equals("ayushi"))
  // res35: Boolean = false
  println("Ayushi".equals("Ayushi"))
  // res36: Boolean = true
  val barr1 = Array("Ayushi")
  // b: Array[String] = Array(Ayushi)
  println("Ayushi".equals(barr1))
  // res37: Boolean = false
  println("Ayushi".equals(7))
  // res39: Boolean = false
  println("7".equals(7))
  // res40: Boolean = false
  println("Ayushi".equals("aYushi"))
  // res43: Boolean = false
  println("")

  /** 9. Boolean equalsIgnoreCase(String anotherString)
   * Это похоже на equals (), за исключением того,
   * что игнорирует регистр символов.
   */
  println("9. Boolean equalsIgnoreCase(String anotherString) - сравнение игнорирует регистр символов ")
  println("Ayushi".equalsIgnoreCase("aYushi"))
  // res42: Boolean = true
  println("")

  /** 10. byte getBytes() - строка в последовательность байтов
   * getBytes Scala String Method кодирует строку в последовательность байтов
   * и сохраняет ее в новый массив байтов.
   * Для этого используется кодировка платформы по умолчанию.
   * */
  println("10. byte getBytes() - строка в последовательность байтов")
  println("Ayushi".getBytes())
  // res44: Array[Byte] = Array(65, 121, 117, 115, 104, 105)
  println("ABCcba".getBytes())
  // res45: Array[Byte] = Array(65, 66, 67, 99, 98, 97)
  println(" ".getBytes())
  // res46: Array[Byte] = Array(32)
  println("")


  /** 11. byte[] getBytes(String charsetName) - извлечение в соответствии с кодировкой текста
   * С именованной кодировкой в качестве параметра getBytes
   * будет использовать эту кодировку для кодирования строки.
   * */
  println("11. byte[] getBytes(String charsetName) - извлечение в соответствии с кодировкой текста")
  println("Ayushi".getBytes("Unicode"))
  // res47: Array[Byte] = Array(-2, -1, 0, 65, 0, 121, 0, 117, 0, 115, 0, 104, 0, 105)
  println("Ayushi".getBytes("UTF-8"))
  // res48: Array[Byte] = Array(65, 121, 117, 115, 104, 105)
  /** Однако указание неверного имени кодировки приведет к ошибке: */
//  println("Ayushi".getBytes("Union"))
//  java.io.UnsupportedEncodingException: Union
//  at java.lang.StringCoding.encode(Unknown Source)
//  at java.lang.String.getBytes(Unknown Source)
//  ... 28 elided
  println("")

  /**
   * 12. void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
   *
   * Замена символов, символами взятыми из другой строки
   *
   * Этот метод Scala String копирует символы из строки в целевой массив символов.
   * scrBegin обозначает индекс, с которого начинается исходная строка, а
   * srcEnd обозначает индекс, которым нужно закончить в исходной строке.
   * dstBegin обозначает индекс, с которого нужно начинать в целевом массиве символов.
   * dst - это целевой массив символов.
   */
    println("12. Замена символов, символами взятыми из другой строки")
  val c:Array[Char]=Array('A','y','u','s','h','i')
  // c: Array[Char] = Array(A, y, u, s, h, i)
  val d="01234"
//  d: String = 01234
  d.getChars(2,4,c,2)
  println(c.mkString)
//  scala> c
//  res57: Array[Char] = Array(A, y, 2, 3, h, i)
  println("")

  /**
   * 13. int hashCode() - хэш-код для строки
   * Этот метод int hashCode возвращает хэш-код для строки.
   */
  println("13. int hashCode() - хэш-код для строки")
  println("Ayushi".hashCode())
//  res58: Int = 1976240247
  val name="Ayushi"
//  name: String = Ayushi
  println(name.hashCode())
//  res60: Int = 1976240247
  println("")



  /** Индекс первого в строке включения последовательности символов */
  println(fName.indexOf("_"))

  /** Индекс последнего в строке включения последовательности символов */
  println(fName.lastIndexOfSlice("_"))

  /** Добавление последовательности символов в начало строки */
  val newFileNameSeq = "file_" +: fName // - это последовательность IndexedSec
  val newFileName = newFileNameSeq.mkString  // Преобразование последовательности в строку
  println("newFileName = "+newFileName)
  println("StartAddedStr_".concat(fName)) // Слияние строк без явного преобразования в последовательность

  /**
   *   Возвращает индекс первого элемента в xs, удовлетворяющего условию p
   *   (существует несколько вариантов).
   */
  val index0 = newFileName.indexWhere(symbol => symbol >= '1' && symbol <= '9') // первая цифра в строке
  println("index0 = " + index0)

  /**
   * Возвращает длину самого длинного непрерывного сегмента элементов в
   * xs, начинающегося с xs(i), который удовлетворяет условию p.
   */
  val symLength = newFileName.segmentLength(symbol => symbol == '0', 0)
  println("symLength = " + symLength)

  println("qwe.asdf.png".lastIndexOfSlice(".png"))
  println("qwe.asdf.png".lastIndexOfSlice("asd"))
  println("qwe.asdf.png".lastIndexOfSlice("frt"))




}





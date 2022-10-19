package StringOperations

import ScalaHelp.HelpString
import UBTF.UbtfCoreCode







/**  HELP
 * Методы работы со строками  https://data-flair.training/blogs/scala-string-method/
 */
object StringOptionsHelp extends App with UbtfCoreCode with HelpString {

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
  println("Ayushi".getBytes)
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


  /**
   * 14. int indexOf(int ch) - индекс первого найденного символа
   * Этот Scala Sring возвращает индекс первого вхождения символа ch в строке.
   */
  println("14. int indexOf(int ch) - индекс первого найденного символа")
  println("Banana".indexOf('a'))
//  res61: Int = 1
  println("Banana".indexOf('n'))
//  res62: Int = 2
  println("")

  /**
   * 15. int indexOf(int ch, int fromIndex) - индекс первого найденного символа - поиск от указанной позиции
   * Это похоже на indexOf, за исключением того, что он начинает поиск с указанного нами индекса.
   */
  println("15. int indexOf(int ch, int fromIndex) - индекс первого найденного символа - поиск от указанной позиции")
  println("Banana".indexOf('a',2))
//  res63: Int = 3
  println("")

  /**
   * 16. int indexOf(String str) - индекс первого вхождения подстроки в строке
   * Этот метод Scala String возвращает индекс первого вхождения указанной нами подстроки в строке.
   */
  println("16. int indexOf(String str) - индекс первого вхождения подстроки в строке")
  println("Banana".indexOf("na"))
//  res64: Int = 2
  println("")

  /**
   * 17. int indexOf(String str, int fromIndex) - индекс подстроки в строке - поиск с указанной позиции
   * Как и другая версия indexOf для одиночного символа,
   * поиск начинается с указанного нами индекса.
   */
  println("17. int indexOf(String str, int fromIndex) - индекс подстроки в строке - поиск с указанной позиции")
  println("Banana".indexOf("na",3))
//  res66: Int = 4
  println("")

  /**
   * 18. String intern() - каноническое представление строкового объекта.
   * intern возвращает каноническое представление строкового объекта.
   */
  println("18. String intern() - каноническое представление строкового объекта.")
  println("Hello,\n\tWorld")
  println("Hello,\n\tWorld".intern())
//  res69: String =
  println("")

  /**
   * 19. int lastIndexOf(int ch) - индекс последнего вхождения символа
   * В отличие от indexOf, этот String Method возвращает
   * индекс последнего вхождения указанного символа.
   */
  println("19. int lastIndexOf(int ch) - индекс последнего вхождения символа")
  println("Banana".lastIndexOf('n'))
//  res70: Int = 4
  println("")

  /**
   * 20. int lastIndexOf(int ch, int fromIndex) - индекс последнего вхождения символа,
   * он начинает поиск с указанного нами индекса и выполняет поиск справа налево.
   */
  println("20. int lastIndexOf(int ch, int fromIndex) - индекс последнего вхождения символа")
  println("Banana".lastIndexOf('n',1))
//  res71: Int = -1
//  Here, it returns -1 because it couldn’t find ‘n’.
//  Здесь он возвращает -1, потому что не может найти "n"
  println("Banana".lastIndexOf('n',3))
  println("012345n7n90123".lastIndexOf('n',8))
//  res72: Int = 2
  println("")

  /**
   * 21. int lastIndexOf(String str) - индекс последнего вхождения подстроки в строку
   * Этот метод Scala String возвращает индекс последнего
   * вхождения указанной нами подстроки в строку.
   */
  println("21. int lastIndexOf(String str) - индекс последнего вхождения подстроки в строку")
  println("Bananaku".lastIndexOf("na"))
//  res73: Int = 4
  println("")

  /**
   * 22. int lastIndexOf(String str, int fromIndex) - индекс последнего вхождения подстроки в строку с условным концом строки
   * Этот строковый метод Scala похож на предыдущий, за исключением того,
   * что он начинает поиск с указанного нами индекса и выполняет поиск справа налево.
   */
  println("22. Индекс последнего вхождения подстроки в строку")
  println("Banana".lastIndexOf("na",3))
//  res74: Int = 2
  println("01234na7890".lastIndexOf("na",4))
//  res75: Int = 5
  println("Banana".lastIndexOf("na",1))
//  res76: Int = -1
  println("")

  /**
   * 23. int length() - длина строки
   * Этот метод Scala String Method просто возвращает длину строки.
   */
  println("23. int length() - длина строки")
  println("Ayushi!".length())
//  res77: Int = 7
  println("".length())
//  res78: Int = 0
  println("")

  /**
   * 24. Boolean matches(String regex) - проверка соответствия с регулярным выражением
   * Если строка соответствует указанному нами регулярному выражению,
   * возвращается истина; в противном случае - ложь.
   */
  println("24. Boolean matches(String regex) - проверка соответствия с регулярным выражением")
  println("Ayushi".matches(".i.*"))
//  res80: Boolean = false
  println("Ayushi".matches(".*i"))
//  res79: Boolean = true
  println("")

  /**
   * 25. Boolean regionMatches(boolean ignoreCase, int toffset, String other, int offset, int len)
   * Если две строковые области равны, возвращается истина;
   * в противном случае - ложь.
   * ignoreCase, если задано значение true, игнорирует различия в регистре.
   */
  println("25. regionMatches Если две строковые области равны, возвращается истина")
  println("Ayushi".regionMatches(true,0,"Ayushi",0,4))
//  res81: Boolean = true
  println("Ayushi".regionMatches(true,0,"Ayush",0,4))
//  res82: Boolean = true
  println("Ayushi".regionMatches(true,0,"ayush",0,3))
//  res83: Boolean = true
  println("Ayushi".regionMatches(true,0,"Ay",0,3))
//  res84: Boolean = false
  println("")

  /**
   * 26. Boolean regionMatches(int toffset, String other, int offset, int len)
   * Это похоже на предыдущий метод, за исключением того, что в нем нет ignoreCase.
   */
  println("26. regionMatches похоже на предыдущий метод, за исключением того, что в нем нет ignoreCase.")
  println("Ayushi".regionMatches(0,"Ayu",0,2))
//  res85: Boolean = true
  println("Ayushi".regionMatches(0,"Ayu",0,4))
//  res86: Boolean = false
  println("")

  /**
   * 27. String replace(char oldChar, char newChar)
   * Replace заменит все вхождения oldChar в строке на newChar и вернет результирующую строку.
   */
  println("27. replace заменит все вхождения oldChar в строке на newChar и вернет результирующую строку.")
  println("Ayushi sharma".replace('s','$'))
//  res87: String = Ayu$hi $harma
  println("Ayushi Sharma".replace('s','$'))
//  res88: String = Ayu$hi Sharma
  println("")

  /**
   * 28. String replaceAll(String regex, String replacement)
   * Это заменит каждую подстроку,
   * соответствующую регулярному выражению.
   * Он заменит его предоставленной нами строкой замены.
   */
  println("28. replaceAll заменит каждую подстроку, соответствующую регулярному выражению.")
  println("potdotnothotokayslot".replaceAll(".ot","**"))
//  res89: String = ********okays**
  println("")

  /**
   * 29. String replaceFirst(String regex, String replacement)
   * Если в приведенном выше примере мы хотим заменить только первое такое вхождение.
   */
  println("29. Если в приведенном выше примере мы хотим заменить только первое такое вхождение.")
  println("potdotnothotokayslot".replaceFirst(".ot","**"))
//  res90: String = **dotnothotokayslot
  println("")

  /**
   * 30. String[] split(String regex)
   * Этот метод Scala String разбивает строку вокруг совпадений
   * с указанным нами регулярным выражением. Он возвращает массив String.
   */
  println("30. split разбивает строку вокруг совпадений")
  println("xpotxdotynotzhotokayslot".split(".ot"))
//  res93: Array[String] = Array(x, x, y, z, okays)
  println("")

  /**
   * 31. String[] split(String regex, int limit)
   * Этот метод Scala String похож на split, за исключением того,
   * что мы можем ограничить количество членов для массива.
   * Таким образом, последний член - это оставшаяся часть строки.
   */
  println("31. Этот метод Scala String похож на split, с ограничением количества членов для массива.")
  println("xpotxdotynotzhotokayslot".split(".ot",2))
//  res94: Array[String] = Array(x, xdotynotzhotokayslot)
  println("xpotxdotynotzhotokayslot".split(".ot",5))
//  res95: Array[String] = Array(x, x, y, z, okayslot)
  println("")

  /**
   * 32. Boolean startsWith(String prefix)
   * Если строка начинается с указанного префикса, возвращается истина
   * в противном случае - ложь. Это похоже на endsWith.
   */
  println("32. Если строка начинается с указанного префикса, возвращается истина")
  println("Ayushi".startsWith("Ay"))
//  res97: Boolean = true
  println("Ayushi".startsWith(" A"))
//  res99: Boolean = false
  println("Ayushi".startsWith("ayu"))
//  res100: Boolean = false
  println("")

  /**
   * 33. Boolean startsWith(String prefix, int toffset)
   * Если строка начинается с указанного префикса в указанном нами индексе,
   * этот строковый метод Scala возвращает true; в противном случае - ложь.
   */
  println("33. Если строка начинается с указанного префикса в указанном нами индексе, true")
  println("Ayushi".startsWith("yu",1))
//  res101: Boolean = true
  println("")

  /**
   * 34. CharSequence subSequence(int beginIndex, int endIndex)
   * subSequence возвращает подпоследовательность из строки,
   * начиная с beginIndex и заканчивая endIndex.
   */
  println("34. subSequence возвращает подпоследовательность из строки, от индекса до индекса")
  val os100: CharSequence = "Ayushi".subSequence(1,4)
  println("Ayushi".subSequence(1,4))
//  res102: CharSequence = yus
  println("")

  /**
   * 35. String substring(int beginIndex)
   * substring возвращает содержимое строки, начинающейся с beginIndex.
   */
  println("35. substring возвращает содержимое строки, начинающейся с beginIndex.")
  val os101 = "Ayushi".substring(3)
  println("Ayushi".substring(3))
//  res103: String = shi
  println("")

  /**
   * 36. String substring(int beginIndex, int endIndex)
   * substring возвращает часть строки, начинающуюся с beginIndex и заканчивающуюся endIndex.
   * Подождите, разве это не то же самое, что и подпоследовательность?
   * В то время как один возвращает CharSequence, другой возвращает String.
   */
  println("36. substring возвращает часть строки, начинающуюся с beginIndex и заканчивающуюся endIndex.")
  var os110="Ayushi".subSequence(1,4)
//  a: CharSequence = yus
  var os111 = "Ayushi".substring(1,4)
//  b: String = yus
  println("")

  /**
   * 37. char[] toCharArray()
   * 37. toCharArray  преобразует строку в CharArray, а затем вернет ее.
   */
  println("37. toCharArray  преобразует строку в CharArray, а затем вернет ее.")
  println("Ayushi".toCharArray())
//  res104: Array[Char] = Array(A, y, u, s, h, i)
  println("")

  /**
   * 38. String toLowerCase()
   * Этот строковый метод в Scala преобразует все символы в строке в нижний регистр,
   * а затем возвращает результирующую строку.
   */
  println("38. преобразует все символы в строке в нижний регистр")
  println("AyU$#I!".toLowerCase())
//  res105: String = ayu$#i!
  println("")

  /**
   * 39. String toLowerCase(Locale locale)
   * Это похоже на toLowerCase, за исключением того, что мы можем указать локаль,
   * по правилам которой нужно следовать.
   */
  println("39. похоже на toLowerCase. Можем указать локаль, по правилам которой нужно следовать.")
  println("")

  /**
   * 40. String toString()
   * toString возвращает сам объект String.
   */
  println("40. toString возвращает сам объект String.")
  println("7".toString())
//  res108: String = 7
  println("")

  /**
   * 41. String toUpperCase()
   * toUpperCase похож на toLowerCase и преобразует все символы в строке в верхний регистр.
   */
  println("41. toUpperCase похож на toLowerCase и преобразует все символы в строке в верхний регистр.")
  println("Ayushi".toUpperCase())
//  res109: String = AYUSHI
  println("")

  /**
   * 42. String toUpperCase(Locale locale)
   * Это похоже на предыдущий метод, за исключением того, что он будет следовать правилам данной локали.
   */

  /**
   * 43. String trim()
   * Команда Trim удалит начальные и конечные пробелы из строки.
   */
  println("43. Команда Trim удалит начальные и конечные пробелы из строки.")
  println("         Ayushi ".trim())
//  res110: String = Ayushi
  println("")






  /** Индекс первого в строке включения последовательности символов */
  println(fName.indexOf("_"))

  /** Индекс последнего в строке включения последовательности символов */
  println(fName.lastIndexOfSlice("_"))

  /** Добавление последовательности символов в начало строки */
  val newFileNameSeq = "file000_" +: fName // - это последовательность IndexedSec
  val newFileName = newFileNameSeq.mkString  // Преобразование последовательности в строку
  println("newFileName = "+newFileName)
  println("StartAddedStr_".concat(fName)) // Слияние строк без явного преобразования в последовательность

  /**
   *   Возвращает индекс первого элемента в xs, удовлетворяющего условию p
   *   (существует несколько вариантов).
   */
  val index0 = newFileName.indexWhere(symbol => symbol >= '0' && symbol <= '9') // первая цифра в строке
  println(newFileName)
  println("index0 = " + index0)

  /**
   * Возвращает длину самого длинного непрерывного сегмента элементов в
   * xs, начинающегося с xs(i), который удовлетворяет условию p.
   */
  val symLength = "000_0000".segmentLength(symbol => symbol == '0', 4)
  println("symLength = " + symLength)

  println("qwe.asdf.png".lastIndexOfSlice("sdf"))
  println("qwe.asdf.png".lastIndexOfSlice("asd"))
  println("qwe.asdf.png".lastIndexOfSlice("frt"))


  /** ------------ Вычисление MD5-хэш кода строки --------------- */
  import java.security.MessageDigest

  def md5(s: String) = {
    MessageDigest.getInstance("MD5").digest(s.getBytes)
  }

  val passMd5 = md5("1")

//  println(arrayByteToHexString(passMd5))

  println("Password = " + "1" + "  Hash = " + arrayByteToHexString(passMd5))
  /** ----------------------------------------------------------- */

  help_String.h1_charAt
  help_String.h2_compareTo
  help_String.h8_equals
  val rez1234 = "asd".compareTo("asd")

}


object testStrOption extends App{
  val txt = "Ч Е Р Н О Е М О Р Е"
  val out = txt.split(" ")//.filter(_ != " ")
  println("в массиве " + out.length + " элементов")
  for(t <- out){
    println(t)
  }

}


package ScalaHelp



/**  HelpString
 * Методы работы со строками  https://data-flair.training/blogs/scala-string-method/
 */
trait HelpString {
  object help_String {

    /** 1. val ch: Char = inString.charAt(index)
     * // Символ, по его номеру в строке */
    def h1_charAt = {}

    /** 2. val rez: Int = oneStr.compareTo(twoStr: String)
     * // Сравнение - при равенстве строк возвращает 0 */
    def h2_compareTo = {}

    /** 4. val rez: Int = oneStr.compareToIgnoreCase(twoStr: String)
     * // Сравнение игнорируя регистр букв - при равенстве строк возвращает 0 */
    def h4_compareToIgnoreCase = {}

    /** 5. val rez: String = oneStr.concat(twoStr: String) // Слияние строк */
    def h5_concat = {}

    /** 6. val rez: Boolean = oneStr.contentEquals(twoStr: StringBuffer) // сравнение строк
     * сравнивает строку с содержимым StringBuffer */
    def h6_contentEquals = {}

    /** 7. val rez: Boolean = oneStr.endsWith(twoStr: String) // соответствие окончания
     * строки oneStr суффиксу twoStr */
    def h7_endsWith = {
      println("Ayushi".endsWith("shi"))
    }

    /** 8. val rez: Boolean = oneStr.equals(anObject: Object) // Сравнение строки и объекта.
     * equals возвращает true, если строка и объект(не равный нулю) равны,
     * в противном случае - ложь. */
    def h8_equals = {}

    /** 9. Boolean equalsIgnoreCase(String anotherString) // Сравнение строки и объекта
     * equalsIgnoreCase похоже на equals, за исключением того,
     * что игнорирует регистр символов. */
    def h9_equalsIgnoreCase = {}

    /** 10. val rez: Array[Byte] = oneStr.getBytes() // преобразование строки oneStr в массив байтов */
    def h10_getBytes = {}



  }
}

object StringOptionsHelp extends App with HelpString {
  help_String.h7_endsWith
}




package ScalaLangElements

object EnumerationHelp extends App {
  /** Номер, присвоенный константе в классе Enumeration  Value */
  println(FontDepth.HEAVY.id)

  /** Строка(Тег), присвоенный в классе Enumeration  Value */
  println(FontDepth.HEAVY)

  /** equals Сравнение двух значений в классе Enumeration */
  println(FontDepth.HEAVY.equals(FontDepth.HEAVY))

  /** Перебор значений в перечислении Enumeration */
  for( fDepth <- FontDepth.values) print(fDepth + " " + fDepth.id + "\n")

  println(FontDepth.values)
}



/**
 * Возможные значения для толщины контура шрифта
 */
object FontDepth extends Enumeration{
  val THIN = Value(100, "THIN")
  val EXTRALIGHT = Value(200, "EXTRALIGHT")
  val LIGHT = Value(300, "LIGHT")
  val NORMAL = Value(400, "NORMAL")
  val MEDIUM = Value(500, "MEDIUM")
  val SEMIBOLD = Value(600, "SEMIBOLD")
  val BOLD = Value(700, "BOLD")
  val EXTRABOLD = Value(800, "EXTRABOLD")
  val HEAVY = Value(900, "HEAVY")
}



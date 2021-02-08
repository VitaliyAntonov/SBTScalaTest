

// topic 15. Case-классы и поиск по шаблону

// topic Листинг 15.1. Определение case-классов
abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object exprDemo{
  val v = Var("x")
  val op = BinOp("+", Number(1), v)
  println(v.name)
  println(op.left)
  println(op)

  /** Поскольку метод == в Scala передает полномочия методу equals, это означает, что
  * элементы case-классов всегда сравниваются структурно: */
               println("(op.right == Var(\"x\")) = " + (op.right == Var("x")))

  /** можно создать операцию, похожую на op во всем,
  * кроме того, что будет изменен параметр operator: */
  op.copy(operator = "-")
  println("simplifyTop(op) = " + simplifyTop(op))

// topic Поиск по шаблону

  // topic Листинг 15.2. Функция simplifyTop, выполняющая поиск по шаблону
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e // Двойное отрицание
    case BinOp("+", e, Number(0)) => e // Прибавление нуля
    case BinOp("*", e, Number(1)) => e // Умножение на единицу
    case _ => expr
  }

  // https://docs.scala-lang.org/ru/tour/pattern-matching.html
  // topic споставление с образцом
  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }
  println(matchTest(3)) // many
  println(matchTest(1))
//  matchTest(1)  // one


  def showNotification(notification: Notification): String = {
    notification match {
      case Email(email, title, _) =>
        s"You got an email from $email with title: $title"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"
      case VoiceRecording(name, link) =>
        s"you received a Voice Recording from $name! Click the link to hear it: $link"
    }
  }

  // topic Ограждения примеров
  def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
    notification match {
      case Email(email, _, _) if importantPeopleInfo.contains(email) =>
        "You got an email from special someone!"
      case SMS(number, _) if importantPeopleInfo.contains(number) =>
        "You got an SMS from special someone!"
      case other =>
        showNotification(other) // в этом варианте считается подходящими параметры любого типа. Значит этот вариант выполняется во всех случаях и передает исходный параметр в функцию showNotification
    }
  }

  abstract class Notification
  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification
  case class VoiceRecording(contactName: String, link: String) extends Notification

  val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")

  val someSms = SMS("867-5309", "Are you there?")
  val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
  val importantEmail = Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
  val importantSms = SMS("867-5309", "I'm here! Where are you?")

  println(showImportantNotification(someSms, importantPeopleInfo))
  println(showImportantNotification(someVoiceRecording, importantPeopleInfo))
  println(showImportantNotification(importantEmail, importantPeopleInfo))
  println(showImportantNotification(importantSms, importantPeopleInfo))

// topic Универсальные шаблоны сопоставления

  // topic   Листинг 15.4. Поиск по шаблону с универсальными шаблонами сопоставления
  def simExpr(expr: Expr): Unit = expr match {
    case BinOp(_, _, _) => println(expr + " is a binary operation")
    case _ => println("It's something else")
  }

  // topic   Листинг 15.5. Поиск по шаблону с использованием шаблонов-констант
  def describe(x: Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

  println()
  println("Поиск по шаблону с использованием шаблонов-констант")
  println("describe(5) = " + describe(5))
  println("describe(true) = " + describe(true))
  println("describe(\"hello\") = " + describe("hello"))
  println("describe(Nil) = " + describe(Nil))
  println("describe(List(1,2,3)) = " + describe(List(1,2,3)))


  // topic Шаблоны-переменные
  // topic Листинг 15.6. Поиск по шаблону с использованием шаблона-переменной
  def describe1(x: Any) = x match {
    case 0 => "zero"
    case somethingElse => "not zero:" + somethingElse
  }

  // topic Шаблоны-конструкторы
  // topic Листинг 15.7. Поиск по шаблону с использованием шаблона-конструктора
  def describe2(x: Any) = x match {
    case BinOp("+", e, Number(0)) => println("a deep match")
    case _ =>
  }

  // topic Шаблоны-последовательности
  // topic Листинг 15.8. Шаблон-последовательность фиксированной длины
  def describe3(x: Any) = x match {
    case List(0, _, _) => println("found it")
    case _ =>
  }
  // topic Листинг 15.9. Шаблон-последовательность произвольной длины
  def describe4(x: Any) = x match {
    case List(0, _*) => println("found it")
    case _ =>
  }

  // topic Шаблоны-кортежи
  // topic Листинг 15.10. Поиск по шаблону с использованием шаблона-кортежа
  def tupleDemo(expr: Any) =
    expr match {
      case (a, b, c) => println("matched " + a + b + c)
      case _ =>
    }
  println("\n Шаблоны-кортежи")
  tupleDemo(777,"A",3)

  // topic Типизированные шаблоны
  // topic Листинг 15.11. Поиск по шаблону с использованием типизированных шаблонов
  def generalSize(x: Any) = x match {
    case s: String => s.length
    case m: Map[_, _] => m.size
    case d: Double => d.toString
    case _ => -1
  }

  println("generalSize(\"abc\") = " + generalSize("abc"))
  println("generalSize(Map(1 -> 'a', 2 -> 'b')) = "+ generalSize(Map(1 -> 'a', 2 -> 'b')))
  println("generalSize(math.Pi) = " + generalSize(math.Pi))

  // topic Листинг 15.13. Шаблон с привязанной переменной (посредством использования знака @)
  def describe5(x: Any) = x match {
    case UnOp("abs", e @ UnOp("abs", _)) => e
    case _ => println("nothing")
  }

// topic 15.3. Ограничители шаблонов
  // topic Листинг 15.14. Поиск по шаблону с применением ограничителя шаблонов
  def simplifyAdd(e: Expr) = e match {
    case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2))
    case _ => e     }

  // topic наложение шаблонов
  // topic Листинг 15.15. Выражение соответствия, где порядок следования вариантов имеет значение
  def simplifyAll(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => simplifyAll(e) // '-' является своей собственной обратной величиной
    case BinOp("+", e, Number(0)) => simplifyAll(e) // '0' нейтральный элемент для '+'
    case BinOp("*", e, Number(1)) => simplifyAll(e) // '1' нейтральный элемент для '*'
    case UnOp(op, e) => UnOp(op, simplifyAll(e))
    case BinOp(op, l, r) => BinOp(op, simplifyAll(l), simplifyAll(r))
    case _ => expr
  }



}
















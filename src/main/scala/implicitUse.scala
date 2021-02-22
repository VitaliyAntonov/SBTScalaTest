import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JButton


object iButon {
  val button = new JButton

  implicit def function2ActionListener(f: ActionEvent => Unit) =
    new ActionListener {
      def actionPerformed(event: ActionEvent) = f(event)
    }

  button.addActionListener(
    function2ActionListener(
      (_: ActionEvent) => println("pressed!")
    )
  )

  /** использование встроенного преобразования типа из <scala.Predef>
   * implicit def int2double(x: Int):Double = x.toDouble
   */
  val x: Int = 0
  val y: Double = 0
  val z = x + y
  println(z)

  // topic 21.5. Подразумеваемые параметры

/**  Листинг 21.1. Подразумеваемый список параметров с несколькими
  параметрами */
  class PreferredPrompt(val preference: String)
  class PreferredDrink(val preference: String)
  object Greeter {
    def greet(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink) = {
      println("Welcome, " + name + ". The system is ready.")
      print("But while you work, ")
      println("why not enjoy a cup of " + drink.preference + "?")
      println(prompt.preference)
    }
  }
  object JoesPrefs {
    implicit val prompt = new PreferredPrompt("Yes, master> ")
    implicit val drink = new PreferredDrink("tea")
  }

  import JoesPrefs._ /** помещаем подразумеваемые переменные в область видимости */
  Greeter.greet("Масяня")

  /** Листинг 21.2. Функция с верхним ограничителем
   * которая возвращает максимальный элемент из переданного списка.
   */
  def maxListOrdering[T](elements: List[T])(ordering: Ordering[T]): T =
    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest => val maxRest = maxListOrdering(rest)(ordering)
        if (ordering.gt(x, maxRest)) x
        else maxRest
    }

  /** Листинг 21.3. Функция с подразумеваемым параметром */
  def maxListImpParm[T](elements: List[T])(implicit ordering: Ordering[T]): T =
    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListImpParm(rest)(ordering)
        if (ordering.gt(x, maxRest)) x
        else maxRest
    }
  val max = maxListImpParm(List(1,5,10,3))
  println("max = " + max)
  val max1 = maxListImpParm(List(1.5, 5.2, 10.7, 3.14159))
  println("max1 = " + max1)
  val max2 = maxListImpParm(List("one", "two", "three"))
  println("max2 = " + max2)


  /** Листинг 21.4. Функция, использующая подразумеваемый параметр внутри себя */
  def maxList[T](elements: List[T]) (implicit ordering: Ordering[T]): T =
    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest)       /** здесь(ordering) подразумевается */
        if (ordering.gt(x, maxRest)) x    /** а этот ordering по-прежнему  */
        else maxRest                      /** указан явно */
    }

  /** Листинг 21.6. Функция с контекстным ограничителем topic [T : Ordering]*/
  def maxList1[T : Ordering](elements: List[T]): T =
    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest)
        if (implicitly[Ordering[T]].gt(x, maxRest)) x
        else maxRest
    }
}


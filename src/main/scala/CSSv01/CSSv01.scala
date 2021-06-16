package CSSv01

import CSSv01.Tags.tagsCSS

import java.io.PrintWriter
import scala.Array.ofDim
import scala.collection.mutable.ArrayBuffer


/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */


object Order{
  protected class Order{var buffer = ""}
  /** Добавляем в Order свойство */
  def proToOrder(name: String) = {in.buffer += "  " + name + ": "}
  /** Добавляем в Order атрибут */
  def attToOrder(name: String) = {in.buffer += name + " ;"}
  /** Enter */
  def enter = {in.buffer += "\n"}
  /** Якорь. Вызовом метода Order.row.hear входим в низ(класс Row) классов свойств CSS */
  val row = new Row
  /** Якорь для обращения к текущему Ордеру. Пример: in.buffer */
  var in: Order = null /** якорь */
  /** Создание нового Ордера */
  def create = {in = new Order; row} /** ЯКОРЬ Создан */
}

/** Сюда необходимо прийти при начале ордера и после закрытия фигурных скобок */
class Selector{
  /** Селекторы по тегу */
  def p = {}
  def span = {}
  def div = {}
  /** Селектор по классу "." */
  def sCl(className: String) = {}
  /** Селектор по идентификатору "#" (id) */
  def \# = {}
  /** Универсальный селектор "*" */
  def \* = {}
  /** Селектор по атрибуту */
  def sAtr(atr: String) = {}
  /** Псевдоклассы ":link"*/
  def link = {}
  /** Группировка селекторов - аналог записи через запятую */
  def \& = {}
  /** Группировка селекторов - последовательная запись без пробела */
  /** Символы комбинаторы: Пробел, ">", "+", "~" */
  /** Пробел */
  def \\ = {}
  /** > */
  def \> = {}
  /** + */
  def \+ = {}
  /** ~ */
  def \~ = {}

}
object Selector{


}

/** =============================================================== */
class align_content extends align_items{
  private val x = new Attribute
  class Attribute{
    def stretch = {Order.attToOrder("stretch"); Order.row }
    def center = {Order.attToOrder("center"); Order.row }
    def flex_start = {Order.attToOrder("flex-start"); Order.row }
    def flex_end = {Order.attToOrder("flex-end"); Order.row }
    def space_between = {Order.attToOrder("space-between"); Order.row }
    def space_around = {Order.attToOrder("space-around"); Order.row }
    def space_evenly = {Order.attToOrder("space-evenly"); Order.row }
    def initial = {Order.attToOrder("initial"); Order.row }
    def inherit = {Order.attToOrder("inherit"); Order.row }
  }
  def align_content = { Order.proToOrder("align-content"); x }
}

class align_items extends align_self{
  private val x = new Attribute
  class Attribute{
    def stretch = {Order.attToOrder("stretch"); Order.row }
    def center = {Order.attToOrder("center"); Order.row }
    def flex_start = {Order.attToOrder("flex-start"); Order.row }
    def flex_end = {Order.attToOrder("flex-end"); Order.row }
    def baseline = {Order.attToOrder("baseline"); Order.row }
    def initial = {Order.attToOrder("initial"); Order.row }
    def inherit = {Order.attToOrder("inherit"); Order.row }
  }
  def align_items = { Order.proToOrder("align-items"); x }
}

class align_self extends all{
  private val x = new Attribute
  class Attribute{
    def auto = {Order.attToOrder("auto"); Order.row }
    def stretch = {Order.attToOrder("stretch"); Order.row }
    def center = {Order.attToOrder("center"); Order.row }
    def flex_start = {Order.attToOrder("flex-start"); Order.row }
    def flex_end = {Order.attToOrder("flex-end"); Order.row }
    def baseline = {Order.attToOrder("baseline"); Order.row }
    def initial = {Order.attToOrder("initial"); Order.row }
    def inherit = {Order.attToOrder("inherit"); Order.row }
  }
  def align_self = { Order.proToOrder("align-self"); x }
}

class all {
  private val x = new Attribute
  class Attribute{
    def initial = {Order.attToOrder("initial"); Order.row }
    def inherit = {Order.attToOrder("inherit"); Order.row }
    def unset = {Order.attToOrder("unset"); Order.row }
  }
  def all = { Order.proToOrder("all"); x }
}

/** =============================================================== */

/** Класс для входа в Свойства CSS - начало строки CSS кода в фигурных скобках
 * Сюда приходим после метода замены фигурных скобок */
class Row extends align_content{
  /** Enter и Табуляция в начале строки */
  def > = {Order.enter; this}
  /** Начало CSS Ордера */
  def <<|  =  {Order.in.buffer = "{ "; this}
  /** Окончание CSS Ордера */
  def |>>  =  {Order.in.buffer += "}"}


  /** Замена левой фигурной скобки == { */
  def << = {Order.in.buffer += "{"}
  /** Замена правой фигурной скобки == } */
  def >> = {Order.in.buffer += "}"}
  /** Замена символа Enter == |  */
  def | = {Order.in.buffer += "\n"}

}


/** Генератор CSS классов для свойств и атрибутов */
object CSSLibGenerate {
  /** Верхняя часть класса с имененм CSS свойства */
  var classesTemplateCSShead = ""
  /** следующее в списке CSS свойств для наследования - вся строка */
  var nextExtends = ""
  /** набор атрибутов генерируемого свойства CSS */
  var classesTemplateCSSatt = ""
  /** нижняя часть класса с именем свойства CSS в качестве метода */
  var classesTemplateCSSfooter = ""
  /** Буфер для формирования сгенерированного файла */
  var buffer = ""
  /** Имя свойства CSS из базы tagsCSS */
  var nameProperty = ""
  /** Имя свойства CSS с заменой знака минус на знак нижнего подчёркивания */
  var scalaNameProperty = ""
  /** имя следующего свойства */
  var scalaNextPropertyName = ""
  /** Имя атрибута */
  var nameAttribute = ""
  /** Имя атрибута с заменой символа минус на символ подчёркивания */
  var scalaNameAttribute = ""


  def nameToScala(name: String): String = {
    var s = ""
    for(i <- 0 until name.length){
      val sim = name.slice(i, i+1)
      if(sim != "-") s += sim
      else s += "_"
    }
    s
  }

  /** Генерация шаблона классов свойств CSS */
  def generatePropertyClass = {
    for(i <- tagsCSS.pro.indices){
      /** имя свойства CSS для названия класса */
      nameProperty = tagsCSS.pro(i)(0)
      scalaNameProperty = nameToScala(nameProperty)
      /** для последнго свойства в списке наследование не делаем */
      if(i < (tagsCSS.pro.length - 1)){
        scalaNextPropertyName = nameToScala(tagsCSS.pro(i+1)(0))
        nextExtends = s"extends $scalaNextPropertyName"
      }
      else nextExtends = ""

      /** определяем верхнюю часть класса */
      classesTemplateCSShead = {
        s"""
           |class $scalaNameProperty $nextExtends{
           |  private val x = new Attribute
           |  class Attribute{
           |""".stripMargin
      }
      buffer += classesTemplateCSShead

      for(k <- 1 until tagsCSS.pro(i).length){
        nameAttribute = tagsCSS.pro(i)(k)
        scalaNameAttribute = nameToScala(nameAttribute)
        /** Определяем атрибут */
        classesTemplateCSSatt = {
          s"""    def $scalaNameAttribute = {Order.attToOrder("$nameAttribute"); Order.row }
             |""".stripMargin
        }

        buffer += classesTemplateCSSatt
      }
      /** Определяем нижнюю часть класса */
      classesTemplateCSSfooter = {
        s"""  }
           |  def $scalaNameProperty = { Order.proToOrder("$nameProperty"); x }
           |}
           |""".stripMargin
      }
      buffer += classesTemplateCSSfooter
    }
  }

  /** Сохранение полученного шаблона в файл */
  def saveTemplate(fileName: String) = {
    val out = new PrintWriter(fileName)
    out.println(buffer)
    out.close()
  }


}



object HierarchyTest5{

  /** Получение имени класса */
//  def name = this.getClass.getSimpleName

  val ord = Order.create
  ord.<<|.align_content.space_around.>

  println(Order.in.buffer)

  val a = ArrayBuffer.fill(2,2)("a")
  println(a.mkString(" "))
  a(1)(1) = "b"
  println(a.mkString(" "))
  a(0) = ArrayBuffer("1", "2", "3")
  println(a.mkString(" "))

  val b = ofDim[Int](3,3)
  val c = ArrayBuffer[Int](3,3)


  /** Генерация шаблона */
  CSSLibGenerate.generatePropertyClass
  /** Сохранение шаблона в файл */
  CSSLibGenerate.saveTemplate("TemplateTagsCSS.scala")

}



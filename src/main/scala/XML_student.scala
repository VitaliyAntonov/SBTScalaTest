

//import org.w3c.dom.{Attr, Document, Element, NamedNodeMap, Node, NodeList, TypeInfo, UserDataHandler}
//import org.w3c.dom.Element
//import scala.xml._

//import org.w3c.dom.Element


object testXml{
  val foo = <foo><bar type="greet">hi</bar><bar type="count">1</bar><bar type="color">yellow</bar></foo>

  val getBar = foo \ "bar"

  // topic Безымянный класс образуется как объект абстрактного класса

  val therm = new CCTherm {
    val description = "hot dog #5"
    val yearMade = 1952
    val dateObtained = "March 14, 2006"
    val bookPrice = 2199
    val purchasePrice = 500
    val condition = 9
  }
  /**  <toXML> topic фиксация полей объета в XML **/
  println( therm.toXML )

  /**  <text> topic извлечение текста из тегов XML **/
  println( therm.toXML.text )

  /** <\> topic подчиненный элемент по имени его тега **/
  println( therm.toXML \ "description")
  val aXml = <a><b><c>hello</c></b></a>
  println("aXml \\ \"a\" = " + aXml \ "a")
  println("aXml \\ \"b\" = " + aXml \ "b")
  println("aXml \\ \"c\" = " + aXml \ "c")

  /** <\\> topic углубленный поиск через несколько подчиненных элементов **/
  println("aXml \\\\ \"a\" = " + aXml \\ "a")
  println("aXml \\\\ \"b\" = " + aXml \\ "b")
  println("aXml \\\\ \"c\" = " + aXml \\ "c")


//  val sxml: Element = <a id ="hello"> Hello ! </a>
//  println(sxml)
  val sxml = <a id ="hello"> Hello ! </a>
  val id = sxml.indexOf()
  println(id)

  var s =  <html class="body">   Hello </html> <style> myStyle </style>
  val s1 = s.text
  println(s)
  println(s1)
}


abstract class CCTherm {
  val description: String
  val yearMade: Int
  val dateObtained: String
  val bookPrice: Int
  // в центах США
  val purchasePrice: Int // в центах США
  val condition: Int
  // от 1 до 10
  override def toString = description

  def toXML =
    <cctherm>
      <description>{description}</description>
      <yearMade>{yearMade}</yearMade>
      <dateObtained>{dateObtained}</dateObtained>
      <bookPrice>{bookPrice}</bookPrice>
      <purchasePrice>{purchasePrice}</purchasePrice>
      <condition>{condition}</condition>
    </cctherm>
}








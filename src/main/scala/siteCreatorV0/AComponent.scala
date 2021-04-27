package siteCreatorV0

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @author Виталий Антонов @date 4/21/21
 *         kaligraf@yandex.ru
 * */
abstract class AComponent {

}


class HTag(tag: String) extends Enumeration {

  val allStack = ListBuffer[mutable.Stack[String]]()

  val testLb = ListBuffer[String]()

  val stackTags = mutable.Stack[String]()
  def oTag = {stackTags.push(tag); s"<$tag>"}
  def cTag = {s"</$tag>"}


  def iTag(content: String) = {
    oTag + content + cTag
  }

  /** Создание нового стека */
  def << = { allStack += stackTags; this}

  /** Закрытие стека */
  def >> = {
    if (allStack.nonEmpty) {
      val end = allStack.length - 1 /** последний стек */
      if (allStack(end).nonEmpty) for (i <- stackTags) {
        val a = stackTags.pop
      }
    }
  }


  /** Закрытие открытых тегов */
  def tagClose = {}

}


object TagTest{
  val p = new HTag("p")
  val span = new HTag("span")
  val div = new HTag("div")

  val html = div.oTag + span.oTag + "a" + span.cTag + div.cTag

  println(html)

  p.stackTags.push("p1")
  p.stackTags.push("p2")
  println(p.stackTags.length)
  p.stackTags.pop
  println(p.stackTags.length)

  p.testLb += "p1"
  p.testLb += "p2"
  println(p.testLb.length)
  println(p.testLb.mkString(" "))
  p.testLb.dropRightInPlace(1)
  println(p.testLb.length)
  println(p.testLb.mkString(" "))

}


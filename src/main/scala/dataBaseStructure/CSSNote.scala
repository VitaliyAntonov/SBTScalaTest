package dataBaseStructure

import scala.collection.mutable

/**
 * @author Виталий Антонов @date 6/12/21
 *         kaligraf@yandex.ru
 *
 * Краткие описания к тегам
 * */

object CSSNote {
  val noteMap = mutable.Map[String, String]()

  noteMap += ("background-attachment" ->
    s"""В background-attachment наборах свойств
       |прокручивается ли фоновое изображение с
       |остальной частьюстраницы, или фиксировано""".stripMargin)

  noteMap += ("background-blend-mode" ->
    s"""Свойство определяет режим смешивания каждого фонового
       |слоя (цвета и / или изображения)""".stripMargin)

  noteMap += ("background_clip" ->
    s"""Свойство определяет, насколько фон (цвет или изображение)
       |должен распространяться внутри элемента""".stripMargin)
}

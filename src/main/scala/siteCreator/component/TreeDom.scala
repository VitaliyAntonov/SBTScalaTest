package siteCreator.component

import scala.collection.mutable.ArrayBuffer

/**
 * @author Виталий Антонов @date 6/8/21
 *         kaligraf@yandex.ru
 * */

/** Класс для фиксации DOM структуры при создании HTML компонентов */
class TreeDom {
  /** Массив для хранения модели DOM HTML страницы
   * 1 - уровень вложенности
   * 2 - ID при наличии
   * 3 - массив имён селекторов класса */
    val dom = new ArrayBuffer[(Int, String, ArrayBuffer[String])]()
}

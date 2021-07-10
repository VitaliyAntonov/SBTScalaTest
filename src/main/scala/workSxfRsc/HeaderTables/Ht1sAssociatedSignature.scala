package workSxfRsc.HeaderTables

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/9/21
  **/
class Ht1sAssociatedSignature {

}


/**
  * Связанная подпись объекта определяет шрифт, предназначенный для нанесения подписей объекта,
  * текст которых содержится в качестве семантической характеристики этого объекта.
  * Описание связанных подписей имеет следующий вид:
  */


object Ht1sAssociatedSignature {

  /** Идентификационный код связанной подписи. Неизменяемый уникальный номер подписи */
  val assSignatureId = TabOFL(0, 4)

  /** Классификационный код семантики. Код семантики объекта, содержащей текст подписи */
  val semanticObjId = TabOFL(4, 4)

  /** Постоянный префикс для подписи. В байтах */
  val signaturePrefix = TabOFL(8, 7)

  /** Количество десятичных знаков после запятой. Используется при печати подписи */
  val signsAfterPoint = TabOFL(15, 1)

  /** ИТОГО:  16 байт  */

}







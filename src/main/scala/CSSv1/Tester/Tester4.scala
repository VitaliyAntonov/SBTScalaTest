package CSSv1.Tester


import scala.language.postfixOps /** постфиксная нотация **/

class overType{
  def overTypeMetod = {}
}

/** Метод должен возвращать свой тип */
class ReturnType extends overType{

  def metod1 = this
  def metodOver = {overTypeMetod; this}
}


object Test4{

  /** Свой тип имеет экземпляр класса - создание экземпляра через new */
  val np = new ReturnType
  /** Метод того же класса возвращает тот-же тип что и экземпляр этого класса */
  val np1 = np.overTypeMetod
  /** методы  класса могет вызвать только собственные методы и методы вышестоящего класса*/

  /** При вызове метода верхнего класса тип цепочки методов соответствует типу верхнего класса
   * и собственные методы экземпляра становятся недоступны */

  /**
   * Иерархия CSS тегов
   *
   *  Верхний класс
   *  - EndOrder -    содержит метод EndOrder
   *  - ENTER -       Метод ENTER можно вызвать после любого тега в конце строки
   *  - Attribute -   Класс содержит методы для тегов(атрибуиы, константы CSS, расположенных в конце строки
   *  - Property -    Класс содержит методы для свойств
   *  - NewRow  -     Нижний класс для доступа к свойствам с начала строки
   *  Нижний класс
   */



}
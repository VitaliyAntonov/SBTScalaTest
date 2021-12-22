package UBTF


/** Общий класс типов полей данных формата UBTF */
abstract class UType extends UbtfCoreCode {

  /** Преобразование пришедшего значения в массив типа Array[Byte] */
  protected def tArrByte: Array[Byte]

  /** Значение массива, полученного из пришедшего типа */
  val arrB: Array[Byte]

  /** Длина массива для данного типа данных */
  def tLength: Int

}

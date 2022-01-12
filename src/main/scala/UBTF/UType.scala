package UBTF

/**
 * Поле данных UBTF формата => FieldUBTF
 * 1 - указатель длины  (1...8 байт)
 * 2 - указатель типа   (1...8 байт)
 * 3 - само поле с данными - последовательность байт, загружаемая в массив Array[Byte]
 */


/** Общий класс типов полей данных формата UBTF */
abstract class UType extends UbtfCoreCode {

  /** Преобразование пришедшего значения в массив типа Array[Byte] */
  protected def tArrByte: Array[Byte]

  /** Значение массива, полученного из пришедшего типа */
  val arrB: Array[Byte]

  /** Длина массива для данного типа данных */
  def tLength: Int

}

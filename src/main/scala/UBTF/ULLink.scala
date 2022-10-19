package UBTF



/**
 * Указатель длины для поля данных
 * @param lengthField  - длина поля данных,
 *                       для которого создаётся указатель длины
 */
class ULLink(val lengthField: Long) extends Ubtf {

  if(lengthField > 0x1FFFFFFFFFFFFFF7L ) {
    throw new IllegalArgumentException(" >>> Превышена длина поля. Класс ULLink applay(lengthField: Long)")
  }

  if( lengthField <= 1 ) {
    throw new IllegalArgumentException(" >>> Длина поля <= 1. Класс ULLink applay(lengthField: Long)")
  }

  /** указатель длины поля данных, длиной lengthField */
  def get: Array[Byte] = arrByteValue
  private val arrByteValue: Array[Byte] = lengthLink(lengthField)

  /**
   * Увеличение указателя длины на произвольное количество байт
   * @param addValue  Long Прибавляемое значение
   * @return
   */
  def +(addValue :Long): ULLink = new ULLink(lengthField + addValue)

  /**
   * Увеличение указателя длины на длину поля данных другого указателя длины
   * @param addULLink  ULLink Прибавляемое значение
   * @return
   */
  def +(addULLink: ULLink): ULLink = new ULLink(lengthField + addULLink.lengthField)

  /**
   * Уменьшение указателя длины на произвольное количество байт
   * @param subValue  Long вычитаемое значение
   * @return
   */
  def -(subValue: Long): ULLink = new ULLink(lengthField - subValue)

  /**
   * Длина в байтах от 1 до 8 самого указателя длины
   * @return
   */
  def length: Int = ((arrByteValue(0) >> 5) & 0x07) + 1

  /** Преобразование массива типа ULLink в строку */
  override def toString: String ={
    var s = s"ULLink(${arrByteValue.length})=0x("
    for(i <- arrByteValue.indices){
      if(i == (arrByteValue.length - 1)) s += byteToHexString(arrByteValue(i)) + ")"
      else s += byteToHexString(arrByteValue(i)) + ","
    }
    s
  }

}
/** Указатель длины для поля данных */
object ULLink{

  def apply(lengthField: Long) = {
    new ULLink(lengthField)
  }

  def apply(lengthField: Int) = {
    new ULLink(lengthField.toLong)
  }
}

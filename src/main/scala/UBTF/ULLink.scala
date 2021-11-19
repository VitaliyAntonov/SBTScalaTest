package UBTF



/**
 * Указатель длины для поля данных
 * @param lengthField  - длина поля данных,
 *                       для которого создаётся указатель длины
 */
class ULLink(val lengthField: Long) extends Ubtf {

  if((lengthField > 0x1FFFFFFFFFFFFFF7L) && (lengthField < 0) ) {
    throw new IllegalArgumentException(" >>> Некорректная длина поля. Класс ULLink applay(lengthField: Long)")
  }

  /** указатель длины поля данных */
  def arrByte: Array[Byte] = arrByteValue
  private val arrByteValue: Array[Byte] = lengthLink(lengthField)

  def +(addValue :Long): ULLink = new ULLink(lengthField + addValue)

  def -(subValue: Long): ULLink = new ULLink(lengthField - subValue)

  override def toString: String = arrayByteToHexString(arrByte)

}
/** Объект для конструктора LLink без new */
object ULLink{

  def apply(lengthField: Long) = {
    new ULLink(lengthField)
  }

  def apply(lengthField: Int) = {
    new ULLink(lengthField.toLong)
  }
}

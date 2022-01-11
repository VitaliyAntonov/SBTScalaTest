package UBTF





/** UBTF формат типа Int - 4 байта */
class IntUType(val inInt: Int) extends UType {

  /** Длина массива для данного типа данных */
  override def tLength = 4

  /** Значение массива, полученного из пришедшего типа */
  override val arrB: Array[Byte] = tArrByte

  /** Преобразование типа Int в масссив байтов */
  protected def tArrByte: Array[Byte] = {
    var lField = inInt
    val result = new Array[Byte](tLength)
    for (i <- 3 to 0 by -1) {
      result(i) = (lField & 0xFF).toByte
      lField >>= 8
    }
    result
  }

  override def toString: String = s"IntUBTF[Byte](${arrB.length})" + arrByteToHexStr(arrB)
}
/** UBTF формат типа Int - 4 байта */
object IntUType {
  def apply(inInt: Int) = new IntUType(inInt)
}


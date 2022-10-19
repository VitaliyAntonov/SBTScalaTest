package UBTF


/**
 *  Трейт содержит основные функции
 *  для реализации UBTF формата
 */
trait UbtfCoreCode {

  /** Формирование указателя типа UbtfType
   * Указатель типа представляет собой массив типа Array[Byte]
   * длиной от 1 до 8 байт
   *
   * hByte - старший(0-й в массиве) байт указателя типа
   * старшие 3 бита hByte определяют количество
   * дополнительных байт в указателе от 0 до 3
   *
   * */


 /** Формирование указателя длины, который присоединится
   * к массиву длиной lField при формировании массива типа UBTF
   *
   * Указатель длины представляет собой массив типа Array[Byte]
   * длиной от 1 до 8 байт
   *
   * hByte - старший(0-й в массиве) байт указателя длины
   * старшие 3 бита hByte определяют количество
   * дополнительных байт в указателе от 0 до 7
   * @param lField  -  длина массива для присоединения указателя длины
   * @return        -  Array[Byte] - указатель длины в UBTF формате
   */
  def lengthLink(lField: Long): Array[Byte] = {

    if(lField > 0x1FFFFFFFFFFFFFF7L) {
      throw new IllegalArgumentException(" >>> Превышена длина поля \nпри вычислении указателя длины в функции lengthByte трэйт UbtfCreateCode")
    }

    /** маска для поиска */
    var byteMask = 0xFF00000000000000L
    /** номер первого значимого байта в Long */
    var numByte: Int = 0 //
    /** старший значимый байт в length0 */
    var hByte: Long = 0
    /** Количество дополнительных байт указателя длины */
    var linkBQua: Int = 0

    /** Поиск номера старшего значимого байта в lField */
    while(((byteMask & lField) == 0) & (numByte < 7)){
      byteMask >>= 8
      numByte += 1
    }
    /** предварительная расчётная длина массива с присоединённым указателем */
    var length0 = lField + 8 - numByte

    byteMask = 0xFF00000000000000L // восстанавливаем маску
    numByte = 0 // восстанавливаем номер

    /** Старший значимый байт в length0 в собственной позиции */
    var hByteMasked: Long = 0
    /** Поиск номера старшего значимого байта в length0 */
    while(((byteMask & length0) == 0) & (numByte < 7)){
      byteMask >>= 8
      numByte += 1
      hByteMasked = byteMask & length0
    }

    linkBQua = 7 - numByte  // количество доп. байт указателя длины
    hByte =  hByteMasked >> (linkBQua*8)  // значение старшего значимого байта

    if(hByte > 0x1F){
      length0 += 1
      linkBQua += 1
      numByte -= 1
    }

    /** Окончательная расчётная длина массива с присоединённым указателем */
    val length = length0

    /** Указатель длины => результат */
    val lLink = new Array[Byte](linkBQua + 1)

    // читаем из length: Long массив указателя длины
    var lOper = length
    for (i <- linkBQua to 0 by -1) {
      i match {
        /** в старший байт количество дополнительных байт указателя */
        case 0 => lLink(i) = ((lOper & 0xFF).toByte | (linkBQua.toByte << 5)).toByte
        /** в остальные байты простое копирование */
        case _ => lLink(i) = (lOper & 0xFF).toByte
      }
      lOper >>= 8
    }

    lLink
  }
  /** --------------------------------------------- */

  /** Преобразование Int числа в строку HEX формата вида 0x00000000 */
  def intToHexString(inInt: Int): String = {
    val symbols = "0123456789ABCDEF".toCharArray
    var s = "0x"
    for(i <- 0 until 8){
      val index = (inInt >> (28 - i*4)) & 0x0F
      s += symbols(index)
    }
    s
  }

  /** Преобразование Byte числа в строку HEX формата вида(например) A7 */
  def byteToHexString(inByte: Byte): String = {
    val symbols = "0123456789ABCDEF".toCharArray
    var s = ""
    for(i <- 0 until 2){
      val index = (inByte >> (4 - i*4)) & 0x0F
      s += symbols(index)
    }
    s
  }

  /** Преобразование массива типа Byte в HEX строку */
  def arrayByteToHexString(arr: Array[Byte]): String ={
    var s = s"Array[Byte](${arr.length})=0x("
    for(i <- arr.indices){
      if(i == (arr.length - 1)) s += byteToHexString(arr(i)) + ")"
      else s += byteToHexString(arr(i)) + ","
    }
    s
  }

  /** Преобразование массива типа Byte в Hex строку, но без идентификатора массива */
  def arrByteToHexStr(arr: Array[Byte]): String ={
    var s = s"=0x("
    for(i <- arr.indices){
      if(i == (arr.length - 1)) s += byteToHexString(arr(i)) + ")"
      else s += byteToHexString(arr(i)) + ","
    }
    s
  }

  /** -------------------------------------
   * Преобразование переменной типа Long
   * в массив типа Array[Byte]
   * @param l  - переменная Long типа
   * @return   - Array[Byte]
   */
  def longToArrayByte(l: Long): Array[Byte] = {
    var lField = l
    val result = new Array[Byte](8)
    for (i <- 7 to 0 by -1) {
      result(i) = (lField & 0xFF).toByte
      lField >>= 8
    }
    result
  }
  /** ------------------------------------ */

  /** -----------------------------------------
   * Преобразование массива типа Array[Byte]
   * в переменную типа Long
   * @param b - массив типа Array[Byte]
   * @return  - переменная типа Long
   */
  def arrayByteToLong(b: Array[Byte]): Long = {
    if(b.length > 8){
      throw new IllegalArgumentException(" >>> Трейт UbtfCreateCode функция arrayByteToLong \n длина входящего массива больше 8 байт")
    }
    var result = 0L
    for (i <- b.indices) {
      result <<= 8
      result |= (b(i) & 0xFF)
    }
    result
  }
  /** ------------------------------------ */



}

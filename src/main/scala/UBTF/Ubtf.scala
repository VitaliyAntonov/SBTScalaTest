package UBTF

import java.io.{ByteArrayOutputStream, DataOutputStream}
//import java.lang.Double.longBitsToDouble
import scala.collection.mutable.ArrayBuffer


/** Universal Binary Type Format */
abstract class Ubtf {
  /** Длина поля */
  val fLength: Long
  /** Тип поля */
  val fType: Long
  /** Бинарный массив с данными поля */
  val fData: Array[Byte]

  /**
   * Определение количества дополнительных байт (0...7)
   * в указателе типа или в указателе длины
   * @param fieldValue  - значение поля
   * @return            - количество доп. байт для данного значения
   */
  def bytesQua(fieldValue: Long): Byte = {
    if(fieldValue <= 0x1F)  0
    else if(fieldValue <= 0x1FFF) 1
    else if(fieldValue <= 0x1FFFFF) 2
    else if(fieldValue <= 0x1FFFFFFF) 3
    else if(fieldValue <= 0x1FFFFFFFFFL) 4
    else if(fieldValue <= 0x1FFFFFFFFFFFL) 5
    else if(fieldValue <= 0x1FFFFFFFFFFFFFL) 6
    else  7
  }



  /**
   * Создание указателя длины
   * Определение длины в виде массива типа Byte
   * @param sizeField  - длина массива, к которому присоединится указатель длины
   * @return
   */
  def setByteArrayLength(sizeField: Long) : Array[Byte] = {
    /** Определение количества дополнительных байт указателя длины */
    val byteLinkQua0 = bytesQua(sizeField + 1) // первое определение не учитывает
                                               // длину доп. байтов самого указателя длины
    val byteLinkQua1 = bytesQua(sizeField + 1 + byteLinkQua0) // здесь учтена длина доп. байтов

    val sizeResult = sizeField + 1 + byteLinkQua1 // итоговая длина поля вместе с указателем длины

    val rezArr = ArrayBuffer[Byte]()
    for(i <- 0 until byteLinkQua1) {
      rezArr += ((sizeResult >> (i*8)) & 0xFF).toByte
    }
    val b0 = ((sizeResult >> (byteLinkQua1 * 8) & 0xFF) | (byteLinkQua1 << 5)).toByte
    rezArr += b0
    rezArr.toArray.reverse
  }

}

/** Класс для описания поля типа Int */
class IntUbtf extends Ubtf{
  /** Длина поля */
  override val fLength: Long = 4
  /** Тип поля */
  override val fType: Long = 1
  /** Бинарный массив с данными поля */
  override val fData: Array[Byte] = Array(0,0,0,0)
}

trait ubtfSource{

  /** -------------------------------------
   * Преобразование переменной типа Long
   * в массив типа Array[Byte]
   * @param l  - переменная Long типа
   * @return   - Array[Byte]
   */
  def longToBytes(l: Long): Array[Byte] = {
    var lOper = l
    val result = new Array[Byte](8)
    for (i <- 7 to 0 by -1) {
      result(i) = (lOper & 0xFF).toByte
      lOper >>= 8
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
  def bytesToLong(b: Array[Byte]): Long = {
    var result = 0L
    for (i <- 0 until 8) {
      result <<= 8
      result |= (b(i) & 0xFF)
    }
    result
  }
  /** ------------------------------------ */




}


object testUbtf extends App with ubtfSource {

  val maxInt = 0xFFFFFFFFL
  println("maxInt = " + maxInt)

  val vArr = doubleToByteArray(31d)
  arrToLog(arrayByteToInt(vArr).reverse)

  val rez = Array(1,2,3,4,5,6,7,8).zip("Worldes")
  rez.foreach{field => println("field1: " + field._1 + "   field2: " + field._2)}


  val fUbtf = new IntUbtf
  val test = fUbtf.setByteArrayLength(0x1FFFl)
  arrToLog(arrayByteToInt(test))

  val lSource = 511L
  val arrSource = longToBytes(lSource)
  arrToLog(arrSource)
  println(bytesToLong(arrSource))



  /** Преобразование массива из типа Byte в тип Int ----------------- */
  def arrayByteToInt(arrVal: Array[Byte]) = {
    val result = ArrayBuffer[Int]()
    arrVal.foreach{bField =>
      val rField = (0 | bField) & 0xFF
      result += rField
    }
    result.toArray
  }
  /** --------------------------------------------------------------- */


  /** Преобразование типа Double в массив типа Byte(8 байт) --------- */
  import java.io.ByteArrayOutputStream
  import java.io.IOException
  @throws[IOException]
  def doubleToByteArray(i: Double) = {
    val bos = new ByteArrayOutputStream
    val dos = new DataOutputStream(bos)
    dos.writeDouble(i)
    dos.flush
    bos.toByteArray
  }
  /** ----------------------------------------------------------------- */

  /** Вывод массива обобщённого типа в лог -------------------------- */
  import scala.reflect.ClassTag  /** Позволяет работать с обобщёнными типами массивов */
  def arrToLog[T: ClassTag](arrData: Array[T]) = {
    arrData.foreach{field => print(field + " ")}
    println("")
  }
  /** --------------------------------------------------- */

}


import java.lang._
// Java program to demonstrate
// Double.doubleToRawLongBits() method


object Gfg1 {
  def main(args: Array[String]): Unit = {
    val doubleField = 1.5d
    // function call
    val longField = Double.doubleToLongBits(doubleField)
    val answer = Double.doubleToRawLongBits(doubleField) // так тоже работает
    // print
    System.out.println(doubleField + " in raw long bits: " + longField)
    val resDouble = Double.longBitsToDouble(longField)
    println(resDouble)

  }
}



package UBTF

import java.io.{ByteArrayOutputStream, DataOutputStream}
import scala.util.control.Breaks.break
//import java.lang.Double.longBitsToDouble
import scala.collection.mutable.ArrayBuffer



/** Universal Binary Type Format */
abstract class UbtfTest {
  /** Длина поля */
  val lengthField: Long
  /** Тип поля */
  val fType: Long
  /** Бинарный массив с данными поля */
  val fData: Array[Byte]
  /** Контрольная сумма поля, если используется */
}

/** Класс для описания поля типа Int */
class IntUbtf extends UbtfTest{
  /** Длина поля */
  override val lengthField: Long = 4
  /** Тип поля */
  override val fType: Long = 1
  /** Бинарный массив с данными поля */
  override val fData: Array[Byte] = Array(0,0,0,0)
}


/**
 *  Трэйт содержит основные функции
 *  для реализации UBTF формата
 */
trait ubtfSource{

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
    var result = 0L
    for (i <- 0 until 8) {
      result <<= 8
      result |= (b(i) & 0xFF)
    }
    result
  }
  /** ------------------------------------ */

  /**
   * Формирование указателя длины, который присоединится
   * к массиву длиной lField
   * @param lField  -  длина массива для присоединения указателя длины
   * @return        -  Array[Byte] - указатель длины в UBTF формате
   */
  def lengthByte(lField: Long): Array[Byte] = {

    if(lField > 0x1FFFFFFFFFFFFFF7L) {
      throw new IllegalArgumentException(" >>> Превышена длна поля \nпри вычислении указателя длины в функции lengthByte трэйт UbtfCreateCode")
    }

    /** маска для поиска */
    var byteMask = 0xFF00000000000000L
    /** номер первого значимого байта в Long */
    var numByte: Int = 0 //
    /** старший значимый байт в length0 */
    var hByte: Byte = 0
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

    var hByteMasked: Long = 0
    /** Поиск номера старшего значимого байта в length0 */
    while(((byteMask & length0) == 0) & (numByte < 7)){
      byteMask >>= 8
      numByte += 1
      hByteMasked = byteMask & length0
    }

    linkBQua = 7 - numByte  // количество доп. байт указателя длины
    hByte = ( hByteMasked >> (linkBQua*8) ).toByte // значение старшего значимого байта

    if(hByte > 0x1F){
      length0 += 1
      linkBQua += 1
      numByte -= 1
    }

    /** Окончательная расчётная длина массива с присоединённым указателем */
    val length = length0

    /** Указатель длины - результат */
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



}


object testUbtf extends App with ubtfSource {

  val maxInt = 0xFFFFFFFFL
  println("maxInt = " + maxInt)

  val vArr = doubleToByteArray(31d)
  arrayToLog(arrayByteToInt(vArr).reverse)

  val rez = Array(1,2,3,4,5,6,7,8).zip("Worldes")
  rez.foreach{field => println("field1: " + field._1 + "   field2: " + field._2)}

  println(IntUType(0xa0a1a2a3))



  val lLink = lengthByte(0x1FFFFFFFFFFFFFF7L)

  println(arrayByteToHexString(lLink))

  val nULink = ULLink(0x1FFFFFFFFFFFFFF7L)
  println(nULink)
  val nULink1 = nULink.get
  println(arrayByteToHexString(nULink1))
  println(nULink.length)





  println(ULLink(31) + ULLink(2))
  println("uLLinkLength: " + ULLink(31).length)

  println(ULLink(31) + 7)
  println("uLLinkLength: " + (ULLink(31)+7).length)


  val expr = BinOp("+", Var("b"), Var("c"))
  inSelect(expr)


  def inSelect(inval: Any)= inval match {
    case BinOp(op, left, right) =>
      println(inval + " является бинарной операцией")
    case _ => println("Это не бинарная операция ")
  }




  abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr






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
  def arrayToLog[T: ClassTag](arrData: Array[T]) = {
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

object TryCatchDemo {
  def main(args: Array[String]) {
    try {
      val result = 100 / 0;
      println(result);
    } catch {
      case ex: ArithmeticException => {
        println("ArithmeticException - cannot divide by zero");
      }
    }
  }
}

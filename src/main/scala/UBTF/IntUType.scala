package UBTF

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


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

  /** Преобразование массива в строку */
  override def toString: String = s"IntUBTF[Byte](${arrB.length})" + arrByteToHexStr(arrB)
}
/** UBTF формат типа Int - 4 байта */
object IntUType {
  def apply(inInt: Int) = new IntUType(inInt)
}


object UbtfIntTest extends App{
  val ubtfInt = IntUType(2345)
  println(ubtfInt)
  println(ubtfInt.inInt)

  val a = Seq(4,3,2,3,2,8,1,0)
  val b = a.sortWith(_ < _)
  val c = a.takeRight(4)
  val d = a.toList

  val w = a.isDefinedAt(7)
  println(a.isDefinedAt(7))

  print("b: Seq[Int]= ")
  for(obj <- b) print(obj + " ")
  println("  " + b(6))

  for(obj <- c) print(obj + " ")

  print("\nd: List[Int]= ")
  for(obj <- d) print(obj + " ")

  println("")
  println(a.lengthCompare(9))


  val f = Seq(1,2,3,4)
  val g = Seq(2,3,7,5)

  println(f intersect g)
  val ds = a.distinct
  val vBuf = ArrayBuffer[Int]()
  a.copyToBuffer(vBuf)
  println(vBuf)
  val wBuf = vBuf.distinct.toList
  println(wBuf)
  println(a.distinct.sorted.toList)


  val it1 = a.iterator
  println("\nit1.toList: ")
  print(it1.toList)

  println("\nit1.next(): ")
  while(it1.hasNext){
    print(it1.next() + " ")
  }

  val it2 = a.iterator
  println("\nit2.next(): ")
  while(it2.hasNext){
    print(it2.next() + " ")
  }

  println("\nit2.toList: ")
  print(it2.toList)




}


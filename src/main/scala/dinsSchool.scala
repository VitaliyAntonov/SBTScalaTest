


object testDropFalse{



  def dropFalse(list: List[Boolean]): List[Boolean] = {
    if(list.nonEmpty) {
      if (list.head) list
      else dropFalse(list.tail)
    }
    else list
  }

  println(dropFalse(List(false, false, false, true, false)))



  def findPattern(list: List[Int]): Boolean = {
    var path = List(1, 2, 3, 2, 1)
    def rez(list: List[Int], path: List[Int]): Boolean = {
      if (list.head == path.head) true
      else false
    }
    if (list.nonEmpty) {
      if (list.head == path.head) rez(list.tail, path.tail)
      else rez(list.tail, path)
    }
    else false
  }

  println(findPattern(List(65, 1, 2, 3, 2, 1, 5, 2)))
  println(findPattern(List(8, 3, 2, 1)))


  def addIndexAndDropEven(list: List[Int]): List[Int] = {
    import scala.collection.mutable.ListBuffer
    val buf = ListBuffer[Int]()
    for(i <- 0 until list.length ) {
      if (i % 2 != 0 ) buf += (list(i) + i)
    }
    buf.toList
  }

  println(addIndexAndDropEven(List(1, 2, 3, 4, 5)))
  println(addIndexAndDropEven(List(54, 22, 45, 59)))

  def computeFrequencies(list: List[Boolean]): (Int, Int) = {
    var xtrue: Int = 0
    var xfalse: Int = 0
    for(a <- list){
      if (a) xtrue += 1
      else xfalse += 1
    }
    (xfalse, xtrue)
  }

  def recoverList(falseCount: Int, trueCount: Int): List[Boolean] = {
    val xtrue = List.fill(trueCount)(true)
    val xfalse = List.fill(falseCount)(false)
    xfalse ::: xtrue
  }

  def countSort(list: List[Boolean]): List[Boolean] = {
    val frequencies = computeFrequencies(list)
    recoverList(frequencies._1, frequencies._2)
  }


  println(countSort(List(false, true, true, false, false, false, true)))

}




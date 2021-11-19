package UBTF




object TestUbtf extends App {

  println(ULLink(0x8001L))

  for(i <- 0x1FFFF0 to 0x200005){
    println(s"i = $i  " + ULLink(i))
  }


}

//i = 32762  Array[Byte](3) = ( 40 7F FD )
//i = 32763  Array[Byte](3) = ( 40 7F FE )
//i = 32764  Array[Byte](3) = ( 40 7F FF )
//i = 32765  Array[Byte](3) = ( 40 80 00 )
//i = 32766  Array[Byte](2) = ( A0 00 )
//i = 32767  Array[Byte](2) = ( A0 01 )
//i = 32768  Array[Byte](2) = ( A0 02 )
//i = 32769  Array[Byte](2) = ( A0 03 )
//i = 32770  Array[Byte](2) = ( A0 04 )
//i = 32771  Array[Byte](2) = ( A0 05 )

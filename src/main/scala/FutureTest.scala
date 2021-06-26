
/**
 * @author Виталий Антонов @date 6/24/21
 *         kaligraf@yandex.ru
 * */

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FutureClass{

  val fut: Future[Int] = Future {
    println("Внутри фьючерса ")
    Thread.sleep(1000)
    21 + 21
  }

}


object FutureTest {


  val fut1 = new FutureClass

  for(rez <- fut1.fut){
    println("Выполнен: " + rez)
  }



  println("Первый опрос " + fut1.fut.isCompleted.toString)

  if(fut1.fut.isCompleted){println(fut1.toString)}

  Thread.sleep(3000)

  println("Второй опрос " + fut1.fut.isCompleted.toString)

  if(fut1.fut.isCompleted){println(fut1.fut.toString)}

}




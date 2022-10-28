package TimerUseClipboard

import java.awt.datatransfer.UnsupportedFlavorException
import java.io.{FileNotFoundException, IOException}
import java.util.{Timer, TimerTask}

object MyTask {
  var i = 0
  var lastString = ""
}

class MyTask extends TimerTask {
  override def run(): Unit = {
    //    System.out.println("timer iteration: " + {MyTask.i += 1; MyTask.i})

    /**  Выборка из буфера обмена с обработкой исключений */
    try{
      val x = clipboardString.get /** Текст из буфера обмена */
      if(MyTask.lastString != x){
        println(x)
        MyTask.lastString = x
      }
    } catch {
      case ex: IOException => {}
      case ex: UnsupportedFlavorException => {}
      case ex: InterruptedException => {}
//      case ex: ClassNotFoundException => {}
    }

  }
}

object GetClipboardContent {
  def main(args: Array[String]): Unit = {
    val timer = new Timer
    val task = new MyTask
    timer.schedule(task, 1, 50) // 2000 - delay (can set to 0 for immediate execution), 5000 is a frequency.
  }
}

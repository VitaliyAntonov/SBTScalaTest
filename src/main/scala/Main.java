


/** Работа с буфером обмена  JAVA */
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class Main{
  public static void main(String[] args) throws IOException, UnsupportedFlavorException, InterruptedException {
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//    StringSelection stringSelection = new StringSelection("myClipboardString"); /** Строка для буфера обмена **/
//    clipboard.setContents(stringSelection, null); /** Размещение текста в буфер обмена **/

//    Thread.sleep(10000);

    /** Забираем строку из буфера обмена **/
    DataFlavor flavor = DataFlavor.stringFlavor;
    if(clipboard.isDataFlavorAvailable(flavor)){
      System.out.println(clipboard.getData(flavor));
    }

  }
}


class clipboardString{
  public static String get() throws IOException, UnsupportedFlavorException { //  throws IOException, UnsupportedFlavorException, InterruptedException
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    /** Забираем строку из буфера обмена **/
    DataFlavor flavor = DataFlavor.stringFlavor;
    if(clipboard.isDataFlavorAvailable(flavor)){
      return (String) clipboard.getData(flavor);
    }
    else return "";
  }
}




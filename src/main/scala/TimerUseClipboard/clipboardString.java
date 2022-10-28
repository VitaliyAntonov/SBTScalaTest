package TimerUseClipboard;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class clipboardString{
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

package folderIndex

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

trait GraphicsOption extends FileSystem {

  /**
   * Функция слияния двух рисунков
   * накладывает рисунки тайлов, оказавшиеся на границе карт
   * @param imagePath1 - путь к первому рисунку
   * @param imagePath2 - путь ко второму рисунку
   * @param resultPath - путь к итоговому рисунку, полученному слиянием исходных рисунков
   */
  def mergeTails(imagePath1: String, imagePath2: String, resultPath: String): Unit ={
    /** Пути к файлам рисунков - без имён файлов */
    val folder1 = new java.io.File(folderFromFilePath(imagePath1)) // base path of the images тип java.io.File
    val folder2 = new java.io.File(folderFromFilePath(imagePath2))

    val resultFolder = new java.io.File(folderFromFilePath(resultPath))

    // load source images
    val image = ImageIO.read(new java.io.File(folder1, fileNameFromPath(imagePath1)))

    val overlay = ImageIO.read(new java.io.File(folder2, fileNameFromPath(imagePath2)))

    // create the new image, canvas size is the max. of both image sizes
    val w: Int = Math.max(image.getWidth(), overlay.getWidth());
    val h: Int = Math.max(image.getHeight(), overlay.getHeight());
    val combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    // paint both images, preserving the alpha channels
    val g = combined.getGraphics;
    g.drawImage(image, 0, 0, null);
    g.drawImage(overlay, 0, 0, null);

    g.dispose()

    // Save as new image
    ImageIO.write(combined, "PNG", new java.io.File(resultFolder, fileNameFromPath(resultPath)));
  }


}

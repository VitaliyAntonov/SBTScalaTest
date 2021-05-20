package siteCreator.component

import siteCreator.HtmlPC

/**
 * @author Виталий Антонов @date 5/13/21
 *         kaligraf@yandex.ru
 * */


/**
 * Контейнер со скроллингом внутреннего большего контейнера
 */
class InDiv(val name: String) extends Component ("InDiv" + name){

  /** Ссылки на основные данные класса */
  /** Selectors Class  - перечень классов для селекторов CSS */
  val SClass = OSClass
  /** Component Css  -  определение блоков CSS кода компонента */
  val CCss = OCCss
  /** Component Size - размеры компонента и его составляющих */
  val CSize = new BoxSize
  /** Component Colors - цвета компонента и его составляющих */
  val CColors = OCColors

  /**
   * Метод строит HTML шаблон компонента и добавляет шаблоны компонента в CSS и JS файлы
   *
   * @param page имя страницы, которая формируется
   */
  override def html(page: HtmlPC): Unit = {

  }

  /** Selectors Class  - перечень классов для селекторов CSS */
  object OSClass {
    /** имя селектора класса */
    val sClassName = componentName
  }
  /** Component Css  -  определение блоков CSS кода компонента */
  object OCCss
  /** Component Size - размеры компонента и его составляющих */
  object OCSize {

  }
  /** Component Colors - цвета компонента и его составляющих */
  object OCColors


}
object InDiv {
  /** Контейнер со скроллингом внутреннего большего контейнера
   * Скроллинг вверх - вниз по положению указателя мыши в контейнере
   * внутри контейнера 3 разметочных контейнера*/
  lazy val BoxFs = new InDiv("BoxFs")
  BoxFs.CSize.boxWidth = 200
  BoxFs.CSize.boxHeight = 600
  /** Контейнер большого размера, вложенный в меньший контейнер */
  lazy val BoxMap = new InDiv("BoxMap")
  BoxMap.CSize.boxWidth = 200
  BoxMap.CSize.boxHeight = 1000

  /** Контейнеры разметки для перемещения по hover */
  lazy val boxMapHeader = new InDiv("boxMapHeader")
  boxMapHeader.CSize.boxWidth = 200
  boxMapHeader.CSize.boxHeight = 200
  boxMapHeader.CSize.boxLeftOffset = 0

  lazy val boxMapCenter = new InDiv("boxMapCenter")
  boxMapHeader.CSize.boxWidth = 200
  boxMapHeader.CSize.boxHeight = 200

  lazy val boxMapFooter = new InDiv("boxMapFooter")
  boxMapFooter.CSize.boxWidth = 200
  boxMapFooter.CSize.boxHeight = 200
}







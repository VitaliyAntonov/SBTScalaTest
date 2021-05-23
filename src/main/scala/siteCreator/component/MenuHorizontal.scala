package siteCreator.component
import siteCreator.HtmlPC

/**
 * @author Виталий Антонов @date 5/22/21
 *         kaligraf@yandex.ru
 * */
class MenuInHoBox(name: String) extends Component(name + ""){
  /**
   * Метод строит HTML шаблон компонента и добавляет шаблоны компонента в CSS и JS файлы
   * @param page имя страницы, которая формируется
   */
  override def html(page: HtmlPC): Unit = {}


  lazy val menuBox = new Div("HoMenuHeader")

  /** CSS код контейнера меню при его создании */
  menuBox.CSize.boxWidthAsString = s"  width: 100vw;"
  menuBox.CSize.boxHeight = 30

}

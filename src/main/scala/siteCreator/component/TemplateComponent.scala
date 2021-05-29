package siteCreator.component

import siteCreator.HtmlPC

/**
 * @author Виталий Антонов @date 5/24/21
 *         kaligraf@yandex.ru
 *
 *       Шаблон класса компонента
 * */
class TemplateComponent(name: String)(panels: String*) extends Component(name + "TemplateComponent") {

  /** html */
  var html: String = ""

  override def html(page: HtmlPC): Unit = {}

  /** Object Selectors Class назначенные имена классов для селекторов CSS */
  object SClass{

  }

  /** Component CSS - CSS код, используемый для формирования компонента на странице */
  object CCss {

  }

  /** Component Size - размеры компонента и его составляющих */
  object CSize {

  }

  /** Component Colors - цвета компонента и его составляющих */
  object  CColors {

  }

}

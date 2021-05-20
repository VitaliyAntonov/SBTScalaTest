package siteCreator.component

import siteCreator.HtmlPC

/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */



class Grids(nameGrid: String = "")(val areas: String*)(val css: String)
  extends Component("Grid" + nameGrid) {

  override def html(page: HtmlPC) = {}

}

object Grids{
  val gridPageWorkV0 = new Grids("PageWorkV0")(
    "header",
    "work",
    "footer")(s"""
       |  "grid-template-areas:"
       |    "header"
       |    "work"
       |    "footer";
       |""".stripMargin)
}


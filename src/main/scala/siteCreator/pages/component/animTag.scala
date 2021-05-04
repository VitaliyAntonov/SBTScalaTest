package siteCreator.pages.component
import siteCreator.pages.HtmlPC

/**
 * @author Виталий Антонов @date 5/2/21
 *         kaligraf@yandex.ru
 * */


/**
 * Набор тегов, заключённых в контейнеры, расположенные друг под другом
 * Теги с анимацией при наведении по hover
 * здесь создаём селекторы с координатой top
 */
class animTags(
               /** имя блока контейнеров */
               val nameTagsBlock: String = "",
               /** Начальная высота блока тегов */
               val top: Int = 10,
               /** ширина контейнера единичного тега */
               val tagBoxWidth: Int = 200,
               /** высота контенера единичного тега */
               val tagBoxHeight: Int = 50,
               /** зазор между контейнерами по высоте */
               val boxGapY: Int = 10
             )(
               /** перечень тегов для размещения */
               tags: String*
              ) extends Component("AnimTag" + nameTagsBlock) {

  /**
   * @param page имя страницы, которая формируется
   */
  override def html(page: HtmlPC): Unit = {

  }

  /** Формирование имени класса для селектора контейнера(div) тега */
  def tagBoxClassName(tag: String): String = {
    s"${nameTagsBlock}-box-tag-${tag}"
  }

  /** имя класса для всех сформированных контейнеров(div) */
  def allTagsClass: String = s"${nameTagsBlock}-all-boxes"

  /**
   * Метод заключает каждый символ тега в "span".
   * Каждому "span" присваивается класс tl0...tlx, где
   * x - количество символов тега минус 1
   * @param tag - Тег, символы которого необходимо обернуть в "span"
   * @return - текст для вставки в буфер html
   */
  def tagInSpan(tag: String): String = {
    var s: String = s"""<div class="${tagBoxClassName(tag)} $allTagsClass">\n  """
    for(i <- 0 until tag.length){
      s += s"""<span class="tl${i.toString}">${tag(i)}</span> """
    }
    s += s"""\n</div>"""
    s
  }





}
object animTags{
  def apply(nameTagsBlock: String = "",
            top: Int
           ) = {
    new animTags(nameTagsBlock, top)()
  }

  /** Пример создания анимированного тега */
  val alignContent = new animTags("align-content", 10)()

}

object testerAnimTag{
  val a = animTags("litera-a", 10)
  println(a.tagInSpan("animation-timing-function"))
}

package CSS.Grid


class Grid {

  /** css Свойства для контейнера **/
  /**
   * Определяет элемент как контейнер и устанавливает новый контекст
   * форматирования сетки для его содержимого.
   * Значения:
   * grid - формирует сетку как блок;
   * inline-grid - формирует сетку как инлайновый блок;
   * subgrid - если ваш контейнер это ещё и элемент (вложенная сетка),
   * то вы можете использовать это свойство для обозначения того,
   * чтобы размеры строк/колонок были взяты из родительского элемента,
   * а не определяли собственный;
   * @return
   */
  def display = "display"

  def grid_template_columns =  "grid-template-columns"
  def grid_template_rows = "grid-template-rows"
  def grid_template_areas = "grid-template-areas"
  def grid_template = "grid-template"
  def grid_column_gap = "grid-column-gap"
  def grid_row_gap = "grid-row-gap"
  def grid_gap = "grid-gap"
  def justify_items = "justify-items"
  def align_items = "align-items"
  def justify_content = "justify-content"
  def align_content = "align-content"
  def grid_auto_columns = "grid-auto-columns"
  def grid_auto_rows = "grid-auto-rows"
  def grid_auto_flow = "grid-auto-flow"
  def grid = "grid"

  /** css Свойства для элементов **/
  def grid_column_start = "grid-column-start"
  def grid_column_end = "grid-column-end"
  def grid_row_start = "grid-row-start"
  def grid_row_end = "grid-row-end"
  def grid_column = "grid-column"
  def grid_row = "grid-row"
  def grid_area = "grid-area"
  def justify_self = "justify-self"
  def align_self = "align-self"

}

object CSSTest{

  val data1 = "string"
  val data2 = Array(1, 2, 3, 4)
  val comparisonBool = data1 == data2
  println(comparisonBool)
}



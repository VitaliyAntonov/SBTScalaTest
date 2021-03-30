package CSSv01.Tags


trait AlignAttribute  extends RowTags{
  def stretch = {row += "stretch"; hearEnter;}
  def center = {row += "center"; hearEnter; }
  def flex_start = {row += "flex-start"; hearEnter;}
  def flex_end = {row += "flex-end"; hearEnter;}
  def baseline = {row += "baseline"; hearEnter;}
  def initial = {row += "initial"; hearEnter;}
  def inherit = {row += "inherit"; hearEnter;}
}

trait AlignProperty extends AlignAttribute with PropertyAlignContent with PropertyAlignItems
        with PropertyAlignSelf

trait AttributeAlignContent extends AlignAttribute{
  protected def hearAttributeAlignContent = {this}
  /** Значение по умолчанию. Линии растягиваются, чтобы занять оставшееся место */
  abstract override def stretch = {super.stretch}
  /** Линии упакованы к центру гибкого контейнера */
  abstract override def center = { super.center }
  /** Строки упаковываются в сторону начала гибкого контейнера */
  abstract override def flex_start = super.flex_start
  /** Линии упаковываются ближе к концу гибкого контейнера */
  abstract override def flex_end = super.flex_end
  /** Строки равномерно распределены в гибком контейнере */
  def space_between = {row += "space-between"; hearEnter }
  /** Линии равномерно распределены в гибком контейнере, с промежутками половинного размера на обоих концах */
  def space_around = {row += "space-around"; hearEnter}
  /** Строки равномерно распределены в гибком контейнере с равным пространством вокруг них */
  def space_evenly = {row += "space-evenly"; hearEnter}
  /** Устанавливает для этого свойства значение по умолчанию. */
  abstract override def initial = super.initial
  /** Наследует это свойство от родительского элемента. */
  abstract override def inherit = super.inherit
}
trait PropertyAlignContent extends AttributeAlignContent {
  /** Задает выравнивание между линиями внутри гибкого контейнера,
   * когда элементы не используют все доступное пространство 9 допустимых атрибутов */
  def align_content= {row += "align-content: "; hearAttributeAlignContent}
}

trait AttributeAlignItems extends AlignAttribute{
  protected def hearAttributeAlignItems = {this}
  /** Значение по умолчанию. Линии растягиваются, чтобы занять оставшееся место */
  abstract override def stretch = super.stretch
  /** Линии упакованы к центру гибкого контейнера */
  abstract override def center = {super.center}
  /** Строки упаковываются в сторону начала гибкого контейнера */
  abstract override def flex_start = super.flex_start
  /** Линии упаковываются ближе к концу гибкого контейнера */
  abstract override def flex_end = super.flex_end
  /** базовая линия Элементы позиционируются на базовой линии контейнера */
  abstract override def baseline = super.baseline
  /** Устанавливает для этого свойства значение по умолчанию. */
  abstract override def initial = super.initial
  /** Наследует это свойство от родительского элемента. */
  abstract override def inherit = super.inherit
}
trait PropertyAlignItems extends AttributeAlignItems {
  /** Задает выравнивание для элементов внутри гибкого контейнера  7 допустимых атрибутов */
  def align_items = {row += "align-items: "; hearAttributeAlignItems}
}

trait AttributeAlignSelf extends AlignAttribute{
  protected def hearAttributeAlignSelf = {this}
  /** По умолчанию. Элемент наследует свойство align-items своего
   * родительского контейнера или "stretch", если у него нет родительского контейнера. */
  def auto = {row += "auto"; hearEnter}
  /** Значение по умолчанию. Линии растягиваются, чтобы занять оставшееся место */
  abstract override def stretch = {super.stretch}
  /** Линии упакованы к центру гибкого контейнера */
  abstract override def center = {super.center}
  /** Строки упаковываются в сторону начала гибкого контейнера */
  abstract override def flex_start = super.flex_start
  /** Линии упаковываются ближе к концу гибкого контейнера */
  abstract override def flex_end = super.flex_end
  /** базовая линия Элементы позиционируются на базовой линии контейнера */
  abstract override def baseline = super.baseline
  /** Устанавливает для этого свойства значение по умолчанию. */
  abstract override def initial = super.initial
  /** Наследует это свойство от родительского элемента. */
  abstract override def inherit = {super.inherit}
}

trait PropertyAlignSelf extends AttributeAlignSelf {
  /** Задает выравнивание для выбранных элементов внутри гибкого контейнера 8 допустимых атрибутов*/
  def align_self = {row += "align-self: "; hearAttributeAlignSelf}
}



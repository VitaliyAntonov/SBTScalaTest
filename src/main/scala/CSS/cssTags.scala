package CSS


/** outline
 * Контур - это линия, которая проводится вокруг элементов
 * за пределами границ, чтобы выделить элемент.
 * Свойство является сокращенным свойством для:
 */
trait outline extends CSS{
  def outline_style = new Property("outline-style")

  def none = new Attribute("display")
  def hidden = new Attribute("hidden")
  def dotted = new Attribute("dotted")
  def dashed = new Attribute("dashed")
  def solid = new Attribute("solid")
  def double = new Attribute("double")
  def groove = new Attribute("groove")
  def ridge = new Attribute("ridge")
  def inset = new Attribute("inset")
  def outset = new Attribute("outset")

  /** initial используется для установки свойства CSS значения по умолчанию.
   * initial можно использовать для любого свойства CSS и для любого элемента HTML. */
  def initial = new Attribute("initial")
  def inherit = new Attribute("inherit")

  def outline_width = new Property("outline-width")
  def outline_color = new Property("outline-color")
}


object myString

object order {
  object align_self {
    object auto  /** Дефолт. Элемент наследует свойство align-items своего родительского контейнера или "stretch", если у него нет родительского контейнера. */
    object stretch	/** Элемент расположен по размеру контейнера. */
    object center	/** Элемент расположен в центре контейнера. */
    object flex_start /** Элемент располагается в начале контейнера */
    object flex_end /**  Элемент располагается в конце контейнера */
    object baseline	/** Элемент располагается на базовой линии контейнера. */
    object initial	/** Устанавливает для этого свойства значение по умолчанию. Читайте о начальных */
    object inherit	/** Наследует это свойство от своего родительского элемента. Читайте о наследовании */
  }
}




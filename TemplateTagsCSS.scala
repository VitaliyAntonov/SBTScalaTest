
class align_content extends align_items{
  private val x = new Attribute
  class Attribute{
    def stretch = {Order.attToOrder("stretch"); Order.row }
    def center = {Order.attToOrder("center"); Order.row }
    def flex_start = {Order.attToOrder("flex-start"); Order.row }
    def flex_end = {Order.attToOrder("flex-end"); Order.row }
    def space_between = {Order.attToOrder("space-between"); Order.row }
    def space_around = {Order.attToOrder("space-around"); Order.row }
    def space_evenly = {Order.attToOrder("space-evenly"); Order.row }
    def initial = {Order.attToOrder("initial"); Order.row }
    def inherit = {Order.attToOrder("inherit"); Order.row }
  }
  def align_content = { Order.proToOrder("align-content"); x }
}

class align_items extends align_self{
  private val x = new Attribute
  class Attribute{
    def stretch = {Order.attToOrder("stretch"); Order.row }
    def center = {Order.attToOrder("center"); Order.row }
    def flex_start = {Order.attToOrder("flex-start"); Order.row }
    def flex_end = {Order.attToOrder("flex-end"); Order.row }
    def baseline = {Order.attToOrder("baseline"); Order.row }
    def initial = {Order.attToOrder("initial"); Order.row }
    def inherit = {Order.attToOrder("inherit"); Order.row }
  }
  def align_items = { Order.proToOrder("align-items"); x }
}

class align_self extends all{
  private val x = new Attribute
  class Attribute{
    def auto = {Order.attToOrder("auto"); Order.row }
    def stretch = {Order.attToOrder("stretch"); Order.row }
    def center = {Order.attToOrder("center"); Order.row }
    def flex_start = {Order.attToOrder("flex-start"); Order.row }
    def flex_end = {Order.attToOrder("flex-end"); Order.row }
    def baseline = {Order.attToOrder("baseline"); Order.row }
    def initial = {Order.attToOrder("initial"); Order.row }
    def inherit = {Order.attToOrder("inherit"); Order.row }
  }
  def align_self = { Order.proToOrder("align-self"); x }
}

class all {
  private val x = new Attribute
  class Attribute{
    def initial = {Order.attToOrder("initial"); Order.row }
    def inherit = {Order.attToOrder("inherit"); Order.row }
    def unset = {Order.attToOrder("unset"); Order.row }
  }
  def all = { Order.proToOrder("all"); x }
}


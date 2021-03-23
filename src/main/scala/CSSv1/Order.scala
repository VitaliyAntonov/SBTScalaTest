package CSSv1









/** Цепочка со встроенным буфером **/
//object ChainVar
class Chain_1(var str:String =""){
  override def toString: String = str
  val myValue = 2021
  /** За методом закреплён определённый string **/
  def new_property = {this.str += "new-property "; this}
  def new_attribute = {this.str += "new-attribute "; this}
  def new_value = {this.str += s"(var--${myValue})"}
}








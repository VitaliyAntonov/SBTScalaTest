package CSSv1.Tags

import CSSv1.{CSS, OrderVar}


trait Align extends CSS with OrderVar{

  def align_items = {order += "align-items "; this} // переменная вынесена в трейт

}


//class AlignProperty extends TypeRules
//class align_content extends CSSTag{
//  type TagType = AlignProperty
//  /** Имя тега в CSS коде */
//  override val name: String = "align-content"
//  /** Тип допустимого предыдущего тега */
//  override val preType: Selector = _
//  /** Тип текущего тега */
//  override val thisType: Any = _
//  /** Тип допустимого следующего тега */
//  override val nextType: Any = _
//}
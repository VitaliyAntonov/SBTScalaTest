package CSSv1.Tags

import CSSv1.{CSS, Order, OrderVRules}

/**  */
/** Трейт собирает все CSS теги для Align */
trait AlignOrder extends CSS with AlignProperty with AlignAttribute

/** Содержит селекторы типов для Align тегов. Подмешивается в OrderVRules*/
trait AlignVRules extends CSS{
  object CSSAlignProperty
  type CSSAlignProperty
  object CSSAlignAttribute
  type CSSAlignAttribute
}

/** Свойства Align (в начале строки ордера - следующее двоеточие)  */
trait AlignProperty extends CSS with OrderVRules {


  def align_items(x: Order) = x.tagSelect("align-items", CSSProperty, CSSAttribute )
  def align_content(x: Order) = x.tagSelect("align-content", CSSProperty, CSSAttribute )


}

/** Атрибуты (в конце строки ордера - следующее двоеточие) для Align */
trait AlignAttribute extends CSS with OrderVRules {


  def stretch(x: Order) = x.tagSelect("stretch", CSSAttribute, CSSEndRow)

  /** Блок прижат к верху поперечной оси */
  def flex_start(x: Order) = x.tagSelect("flex-start", CSSAttribute, CSSEndRow)

}




package CSSv01.Tags

import scala.collection.mutable.ArrayBuffer


/**
 * @author Виталий Антонов @date 4/17/21
 *         kaligraf@yandex.ru
 * */


object tagsCSS {
  val pro  = ArrayBuffer[Array[String]]()
  pro += Array("align-content","stretch","center","flex-start","flex-end","space-between",
                     "space-around","space-evenly","initial","inherit")
  pro += Array("align-items","stretch","center","flex-start","flex-end","baseline","initial","inherit")
  pro += Array("align-self","auto","stretch","center","flex-start","flex-end","baseline","initial","inherit")
  pro += Array("all","initial","inherit","unset")
  pro += Array("animation","initial","inherit","animationCustom_TODO") /** TODO - Делать сокращённое написание */
  pro += Array("animation-name","keyframename","none","initial","inherit")
  pro += Array("animation-duration","time","initial","inherit")
  pro += Array("animation-timing-function","linear","ease","ease-in","ease-out","ease-in-out","step-start",
                     "step-end","steps_int-start_end_TODO","cubic-bezier_TODO","initial","inherit") /** TODO - Делать */
  pro += Array("animation-delay","time_TODO","initial","inherit") /** TODO - Делать */
  pro += Array("animation-iteration-count","number_TODO","infinite","initial","inherit")
  pro += Array("animation-direction","normal","reverse","alternate","alternate-reverse","initial","inherit")
  pro += Array("animation-fill-mode","none","forwards","backwards","both","initial","inherit")
  pro += Array("animation-play-state","paused","running","initial","inherit")
  pro += Array("backface-visibility","visible","hidden","initial","inherit")
  pro += Array("background","lite_TODO") /** TODO делать сокращённую форму записи */
  pro += Array("background-attachment","scroll","fixed","local","initial","inherit")
  pro += Array("background-blend-mode","normal","multiply","screen","overlay","darken","lighten",
                     "color-dodge","saturation","color","luminosity")
  pro += Array("background-clip","border-box","padding-box","content-box","initial","inherit")
  pro += Array("background-color","color_TODO","transparent","initial","inherit") // TODO делать color
  pro += Array("background-image","url_URL_TODO","none","linear-gradient_TODO","radial-gradient_TODO",
                     "repeating-linear-gradient_TODO","repeating-radial-gradient_TODO","initial","inherit")
  pro += Array("background-origin","padding-box","border-box","content-box","initial","inherit")
  pro += Array("background-position","left_top","left_center","left_bottom","right_top","right_center",
    "right_bottom","center_top","center_center","center_bottom","50procent_50_TODO","p50px_50px","initial","inherit")// TODO Делать
  pro += Array("background-repeat","repeat","repeat-x","repeat-y","no-repeat","space","round","initial","inherit")
  pro += Array("background-size","auto","length","percentage","cover","contain","initial","inherit")
  pro += Array("border","border-width","border-style","border-color","initial","inherit")
  pro += Array("border-bottom","lait_TODO")// TODO Делать
  pro += Array("border-bottom-color","color","transparent","initial","inherit")// TODO делать color
  pro += Array("border-bottom-left-radius", "PX_radius_TODO")
  pro += Array("border-bottom-right-radius", "PX_radius_TODO")
  pro += Array("border-bottom-style","none","hidden","dotted","dashed","solid","double","groove","ridge",
                      "inset","outset","initial","inherit")
  pro += Array("border-bottom-width","PX_width_TODO")
  pro += Array("border-collapse","separate","collapse","initial","inherit")
  pro += Array("border-color","color_TODO") // TODO делать color
  pro += Array("border-image","url_TODO") // TODO делать
  pro += Array("border-image-outset","PX_TODO") // TODO Делать
  pro += Array("border-image-repeat","stretch","repeat","round","space","initial","inherit")
  pro += Array("border-image-slice","number_TODO","%_TODO","fill_TODO","initial","inherit") // TODO Делать
  pro += Array("border-image-source","url_TODO")  // TODO Делать
  pro += Array("border-image-width","length_TODO","number_TODO","%_TODO","auto","initial","inherit") // TODO Делать
  pro += Array("border-left", "border_TODO") // TODO Делать
  pro += Array("border-left-color","color_TODO") // TODO делать color
  pro += Array("border-left-style","none","hidden","dotted","dashed","solid","double","groove","ridge",
                      "inset","outset","initial","inherit")
  pro += Array("border-left-width","medium","thin","thick","length_TODO","initial","inherit")// TODO Делать
  pro += Array("border-radius","length_TODO","%_TODO","initial","inherit")// TODO Делать
  pro += Array("border-right","border_TODO","initial","inherit")// TODO Делать
  pro += Array("border-right-color","color_TODO","transparent","initial","inherit") // TODO делать color
  pro += Array("border-right-style","none","hidden","dotted","dashed","solid","double","groove","ridge",
                      "inset","outset","initial","inherit")
  pro += Array("border-right-width","medium","thin","thick","length_TODO","initial","inherit")// TODO Делать
//  pro += Array()
//  pro += Array()
//  pro += Array()
//  pro += Array()
//  pro += Array()
//  pro += Array()
//  pro += Array()
//  pro += Array()
//  pro += Array()
//  pro += Array()
}



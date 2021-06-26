package dataBaseStructure

/**
 * @author Виталий Антонов @date 6/11/21
 *         kaligraf@yandex.ru
 * */

/** Типы базы данных CSS */
object CSSType extends Enumeration{

  /** Отсутствие типа - тип не определён */
  val NoType = Value("NoType")

  /** Свойства (width, color...) */
  val Property = Value("Property")

  /** Атрибуты (stretch, center, inherit... ) */
  val Attribute = Value("Attribute")

  /** Unit Position Единицы измерения координат дисплея и размеров */
  val UPos = Value("UPos")
  /** в пикселях */
  val px = Value("px")
  /** единица гибкости */
  val fr = Value("fr")
  /** размер шрифта - пропорция от начального размера, например 0.5em */
  val em = Value("em")
  /** в процентах от начального значения свойства */
  val % = Value("%")
  /** смесь px и em задаёт размер относительно размера шрифта HTML */
  val rem = Value("rem")
  /** процентная величина от общей ширины области просмотра */
  val vw = Value("vw")
  /** процентная величина от общей высоты области просмотра */
  val vh = Value("vh")
  /** процентная величина от меньшей ширины или высоты области просмора */
  val vmin = Value("vmin")
  /** процентная величина от БОЛЬШЕЙ ширины или высоты области просмора */
  val vmax = Value("vmax")

  /** Unit Color    Единицы определения цвета */
  val UColor = Value("UColor")
    /** #3E467A цвет в HEX формате*/
    val hex = Value("hex")
    /** rgb(255, 176, 0) цвет в формате rgb */
    val rgb = Value("rgb")
    /** rgba(255, 176, 0, 0.5) цвет в формате rgba; a=1 - непрозрачный; a=0 - полностью прозрачен */
    val rgba = Value("rgba")
    /** название цвета red, green, yellow... */
    val cln = Value("cln")
    /** hsl(183, 100%, 50%) цвет в формате hsl; (hue — оттенок, saturate — насыщенность, lightness — светлота) */
    val hsl = Value("hsl")
    /** hsla(183, 100%, 50%, 0.5) цвет в формате hsla - hsl c прозрачностью */
    val hsla = Value("hsla")

  /** Селекторы */
  val Selector = Value("Selector")
    /** div{...} Селектор по тегу HTML */
    val SelTag = Value("SelTag")
    /** .className{...} -  Селектор по именеи класса */
    val SelClass = Value("SelClass")
    /** #ident{...} по идентификатору */
    val SelID = Value("SelID")
    /** *{...}  универсальный селектор */
    val SelU = Value("SelU")
    /** селектор по атрибуту */
    val SelA = Value("SelA")
      /** [attr]  все элементы страницы с атрибутом attr */
      val SelAName = Value("SelAName")
      /** [rel = "nofollow"] все элементы страницы с атрибутом rel со значением nofollow */
      val SelAEqu = Value("SelAEqu")
      /** [class^="col"] все элементы с атрибутом class, начинающийся на col */
      val SelAStart = Value("SelAStart")
      /** [class|="test"] все элементы с классом "test" или начинающимыя на "test-" */
      val SelAOr = Value("SelAOr")
      /** [class$="color"] все элементы, имя класса которых заканчивается на "color" */
      val SelAEnd = Value("SelAEnd")
      /** [href*="youtu.be"] все элементы с атрибутом href с подстрокой "youtu.be" */
      val SelAString = Value("SelAString")
      /** [data-content~="news"] все элементы с атрибутом data-content, с "news" отделённым пробелом или "-" */
      val SelASSpace = Value("SelASSpace")

  /** Псевдоклассы */
  val PClass = Value("PClass")

  /** краткое описание */
  val Note = Value("Note")

  /** подробное описание */
  val Description = Value("Description")

  /** Sample Source примеры кода */
  val SSource = Value("SSource")

  val animation =
    s"""animation-name
       |animation-duration
       |animation-timing-function
       |animation-delay
       |animation-iteration-count
       |animation-direction
       |animation-fill-mode
       |animation-play-state
       |initial
       |inherit
       |""".stripMargin

  /** time int/float second/millisecond */
  val time =
    s"""s
       |ms
       |""".stripMargin

  val int = "IntCSS" // TODO

  val keyframe = "@keyframes NameCSS { }"  // TODO

  val steps = s"steps ( int , start | end ) " // TODO

  val cubicBezier = s"cubic-bezier(n,n,n,n)"  // TODO

  val background =      // TODO
    s"""background-color
       |background-image
       |background-position
       |background-size
       |background-repeat
       |background-origin
       |background-clip
       |background-attachment
       |""".stripMargin

  val colorCSS =  // TODO
    s"""color
       |""".stripMargin

  val backgroundImage = s"href" // TODO

  val backgroundPosition =  // TODO
    s"""left top
       |left center
       |left bottom
       |right top
       |right center
       |right bottom
       |center top
       |center center
       |center bottom
       |x% y%
       |xpos ypos
       |initial
       |inherit
       |""".stripMargin

  val backgroundSize =  // TODO
    s"""auto
       |length
       |percentage
       |cover
       |contain
       |initial
       |inherit
       |""".stripMargin

  val border =  // TODO
    s"""border-width
       |border-style
       |border-color
       |""".stripMargin

  val borderRadius =  // TODO
    s"""
       |length
       |%
       |""".stripMargin

  /** centimeters; millimeters; inches(1in = 96px = 2.54cm); *	pixels (1px = 1/96th of 1in);
   *  points(1pt = 1/72 of 1in); picas(1pc = 12 pt) */
  val CSSType_length =   // TODO
    s"""
       |cm
       |mm
       |in
       |px
       |pt
       |pc
       |""".stripMargin

  val CSSType_length4 =
    s"""
       | length length length length
       |""".stripMargin


  val borderImage =  // TODO
    s"""
       |border-image-source
       |border-image-slice
       |border-image-width
       |border-image-outset
       |border-image-repeat
       |initial
       |inherit
       |""".stripMargin

  val number =
    s"""
       |0
       |1
       |1 0
       |1 0 0
       |1 0 1 1
       |""".stripMargin


  val pro_% = s"Int %"  // TODO

  val image = "href"    // TODO

  val CSSType_shadow_h_offset = "CSSType_shadow_h_offset" // TODO

  val CSSType_shadow_v_offset = "CSSType_shadow_v_offset" // TODO

  val CSSType_shadow_blur = "CSSType_shadow_blur" // TODO

  val CSSType_shadow_spread = "CSSType_shadow_spread" // TODO

  /** Кодировка символов CSS стилей */
  val CSSType_charset = "CSSType_charset" // TODO

  val CSSType_clip_shape = "rect (top, right, bottom, left)" // TODO

  val CSSType_clip_source = "CSSType_clip_source" // TODO

  val CSSType_basic_shape = "CSSType_basic_shape" // TODO

  val CSSType_column_count_number = "CSSType_column_count_number" // TODO

  val CSSType_column =
    s"""
       |CSSType_column_width CSSType_Int CSSType_sizeUnit_px
       |CSSType_column_count CSSType_Int
       |""".stripMargin




}



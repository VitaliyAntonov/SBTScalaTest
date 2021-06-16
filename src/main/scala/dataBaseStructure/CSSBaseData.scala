package dataBaseStructure

/**
 * @author Виталий Антонов @date 6/12/21
 *         kaligraf@yandex.ru
 * */





object CSSBaseData{
  /** tags Property Attribute */
  val tagsPA =
    s"""align-content stretch center flex-start flex-end space-between space-around space-evenly initial inherit
       |align-items stretch center flex-start flex-end baseline initial inherit
       |align-self auto stretch center flex-start flex-end baseline initial inherit
       |all initial inherit unset
       |animation CSSType_animation
       |animation-delay CSSType_time initial inherit
       |animation-direction normal reverse alternate alternate-reverse initial inherit
       |animation-duration CSSType_time initial inherit
       |animation-fill-mode none forwards backwards both initial inherit
       |animation-iteration-count CSSType_Int infinite initial inherit
       |animation-name CSSType_keyframe none initial inherit
       |animation-play-state paused running initial inherit
       |animation-timing-function linear ease ease-in ease-out ease-in-out step-start step-end CSSType_steps CSSType_cubicBezier initial inherit
       |backface-visibility visible hidden initial inherit
       |background CSSType_background
       |background-attachment scroll fixed local initial inherit
       |background-blend-mode normal multiply screen overlay darken lighten color-dodge saturation color luminosity
       |background-clip border-box padding-box content-box initial inherit
       |background-color CSSType_colorCSS transparent initial inherit
       |background-image CSSType_backgroundImage
       |background-origin padding-box border-box content-box initial inherit
       |background-position CSSType_backgroundPosition
       |background-repeat repeat repeat-x repeat-y no-repeat initial inherit
       |background-size CSSType_backgroundSize
       |border CSSType_border4 initial inherit
       |border-bottom CSSType_border initial inherit
       |border-bottom-color CSSType_colorCSS transparent initial inherit
       |border-bottom-left-radius CSSType_borderRadius initial inherit
       |border-bottom-right-radius CSSType_borderRadius initial inherit
       |border-bottom-style none hidden dotted dashed solid double groove ridge inset outset initial inherit
       |border-bottom-width medium thin thick CSSType_length initial inherit
       |border-collapse separate collapse initial inherit
       |border-color CSSType_colorCSS transparent initial inherit
       |border-image CSSType_borderImage
       |border-image-outset CSSType_length CSSType_number initial inherit
       |border-image-repeat stretch repeat round initial inherit
       |border-image-slice CSSType_number CSSType_pro_% fill initial inherit
       |border-image-source none CSSType_image initial inherit
       |border-image-width CSSType_length CSSType_number CSSType_pro_% auto initial inherit
       |border-left CSSType_border initial inherit
       |border-left-color CSSType_colorCSS transparent initial inherit
       |border-left-style none hidden dotted dashed solid double groove ridge inset outset initial inherit
       |border-left-width medium thin thick CSSType_length initial inherit
       |border-radius CSSType_borderRadius initial inherit
       |border-right  CSSType_border initial inherit
       |border-right-color CSSType_colorCSS transparent initial inherit
       |border-right-style none hidden dotted dashed solid double groove ridge inset outset initial inherit
       |border-right-width medium thin thick CSSType_length initial inherit
       |border-spacing CSSType_length initial inherit
       |border-style none hidden dotted dashed solid double groove ridge inset outset initial inherit
       |border-top  CSSType_border initial inherit
       |border-top-color CSSType_colorCSS transparent initial inherit
       |border-top-left-radius CSSType_borderRadius initial inherit
       |border-top-right-radius CSSType_borderRadius initial inherit
       |border-top-style none hidden dotted dashed solid double groove ridge inset outset initial inherit
       |border-top-width medium thin thick CSSType_length initial inherit
       |border-width medium thin thick CSSType_length4 initial inherit
       |bottom auto CSSType_length initial inherit
       |box-decoration-break slice clone initial inherit unset
       |box-shadow none CSSType_shadow_h_offset CSSType_shadow_v_offset CSSType_shadow_blur CSSType_shadow_spread CSSType_colorCSS inset initial inherit
       |box-sizing content-box border-box initial inherit
       |break-after auto all always avoid avoid-column avoid-page avoid-region column left page recto region right verso initial inherit
       |break-before auto all always avoid avoid-column avoid-page avoid-region column left page recto region right verso initial inherit
       |break-inside  auto all always avoid avoid-column avoid-page avoid-region column left page recto region right verso initial inherit
       |caption-side top bottom initial inherit
       |caret-color auto CSSType_colorCSS initial inherit
       |@charset CSSType_charset
       |clear none left right both initial inherit
       |clip auto CSSType_clip_shape initial inherit
       |clip-path CSSType_clip_source CSSType_basic_shape margin-box border-box padding-box content-box fill-box stroke-box view-box none initial inherit
       |color CSSType_colorCSS initial inherit
       |column-count CSSType_column_count_number auto initial inherit
       |column-fill balance auto initial inherit
       |column-gap CSSType_length normal initial inherit
       |column-rule
       |column-rule-color
       |column-rule-style
       |column-rule-width
       |column-span
       |column-width
       |columns
       |content
       |counter-increment
       |counter-reset
       |cursor
       |direction
       |display
       |empty-cells
       |filter
       |flex
       |flex-basis
       |flex-direction
       |flex-flow
       |flex-grow
       |flex-shrink
       |flex-wrap
       |float
       |font
       |@font-face
       |font-family
       |font-feature-settings
       |font-kerning
       |font-size
       |font-size-adjust
       |font-stretch
       |font-style
       |font-variant
       |font-variant-caps
       |font-weight
       |gap
       |grid
       |grid-area
       |grid-auto-columns
       |grid-auto-flow
       |grid-auto-rows
       |grid-column
       |grid-column-end
       |grid-column-gap
       |grid-column-start
       |grid-gap
       |grid-row
       |grid-row-end
       |grid-row-gap
       |grid-row-start
       |grid-template
       |grid-template-areas
       |grid-template-columns
       |grid-template-rows
       |hanging-punctuation
       |height
       |hyphens
       |@import
       |isolation
       |justify-content
       |@keyframes
       |left
       |letter-spacing
       |line-height
       |list-style
       |list-style-image
       |list-style-position
       |list-style-type
       |margin
       |margin-bottom
       |margin-left
       |margin-right
       |margin-top
       |max-height
       |max-width
       |@media
       |min-height
       |min-width
       |mix-blend-mode
       |object-fit
       |object-position
       |opacity
       |order
       |outline
       |outline-color
       |outline-offset
       |outline-style
       |outline-width
       |overflow
       |overflow-x
       |overflow-y
       |padding
       |padding-bottom
       |padding-left
       |padding-right
       |padding-top
       |page-break-after
       |page-break-before
       |page-break-inside
       |perspective
       |perspective-origin
       |pointer-events
       |position
       |quotes
       |resize
       |right
       |row-gap
       |scroll-behavior
       |tab-size
       |table-layout
       |text-align
       |text-align-last
       |text-decoration
       |text-decoration-color
       |text-decoration-line
       |text-decoration-style
       |text-indent
       |text-justify
       |text-overflow
       |text-shadow
       |text-transform
       |top
       |transform
       |transform-origin
       |transform-style
       |transition
       |transition-delay
       |transition-duration
       |transition-property
       |transition-timing-function
       |unicode-bidi
       |user-select
       |vertical-align
       |visibility
       |white-space
       |width
       |word-break
       |word-spacing
       |word-wrap
       |writing-mode
       |z-index
       |""".stripMargin

}



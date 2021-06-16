package CSSv01.Tags

import scala.collection.mutable

/**
 * @author Виталий Антонов @date 4/27/21
 *         kaligraf@yandex.ru
 * */
object CssI {
  val descriptions: mutable.Map[String, String] = mutable.Map(

    "align-content" ->
      s"""
         |CSS свойство align-content определяет как браузер распределяет
         |пространство между и вокруг флекс элементов вдоль поперечной оси
         |контейнера (вертикально), или производит выравнивание всего макета
         |сетки по оси столбца grid-контейнера.
         |""".stripMargin,

    "align-items" ->
      s"""
         |CSS свойство align-items производит выравнивание всех элементов
         |внутри флекс контейнера вдоль поперечной оси, или производит выравнивание
         |всех элементов макета сетки по оси столбца grid-контейнера.
         |""".stripMargin,

    "align-self" ->
      s"""
         |CSS свойство align-self задает выравнивание отдельных элементов
         |строки внутри флекс контейнера, или производит выравнивание элемента
         |макета сетки внутри ячейки по оси столбца grid-контейнера.
         |
         |Свойство переопределяет значение выравнивания, заданного для
         |контейнера свойством align-items конкретному элементу.
         |""".stripMargin,

    "all" ->
      s"""
         |CSS свойство all сбрасывает все свойства элемента к их первоначальному
         |или унаследованному значению (за исключением свойства direction,
         |отвечающего за направление текста и свойство unicode-bidi, которое
         |позволяет задействовать двунаправленный алгоритм отображения текста.)
         |
         |Предпологается, что это свойство упростит интеграцию сторонних блоков
         |на страницы сайтов, т.к. влияние встроенных стилей сайта будет минимизировано.
         |""".stripMargin,

    "animation" ->
      s"""
         |CSS свойство animation позволяет задать все свойства анимации в одном объявлении.
         |Это свойство является короткой записью для следующих свойств:
         |
         |animation-name ("keyframename /-s | none | initial | inherit")
         |animation-duration ("time | initial | inherit")
         |animation-timing-function ("linear | ease | ease-in | ease-out | ease-in-out |
         |step-start | step-end | steps(int, start | end) | cubic-bezier(n,n,n,n) | initial | inherit")
         |animation-delay ("time | initial | inherit")
         |animation-iteration-count ("number | infinite | initial | inherit")
         |animation-direction ("normal | reverse | alternate | alternate-reverse | initial | inherit")
         |animation-fill-mode ("none | forwards | backwards | both | initial | inherit")
         |animation-play-state ("paused | running | initial | inherit")
         |Обратите внимание на важный момент, порядок свойств в списке соответствует необходимому
         |порядку указания значений в свойстве animation. Имя анимации может быть указано
         |как в начале списка, так и в его конце.
         |""".stripMargin,

      "animation-delay" ->
      s"""
         |CSS свойство animation-delay определяет задержку для запуска анимации.
         |Задержка анимации задается в секундах (s) или миллисекундах (ms).
         |
         |Удобнее использовать
         |сокращенное обозначение свойства анимации - animation, чтобы
         |установить все свойства анимации в одном объявлении.
         |""".stripMargin,

      "animation-direction" ->
      s"""
         |CSS свойство animation-direction определяет будет ли анимация воспроизводиться
         |в обратном направлении, или в виде чередующихся циклов.
         |Допускается указывать несколько значений, перечисленных через запятую
         |(для каждой отдельной анимации).
         |""".stripMargin,

      "animation-duration" ->
      s"""
         |CSS свойство animation-duration определяет, сколько секунд или миллисекунд
         |затрачивается на выполнение одного цикла анимации.
         |""".stripMargin,

      "animation-fill-mode" ->
      s"""
         |CSS свойство animation-fill-mode задает стиль для элемента,
         |когда анимация не воспроизводится (когда она будет закончена, или,
         |когда она имеет задержку, установленную свойством animation-delay).
         |""".stripMargin,

      "animation-iteration-count" ->
      s"""
         |CSS свойство animation-iteration-count указывает, сколько раз анимация
         |должна быть воспроизведена (сколько раз будет проигрываться анимационный цикл).
         |""".stripMargin,

      "animation-name" ->
      s"""
         |CSS свойство animation-name указывает имя анимации/список анимаций, которые должны
         |быть применены к выбранному элементу. Каждое имя указывает на правило
         |(@keyframes), которое определяет значения свойств анимации.
         |""".stripMargin,

      "animation-play-state" ->
      s"""
         |CSS свойство animation-play-state определяет состояние анимации
         |(анимация воспроизводится, либо приостановлена).
         |""".stripMargin,

      "animation-timing-function" ->
      s"""
         |CSS свойство animation-timing-function определяет кривую скорости для
         |анимации (используется математическая функция - кубическая кривая Безье).
         |Кривая скорости определяет время анимации, используемое,
         |чтобы изменить один набор стилей CSS на другой.
         |""".stripMargin,

      "backface-visibility" ->
      s"""
         |CSS свойство backface-visibility определяет, должна ли быть видна задняя
         |сторона элемента или нет. Данное свойство применяется для того, чтобы при
         |трансформации элемента скрыть его заднюю сторону, а не отображать её зеркально
         |(значение по умолчанию).
         |""".stripMargin,

    "background" ->
      s"""
         |CSS свойство background позволяет установить необходимые свойства фона
         |в одном объявлении (универсальное свойство). Значения могут быть указаны
         |в любом порядке (и в любом необходимом количестве), браузер автоматически определит,
         |какое из них соответствует необходимому свойству.
         |
         |Значения следующих свойств могут быть установлены:
         |
         |background-color (color | transparent| initial | inherit)
         |background-image (url | none | initial | inherit)
         |background-position (значение)
         |background-size (auto | length | cover | contain | initial | inherit)
         |background-repeat (repeat | repeat-x |repeat-y | no-repeat | initial | inherit)
         |background-origin (padding-box | border-box | content-box | initial | inherit)
         |background-clip (border-box | padding-box | content-box | initial | inherit)
         |background-attachment (scroll | fixed | local | initial | inherit)
         |""".stripMargin,

      "background-attachment" ->
        s"""
         |CSS свойство background-attachment устанавливает поведение фонового
         |изображения во время прокрутки страницы. По умолчанию изображение прокручивается
         |с остальной частью страницы, но допускается также зафиксировать фон относительно
         |уровня просмотра.
         |""".stripMargin,

      "background-blend-mode" ->
        s"""
         |CSS свойство background-blend-mode определяет режим смешивания слоя каждого
         |фонового цвета ( и / или изображения).
         |""".stripMargin,

      "background-clip" ->
        s"""
           |CSS свойство background-clip определяет область элемента для которой
           |будет задан задний фон.
           |
           |А в чем заключается разница между свойством background-origin и background-clip?
           |Разница заключается в том, что свойство background-clip в отличие от
           |background-origin обрезает ту часть фона, которая выходит из указанных рамок.
           |Свойство background-origin лишь определяет, как позиционируется фоновое изображение.
           |
           |background-clip:"border-box | padding-box | content-box | initial | inherit";
           |""".stripMargin
  )
  descriptions += ("" -> "")
}

object mapTester{
  val s = CssI.descriptions.getOrElse("background","")
  print(s)
}



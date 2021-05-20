package siteCreator.component

import siteCreator.HtmlPC

/**
 * @author Виталий Антонов @date 5/5/21
 *         kaligraf@yandex.ru
 * */

/**
 * Класс строит набор литер английского алфавита для страницы helpCss
 */
class literaBoxes extends Component("literaBoxes") {


  /**
   * Метод строит HTML шаблон компонента и добавляет шаблоны компонента в CSS и JS файлы
   * @param page имя страницы, которая формируется
   */
  override def html(page: HtmlPC): Unit = {
    /** Начальный отступ блока литер  от верха материнского контейнера */
    val top: Int = 20
    /** Расчётный отступ строки литеры */
    var topRow: Int = top
    /** Начальный отступ слева блока литер */
    val leftColumn1: Int = 20
    /** Отступ слева 2-го столбца литер */
    val leftColumn2: Int = 110
    /** Ширина литеры */
    var width: Int = 35
    /** Высота литеры */
    var height: Int = 35
    /** Зазор между литерами */
    var gap: Int = 8

    /** Добавляем CSS, Общий для всех литер */
    addCss(page, literaBoxes.cssForAll)

    /** изменяющийся от тега к тегу CSS */
    var css: String = ""
    /** изменяющаяся от тега к тегу координата left */
    var left: Int = leftColumn1
    /** изменяющаяся для каждой литеры количество тегов в базе */
    var tagsQua: Int = 0
    /** изменяющееся имя класса контейнера тегов для литеры */
    var classNameLiteraTagsBox: String = ""
    /** При отсутствии или наличии тегов меняется цвет контейнера литеры */
    var literaEmptyTagsCss: String = ""

    /** Цикл для прорисовки всех букв англ. алфавита */
    for(i <-0 until alphabet.length){                   // перебор всех литер
      addHtml(page, literaBoxes.contPlus(alphabet(i)))   // Добавили HTML литеры
      /** Проверка столбца букв формирование CSS для left и top */
      if(i < 13) {
        topRow = (height + gap) * i + top
        left = leftColumn1
      }
      else {
        topRow = (height + gap) * (i - 13) + top
        left = leftColumn2
      }
      css = s""".litera-${alphabet(i)} {left: ${left}px; top: ${topRow}px;}\n""" // сформировали CSS
      addCss(page, css)    // добавили CSS литеры для left и top
    }
    addCss(page, "\n") //  перевод строки для форматирования CSS кода

    /** Цикл для прорисовки контейнеров тегов литер */
    for(i <-0 until alphabet.length) { // перебор всех литер
      /** Проверка столбца букв формирование CSS для left и top */
      if (i < 13) left = leftColumn1
      else left = leftColumn2
      /** Для каждой литеры добавляется контейнер с анимированными тегами
       * имя класса для контейнера формируем здесь и передаём в функцию
       * создания контейнера с тегами. Пример имени: helpCss-litera-a-tags-box
       * имя содержит имя страницы, имя литеры, название бокса.
       * В CSS контейнера определяем координату left */
      classNameLiteraTagsBox = s"""helpCss-litera-${alphabet(i)}-tags-box""" // имя класса для контейнера
      /** Для новой справочной системы здесь можно заменить HtmlCom.aTags... */
      tagsQua = HtmlCom.aTags.literaTagsBox(page, alphabet(i), classNameLiteraTagsBox,
        10, left + 70)      // Добавили HTML контейнера

      if(tagsQua > 0) {  // При наличии тегов литеры в базе
        /** Для каждой литеры добавляем CSS для hover, если список тегов не пуст */
        addCss(page, literaHoverCss(s"""litera-${alphabet(i)}""", classNameLiteraTagsBox))
        /** Так-же добавляем CSS для визуализации тегов в боксе тегов литеры по hover */
        addCss(page, HtmlCom.aTags.CCss.cssForHoverVisualTagsBoxes(s"""litera-${alphabet(i)}""", classNameLiteraTagsBox))
      }
      /** При пустом списке тегов изменяем цвет литеры и фона контейнера литеры */
      /** Добавляем CSS цвет буквы от наличия тегов */
      addCss(page, literaBoxes.literaIsEmptyTags(s"""litera-${alphabet(i)}""", tagsQua))
    }

  }

  /** перечень символов английского алфавита */
  val alphabet: String = "abcdefghijklmnopqrstuvwxyz"

  /**
   * функция формирует CSS для hover литеры
   * @param className  - имя класса литеры
   * @param boxClassName - имя класса контейнера с тегами для литеры
   * @return - сформированный CSS код
   */
  def literaHoverCss(className: String, boxClassName: String): String =
    s"""
       |.${className}:hover ~ .${boxClassName} {
       |  width: 600px;
       |}
       |""".stripMargin

}

object literaBoxes{
  /**
   * Метод формирует контейнер для буквы
   * @param  litera - буква контейнера
   * @return - html контейнера
   */
  def contPlus(litera: Char): String = {
    /** Сформированный HTML */
    var s = ""
    s += s"""    <div class="litera litera-${litera.toString}"><span>${litera.toString}</span></div>\n"""
    s
  }

  /**  CSS для всех литер  */
  def cssForAll: String =
    s"""
       |.litera {
       |  position: absolute;
       |  display: flex;
       |  align-content: center;
       |  justify-content: center;
       |  width: 35px;
       |  height: 35px;
       |  border-radius: 8px;
       |  border: 1px solid green;
       |  user-select: none;
       |  cursor: pointer;
       |  transition: all 50ms ease-in-out;
       |}
       |
       |.litera>span {
       |  font-size: 22px;
       |  transition: all 50ms ease-in-out;
       |}
       |""".stripMargin

  /** CSS буквы, меняем цвета, в зависимости от наличия тегов в базе */
  def literaIsEmptyTags(className: String, quaTagsInBase: Int): String = {
    var s = ""
    if(quaTagsInBase > 0){ // есть теги в базе
      s = s"""\n.${className} {color: #f9fc75; background-color: #384952;}"""

      s +=
        s"""
           |.${className}:hover {
           |  padding-right: 10px;
           |  justify-content: flex-end;
           |  transform: translateY(-10px);
           |  z-index: 1;
           |  background-color: darkmagenta;
           |  width: 80px;
           |  height: 55px;
           |}
           |
           |.${className}:hover>span {
           |  color: rgb(249, 252, 117);
           |  font-size: 40px;
           |}
           |""".stripMargin

    }
    else {  // нет тегов в базе
      s = s"""\n.${className} {color: #000000; background-color: #4b4b4b;}"""
    }
    s
  }


}



package workSxfRsc

import scala.collection.mutable.ArrayBuffer

/**
  * @author Виталий Антонов @date 7/2/21
  **/

/** Класс для парсинга классификатора RSC формата Panorama */
class RscParser {

}


/** ---------------------------- Структура классификатора -------------------------- */

/** Создание последовательности из переменных класса
  * при наследовании Enums:
  * val a = Enumeration.values[RscTab]
  * */

/**
  * Структура таблиц, прописанных в заголовке классификатора
  * @param ofsetTab         - Смещение на таблицу
  * @param lengthTab        - Длина таблицы в байтах
  * @param recordTabCount   - Число записей в таблице
  * @param preId            - Идентификатор перед таблицей(4 байта символов 'char' внутри Int)
  */
case class RscSection(ofsetTab: RscHeader, lengthTab: RscHeader,
                      recordTabCount: RscHeader, preId : Int)

/** ЗАГОЛОВОК КЛАССИФИКАТОРА
  * Структура полей(в байтах) заголовка классификатора
  * в документации Ошибка в поле смещения в таблице возможных семантик
  * поэтому необходимо пересчитать поле offsetBytes - оставлен var
  * @param offsetBytes   - Смещение от начала файла в байтах
  * @param lengthBytes  - Длина поля в таблице в байтах
  * @param valueInt     - Значение типа Int(4 байта), если используется
  */
case class RscHeader(var offsetBytes: Int, lengthBytes: Int, valueInt: Int){
  RscHeader.buffer += this // добавляем экземпляр класса в список
}

/** Поля заголовка классификатора */
object RscHeader {

  /** список всех объектов класса RscHeader */
  private val buffer = ArrayBuffer[RscHeader]()

  /** Функция возвращает список всех объектов класса RscHeader */
  def getObjects =  buffer.toArray

  /** ------------------ Структура полей заголовка классификатора ------------------- */
  /** Идентификатор файла */
  val fileId = RscHeader(0, 4, 0x00435352) // 0x00435352 (RSC)
  /** Длина файла */
  val fileLength = RscHeader(4, 4, 0) // В байтах
  /** Версия структуры RSC */
  val rscStructVersion = RscHeader(8, 4, 0x0700)
  /** Кодировка */
  val rscEncoding = RscHeader(12, 4, 0) // Для всего файла
  /** Номер состояния файла */
  val numFileState = RscHeader(16, 4, 0) // Учет корректировок,
                                         // требующих перегрузки данных
  /** Номер модификации состояния */
  val numFileModState = RscHeader(20, 4, 0) // Учет корректировок
  /** Используемый язык */
  val uLang = RscHeader(24, 4, 0) // 1 - английский, 2 - русский
  /** Максимальный идентификатор таблицы объектов */
  val maxNextID = RscHeader(28, 4, 0) // Идентификатор для нового объекта
  /** Дата создания файла */
  val date = RscHeader(32, 8, 0) // ГГГГММДД
  /** Тип карты */
  val mapType = RscHeader(40, 32, 0) //    Примечание 1.
  /** Условное название классификатора */
  val classifyName = RscHeader(72, 32, 0) // ANSI
  /** Код классификатора */
  val classifyCode = RscHeader(104, 8, 0) // ANSI
  /** Масштаб карты */
  val scale = RscHeader(112, 4, 0) // Базовый масштаб карты, на который составлен классификатор.
                                   // Значение поля не накладывает ограничений на применение
                                   // с картами другого базового масштаба.
  /** Масштабный ряд */
  val scaleRow = RscHeader(116, 4, 0)  // Примечание 2

  /** --------------------- Таблицы ------------------------- */

  /** 1 Таблица объектов */
  val objectsTab = RscSection(
    RscHeader(120,4,0), //  Смещение на таблицу от начала файла
    RscHeader(124,4,0), //  Длина таблицы в байтах
    RscHeader(128,4,0), //  Число записей. Записи переменной длины
    0x004A424F  // .OBJ
  )

  /** 2 Таблица семантик */
  val semanticTab = RscSection(
    RscHeader(132, 4, 0),  // Смещение на таблицу От начала файла
    RscHeader(136, 4, 0),  // Длина таблицы В байтах
    RscHeader(140, 4, 0), // Число записей Записи постоянной длины
    0x004D4553  // .SEM
  )

  /** 3 Таблица классификатор семантики */
  val semClassTab = RscSection(
    RscHeader(144, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(148, 4, 0), // Длина таблицы В байтах
    RscHeader(152, 4, 0),  // Число записей Записи постоянной длины
    0x00534C43  // .CLS
  )

  /** 4 Таблица умолчаний */
  val defaultsSemTab = RscSection(
    RscHeader(156, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(160, 4, 0), // Длина таблицы В байтах
    RscHeader(164, 4, 0),  // Число записей Записи постоянной длины
    0x00464544  // .DEF
  )

  /** 5 Таблица возможных семантик */
  val semanticPossibleTab = RscSection(
    RscHeader(168, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(152, 4, 0), // Длина таблицы В байтах
    RscHeader(156, 4, 0), // Число записей Записи переменной длины
    0x00534F50  // .POS
  )

  /** 6 Таблица сегментов (слоев) */
  val layersTab = RscSection(
    RscHeader(160, 4, 0),  // Смещение на таблицу От начала файла
    RscHeader(164, 4, 0),  // Длина таблицы В байтах
    RscHeader(168, 4, 0),  // Число записей Записи переменной длины
    0x00474553  // .SEG
  )

  /** 7 Таблица Порогов */
  val limitsTab = RscSection(
    RscHeader(172, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(176, 4, 0), // Длина таблицы В байтах
    RscHeader(180, 4, 0), // Число записей Записи переменной длины
    0x004D494C  // .LIM
  )

  /** 8 Таблица параметров ЭКРАНА (параметры отображения на экране) */
  val paramDisplayTab = RscSection(
    RscHeader(184, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(188, 4, 0), // Длина таблицы В байтах
    RscHeader(192, 4, 0), // Число записей Записи переменной длины
    0x00524150  // .PAR
  )

  /** 9 Таблица печати (параметры печати) */
  val printTab = RscSection(
    RscHeader(196, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(200, 4, 0), // Длина таблицы В байтах
    RscHeader(204, 4, 0), // Число записей Записи переменной длины
    0x004E5250  // .PRN
  )

  /** 10 Таблица палитр */
  val palettesTab = RscSection(
    RscHeader(208, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(212, 4, 0), // Длина таблицы В байтах
    RscHeader(216, 4, 0), // Число записей Записи постоянной длины
    0x004C4150  // .PAL
  )

  /** 11 Таблица шрифтов */
  val fontsTab = RscSection(
    RscHeader(220, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(224, 4, 0), // Длина таблицы В байтах
    RscHeader(228, 4, 0), // Число записей Записи постоянной длины
    0x00545854  // .TXT
  )

  /** 12 Таблица библиотек */
  val libsTab = RscSection(
    RscHeader(232, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(236, 4, 0), // Длина таблицы В байтах
    RscHeader(240, 4, 0), // Число записей Записи постоянной длины
    0x004C4D49  // .IML
  )

  /** 13 Таблица изображений семантики */
  val imageSemanticTab = RscSection(
    RscHeader(244, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(248, 4, 0), // Длина таблицы В байтах
    RscHeader(252, 4, 0), // Число записей Записи постоянной длины
    0x00000000
  )

  /** 14 Таблица таблиц */
  val tableTab = RscSection(
    RscHeader(256, 4, 0), // Смещение на таблицу От начала файла
    RscHeader(260, 4, 0), // Длина таблицы В байтах
    RscHeader(264, 4, 0), // Число записей Записи постоянной длины
    0x00424154  // .TAB
  )

  /** ^^^^^^^^^^^^^^^^^^^^  Окончание блока таблиц  ^^^^^^^^^^^^^^^^^^^^^^^^ */

  /** Флаг использования ключей как кодов */
  val flagKeysAsCodes = RscHeader(268, 1, 0)

  /** Флаг модификации палитры */
  val flagPaletteMods = RscHeader(269, 1, 0) // Учет корректировок

  /** Резерв НЕ ИСПОЛЬЗОВАТЬ */
  val noUsing = RscHeader(270, 30, 0)

  /** Кодировка шрифтов */
  val fonsEncoding = RscHeader(300, 4, 0) //  125- строка (KOI8), ограниченная нулем;
                                          //  126 - строка (ANSI, WINDOWS), ограниченная нулем.
  /** Количество цветов в палитрах */
  val colorsInPalette = RscHeader(304, 4, 0)  //  Не более 256, одинаково для всех палитр


  /** Пересчёт смещения в полях заголовка классификатора */
  private var offset: Int = 0
  private var sizeField: Int = 0
  for(i <- buffer.indices){
    if(i > 0){
      buffer(i).offsetBytes = offset + sizeField
    }
    offset = buffer(i).offsetBytes
    sizeField = buffer(i).lengthBytes
  }

}


/**
  * Строка таблицы смещения и длины данных
  * Смещение и длина уточняются при данных переменной длины - оставлен var
  * @param offsetBytes - смещение от начала таблицы в байтах
  * @param lengthBytes - длина в байтах
  */
case class TabOFL(var offsetBytes: Int, var lengthBytes: Int)

/** ------------------------------------------------------------- */






/** ------------------------------------------------------------- */


/** ------------------------------------------------------------- */












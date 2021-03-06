package workSxfRsc.HeaderTables

import workSxfRsc.TabOFL

/**
  * @author Виталий Антонов @date 7/9/21
  **/
class Ht2Semantic {

}

/**
  * 2.1.3 Структура таблицы семантик  классификатора
  *
  * Таблица семантик классификатора находится по смещению на таблицу семантик,
  * имеет общую длину, указанную в заголовке классификатора.
  * Перед таблицей семантик классификатора находится идентификатор таблицы
  *
  * “.SEM” (шестнадцатеричное число 0X004D4553)  (не входит в длину таблицы).
  *
  * Записи таблицы семантики постоянной длины, 84 байт.
  * Одна запись на одну семантику классификатора.
  */



object Ht2Semantic {

  /** Код семантики. Неизменяемый уникальный */
  val semanticId = TabOFL(0, 4)

  /** Тип значения семантики. Примечание 1 */
  val typeSemantic = TabOFL(4, 2)

  /** Повторяемость семантики. 1 - у объекта может быть несколько значений семантики с таким кодом */
  val cloneSemanticFlag = TabOFL(6, 1)

  /**  Признак служебной семантики. 1 - семантику можно использовать для всех объектов классификатора */
  val serviceSemanticFlag = TabOFL(7, 1)

  /** Название ANSI */
  val name = TabOFL(8, 32)

  /** Короткое имя семантики. Уникальное символьное имя (ANSI).
    * Используется для подписей полей в базах данных */
  val uniqueName = TabOFL(40, 16)

  /** Единица измерения  ANSI */
  val unitOfMeass = TabOFL(56, 8)

  /** Размер поля семантики. Число от 0 до 255 */
  val semanticFieldSize = TabOFL(64, 2)

  /** Точность семантики. Количество цифр после запятой (при выводе) */
  val signsAfterPoint = TabOFL(66, 1)

  /** Флаг.  2 семантика составная */
  val compoundFlag = TabOFL(67, 1)

  /** Смещение на описание классификатора семантики. От начала файла. Если нет записей – 0 */
  val classifierOffset = TabOFL(68, 4)

  /** Количество записей в классификаторе данной семантики. Если нет записей – 0 */
  val semRecordsClassQua = TabOFL(72, 4)

  /** Смещение на умалчиваемые значения семантики. От начала файла. Если нет записей – 0. */
  val defaultSemanticOffset = TabOFL(76, 4)

  /** Количество записей для умалчиваемых значений. Если нет записей – 0 */
  val defaultSemanticQua = TabOFL(80, 4)

  /** ИТОГО:   84 байта */

}


/**
  * Примечания:
  * 1. Тип значения семантики  (1 байт):
  * 0х00  -  символьная строка,
  * 0х01  -  числовое значение или значение в виде числового кода из классификатора значений
  * 0х09  -  имя файла зарегистрированного типа
  * 0х0А -  имя файла BMP
  * 0x0B -  имя файла, обрабатываемого OLE-сервером
  * 0x0C - ссылка на произвольный объект карты (уникальный номер объекта)
  * 0x0D - имя файла-паспорта района
  * 0x0E -  имя текстового файла
  * 0x0F -  имя файла PCX
  *
  * 2. В классификаторе существуют зарезервированные коды семантических  характеристик:
  * 32800 -  классификационный код объекта,
  * 32801 - признак объекта, имеющего ссылку на подчиненный объект,
  * 32802 - признак объекта, на который имеется ссылка от главного объекта,
  * 32803 - признак объекта, входящего в группу равноправных объектов,
  * 32804 - ссылка на подпись от объекта,
  * 32805 - ссылка на объект от подписи,
  * 32810 - короткое имя слоя объекта,
  * 32811 - короткое имя объекта (ключ),
  * 91000000l - внешний код рамки листа.
  *
  */






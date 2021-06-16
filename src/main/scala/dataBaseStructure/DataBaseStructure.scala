package dataBaseStructure

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * @author Виталий Антонов @date 6/9/21
 *         kaligraf@yandex.ru
 *
 *
 * Структура базы данных справки
 * */


/** Типы кортежей, используемые для баз данных */
trait DRowTypes{
  /** Определение типа RowFFAs (Строка(кортеж) из типов String, String, ArrayBuffer[String]) */
  type RowSSAs = (String, String, ArrayBuffer[String])
  /** Создание таблицы из строк */
  def newTable[T] = ArrayBuffer[T]()
}

class DB

/** База данных */
class DataBase(val name:String) extends DB with DRowTypes{
  /** Корневой каталог базы */
  val bdRoot = newTable[RowSSAs]
}

/** Класс для определения диапазона ID тегов */
class TagRangeID (val minID: Int, val maxID: Int)


/** База данных таблиц стилей CSS */
class CSSDBase extends DataBase("CSSBase"){
  /**
   * Property     Свойства (width, color...)
   * Attribute    Атрибуты (stretch, center, inherit... )
   * UPos         Unit Position Единицы измерения координат дисплея и размеров
   * UColor       Unit Color    Единицы определения цвета
   * Selector     Селекторы
   * PClass       Псевдоклассы
   * Note         краткое описание
   * Description  подробное описание
   * SSource      Sample Source примеры кода
   * CSSTypes     Типы тегов, таблиц, полей базы данных
   * */


  /**
   * Таблица тегов содержит все теги базы данных CSS
   * Привязывает тег к его ID
   * ID назначаются в определённых диапазонах
   * По значению ID определяются варианты обработки тегов
   * mutable.Map[String, Int]()
   * _1  имя тега
   * _2  ID тега
   * */
  lazy val tagsMap = mutable.Map[String, Int]()


  /** КОНСТРУКТОР добавляем имена и ID property в массив тегов tagsMap */
  for(i <- property.indices) tagsMap += (property(i) -> (propertyRangeID.minID + i))


  /** Диапазон ID для property */
  lazy val propertyRangeID = new TagRangeID(1000,1299)
  /** Диапазон ID для Attribute */
  lazy val attributeRangeID = new TagRangeID(1300,1599)


  /** Список ИМЁН всех свойств Property */
  lazy val property: Array[String] = CSSBaseData.tagsPA.split("\n") // массив имён свойств CSS





}


/** Список баз */
class DataBaseList {

  /** список(map) Неповторяющихся имён баз для проверки уникальности имени */
  val mapBasesNames = mutable.Map[String, Int]()

  /**
   * Создание и добавление базы в список баз
   * @param dbName
   */
  def addBaseToList(dBase: DataBase): Unit = {
    if(!mapBasesNames.contains(dBase.name)){
      val num = mapBasesNames.size  // порядковый номер равен количеству баз в списке
      mapBasesNames += (dBase.name -> num) // добавляем имя базы в список неповторяющихся имён
    }
    else{
      println("Имя базы повторяется. Невозможно добавить базу!")
    }
  }
}



object dataBaseTest{

  /** список баз данных */
  val listBases = new DataBaseList

  /** Формирование базы CSS */
  val CSSBase = new CSSDBase

  // Добавляем базу в список
  listBases.addBaseToList(CSSBase)

  println("CSSBase.property.size : " + CSSBase.property.length)
  println("CSSBase.tagsMap.size : " + CSSBase.tagsMap.size)

  println(CSSBase.property.mkString("\n"))
  for(i <- CSSBase.property.indices) {
    println(CSSBase.tagsMap(CSSBase.property(i)) )
  }
  for ((k,v) <- CSSBase.tagsMap) println(s"key: $k, value: ${v}")

}





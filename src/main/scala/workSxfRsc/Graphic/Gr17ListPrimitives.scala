package workSxfRsc.Graphic

import workSxfRsc.{TabGP, TabOFL}

/**
  * @author Виталий Антонов @date 7/8/21
  **/


class Gr17ListPrimitives {

}

/** 17. Набор примитивов, код типа примитива  147. */
object Gr17ListPrimitives {

  /** Идентификатор записи. 0x7FFF7FFFE */
  val tRecordId = TabOFL(0, 4)

  /** Полная длина параметров. В байтах(N1) */
  val tLength = TabOFL(4, 4)

  /** Количество примитивов */
  val tPrimitiveCount = TabOFL(8, 4)

  /** Примитив 1 - текстовый */
  val tPrim1 = TabGP(
    TabOFL(12, 2),  /** Длина 1 примитива. Длина параметров +4 */
    TabOFL(14, 2),  /** Номер примитива. */
    TabOFL(16, 0)  /** Параметры по типу примитива. */
  )

  /** Далее три  строки для каждого примитива */

  /** Итого:   N1 */

}



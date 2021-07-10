package workSxfRsc

/**
  * @author Виталий Антонов @date 7/7/21
  **/



/**
  * Таблица графического Примитива
  * @param paramLength    - длина параметров примитива в байтах + 4
  * @param primId         - код(номер) примитива
  * @param parameters     - параметры примитива (параметры векторного знака)
  */
case class TabGP(paramLength: TabOFL, primId: TabOFL, parameters: TabOFL)







/** ==================================================================================== */
/** topic <List> methods */

/** val abcde = List("a","b","c","d","e")  Объявление списка */

/** Основные операции, проводимые над списками */
/** <::>        добавить элемент в начало списка */
/** <head>      возвращает первый элемент списка */
/** <tail>      возвращает список, состоящий из всех элементов, за исключением первого */
/** <isEmpty>   возвращает true, если список пуст */
/**  Методы head и tail определены только для непустых списков */

/** Методы первого порядка, определенные в классе List */
/** <:::>       объединение двух списков */
/** <length>    получение длины списка */
/** <last>      возвращает последний элемент непустого списка */
/** <init>      список, состоящий из всех элементов, за исключением последнего */
/** <reverse>   реверсирование списков */

/** Префиксы и суфиксы */
/** <take>      Выражение xs take n возвращает первые n элементов списка xs */
/** <drop>      xs drop n возвращает все элементы списка xs, за исключением первых n элементов */
/** <splitAt>   разбивает список по заданному индексу, возвращая пару из двух списков */

/** Выбор элемента */
/** <apply>     возвращает элемент с указанным индексом (большое время на длинных списках) */
/** <indices>   возвращает список, состоящий из всех допустимых индексов заданного списка */

/** Линеаризация списка списков: flatten */
/** <flatten>   получает список списков и линеаризирует его в единый список */

/** Объединение в пары и обратное разбиение */
/** <zip>       получает два списка и формирует список из пар их значений */
/** <zipWithIndex>  составляет пары из элементов списка и той позиции, в которой они расположены */
/** <unzip>     список кортежей преобразуется обратно в кортеж списков */

/** Отображение списков */
/** <toString>  возвращает каноническое строковое представление списка */
/** <mkString>  mkString (pre, sep, post) преобразует List в строку из всех элементов списка */
/** <addString> добавляют созданную строку к объекту StringBuilder */

/** Преобразование списков */
/** <toArray>   Преобразование в массив */
/** <copyToArray> xs copyToArray(arr, start) из списка xs в массив arr, начиная с позиции start */
/** <iterator>  val it = abcde.iterator; it.next  доступ к элементам списка через итератор*/

/** сортировка методом слияния */

// topic 16.7. Методы высшего порядка, определенные в классе List
/** <map>       xs map f получает в качестве операндов список xs типа
                List[T] и функцию f типа T => U. Она возвращает список,
                получающийся в результате применения f к каждому элементу списка xs
                пример:  List(1, 2, 3) map (_ + 1) */
/** <flatMap>   похож на map, но в качестве правого
                операнда получает функцию, возвращающую список элементов. Он
                применяет функцию к каждому элементу списка и возвращает
                объединение всех результатов выполнения функции. */
/** <foreach>   получает в качестве
                правого операнда процедуру (функцию, результатом которой
                является тип Unit). Она просто применяет процедуру к каждому
                элементу списка. А сам результат операции также имеет тип Unit */
/** <range>     является вспомогательным методом, создающим
                список из всех целых чисел в некотором диапазоне */
// topic Фильтрация списков
/** <filter>    Операция xs filter p получает в качестве операндов список xs
                типа List[T] и функцию-предикат p, относящуюся к типу T =>
                Boolean. Эта операция выдает список всех элементов x из списка
                xs, для которых p(x) вычисляется в true */
/**  <partition> похож на метод filter, но возвращает пару
                списков. Один список содержит все элементы, для которых
                предикат вычисляется в true, а другой — все элементы, для
                которых предикат вычисляется в false */
/** <find>      также похож на метод filter, но возвращает только первый
                элемент, удовлетворяющий условию заданного предиката */
/** <takeWhile >  Операция xs takeWhile p получает самый длинный префикс списка xs,
                в котором каждый элемент удовлетворяет условию предиката p */
/** <dropWhile> операция xs dropWhile p удаляет самый длинный префикс из списка xs, в
                котором каждый элемент удовлетворяет условию предиката p */
/** <span>      объединяет takeWhile и dropWhile в одну операцию точно так же,
                как метод splitAt объединяет stake и drop. Он возвращает пару из двух списков */
// topic Применение предикатов к спискам
/** <forall>    xs forall p получает в качестве аргументов список xs и
                предикат p. Она возвращает результат true, если все элементы
                списка удовлетворяют условию предиката p */
/** <exists>    xs exists p возвращает true, если в xs имеется элемент,
                удовлетворяющий условию предиката p */
// topic Свертка списков
/** </:>        Операция левой свертки (z /: xs) (op) задействует три
                объекта: начальное значение z, список xs и бинарную операцию op.
                Результатом свертки является применение op между
                последовательно извлекаемыми элементами списка, где в качестве
                префикса выступает значение z */
/** <:\>        Оператор :\ (произносится «правая свертка») задействует те же
                три операнда, что и оператор левой свертки, но два из них
                указываются в обратном порядке: первым операндом является
                свертываемый список, а вторым — начальное значение */
// topic Сортировка списков
/** <sortWith>  Операция xs sortWith before, где xs является списком, а
                before — функцией, которая может использоваться для сравнения
                двух элементов, выполняет сортировку элементов списка xs. */

// topic 16.8. Методы объекта List
/** <List.apply> Создание списков из их элементов */
/** <List.range> Создание диапазона чисел */
/** <List.fill>  Создание однообразных списков */
/** <List.tabulate> Табулирование функции */
/** <List.concat> Объединение нескольких списков */

// topic Строки, реализуемые посредством StringOps
/** def hasUpperCase(s: String) = s.exists(_.isUpper) метод <exists> относится к классу List
                  строка преобразуется в List и к списку применяется <exists> */

// topic Таблица 17.1. Наиболее распространенные операторы для работы с наборами

//val nums = Set(1, 2, 3) /** Создает неизменяемый набор (nums.toString возвращает Set(1, 2, 3)) */

//nums + 5                /** Добавляет элемент (возвращает Set(1, 2, 3, 5)) */
//nums - 3                /** Удаляет элемент (возвращает Set(1, 2)) */
//nums ++ List(5, 6)      /** Добавляет несколько элементов (возвращает Set(1, 2, 3, 5, 6)) */
//nums -- List(1, 2)      /** Удаляет несколько элементов (возвращает Set(3)) */
//nums & Set(1, 3, 5, 7)  /** Берет пересечение двух наборов (возвращает Set(1, 3)) */
//nums.size               /** Возвращает размер набора (возвращает 3) */
//nums.contains(3)        /** Проверка включения (возвращает true) */

/** Упрощает доступ к изменяемым коллекциям */
//import scala.collection.mutable
//
//val words = mutable.Set.empty[String] /** Создает пустой, изменяемый набор (words.toString возвращает Set()) */

//words += "the" /** Добавляет элемент (words.toString возвращает Set(the)) */
//words -= "the" /** Удаляет элемент, если он существует (words.toString возвращает Set()) */
//words ++= List("do", "re", "mi") /** Добавляет несколько элементов (words.toString возвращает Set(do, re, mi))*/
//words --= List("do", "re") /** Удаляет несколько элементов (words.toString возвращает Set(mi)) */
//words.clear /** Удаляет все элементы (words.toString возвращает Set()) */


// topic Применение отображений
/** Если импортировать пакет с именем mutable,
можно создать пустое изменяемое отображение: */
//val map = mutable.Map.empty[String, Int]  /**  при создании отображения следует указать два типа.
//                                          Первый тип предназначен для ключей отображения,
//                                          а второй — для их значений */
//map("hello") = 1  /** Задание записей в отображении */
//map("there") = 2

// topic Таблица 17.2. Наиболее часто используемые операции для работы с отображениями
//val nums = Map("i" -> 1,  "ii" -> 2) /** Создает неизменяемое отображение (nums.toString возвращает
//                                      Map(i –> 1, ii –> 2)) */
//nums + ("vi" -> 6)                    /** Добавляет запись (возвращает Map(i –> 1, ii –> 2, vi –> 6)) */
//nums - "ii"                           /** Удаляет запись (возвращает Map(i –> 1)) */
//nums ++ List("iii" -> 3, "v" -> 5)    /** Добавляет несколько записей
//                                      (возвращает Map(i –> 1, ii –> 2, iii –> 3, v –> 5)) */
//nums -- List("i", "ii")               /** Удаляет несколько записей (возвращает Map()) */
//nums.size                             /** Возвращает размер отображения (возвращает 2) */
//nums.contains("ii")                   /** Проверяет присутствие (возвращает true) */
//nums("ii")                            /** Извлекает значение по указанному ключу (возвращает 2) */
//nums.keys                             /** Возвращает ключи (возвращает результат итерации,
//                                      выполненной над строками "i" и "ii") */
//nums.keySet                           /** Возвращает ключи в виде набора (возвращает Set(i, ii)) */
//nums.values                           /** Возвращает значения (возвращает результат итерации,
//                                      выполненной над целыми числами 1 и 2) */
//nums.isEmpty  /** Показывает, является ли отображение пустым (возвращает false) */
//
//import scala.collection.mutable             /** Упрощает доступ к изменяемым коллекциям */
//val words = mutable.Map.empty[String, Int]  /** Создает пустое изменяемое отображение */
//words += ("one" -> 1)             /** Добавляет запись в отображение из ключа "one" и значения 1
//                                  (words.toString возвращает Map(one –> 1)) */
//words -= "one"             /** Удаляет запись из отображения, если она существует
//                            (words.toString возвращает Map()) */
//words ++= List("one" -> 1, "two" -> 2, "three" -> 3)  /** Добавляет записи в изменяемое отображение
//                                      (words.toString возвращает Map(one –> 1, two –> 2, three –> 3)) */
//words --= List("one", "two")      /** Удаляет несколько объектов (words.toString возвращает
//                                  Map(three –> 3)) */












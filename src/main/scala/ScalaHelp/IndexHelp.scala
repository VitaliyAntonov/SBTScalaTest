package ScalaHelp

/** Общий индекс информационных файлов SCALA */
class IndexHelp {

  /** На вершине иерархии коллекций находится трейт Traversable.
   * Его единственной абстрактной операцией является foreach
   * Scala - профессиональное программирование
   * Таблица 24.1. Операции в трейте Traversable */
  TraversableInfo.foreach_help
  TraversableInfo.add_help
  TraversableInfo.map_help
  TraversableInfo.flatMap_help
  TraversableInfo.collect_help




  /** Методы последовательностей типа Seq */
  /** Scala - профессиональное программирование. Таблица 24.3 - Операции в трейте Seq */
  // Индексирования и длины
  SeqHelp.apply_help
  SeqHelp.isDefinedAt_help
  SeqHelp.length_help
  SeqHelp.indices_help
  SeqHelp.lengthCompare_help
  // Поиск индекса
  SeqHelp.indexOf_help
  SeqHelp.lastIndexOf_help
  SeqHelp.indexOfSlice_help
  SeqHelp.lastIndexOfSlice_help
  SeqHelp.indexWhere_help
  SeqHelp.segmentLength_help
  SeqHelp.prefixLength_help
  // Добавления
  SeqHelp.addFunctions_help
  // Обновления
  SeqHelp.patch_help
  SeqHelp.updated_help
  SeqHelp.setValue
  // Сортировки
  SeqHelp.sorted_help
  SeqHelp.sortWith_help
  SeqHelp.sortBy_help
  // Реверсирования
  SeqHelp.reverse_help
  SeqHelp.reverseIterator
  SeqHelp.reverseMap_help
  // Сравнение
  SeqHelp.startsWith_help
  SeqHelp.endsWith_help
  SeqHelp.contains_help
  SeqHelp.containsSlice_help
  SeqHelp.corresponds_help
  // Операции над множествами
  SeqHelp.intersect_help
  SeqHelp.union_help
  SeqHelp.distinct_help

  /** Описание методов для работы с последовательностями Buffer (ListBuffer, ArrayBuffer) */
  // Добавления
  BufferHelp.add_help
  BufferHelp.insert_help
  BufferHelp.insertAll_help
  // Удаления
  BufferHelp.delete_help
  BufferHelp.remove_help
  BufferHelp.trimStart_help
  BufferHelp.trimEnd_help
  BufferHelp.clear_help
  // Клонирование
  BufferHelp.clone_help

}

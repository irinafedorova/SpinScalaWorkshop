package scala.workshop

class SimpleListBuilder extends Builder[SimpleList] {
  var current: SimpleList = SNil
  def +=(elem: Any) = current = new SList(elem, current)
  def result: SimpleList = current.reverse
  def clear: Unit = current = SNil
}
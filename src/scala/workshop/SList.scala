package scala.workshop

class SList(element: Any, rest: SimpleList) extends SimpleList {

  def isEmpty: Boolean = element == null && rest.isEmpty
  def head: Any = element
  def tail: SimpleList = rest
}
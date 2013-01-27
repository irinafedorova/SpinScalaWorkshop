package scala.workshop

trait SimpleList extends Traversable {

  def isEmpty: Boolean
  def head: Any
  def tail: SimpleList

  def reverse: SimpleList = {
    def reverseItr(out: SimpleList, in: SimpleList): SimpleList = {
      if (in.isEmpty) out
      else reverseItr(in.head :: out, in.tail)
    }

    reverseItr(SNil, this)
  }

  def prepend(newElement: Any): SimpleList = {
    new SList(newElement, this)
  }

  def ::(newElement: Any): SimpleList = {
    prepend(newElement)
  }

  override def toString() = {
    if (this.isEmpty) "" else this.head + ".{" + this.tail + "}"
  }

  def foreach(func: Any => Unit): Unit = {
    if (!this.isEmpty) {
      func(head)
      if (!this.tail.isEmpty) this.tail.foreach(func)
    }
  }

  val builder = new SimpleListBuilder

  def map(func: Any => Any): SimpleList = {
    builder.clear
    this.foreach((x: Any) => builder += func(x))
    builder.result
  }

  def filter(func: Any => Boolean): SimpleList = {
    builder.clear
    this.foreach((x: Any) => if (func(x)) builder += x)
    builder.result
  }

  def flatMap(func: (Any) => SimpleList): SimpleList = {
    map(func).flatten
  }

  def flatten: SimpleList = {
    def flattenItr(out: SimpleList, in: SimpleList): SimpleList = {
      if (in.isEmpty) out
      else in.head match {
        case list: SimpleList =>
          flattenItr(flattenOneElement(out, list), in.tail)
        case element: Any =>
          flattenItr(element :: out, in.tail)
      }
    }

    def flattenOneElement(out: SimpleList, in: SimpleList): SimpleList = {
      if (in.isEmpty) out
      else flattenOneElement(in.head :: out, in.tail)
    }

    flattenItr(SNil, this).reverse
  }
}
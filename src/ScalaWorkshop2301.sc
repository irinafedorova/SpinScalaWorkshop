object ScalaWorkshop2301 {

  trait Builder[T] {
    def +=(elem: Any)
    def result: T
    def clear: Unit
  }

  class SimpleListBuilder extends Builder[SimpleList] {
    var current: SimpleList = SNil
    def +=(elem: Any) = current = new SList(elem, current)
    def result: SimpleList = current.reverse
    def clear: Unit = current = SNil
  }

  trait Traversable {
    def foreach(f: Any => Unit): Unit
  }

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

  class SList(element: Any, rest: SimpleList) extends SimpleList {

    def isEmpty: Boolean = element == null && rest.isEmpty
    def head: Any = element
    def tail: SimpleList = rest
  }

  object SNil extends SimpleList {
    def isEmpty = true
    def head = null
    def tail = null
  }

  SNil.isEmpty                                    //> res0: Boolean = true
  val innerList = new SList("Fred", SNil)         //> innerList  : ScalaWorkshop2301.SList = Fred.{}
  val outerList = innerList.prepend("Peter")      //> outerList  : ScalaWorkshop2301.SimpleList = Peter.{Fred.{}}

  val ls = "Dennis" :: "The" :: "Menace" :: SNil  //> ls  : ScalaWorkshop2301.SimpleList = Dennis.{The.{Menace.{}}}

  val customList = "A" :: "B" :: "C" :: "A" :: SNil
                                                  //> customList  : ScalaWorkshop2301.SimpleList = A.{B.{C.{A.{}}}}

  //val func = (x: Any) => { println(x.toString + " fish") }
  // ls foreach func

  ls.map(x => "fish" + x)                         //> res1: ScalaWorkshop2301.SimpleList = fishDennis.{fishThe.{fishMenace.{}}}

  customList.filter(x => x == "A")                //> res2: ScalaWorkshop2301.SimpleList = A.{A.{}}

  customList.flatMap(x => x :: x :: x :: SNil)    //> res3: ScalaWorkshop2301.SimpleList = A.{A.{A.{B.{B.{B.{C.{C.{C.{A.{A.{A.{}}
                                                  //| }}}}}}}}}}

  val func = (x: Any) => new SList(x, new SList(x + "1", SNil))
                                                  //> func  : Any => ScalaWorkshop2301.SList = <function1>
  val l = 1 :: 2 :: 3 :: 4 :: SNil                //> l  : ScalaWorkshop2301.SimpleList = 1.{2.{3.{4.{}}}}
  println(l flatMap (func))                       //> 1.{11.{2.{21.{3.{31.{4.{41.{}}}}}}}}

  // 1 2 2 3 3 4 4 5
}
object ScalaWorkshop1601 {

  trait Builder[T] {
    def +=(elem: Any)
    def result: T
    def clear: Unit
  }

  class SimpleListBuilder extends Builder[SimpleList] {
    var current: SimpleList = SNil
    def +=(elem: Any) = current = new SList(elem, current)
    def result: SimpleList = current
    def clear: Unit = current = SNil
  }

  trait Traversable {
    def foreach(f: Any => Unit): Unit
  }

  trait SimpleList extends Traversable {

    def isEmpty: Boolean
    def head: Any
    def tail: SimpleList

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
  val innerList = new SList("Fred", SNil)         //> innerList  : ScalaWorkshop1601.SList = Fred.{}
  val outerList = innerList.prepend("Peter")      //> outerList  : ScalaWorkshop1601.SimpleList = Peter.{Fred.{}}

  val ls = "Dennis" :: "The" :: "Menace" :: SNil  //> ls  : ScalaWorkshop1601.SimpleList = Dennis.{The.{Menace.{}}}

  val customList = "A" :: "B" :: "C" :: "A" :: SNil
                                                  //> customList  : ScalaWorkshop1601.SimpleList = A.{B.{C.{A.{}}}}

  customList foreach (println _)                  //> A
                                                  //| B
                                                  //| C
                                                  //| A

  val func = (x: Any) => { println(x.toString + " fish") }
                                                  //> func  : Any => Unit = <function1>

  ls foreach func                                 //> Dennis fish
                                                  //| The fish
                                                  //| Menace fish

  ls.map(x => "fish" + x)                         //> res1: ScalaWorkshop1601.SimpleList = fishMenace.{fishThe.{fishDennis.{}}}
  
  customList.filter(x => x == "A")                //> res2: ScalaWorkshop1601.SimpleList = A.{A.{}}
}
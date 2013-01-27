object ScalaWorkshop0901 {

  trait SimpleList {

    def isEmpty: Boolean
    def head: Any
    def tail: SimpleList

    def prepend(newElement: Any): SimpleList = {
      new SList(newElement, this)
    }

    def prepend_:(newElement: Any): SimpleList = {
      prepend(newElement)
    }

    def ::(newElement: Any): SimpleList = {
      prepend(newElement)
    }

    def +(newElement: Any): SimpleList = {
      prepend(newElement)
    }

    override def toString() = {
      if (this.isEmpty) "" else head + ".{" + tail + "}"
    }

    def foreach(f: Any => Unit): Unit = {
      if (!this.isEmpty) {
        f(head)
        if (!this.tail.isEmpty) this.tail.foreach(f)
      }
    }
  }

  class SList(element: Any, rest: SimpleList) extends SimpleList {

    def isEmpty: Boolean = element == null && rest.isEmpty
    def head: Any = element
    def tail: SimpleList = rest

    //def prepend(newElement: Any) : SimpleList = {
    //	new SList(newElement, this)
    //}
  }

  object SNil extends SimpleList {
    def isEmpty = true
    def head = null
    def tail = null
    //def prepend(list: Any) = SNil
  }

  SNil.isEmpty                                    //> res0: Boolean = true
  val innerList = new SList("Fred", SNil)         //> innerList  : ScalaWorkshop0901.SList = Fred.{}
  val outerList = innerList.prepend("Peter")      //> outerList  : ScalaWorkshop0901.SimpleList = Peter.{Fred.{}}

  outerList.tail.head                             //> res1: Any = Fred

  val ls = SNil prepend "Menace" prepend "The" prepend "Dennis"
                                                  //> ls  : ScalaWorkshop0901.SimpleList = Dennis.{The.{Menace.{}}}

  val ls1 = SNil + "Menace" + "The" + "Dennis"    //> ls1  : ScalaWorkshop0901.SimpleList = Dennis.{The.{Menace.{}}}
  val ls2 = "Dennis" prepend_: "The" prepend_: "Menace" prepend_: SNil
                                                  //> ls2  : ScalaWorkshop0901.SimpleList = Dennis.{The.{Menace.{}}}

  val ls3 = "Dennis" :: "The" :: "Menace" :: SNil //> ls3  : ScalaWorkshop0901.SimpleList = Dennis.{The.{Menace.{}}}

  val normalList = "A" :: "B" :: "C" :: Nil       //> normalList  : List[java.lang.String] = List(A, B, C)

  normalList foreach (println _)                  //> A
                                                  //| B
                                                  //| C

  val customList = "A" :: "B" :: "C" :: SNil      //> customList  : ScalaWorkshop0901.SimpleList = A.{B.{C.{}}}

  customList foreach (println _)                  //> A
                                                  //| B
                                                  //| C
}
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
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(1153); val res$0 = 

  SNil.isEmpty;System.out.println("""res0: Boolean = """ + $show(res$0));$skip(42); 
  val innerList = new SList("Fred", SNil);System.out.println("""innerList  : ScalaWorkshop0901.SList = """ + $show(innerList ));$skip(45); 
  val outerList = innerList.prepend("Peter");System.out.println("""outerList  : ScalaWorkshop0901.SimpleList = """ + $show(outerList ));$skip(24); val res$1 = 

  outerList.tail.head;System.out.println("""res1: Any = """ + $show(res$1));$skip(66); 

  val ls = SNil prepend "Menace" prepend "The" prepend "Dennis";System.out.println("""ls  : ScalaWorkshop0901.SimpleList = """ + $show(ls ));$skip(49); 

  val ls1 = SNil + "Menace" + "The" + "Dennis";System.out.println("""ls1  : ScalaWorkshop0901.SimpleList = """ + $show(ls1 ));$skip(71); 
  val ls2 = "Dennis" prepend_: "The" prepend_: "Menace" prepend_: SNil;System.out.println("""ls2  : ScalaWorkshop0901.SimpleList = """ + $show(ls2 ));$skip(52); 

  val ls3 = "Dennis" :: "The" :: "Menace" :: SNil;System.out.println("""ls3  : ScalaWorkshop0901.SimpleList = """ + $show(ls3 ));$skip(46); 

  val normalList = "A" :: "B" :: "C" :: Nil;System.out.println("""normalList  : List[java.lang.String] = """ + $show(normalList ));$skip(35); 

  normalList foreach (println _);$skip(47); 

  val customList = "A" :: "B" :: "C" :: SNil;System.out.println("""customList  : ScalaWorkshop0901.SimpleList = """ + $show(customList ));$skip(35); 

  customList foreach (println _)}
}
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
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(1628); val res$0 = 

  SNil.isEmpty;System.out.println("""res0: Boolean = """ + $show(res$0));$skip(42); 
  val innerList = new SList("Fred", SNil);System.out.println("""innerList  : ScalaWorkshop1601.SList = """ + $show(innerList ));$skip(45); 
  val outerList = innerList.prepend("Peter");System.out.println("""outerList  : ScalaWorkshop1601.SimpleList = """ + $show(outerList ));$skip(51); 

  val ls = "Dennis" :: "The" :: "Menace" :: SNil;System.out.println("""ls  : ScalaWorkshop1601.SimpleList = """ + $show(ls ));$skip(54); 

  val customList = "A" :: "B" :: "C" :: "A" :: SNil;System.out.println("""customList  : ScalaWorkshop1601.SimpleList = """ + $show(customList ));$skip(35); 

  customList foreach (println _);$skip(61); 

  val func = (x: Any) => { println(x.toString + " fish") };System.out.println("""func  : Any => Unit = """ + $show(func ));$skip(20); 

  ls foreach func;$skip(28); val res$1 = 

  ls.map(x => "fish" + x);System.out.println("""res1: ScalaWorkshop1601.SimpleList = """ + $show(res$1));$skip(38); val res$2 = 
  
  customList.filter(x => x == "A");System.out.println("""res2: ScalaWorkshop1601.SimpleList = """ + $show(res$2))}
}
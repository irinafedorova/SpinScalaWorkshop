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
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(2524); val res$0 = 

  SNil.isEmpty;System.out.println("""res0: Boolean = """ + $show(res$0));$skip(42); 
  val innerList = new SList("Fred", SNil);System.out.println("""innerList  : ScalaWorkshop2301.SList = """ + $show(innerList ));$skip(45); 
  val outerList = innerList.prepend("Peter");System.out.println("""outerList  : ScalaWorkshop2301.SimpleList = """ + $show(outerList ));$skip(51); 

  val ls = "Dennis" :: "The" :: "Menace" :: SNil;System.out.println("""ls  : ScalaWorkshop2301.SimpleList = """ + $show(ls ));$skip(54); 

  val customList = "A" :: "B" :: "C" :: "A" :: SNil;System.out.println("""customList  : ScalaWorkshop2301.SimpleList = """ + $show(customList ));$skip(112); val res$1 = 

  //val func = (x: Any) => { println(x.toString + " fish") }
  // ls foreach func

  ls.map(x => "fish" + x);System.out.println("""res1: ScalaWorkshop2301.SimpleList = """ + $show(res$1));$skip(37); val res$2 = 

  customList.filter(x => x == "A");System.out.println("""res2: ScalaWorkshop2301.SimpleList = """ + $show(res$2));$skip(49); val res$3 = 

  customList.flatMap(x => x :: x :: x :: SNil);System.out.println("""res3: ScalaWorkshop2301.SimpleList = """ + $show(res$3));$skip(66); 

  val func = (x: Any) => new SList(x, new SList(x + "1", SNil));System.out.println("""func  : Any => ScalaWorkshop2301.SList = """ + $show(func ));$skip(35); 
  val l = 1 :: 2 :: 3 :: 4 :: SNil;System.out.println("""l  : ScalaWorkshop2301.SimpleList = """ + $show(l ));$skip(28); 
  println(l flatMap (func))}

  // 1 2 2 3 3 4 4 5
}
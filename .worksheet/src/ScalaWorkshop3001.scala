import scala.workshop._

object ScalaWorkshop3001 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(69); val res$0 = 

  SNil.isEmpty;System.out.println("""res0: Boolean = """ + $show(res$0));$skip(42); 
  val innerList = new SList("Fred", SNil);System.out.println("""innerList  : scala.workshop.SList = """ + $show(innerList ));$skip(45); 
  val outerList = innerList.prepend("Peter");System.out.println("""outerList  : scala.workshop.SimpleList = """ + $show(outerList ));$skip(51); 

  val ls = "Dennis" :: "The" :: "Menace" :: SNil;System.out.println("""ls  : scala.workshop.SimpleList = """ + $show(ls ));$skip(54); 

  val customList = "A" :: "B" :: "C" :: "A" :: SNil;System.out.println("""customList  : scala.workshop.SimpleList = """ + $show(customList ));$skip(112); val res$1 = 

  //val func = (x: Any) => { println(x.toString + " fish") }
  // ls foreach func

  ls.map(x => "fish" + x);System.out.println("""res1: scala.workshop.SimpleList = """ + $show(res$1));$skip(37); val res$2 = 

  customList.filter(x => x == "A");System.out.println("""res2: scala.workshop.SimpleList = """ + $show(res$2));$skip(49); val res$3 = 

  customList.flatMap(x => x :: x :: x :: SNil);System.out.println("""res3: scala.workshop.SimpleList = """ + $show(res$3));$skip(66); 

  val func = (x: Any) => new SList(x, new SList(x + "1", SNil));System.out.println("""func  : Any => scala.workshop.SList = """ + $show(func ));$skip(35); 
  val l = 1 :: 2 :: 3 :: 4 :: SNil;System.out.println("""l  : scala.workshop.SimpleList = """ + $show(l ));$skip(28); 
  println(l flatMap (func))}

  // 1 2 2 3 3 4 4 5
}
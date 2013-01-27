import scala.workshop._

object ScalaWorkshop3001 {

  SNil.isEmpty                                    //> res0: Boolean = true
  val innerList = new SList("Fred", SNil)         //> innerList  : scala.workshop.SList = Fred.{}
  val outerList = innerList.prepend("Peter")      //> outerList  : scala.workshop.SimpleList = Peter.{Fred.{}}

  val ls = "Dennis" :: "The" :: "Menace" :: SNil  //> ls  : scala.workshop.SimpleList = Dennis.{The.{Menace.{}}}

  val customList = "A" :: "B" :: "C" :: "A" :: SNil
                                                  //> customList  : scala.workshop.SimpleList = A.{B.{C.{A.{}}}}

  //val func = (x: Any) => { println(x.toString + " fish") }
  // ls foreach func

  ls.map(x => "fish" + x)                         //> res1: scala.workshop.SimpleList = fishDennis.{fishThe.{fishMenace.{}}}

  customList.filter(x => x == "A")                //> res2: scala.workshop.SimpleList = A.{A.{}}

  customList.flatMap(x => x :: x :: x :: SNil)    //> res3: scala.workshop.SimpleList = A.{A.{A.{B.{B.{B.{C.{C.{C.{A.{A.{A.{}}}}}}
                                                  //| }}}}}}

  val func = (x: Any) => new SList(x, new SList(x + "1", SNil))
                                                  //> func  : Any => scala.workshop.SList = <function1>
  val l = 1 :: 2 :: 3 :: 4 :: SNil                //> l  : scala.workshop.SimpleList = 1.{2.{3.{4.{}}}}
  println(l flatMap (func))                       //> 1.{11.{2.{21.{3.{31.{4.{41.{}}}}}}}}

  // 1 2 2 3 3 4 4 5
}
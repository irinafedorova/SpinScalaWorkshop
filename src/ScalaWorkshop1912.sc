object ScalaWorkshop1912 {
	2                                         //> res0: Int(2) = 2
	"Hello"                                   //> res1: java.lang.String("Hello") = Hello
	"Chelsea"->"Dennis Wise"                  //> res2: (java.lang.String, java.lang.String) = (Chelsea,Dennis Wise)
	1+2+3*7                                   //> res3: Int = 24
	if(1 < 2) "cool" else "weird"             //> res4: java.lang.String = cool
	List(2, "hello")                          //> res5: List[Any] = List(2, hello)
	
	def totalGoals(home: Int, away: Int) : Int = home + away
                                                  //> totalGoals: (home: Int, away: Int)Int
	def fact(x:Long) : Long = if(x==1) 1 else (x * fact(x-1))
                                                  //> fact: (x: Long)Long
  fact(2)                                         //> res6: Long = 2
  fact(3)                                         //> res7: Long = 6
	fact(10)                                  //> res8: Long = 3628800
	
	val away = 2                              //> away  : Int = 2
	val home = 3                              //> home  : Int = 3
	val totalGoalsValue = away + home         //> totalGoalsValue  : Int = 5
	
	trait SimpleList {
  
		def isEmpty : Boolean
		def head: Any
		def tail: SimpleList
		
		def prepend(newElement: Any) : SimpleList
	}
		
	class SList(element : Any, rest : SimpleList) extends SimpleList {

		def isEmpty = element == null && rest.isEmpty
		def head: Any = element
		def tail: SimpleList = rest

		def prepend(newElement: Any) : SimpleList = {
			new SList(newElement, this)
		}
	}
	
	object SNil extends SimpleList {
		def isEmpty = true
		def head = null
		def tail = null
		def prepend(list: Any) = SNil
	}
	
	SNil.isEmpty                              //> res9: Boolean = true
	
	val innerList = new SList("Fred", SNil)   //> innerList  : ScalaWorkshop1912.SList = ScalaWorkshop1912$$anonfun$main$1$SLi
                                                  //| st$1@334bfe
  innerList.head                                  //> res10: Any = Fred
    
	val outerList = innerList.prepend("Peter")//> outerList  : ScalaWorkshop1912.SimpleList = ScalaWorkshop1912$$anonfun$main$
                                                  //| 1$SList$1@1c24b45
	outerList.isEmpty                         //> res11: Boolean = false
	outerList.head                            //> res12: Any = Peter
	outerList.tail.head                       //> res13: Any = Fred
	
	
	
}
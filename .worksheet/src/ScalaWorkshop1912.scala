object ScalaWorkshop1912 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(29); val res$0 = 
	2;System.out.println("""res0: Int(2) = """ + $show(res$0));$skip(9); val res$1 = 
	"Hello";System.out.println("""res1: java.lang.String("Hello") = """ + $show(res$1));$skip(26); val res$2 = 
	"Chelsea"->"Dennis Wise";System.out.println("""res2: (java.lang.String, java.lang.String) = """ + $show(res$2));$skip(9); val res$3 = 
	1+2+3*7;System.out.println("""res3: Int = """ + $show(res$3));$skip(31); val res$4 = 
	if(1 < 2) "cool" else "weird";System.out.println("""res4: java.lang.String = """ + $show(res$4));$skip(18); val res$5 = 
	List(2, "hello");System.out.println("""res5: List[Any] = """ + $show(res$5));$skip(60); 
	
	def totalGoals(home: Int, away: Int) : Int = home + away;System.out.println("""totalGoals: (home: Int, away: Int)Int""");$skip(59); 
	def fact(x:Long) : Long = if(x==1) 1 else (x * fact(x-1));System.out.println("""fact: (x: Long)Long""");$skip(10); val res$6 = 
  fact(2);System.out.println("""res6: Long = """ + $show(res$6));$skip(10); val res$7 = 
  fact(3);System.out.println("""res7: Long = """ + $show(res$7));$skip(10); val res$8 = 
	fact(10);System.out.println("""res8: Long = """ + $show(res$8));$skip(16); 
	
	val away = 2;System.out.println("""away  : Int = """ + $show(away ));$skip(14); 
	val home = 3;System.out.println("""home  : Int = """ + $show(home ));$skip(35); 
	val totalGoalsValue = away + home
	
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
	};System.out.println("""totalGoalsValue  : Int = """ + $show(totalGoalsValue ));$skip(545); val res$9 = 
	
	SNil.isEmpty;System.out.println("""res9: Boolean = """ + $show(res$9));$skip(43); 
	
	val innerList = new SList("Fred", SNil);System.out.println("""innerList  : ScalaWorkshop1912.SList = """ + $show(innerList ));$skip(17); val res$10 = 
  innerList.head;System.out.println("""res10: Any = """ + $show(res$10));$skip(50); 
    
	val outerList = innerList.prepend("Peter");System.out.println("""outerList  : ScalaWorkshop1912.SimpleList = """ + $show(outerList ));$skip(19); val res$11 = 
	outerList.isEmpty;System.out.println("""res11: Boolean = """ + $show(res$11));$skip(16); val res$12 = 
	outerList.head;System.out.println("""res12: Any = """ + $show(res$12));$skip(21); val res$13 = 
	outerList.tail.head;System.out.println("""res13: Any = """ + $show(res$13))}
	
	
	
}
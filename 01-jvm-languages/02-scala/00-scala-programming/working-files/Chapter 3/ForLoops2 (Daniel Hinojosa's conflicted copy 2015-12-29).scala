val xs = List(1,2,3,4)
var result = List[Int]()
for (a <- xs) {
   result = result :+ (a + 1)
}
println(result)

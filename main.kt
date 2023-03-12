fun main() {
//     создание матрицы
    val m = createMatrix(3,3,2)
    m.set(Cell(0,1),1)
   m.set(2,2,3)
    println(m)
//          
		println("транспонирование")
        println(transpose(m))
        
//     поворот
println("поворот")
   println(rotate(m))
   
//    сложение матриц
   println("сложение матриц")
    println(m.plus(rotate(m)))
   
//    инверсия
     println("инверсия")
    println(m.unaryMinus())
//    
//    умножение матриц
	println("умножение матриц")
   println(m.times(createMatrix(3,1,1)))
   
   
//    поиск дырок
	println("поиск дырок")
      val m2= createMatrix(3,3,0)
   m2.set(1,2,1)
   println(m2)
   println(findHoles(m2))
   
   
//    задание Замочная скважина
	println("замочная скважина")
   val m10 = createMatrix(4,4,0)
   val m20 = createMatrix(2,2,1)
   println(m10)
   println(m20)
    println(canOpenLock(m20,m10))
     m10.set(0,1,1)
     m10.set(1,0,1)
     m10.set(0,2,1)
     m10.set(1,2,1)
     m10.set(2,0,1)
      println(m10)
   println(m20)
   println(canOpenLock(m20,m10))
    m20.set(0,0,0)
     m20.set(1,0,0)
      println(m10)
   println(m20)
   println(canOpenLock(m20,m10))
}

class Calculator {
    init {
        var running = true
        println("Welcome to the calculator, enter a mode")
        while(running) {
            println("sides - enter all sides | angle - enter one angle and one side | exit - exit")
            when (readLine()) {
                "sides" -> {
                    println("Enter all 3 sides, one after the other")
                    println("a")
                    val a = readLine()
                    println("b")
                    val b = readLine()
                    println("c")
                    val c = readLine()
                    if (a != null && b != null && c != null)
                        println(Triangle(a.toDouble(), b.toDouble(), c.toDouble()))
                }
                "angles" -> {
                    println("Enter the values")
                    println("angle name")
                    val aname = readLine()
                    println("angle value")
                    val angle = readLine()
                    println("side name")
                    val sname = readLine()
                    println("side length")
                    val len = readLine()
                    if (aname != null && angle != null && sname != null && len != null)
                        println(Triangle(aname, angle.toDouble(), sname, len.toDouble()))
                }
                "exit" -> running = false
            }
        }
    }
}


fun main() {
    Calculator()
}
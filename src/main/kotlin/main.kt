import java.lang.IllegalArgumentException
import kotlin.math.*

fun main() {
    val triangle = Triangle(4.0, 3.0, 5.0)
    println("alpha: a: ${Triangle("alpha", 53.13, "a", 4.0)}")
    println("alpha: b: ${Triangle("alpha", 53.13, "b", 3.0)}")
    println("alpha: c: ${Triangle("alpha", 53.13, "c", 5.0)}")

    println("beta:  a: ${Triangle("beta", 36.87, "a", 4.0)}")
    println("beta:  b: ${Triangle("beta", 36.87, "b", 3.0)}")
    println("beta:  c: ${Triangle("beta", 36.87, "c", 5.0)}")
    println("\nOriginal$triangle")
}

class Triangle() {

    var a = Side(0.0, "a")
    var b = Side(0.0, "b")
    var c = Side(0.0, "c")

    var alpha = Angle(0.0, "alpha")
    var beta = Angle(0.0, "beta")
    var gamma = Angle(90.0, "gamma")

    init {

    }

    constructor(angleName: String, angleD: Double, sideName: String, side: Double) : this() {
        val angle = angleD.toRadians() //thanks kotlin
        when (angleName) {
            "alpha" -> {
                alpha = Angle(angleD, "alpha")
                when (sideName) {
                    "a" -> {
                        a = Side(side, "a");
                        c = Side(a / sin(angle), "c")
                        b = Side(c * cos(angle), "b")
                    }
                    "b" -> {
                        b = Side(side, "b")
                        c = Side(b / cos(angle), "c")
                        a = Side(c * sin(angle), "a")
                    }
                    "c" -> {
                        c = Side(side, "c")
                        a = Side(c * sin(angle), "a")
                        b = Side(c * cos(angle), "a")
                    }
                }
                beta = Angle(asin(b.length / c.length).toDegree(), "beta")
            }
            "beta" -> {
                beta = Angle(angleD, "beta")
                when (sideName) {
                    "a" -> {
                        a = Side(side, "a");
                        c = Side(a / cos(angle), "c")
                        b = Side(c * sin(angle), "b")
                    }
                    "b" -> {
                        b = Side(side, "b")
                        c = Side(b / sin(angle), "c")
                        a = Side(c * cos(angle), "a")
                    }
                    "c" -> {
                        c = Side(side, "c")
                        a = Side(c * cos(angle), "a")
                        b = Side(c * sin(angle), "a")
                    }
                }
            }
            else -> {
                throw IllegalArgumentException("$angleName is not a valid angle")
            }
        }
    }

    constructor(vararg sides: Double) : this() {
        a = Side(sides[0], "a")
        b = Side(sides[1], "b")
        c = Side(sides[2], "c")
        calculateAngles()
    }


    private fun calculateAngles() {
        alpha = Angle(asin(a.length / c.length).toDegree(), "alpha")
        beta = Angle(asin(b.length / c.length).toDegree(), "beta")
        gamma = Angle(180 - beta.angle - alpha.angle, "gamma")
    }

    private fun Double.toDegree() = this * 180 / PI
    private fun Double.toRadians() = this * PI / 180


    override fun toString(): String {
        return "Seiten: $a, $b, $c   Winkel: $alpha $beta $gamma"
    }

}

class Side(length: Double, name: String) {
    val length = length
    val name = name

    operator fun times(other: Double) = this.length * other
    operator fun div(other: Double) = this.length / other

    override fun toString(): String {
        return "$name = ${"%.2f".format(length)}"
    }
}

class Angle(value: Double, name: String) {
    val angle = value
    val name = name


    override fun toString(): String {
        return "$name = ${"%.2f".format(angle)}"
    }
}
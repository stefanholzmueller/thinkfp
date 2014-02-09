package blog

/**
 * Hello world!
 *
 */
object App1 extends App {



  override def main(args : Array[String]) {
    def getA(): Option[String] = Some("a")

    def aToB(a: String): Option[String] = Some("b")

    val safeB: String = getA().flatMap(a => aToB(a)).getOrElse("xyz")
    println(safeB)
  }
}

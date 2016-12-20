class SecretAgent(val name:String) {
  def shoot(n:Int): Unit = {
    import SecretAgent._
    decrementBullets(n)
  }
}

object SecretAgent {
  private var b:Int = 3000
  private def decrementBullets(count:Int): Unit = {
    if (b - count <= 0) b = 0
    else b = b - count
  }

  def bullets = b
}

object SecretAgentRunner extends App {
  val tom = new SecretAgent("Tom")
  val dick = new SecretAgent("Dick")
  val harry = new SecretAgent("Harry")

  tom.shoot(800)
  dick.shoot(300)
  harry.shoot(200)

  println(SecretAgent.bullets)
}

//$ scala 01-SecretAgent.scala
//1700

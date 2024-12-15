import scala.io.Source

object CeresSearch {
  def main(args: Array[String]): Unit = {
    val filePath = "input"

    try {
      val twoDArray = Source.fromFile(filePath).getLines().map(line => {
        line.toCharArray.toList
      }).toList

      val horizontalXMAS = countHorizontally(twoDArray, "XMAS")
      val horizontalSAMX = countHorizontally(twoDArray, "SAMX")
      val verticalXMAS = countVertically(twoDArray, "XMAS")
      val verticalSAMX = countVertically(twoDArray, "SAMX")
      val primaryDiagonalXMAS = countPrimaryDiagonally(twoDArray, "XMAS")
      val primaryDiagonalSAMX = countPrimaryDiagonally(twoDArray, "SAMX")
      val secondaryDiagonalXMAS = countSecondaryDiagonally(twoDArray, "XMAS")
      val secondaryDiagonalSAMX = countSecondaryDiagonally(twoDArray, "SAMX")

      val total = horizontalXMAS + horizontalSAMX +
                  verticalXMAS + verticalSAMX +
                  primaryDiagonalXMAS + primaryDiagonalSAMX +
                  secondaryDiagonalXMAS + secondaryDiagonalSAMX

      println(s"Total number of occurrences: $total")

      val literalXMas = xShapedMas(twoDArray)

      println(s"Total number of X shaped MAS: $literalXMas")
    } catch {
      case ex: Exception =>
        println(s"An error occurred: ${ex.getMessage}")
    } finally {
      Source.fromFile(filePath).close()
    }
  }

  def countHorizontally(twoDArray: List[List[Char]], target: String): Int = {
    twoDArray.map(row => countSubstring(row.mkString, target)).sum
  }

  def countVertically(twoDArray: List[List[Char]], target: String): Int = {
    twoDArray.transpose.map(row => countSubstring(row.mkString, target)).sum
  }

  def countPrimaryDiagonally(twoDArray: List[List[Char]], target: String): Int = {
    val numRows = twoDArray.length
    val numCols = twoDArray.head.length
    val targetLength = target.length

    var count = 0

    for (row <- 0 to numRows - targetLength; col <- 0 to numCols - targetLength) {
      if ((0 until targetLength).forall(i => twoDArray(row + i)(col + i) == target(i))) {
        count += 1
      }
    }

    count
  }

  def countSecondaryDiagonally(twoDArray: List[List[Char]], target: String): Int = {
    val numRows = twoDArray.length
    val numCols = twoDArray.head.length
    val targetLength = target.length

    var count = 0

    for (row <- 0 to numRows - targetLength; col <- targetLength - 1 until numCols) {
      if ((0 until targetLength).forall(i => twoDArray(row + i)(col - i) == target(i))) {
        count += 1
      }
    }

    count
  }

  def countSubstring(text: String, sub: String): Int = {
    text.sliding(sub.length).count(window => window == sub)
  }

  def xShapedMas(twoDArray: List[List[Char]]): Int = {
    var count = 0

    for (i <- 1 until twoDArray.length - 1; j <- 1 until twoDArray.head.length - 1) {
      if (twoDArray(i)(j) == 'A' &&
          twoDArray(i-1)(j-1) == 'M' && twoDArray(i+1)(j-1) == 'M' &&
          twoDArray(i-1)(j+1) == 'S' && twoDArray(i+1)(j+1) == 'S') {
        count += 1
      }

      if (twoDArray(i)(j) == 'A' &&
          twoDArray(i-1)(j-1) == 'S' && twoDArray(i+1)(j-1) == 'S' &&
          twoDArray(i-1)(j+1) == 'M' && twoDArray(i+1)(j+1) == 'M') {
        count += 1
      }

      if (twoDArray(i)(j) == 'A' &&
          twoDArray(i-1)(j-1) == 'M' && twoDArray(i+1)(j-1) == 'S' &&
          twoDArray(i-1)(j+1) == 'M' && twoDArray(i+1)(j+1) == 'S') {
        count += 1
      }

      if (twoDArray(i)(j) == 'A' &&
          twoDArray(i-1)(j-1) == 'S' && twoDArray(i+1)(j-1) == 'M' &&
          twoDArray(i-1)(j+1) == 'S' && twoDArray(i+1)(j+1) == 'M') {
        count += 1
      }
    }

    count
  }
} 

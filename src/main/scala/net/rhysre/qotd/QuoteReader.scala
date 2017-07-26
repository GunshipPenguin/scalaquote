package net.rhysre.scalaquote

import scala.io.Source
import scala.util.Random
import java.io.InputStream

class QuoteReader (in: InputStream, strictMode: Boolean) {
  val rawInputLines = Source.fromInputStream(in).mkString.split("%\n")

  // Remove quotes that do not comply with RFC865 if strict mode is enabled
  val lines =
    if (!strictMode) rawInputLines
    else rawInputLines.filter(s => s.length < 512 && !s.matches("[^\\x00-\\x7F]"))

  val numQuotes = lines.size
  val rand = new Random

  def getQuote() = lines(rand.nextInt(numQuotes))
}

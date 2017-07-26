package net.rhysre.scalaquote

import scala.io.Source
import scala.util.Random
import java.io.InputStream

class QuoteReader (in: InputStream){
  val lines = Source.fromInputStream(in).mkString.split("%\n")
  val numQuotes = lines.size
  val rand = new Random

  def getQuote() = lines(rand.nextInt(numQuotes))
}

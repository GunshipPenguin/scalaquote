package net.rhysre.scalaquote

import scala.io.Source
import scala.util.Random

class QuoteReader {
  val lines = Source.fromFile("quotes.txt").mkString.split("\n%\n")
  val numQuotes = lines.size
  val rand = new Random

  def getQuote() = lines(rand.nextInt(numQuotes))
}

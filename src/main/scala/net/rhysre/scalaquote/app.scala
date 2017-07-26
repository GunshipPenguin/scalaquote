package net.rhysre.scalaquote

import java.lang.Thread
import java.io.{File, FileInputStream, FileNotFoundException}

object Qotd {
  case class Config (port: Int, quoteFile: String, strictMode: Boolean)

  val defaultConfig = new Config(17, "quotes.txt", false)

  def main(args: Array[String]) = {
    val config = parseArgs(defaultConfig, args.toList)

    try {
      val quoteInputStream = getQuoteInputStream(config.quoteFile)

      val reader = new QuoteReader(quoteInputStream, config.strictMode)

      val tcpThread = startTcpServer(config.port, reader)
      val udpThread = startUdpServer(config.port, reader)

      tcpThread.join()
      udpThread.join()
    } catch {
      case e: FileNotFoundException => {
        println("File " + config.quoteFile + " was not found")
        System.exit(1)
      }
    }
  }

  def getQuoteInputStream(quoteFile: String) = new FileInputStream(new File(quoteFile))

  def parseArgs(config: Config, argList: List[String]): Config = {
    argList match {
      case "--port" :: port :: tail => parseArgs(config.copy(port = port.toInt), tail)
      case "--quotefile" :: quoteFile :: tail => parseArgs(config.copy(quoteFile = quoteFile), tail)
      case "--strict" :: tail => parseArgs(config.copy(strictMode = true), tail)
      case option :: value :: tail => parseArgs(config, tail)
      case option :: Nil => config
      case Nil => config
    }
  }

  def startTcpServer(port: Int, quoteReader: QuoteReader): Thread = {
    val thread = new Thread(new TcpQuoteServer(port, quoteReader))
    thread.start
    thread
  }

  def startUdpServer(port: Int, quoteReader: QuoteReader): Thread = {
    val thread = new Thread(new UdpQuoteServer(port, quoteReader))
    thread.start
    thread
  }
}

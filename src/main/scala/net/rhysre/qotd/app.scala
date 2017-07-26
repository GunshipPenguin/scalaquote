package net.rhysre.scalaquote

import java.lang.Thread

object Qotd {
  case class Config (port: Int)

  val defaultConfig = new Config(17)

  def main(args: Array[String]) = {
    val config = parseArgs(defaultConfig, args.toList)

    val reader = new QuoteReader

    val tcpThread = startTcpServer(config.port, reader)
    val udpThread = startUdpServer(config.port, reader)

    tcpThread.join()
    udpThread.join()
  }

  def parseArgs(config: Config, argList: List[String]): Config = {
    argList match {
      case "--port" :: port :: tail => parseArgs(config.copy(port = port.toInt), tail)
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

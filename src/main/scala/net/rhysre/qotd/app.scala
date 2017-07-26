package net.rhysre.scalaquote

import java.lang.Thread

object Qotd {
  case class Config (port: Option[Int] = None)

  def main(args: Array[String]) = {
    val config = parseArgs(new Config, args.toList)

    val reader = new QuoteReader
    val port = config.port match {
      case Some(port) => port
      case None => 17 // Default port for QOTD servers is 17
    }

    val tcpThread = startTcpServer(port, reader)
    val udpThread = startUdpServer(port, reader)

    tcpThread.join()
    udpThread.join()
  }

  def parseArgs(config: Config, argList: List[String]): Config = {
    argList match {
      case "--port" :: port :: tail => parseArgs(config.copy(port = Some(port.toInt)), tail)
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

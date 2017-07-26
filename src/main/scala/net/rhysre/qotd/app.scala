package net.rhysre.scalaquote

import java.lang.Thread

object Qotd extends App {
  val reader = new QuoteReader

  val tcpQuoteServer = new Thread(new TcpQuoteServer(9999, reader))
  val udpQuoteServer = new Thread(new UdpQuoteServer(9999, reader))

  tcpQuoteServer.start
  udpQuoteServer.start

  tcpQuoteServer.join
  udpQuoteServer.join
}

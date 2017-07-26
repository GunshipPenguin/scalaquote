package net.rhysre.scalaquote

import java.lang.Runnable
import java.net.{DatagramSocket, DatagramPacket}

class UdpQuoteServer(port: Int, reader: QuoteReader) extends Runnable {
  val socket = new DatagramSocket(port)
  val bufsize = 256

  def run() {
    try {
      while (true) {
        val buf = new Array[Byte](bufsize)

        val packet = new DatagramPacket(buf, buf.length)
        socket receive packet

        packet.setData((reader.getQuote map (_.toByte)).toArray)

        socket send packet
      }
    } finally {
      socket.close
    }
  }
}

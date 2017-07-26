package net.rhysre.scalaquote

import java.lang.Runnable
import java.io.PrintStream
import java.net.{Socket, ServerSocket}

class TcpQuoteServer(port: Int, reader: QuoteReader) extends Runnable {
  val serverSocket = new ServerSocket(port)

  def run() {
    try {
      while (true) {
        val clientSocket = serverSocket.accept
        val toClientStream = new PrintStream(clientSocket.getOutputStream)
        toClientStream print reader.getQuote
        toClientStream.close
      }
    } finally {
      serverSocket.close
    }
  }
}

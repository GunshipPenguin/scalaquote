import net.rhysre.scalaquote.QuoteReader
import java.io.{InputStream, ByteArrayInputStream}
import org.scalatest._

class QuoteReaderSpec extends FlatSpec with Matchers {

  "QuoteReader" should "return the quotes in a passed in stream" in {
    val quoteString ="quote1%\nquote2"

    val quoteStream = new ByteArrayInputStream(quoteString.getBytes());
    val quoteReader = new QuoteReader(quoteStream)

    quoteReader.getQuote should (
      be ("quote1") or
      be ("quote2")
    )
  }
}

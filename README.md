# ScalaQuote

This is a [Quote of The Day](https://tools.ietf.org/html/rfc865) server written in Scala.

By default, it looks for quotes in a file called `quotes.txt` in its working
directory. Quotes are separated by `%\n`. This is the same line separator used
by the Unix `fortune` program, allowing fortune cookies to be easily served
as quotes using the `--quotefile` flag.

By default, it allows quotes of arbitrary length and character set, but can be
configured to comply completely with RFC865 with the `--strict` flag, which
will do the following.
- Limits quotes to those less than 512 characters
- Only serves quotes containing only ASCII characters

## Running

Ensure that you have sbt installed, and run:

`sbt run`

## Flags

```
--port <port>      Port to listen on (Default: 17)
--quotefile <file> File to get quotes from (Default: quotes.txt)
--strict           Comply with RFC 865 completely (Default: false)
```

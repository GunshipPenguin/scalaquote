# ScalaQuote

A [Quote of The Day](https://tools.ietf.org/html/rfc865) server written in Scala.
Serves quotes via TCP/UDP as specified in RFC865.

By default ScalaQuote looks for quotes in a file called `quotes.txt` in its working
directory. Quotes are separated by `%\n`. This is the same separator used
by the Unix `fortune` program, allowing fortune cookies to be easily served
as quotes using the `--quotefile` flag.

ScalaQuote allows quotes of arbitrary length and character set by default, but
can be instructed to comply completely with RFC865 by using the `--strict` flag,
which will cause the server to not serve quotes that are greater than 511 characters
in length and/or contain non ASCII characters.

## Running

Ensure that you have sbt installed, and run:

`sbt run`

## Flags

```
--port <port>      Port to listen on (Default: 17)
--quotefile <file> Path to file containing quotes (Default: quotes.txt)
--strict           Enforce strict compliance with RFC 865 (Default: false)
```

## Tests

To run the [ScalaTest](http://www.scalatest.org/) tests, use:

`sbt test`

## License

[MIT](https://github.com/GunshipPenguin/scalaquote/blob/master/LICENSE) © Rhys Rustad-Elliott

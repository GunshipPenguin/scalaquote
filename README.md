# ScalaQuote

This is a [Quote of The Day](https://tools.ietf.org/html/rfc865) server written in Scala.

By default, it looks for quotes in a file called `quotes.txt` in its working
directory. Quotes are separated by `%\n`

## Running

Ensure that you have sbt installed, and run:

`sbt run`

## Usage

```
--port <port>      Port to listen on (Default: 17)
--quotefile <file> File to get quotes from (Default: quotes.txt)
```

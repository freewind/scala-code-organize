package object_as_func

import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.{Iterator => JIterator}

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.sys.process._
import scala.util.Try
import scala.collection.JavaConversions._

//object FetchPage extends (String => Try[String]) {
object FetchPage extends FetchPage {
  def apply(url: String): Try[String] = Try {
    val output = new ByteArrayOutputStream
    (new URL(url) #> output).!!
    output.toString("UTF-8")
  }
}

//object ExtractImages extends (String => List[String]) {
object ExtractImages extends ExtractImages {
  def apply(html: String): List[String] = {
    val doc = Jsoup.parse(html)
    val imgs = doc.select("img").listIterator().asInstanceOf[JIterator[Element]].toList
    imgs.map(_.attr("src"))
  }
}

class WillFindMyImages(fetchPage: FetchPage, extractImages: ExtractImages) extends FindMyImages {
  def apply(url: String): Try[List[String]] = {
    fetchPage(url).map(extractImages)

    // We can do a lot on functions, e.g.
    // func1 andThen func2
    // func2 compose func1
    // ...
    // (Not show in this demo)
  }
}

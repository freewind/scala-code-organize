package func_implicit_params

import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.{Iterator => JIterator}

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.sys.process._
import scala.util.Try
import scala.collection.JavaConversions._

class PageFetcher {
  def fetch(url: String)(implicit dep1: Dep1, dep2: Dep2): Try[String] = Try {
    val output = new ByteArrayOutputStream
    (new URL(url) #> output).!!
    output.toString("UTF-8")
  }
}

class ImageExtractor {
  def extractImages(html: String)(implicit dep2: Dep2, dep3: Dep3): List[String] = {
    val doc = Jsoup.parse(html)
    val imgs = doc.select("img").listIterator().asInstanceOf[JIterator[Element]].toList
    imgs.map(_.attr("src"))
  }
}

object MyImageFinder {
  def find(url: String)(implicit pageFetcher: PageFetcher, imageExtractor: ImageExtractor, dep1: Dep1, dep2: Dep2, dep3: Dep3): Try[List[String]] = {
    pageFetcher.fetch(url).map(html => imageExtractor.extractImages(html))
  }
}

// I add these 3 useless dependencies here just for demo
class Dep1

class Dep2

class Dep3

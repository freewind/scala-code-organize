package ctor_params

import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.{Iterator => JIterator}

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.sys.process._
import scala.util.Try
import scala.collection.JavaConversions._

class PageFetcher {
  def fetch(url: String): Try[String] = Try {
    val output = new ByteArrayOutputStream
    (new URL(url) #> output).!!
    output.toString("UTF-8")
  }
}

class ImageExtractor {
  def extractImages(html: String): List[String] = {
    val doc = Jsoup.parse(html)
    val imgs = doc.select("img").listIterator().asInstanceOf[JIterator[Element]].toList
    imgs.map(_.attr("src"))
  }
}

class MyImageFinder(pageFetcher: PageFetcher, imageExtractor: ImageExtractor) {
  def find(url: String): Try[List[String]] = {
    pageFetcher.fetch(url).map(html => imageExtractor.extractImages(html))
  }
}

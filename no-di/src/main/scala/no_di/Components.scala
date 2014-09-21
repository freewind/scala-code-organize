package no_di

import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.{Iterator => JIterator}

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.collection.JavaConversions._
import scala.sys.process._
import scala.util.Try

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

class MyImageFinder {
  val pageFetcher = new PageFetcher
  val imageExtractor = new ImageExtractor

  def find(url: String): Try[List[String]] = {
    pageFetcher.fetch(url).map(html => imageExtractor.extractImages(html))
  }
}

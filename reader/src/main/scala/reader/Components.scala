//package reader
//
//import java.io.ByteArrayOutputStream
//import java.net.URL
//import java.util.{Iterator => JIterator}
//
//import org.jsoup.Jsoup
//import org.jsoup.nodes.Element
//
//import scala.sys.process._
//import scala.util.Try
//import scala.collection.JavaConversions._
//import scalaz.{Kleisli, Reader}
//
//trait Dependencies {
//  val dep1: Dep1
//  val dep2: Dep2
//  val dep3: Dep3
//}
//
//trait PageFetcher {
//  def fetch(url: String): Reader[Dependencies, Try[String]] = Reader((deps: Dependencies) => Try {
//    val output = new ByteArrayOutputStream
//    (new URL(url) #> output).!!
//    output.toString("UTF-8")
//  })
//}
//
//trait ImageExtractor {
//  def extractImages(html: String): Reader[Dependencies, List[String]] = Reader((deps: Dependencies) => {
//    val doc = Jsoup.parse(html)
//    val imgs = doc.select("img").listIterator().asInstanceOf[JIterator[Element]].toList
//    imgs.map(_.attr("src"))
//  })
//}
//
//
//object MyImageFinder extends PageFetcher with ImageExtractor {
//  def find(url: String) =
////    for (htmlTry <- fetch(url)) yield {
////      for (html <- htmlTry) yield {
////        for (images <- extractImages(html)) yield images
////      }
////    }
//
//      for {
//      htmlTry <- fetch(url)
//      html <- htmlTry
//    } yield extractImages(html)
//
//
//}
//
//// I add these 3 useless dependencies here just for demo
//class Dep1
//
//class Dep2
//
//class Dep3

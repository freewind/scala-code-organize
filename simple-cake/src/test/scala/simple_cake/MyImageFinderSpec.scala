package simple_cake

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

import scala.util.{Failure, Success}

class MyImageFinderSpec extends Specification with Mockito {

  "MyImageFinder" should {
    "return images successfully if pageFetcher can get the page successfully" in {
      val imageFinder = new MyImageFinder with PageFetcher with ImageExtractor {
        override def fetch(url: String) = url match {
          case "test-url" => Success("some-html-code-contains-images")
        }

        override def extractImages(html: String) = html match {
          case "some-html-code-contains-images" => List("a.png", "b.png")
        }
      }

      val images = imageFinder.find("test-url")

      images must beASuccessfulTry(List("a.png", "b.png"))
    }

    "return a failure if pageFetcher can't get the page" in {
      val imageFinder = new MyImageFinder with PageFetcher with ImageExtractor {
        override def fetch(url: String) = url match {
          case "test-url" => Failure(new Throwable("test-connection-error"))
        }

        override def extractImages(html: String) = ???
      }

      val images = imageFinder.find("test-url")

      images must beAFailedTry.which(_.getMessage === "test-connection-error")
    }
  }

}

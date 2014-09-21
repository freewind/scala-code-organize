package ctor_params

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import scala.util.{Failure, Success}

class MyImageFinderSpec extends Specification with Mockito {

  "MyImageFinder" should {
    "return images successfully if pageFetcher can get the page successfully" in new Mocking {
      pageFetcher.fetch("test-url") returns Success("some-html-code-contains-images")
      imageExtractor.extractImages("some-html-code-contains-images") returns List("a.png", "b.png")

      val images = imageFinder.find("test-url")

      images must beASuccessfulTry(List("a.png", "b.png"))
    }

    "return a failure if pageFetcher can't get the page" in new Mocking {
      pageFetcher.fetch("test-url") returns Failure(new Throwable("test-connection-error"))

      val images = imageFinder.find("test-url")

      images must beAFailedTry.which(_.getMessage === "test-connection-error")
    }
  }

  trait Mocking extends Scope {
    val pageFetcher = mock[PageFetcher]
    val imageExtractor = mock[ImageExtractor]
    val imageFinder = new MyImageFinder(pageFetcher, imageExtractor)
  }


}

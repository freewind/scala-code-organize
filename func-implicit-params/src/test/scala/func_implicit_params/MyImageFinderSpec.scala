package func_implicit_params

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import scala.util.{Success, Failure}

class MyImageFinderSpec extends Specification with Mockito {

  "MyImageFinder" should {
    "return images successfully if pageFetcher can get the page successfully" in new Mocking {
      pageFetcher.fetch("test-url") returns Success("some-html-code-contains-images")
      imageExtractor.extractImages("some-html-code-contains-images") returns List("a.png", "b.png")

      val images = MyImageFinder.find("test-url")

      images must beASuccessfulTry(List("a.png", "b.png"))
    }

    "return a failure if pageFetcher can't get the page" in new Mocking {
      pageFetcher.fetch("test-url") returns Failure(new Throwable("test-connection-error"))

      val images = MyImageFinder.find("test-url")

      images must beAFailedTry.which(_.getMessage === "test-connection-error")
    }
  }

  // When you put implicit values in a trait
  // It's a good practice to add the type explicitly
  trait Mocking extends Scope {
    implicit val pageFetcher: PageFetcher = mock[PageFetcher]
    implicit val imageExtractor: ImageExtractor = mock[ImageExtractor]
    implicit val dep1: Dep1 = mock[Dep1]
    implicit val dep2: Dep2 = mock[Dep2]
    implicit val dep3: Dep3 = mock[Dep3]
  }

}

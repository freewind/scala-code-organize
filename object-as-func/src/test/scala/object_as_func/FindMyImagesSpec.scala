package object_as_func

import org.specs2.mutable.Specification

import scala.util.{Failure, Success}

class FindMyImagesSpec extends Specification {

  "MyImageFinder" should {
    "return images successfully if pageFetcher can get the page successfully" in {
      val findMyImages = new WillFindMyImages(
        { case "test-url" => Success("some-html-code-contains-images")},
        { case "some-html-code-contains-images" => List("a.png", "b.png")}
      )

      val images = findMyImages("test-url")

      images must beASuccessfulTry(List("a.png", "b.png"))
    }

    "return a failure if pageFetcher can't get the page" in {
      val findMyImages = new WillFindMyImages(
        _ => Failure(new Throwable("test-connection-error")),
        _ => ???
      )

      val images = findMyImages("any-url")

      images must beAFailedTry.which(_.getMessage === "test-connection-error")
    }
  }

}

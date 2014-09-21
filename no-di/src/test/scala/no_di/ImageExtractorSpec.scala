package no_di

import org.specs2.mutable.Specification

class ImageExtractorSpec extends Specification {

  val imageExtractor = new ImageExtractor

  "ImageExtractor" should {
    "extract images from html if it contains img tags" in {
      val images = imageExtractor.extractImages(
        """
          |<html>
          |<body>
          |<img src="http://test.com/a.png" />
          |<img src="http://test.com/b.png" />
          |</body>
          |</html>
        """.stripMargin)
      images === List("http://test.com/a.png", "http://test.com/b.png")
    }
  }

}

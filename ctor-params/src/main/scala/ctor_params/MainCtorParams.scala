package ctor_params

import scala.util.{Failure, Success}

object MainCtorParams {

  val pageFetcher = new PageFetcher
  val imageExtractor = new ImageExtractor
  val myImageFinder = new MyImageFinder(pageFetcher, imageExtractor)

  def main(args: Array[String]) {
    args.headOption match {
      case Some(url) => myImageFinder.find(url) match {
        case Success(images) => images.foreach(println)
        case Failure(err) => println(err.toString)
      }
      case _ => println("Please input an url")
    }
  }

}

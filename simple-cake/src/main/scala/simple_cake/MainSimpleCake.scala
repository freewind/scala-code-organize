package simple_cake

import scala.util.{Failure, Success}

object MainSimpleCake extends MyImageFinder with PageFetcher with ImageExtractor {

  def main(args: Array[String]) {
    args.headOption match {
      case Some(url) => find(url) match {
        case Success(images) => images.foreach(println)
        case Failure(err) => println(err.toString)
      }
      case _ => println("Please input an url")
    }
  }

}

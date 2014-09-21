package func_implicit_params

import scala.util.{Failure, Success}

object MainFunctionImplicitParams {

  implicit val pageFetcher = new PageFetcher
  implicit val imageExtractor = new ImageExtractor
  implicit val dep1 = new Dep1
  implicit val dep2 = new Dep2
  implicit val dep3 = new Dep3

  def main(args: Array[String]) {
    args.headOption match {
      case Some(url) => MyImageFinder.find(url) match {
        case Success(images) => images.foreach(println)
        case Failure(err) => println(err.toString)
      }
      case _ => println("Please input an url")
    }
  }

}

package object_as_func

import scala.util.{Failure, Success}

object MainObjectAsFunctions {

  val findMyImages = new WillFindMyImages(FetchPage, ExtractImages)

  def main(args: Array[String]) {
    args.headOption match {
      case Some(url) => findMyImages(url) match {
        case Success(images) => images.foreach(println)
        case Failure(err) => println(err.toString)
      }
      case _ => println("Please input an url")
    }
  }

}

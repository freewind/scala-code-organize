package no_di

import scala.util.{Failure, Success}


object MainNoDi {

  val myImageFinder = new MyImageFinder

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

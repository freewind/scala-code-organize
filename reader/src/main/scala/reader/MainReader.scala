//package reader
//
//import scala.util.{Failure, Success}
//
//object MainReader {
//
//  object Deps extends Dependencies {
//    val dep1 = new Dep1
//    val dep2 = new Dep2
//    val dep3 = new Dep3
//  }
//
//  def main(args: Array[String]) {
//    args.headOption match {
//      case Some(url) =>
//        MyImageFinder.find(url)(Deps) match {
//          case Success(images) => images.foreach(println)
//          case Failure(err) => println(err.toString)
//        }
//      case _ => println("Please input an url")
//    }
//  }
//
//}

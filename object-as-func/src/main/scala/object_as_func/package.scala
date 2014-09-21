import scala.util.Try

package object object_as_func {
  type FetchPage = String => Try[String]
  type ExtractImages = String => List[String]
  type FindMyImages = String => Try[List[String]]
}

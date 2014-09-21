//package ttt
//
//import scala.util.Try
//import scalaz.{Id, Kleisli, Reader}
//
//case class User(email: String, supervisorId: Int, firstName: String, lastName: String)
//
//trait UserRepository {
//  def get(id: Int): Try[User]
//
//  def find(username: String): Try[User]
//}
//
//trait Users {
//
//  def getUser(id: Int) = Reader((userRepository: UserRepository) =>
//    userRepository.get(id)
//  )
//
//  def findUser(username: String) = Reader((userRepository: UserRepository) =>
//    userRepository.find(username)
//  )
//}
//
//object UserInfo extends Users {
//
//  def userEmail(id: Int) = {
//    getUser(id) map (ut => ut.map(_.email))
//  }
//
//  def userInfo(username: String) = {
//    val kleisli: Kleisli[Id.Id, UserRepository, Try[Kleisli[Id.Id, UserRepository, Try[Map[String, String]]]]] = for (userTry <- findUser(username)) yield {
//      for (user <- userTry) yield {
//        for (bossTry <- getUser(user.supervisorId)) yield {
//          for (boss <- bossTry) yield {
//            Map(
//              "fullName" -> s"${user.firstName} ${user.lastName}",
//              "email" -> s"${user.email}",
//              "boss" -> s"${boss.firstName} ${boss.lastName}"
//            )
//          }
//        }
//      }
//    }
//    val x = kleisli
//  }
//}

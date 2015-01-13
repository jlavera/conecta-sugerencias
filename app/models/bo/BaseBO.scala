package models.bo

abstract class BaseBO[T] {

  var Id: Int = _

  def complete(row: org.anormcypher.CypherResultRow): T

}
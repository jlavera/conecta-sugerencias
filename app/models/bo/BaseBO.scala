package models.bo

import org.anormcypher.CypherResultRow

abstract class BaseBO[T] {

  var Id: Int = _

  def complete(row: CypherResultRow): T

}
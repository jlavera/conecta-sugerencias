package models.dao

import models.bo.BaseBO
import org.anormcypher.Cypher

abstract class BaseDAO[T <: BaseBO[T]] (implicit man:Manifest[T]) {

  val label: String
  val properties: List[String]

  def MatchAll = {
    var query = "MATCH (x:" + label + ") RETURN "

    properties.foreach(p => query = query.concat("x." + p.toLowerCase + ", "))

    query = query.substring(0, query.length - 2) + ";"

    Cypher(query)().map {
      row =>
        man
          .runtimeClass
          .newInstance
          .asInstanceOf[BaseBO[T]]
          .complete(row)
    }.toList
  }
}
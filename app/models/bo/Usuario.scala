package models.bo

import org.anormcypher.Cypher

class Usuario extends BaseBO[Usuario] {

  var Nombre: String = _
  val AmigoDe: List[Usuario] = List[Usuario]()
  val AnotadoA: List[Evento] = List[Evento]()

  def complete(row: org.anormcypher.CypherResultRow): Usuario = {
    Id = row[Option[Int]]("x.id").getOrElse(0)
    Nombre = row[Option[String]]("x.nombre").getOrElse("")
    this
  }
}
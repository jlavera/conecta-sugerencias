package models.bo

class Evento extends BaseBO[Evento] {

  var Titulo: String = _

  def complete(row: org.anormcypher.CypherResultRow): Evento = {
    Id = row[Option[Int]]("x.id").getOrElse(0)
    Titulo = row[Option[String]]("x.titulo").getOrElse("")
    this
  }
}

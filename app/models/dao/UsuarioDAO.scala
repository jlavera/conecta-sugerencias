package models.dao

import models.bo.Usuario
import org.anormcypher.Cypher

class UsuarioDAO extends BaseDAO[Usuario] {

  override val label = "Usuario"
  override val properties = List[String](
    "Id",
    "Nombre"
  )

  def getAmigos(_id: Int) = {
    new UsuarioDAO()
      .ExecuteQueryMapped("MATCH (xx:Usuario{id:" + _id + "})-[:AMIGODE]->(x) return x.id, x.nombre;")
  }

  def getAmigosDeAmigos(_id: Int) = {
    new UsuarioDAO()
      .ExecuteQueryMapped("MATCH (xx:Usuario{id:" + _id + "})-[:AMIGODE]->()-[:AMIGODE]->(x) WHERE x.id <> " + _id + " RETURN distinct x.id, x.nombre;")
  }
}
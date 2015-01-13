package models.dao

import models.bo.Usuario
import org.anormcypher.Cypher

class UsuarioDAO extends BaseDAO[Usuario] {

  override val label = "Usuario"
  override val properties = List[String](
    "Id",
    "Nombre"
  )

}
package models.dao

import models.bo.Evento

class EventoDAO extends BaseDAO[Evento] {

  override val label = "Evento"
  override val properties = List[String](
    "Id",
    "Titulo"
  )

}
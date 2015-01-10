package controllers

import models.{Evento, Usuario}
import play.api._
import play.api.mvc._
import org.anormcypher._

object Application extends Controller {

  def index = Action { implicit request =>
    val query = Cypher("MATCH (n:Usuario) RETURN n.id, n.nombre;")

    var tipo: String = "a"

    val results: List[(Int, String)] = query().map(row => {
      tipo = row.getClass.toString
      (row[String]("n.id").toInt, row[String]("n.nombre"))
    }).toList
    Ok(views.html.index(tipo, results))
  }

  def verUsuarios = Action {
    val results = Cypher("MATCH (n:Usuario) RETURN n.id, n.nombre;")().map(row => {
      val u = new Usuario
      u.Id = row[String]("n.id").toInt
      u.Nombre = row[String]("n.nombre")
      u
    }).toList
    Ok(views.html.listaUsuarios(results))
  }

  def verEventos = Action {
    val results = Cypher("MATCH (n:Evento) RETURN n.id, n.titulo;")().map(row => {
      val u = new Evento
      u.Id = row[String]("n.id").toInt
      u.Titulo = row[String]("n.titulo")
      u
    }).toList
    Ok(views.html.listaEventos(results))
  }

  def agregarUsuario(_id: Int, _nombre: String) = Action { _ =>
    val user = new Usuario
    user.Id = _id
    user.Nombre = _nombre

    Cypher("create (n:Usuario {id: '" + user.Id + "', nombre: '" + user.Nombre + "'})")()

    Ok(views.html.test())
  }

  def agregarEvento(_id: Int, _titulo: String) = Action { _ =>
    val evento = new Evento
    evento.Id = _id
    evento.Titulo = _titulo

    Cypher("create (n:Evento {id: '" + evento.Id + "', titulo: '" + evento.Titulo + "'})")()

    Ok(views.html.test())
  }

}
package controllers

import models.bo.{Usuario, Evento}
import models.dao.{EventoDAO, UsuarioDAO}
import play.api._
import play.api.mvc._
import org.anormcypher._
import reflect.runtime.universe._

object Application extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.index("index", List[(Int, String)]()))
  }

  def verUsuarios = Action {
    Ok(views.html.listaUsuarios(new UsuarioDAO().MatchAll))
  }

  def verEventos = Action {
    Ok(views.html.listaEventos(new EventoDAO().MatchAll))
  }

  def agregarUsuario(_id: Int, _nombre: String) = Action { _ =>
    val user = new Usuario
    user.Id = _id
    user.Nombre = _nombre

    val res = Cypher("create (n:Usuario {id: " + user.Id + ", nombre: '" + user.Nombre + "'})")
      .execute

    Ok(views.html.test(res.toString))
  }

  def agregarEvento(_id: Int, _titulo: String) = Action { _ =>
    val evento = new Evento
    evento.Id = _id
    evento.Titulo = _titulo

    val res = Cypher("create (n:Evento {id: '" + evento.Id + "', titulo: '" + evento.Titulo + "'})")
      .execute

    Ok(views.html.test(res.toString))
  }

  def agregarAmigo(_id: Int, _id_amigo: Int) = Action { _ =>
    val res = Cypher(
      "MATCH (x:Usuario{id:" + _id + "}),(xx:Usuario{id:" + _id_amigo + "}) " +
        "MERGE (x)-[rel:AMIGODE]->(xx) ON CREATE SET rel.desde = timestamp()"
    ).execute()
    Ok(views.html.test(res.toString))
  }

  def getAmigos(_id: Int) = Action { _ =>
    Ok(views.html.getAmigos(new UsuarioDAO().getAmigos(_id)))
  }

  def getAmigosDeAmigos(_id: Int) = Action { _ =>
    Ok(views.html.getAmigos(new UsuarioDAO().getAmigosDeAmigos(_id)))
  }

}
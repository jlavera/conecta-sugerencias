package controllers

import com.github.nscala_time.time.StaticDateTime
import models.bo.Usuario
import models.bo.{Usuario, Evento}
import models.dao.{EventoDAO, UsuarioDAO}
import play.api._
import play.api.mvc._
import org.anormcypher._
import reflect.runtime.universe._

object Application extends Controller {

  def index = Action { implicit request =>
    //    val query = Cypher("MATCH (n:Usuario) RETURN n.id, n.nombre;")
    //
    //    val results: List[(Int, String)] = query().map(row => {
    //      (row[String]("n.id").toInt, row[String]("n.nombre"))
    //    }).toList
    //    Ok(views.html.index(query.query, results))

    val u = new Usuario

    Ok(views.html.index(typeOf[u.type].members.view.filter(m => m.isPublic).map(m => m.name).mkString(","), List[(Int, String)]()))
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

    val res = Cypher("create (n:Usuario {id: '" + user.Id + "', nombre: '" + user.Nombre + "'})")
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

  def agregarAmigo(_id: Int, _id_amigo: Int)=Action{_=>
    //MERGE (keanu:Person { name:'Keanu Reeves' })
    //ON CREATE SET keanu.created = timestamp();
    val res = Cypher(
      "MERGE (x:Usuario{id:" + _id + "})-[rel:AMIGODE]->(xx:Usuario{id:" + _id_amigo + "}) " +
      "ON CREATE SET rel.desde = timestamp();"
    ).execute()
    Ok(views.html.test(res.toString))
  }

}
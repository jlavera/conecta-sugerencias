import play.api._
import org.anormcypher._
import java.net.URL

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("configuring Neo4j for " + System.getenv("GRAPHENEDB_URL"))
    val neo4jURLString = util.Properties.envOrElse("GRAPHENEDB_URL","http://localhost:7474")
    val url:URL = new URL(neo4jURLString)

    // testing locally don't have user/pass
    if(url.getUserInfo != null) {
      val Array(user:String, pass:String) = url.getUserInfo.split(":")
      //Neo4jREST.setServer(url.getHost, url.getPort, "/db/data/", user, pass)
      Neo4jREST.setServer(url.getHost, url.getPort, "/db/data/", "neo4j", "7aac909ca36ad5265567e9f00559e562")
    } else {
      Neo4jREST.setServer(url.getHost, url.getPort, "/db/data/", "neo4j", "7aac909ca36ad5265567e9f00559e562")
    }
  }
  
}
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "Matt", "Mro13542");
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteClients = "DELETE FROM clients *;";
      String deleteStylists = "DELETE FROM stylists *;";
      con.createQuery(deleteClients).executeUpdate();
      con.createQuery(deleteStylists).executeUpdate();
    }
  }
}

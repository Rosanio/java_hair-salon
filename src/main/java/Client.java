import org.sql2o.*;
import java.util.*;

public class Client {

  private int id;
  private String name;
  private int stylist_id;

  public Client(String name) {
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }
}

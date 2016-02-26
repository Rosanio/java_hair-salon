import org.sql2o.*;
import java.util.*;

public class Client {

  private int id;
  private String name;
  private int stylist_id;

  public Client(String name, int stylist_id) {
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public String getName() {
    return name;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if(!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return newClient.getName().equals(name);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, stylist_id) VALUES (:name, :stylist_id)";
      con.createQuery(sql).addParameter("name", name).addParameter("stylist_id", stylist_id).executeUpdate();
    }
  }
}

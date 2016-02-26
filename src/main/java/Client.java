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

  public int getId() {
    return id;
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
      return newClient.getName().equals(name) && newClient.getStylistId() == stylist_id;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, stylist_id) VALUES (:name, :stylist_id)";
      this.id = (int) con.createQuery(sql, true).addParameter("name", name).addParameter("stylist_id", stylist_id).executeUpdate().getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Client.class);
    }
  }

  public void updateName(String newName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :newName WHERE id = :id";
      con.createQuery(sql).addParameter("newName", newName).addParameter("id", id).executeUpdate();
    }
  }

  public void updateStylist(int stylist_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET stylist_id = :stylist_id WHERE id = :id";
      con.createQuery(sql).addParameter("stylist_id", stylist_id).addParameter("id", id).executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }

  public Stylist getStylist() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id = :stylist_id";
      return con.createQuery(sql).addParameter("stylist_id", stylist_id).executeAndFetchFirst(Stylist.class);
    }
  }

}

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueWhenStylistsHaveSameName() {
    Stylist firstStylist = new Stylist("Matt");
    Stylist secondStylist = new Stylist("Matt");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesStylistToDatabase() {
    Stylist newStylist = new Stylist("Matt");
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void save_assignsIdToStylist() {
    Stylist newStylist = new Stylist("Matt");
    newStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(newStylist.getId(), savedStylist.getId());
  }

  @Test
  public void find_returnsStylistById() {
    Stylist firstStylist = new Stylist("Matt");
    Stylist secondStylist = new Stylist("Jeff");
    firstStylist.save();
    secondStylist.save();
    assertTrue(Stylist.find(firstStylist.getId()).equals(firstStylist));
    assertTrue(Stylist.find(secondStylist.getId()).equals(secondStylist));
  }

  @Test
  public void update_updatesStylistName() {
    Stylist newStylist = new Stylist("Matt");
    String newName = "Xanthor";
    newStylist.save();
    newStylist.update(newName);
    assertTrue(Stylist.find(newStylist.getId()).getName().equals(newName));
  }

  @Test
  public void delete_removesStylistFromDatabase() {
    Stylist newStylist = new Stylist("Matt");
    newStylist.save();
    newStylist.delete();
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void getClients_returnsListOfClientsAssignedToStylist() {
    Stylist newStylist = new Stylist("Jeff");
    newStylist.save();
    Client firstClient = new Client("Matt", newStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Marshall", newStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] {firstClient, secondClient};
    assertTrue(newStylist.getClients().containsAll(Arrays.asList(clients)));
  }

}

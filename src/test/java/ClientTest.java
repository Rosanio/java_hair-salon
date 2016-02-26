import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_returnsEmptyListAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueWhenClientsHaveSameName() {
    Client firstClient = new Client("Matt", 1);
    Client secondClient = new Client("Matt", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesClientToDatabase() {
    Client newClient = new Client("Matt", 1);
    newClient.save();
    assertTrue(Client.all().get(0).equals(newClient));
  }

  @Test
  public void save_assignsIdToClient() {
    Client newClient = new Client("Matt", 1);
    newClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(newClient.getId(), savedClient.getId());
  }

  @Test
  public void find_returnsClientById() {
    Client firstClient = new Client("Matt", 1);
    Client secondClient = new Client("Jeff", 1);
    firstClient.save();
    secondClient.save();
    assertTrue(Client.find(firstClient.getId()).equals(firstClient));
    assertTrue(Client.find(secondClient.getId()).equals(secondClient));
  }

  @Test
  public void updateName_updatesClientName() {
    Client newClient = new Client("Matt", 1);
    String newName = "Jeff";
    newClient.save();
    newClient.updateName(newName);
    assertEquals(Client.find(newClient.getId()).getName(), newName);
  }

  @Test
  public void updateStylist_updatesClientStylistAssignmentId() {
    Client newClient = new Client("Matt", 1);
    newClient.save();
    newClient.updateStylist(2);
    assertEquals(Client.find(newClient.getId()).getStylistId(), 2);
  }
}

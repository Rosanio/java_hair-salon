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
}

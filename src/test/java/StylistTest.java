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

}

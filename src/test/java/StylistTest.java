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

}

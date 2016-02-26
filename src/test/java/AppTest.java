import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Rosanio's Hair Salon");
  }

  @Test
  public void stylistsAreDisplayedTest() {
    Stylist newStylist = new Stylist("Jeff");
    newStylist.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Jeff");
  }

  @Test
  public void addStylistTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Apply to become a member of our staff!"));
    assertThat(pageSource()).contains("So, you want to work at a hair salon?");
  }

  @Test
  public void successTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Apply to become a member of our staff!"));
    fill("#name").with("Michael");
    submit(".btn");
    assertThat(pageSource()).contains("Congratulations");
  }

  @Test
  public void stylistPageTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Apply to become a member of our staff!"));
    fill("#name").with("Michael");
    submit(".btn");
    click("a", withText("Go Home"));
    click("a", withText("Michael"));
    assertThat(pageSource()).contains("Make an Appointment with Michael");
  }
}

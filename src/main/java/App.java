import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/addStylist", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/addStylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/success", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String stylistName = request.queryParams("name");
      Stylist newStylist = new Stylist(stylistName);
      newStylist.save();
      model.put("stylist", newStylist);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("clients", stylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id/appointment", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/appointment.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String clientName = request.queryParams("clientName");
      Client newClient = new Client(clientName, stylist.getId());
      newClient.save();
      model.put("stylist", stylist);
      model.put("clients", stylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id/updated", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String updatedName = request.queryParams("updatedName");
      stylist.update(updatedName);
      model.put("stylist", stylist);
      model.put("template", "templates/updated.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id/deleteStylist", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      stylist.delete();
      model.put("stylist", stylist);
      model.put("template", "templates/deleteStylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}

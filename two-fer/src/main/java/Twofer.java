public class Twofer {

  public String twofer(String name) {
    if (name != null) {
      if (name.trim().equals("")) {
        return "One for , one for me.";
      } else {
        return "One for " + name + ", one for me.";
      }
    }
    return "One for you, one for me.";
  }
}

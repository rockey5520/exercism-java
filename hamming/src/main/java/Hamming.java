import java.util.stream.IntStream;

public class Hamming {


  private int hammingDistance;

  public Hamming(String leftStrand, String rightStrand) {
    if (leftStrand.isEmpty() && rightStrand.isEmpty()) {
      return;
    } else if (leftStrand.trim().equals("")) {
      throw new IllegalArgumentException("left strand must not be empty.");
    } else if (rightStrand.trim().equals("")) {
      throw new IllegalArgumentException("right strand must not be empty.");
    }
    if (leftStrand.length() == rightStrand.length()) {
      hammingDistance = (int) IntStream.range(0, leftStrand.length())
          .filter(x -> leftStrand.charAt(x) != rightStrand.charAt(x)).count();
    } else {
      throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
    }
  }

  public int getHammingDistance() {
    return hammingDistance;
  }

}

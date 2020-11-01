package studyhall.drda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DRDA {
  private static DRDA instance = null;
  private static byte[] bytes;

  private DRDA() {

  }

  public static DRDA getInstance(String inputFileName){
    if (instance == null) {
      instance = new DRDA();
      try {
        byte[] bytes = Files.readAllBytes(Path.of(inputFileName));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return instance;
  }

  public static byte[] getBytes() {
    return bytes;
  }
}

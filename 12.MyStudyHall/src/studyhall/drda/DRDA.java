package studyhall.drda;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class DRDA {
  private static DRDA instance = null;
  private static byte[] bytes;
  private static int length;
  private static int pointer;

  private DRDA() {

  }

  public static DRDA getInstance(String inputFileName) {
    if (instance == null) {
      instance = new DRDA();
      try {
        bytes = Files.readAllBytes(Path.of(inputFileName));
        length = bytes.length;
        pointer = 0;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return instance;
  }

  public byte[] getAll() {
    return bytes;
  }

  public boolean seek(int to) {
    pointer = to;
    return notEndOfData();
  }

  public boolean seekFromCurrent(int count) {
    pointer = pointer + count;
    return notEndOfData();
  }

  public byte[] read(int count) {
    byte[] res = null;
    if (count > 0) {
      res = Arrays.copyOfRange(bytes, pointer, pointer + count);
    } else if (count < 0){
      res = Arrays.copyOfRange(bytes, pointer + count, pointer);
    }
    pointer = pointer + count;
    return res;
  }

  public boolean notEndOfData() {
    return 0 <= pointer && pointer < length;
  }

  public String bytesToHex(byte[] bytes) {
    BigInteger bigInteger = new BigInteger(1, bytes);
    return bigInteger.toString(16);
  }
}

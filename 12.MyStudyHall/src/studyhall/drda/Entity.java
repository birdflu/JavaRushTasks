package studyhall.drda;

import java.math.BigInteger;

public class Entity {
  protected byte[] bytes;

  public Entity() {
  }

  public String bytesToHex(byte[] bytes) {
    BigInteger bigInteger = new BigInteger(1, bytes);
    return bigInteger.toString(16);
  }

  public String byteToHex(byte num) {
    return Integer.toHexString((num >> 4) & 0xF) + Integer.toHexString(num & 0xF);
  }
}

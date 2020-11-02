package studyhall.drda;

import studyhall.drda.dictionary.EthernetType;
import studyhall.drda.dictionary.IPTypicalVersion;
import studyhall.drda.dictionary.Manufacture;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    String inputFileName = "/home/birdflu/work/drda/db2/test_db2_small.pcap";
    DRDA drda = DRDA.getInstance(inputFileName);

    int d = 0x00;
    System.out.println("d = " + d);
    System.out.println(byteToHex((byte) 0));

    findFrame(drda);

    // LG bit: Globally unique address (manufacture default)
    byte[] manufacture = drda.read(3);

    // IG bit: Individual address (unicast)
    byte[] unicast = drda.read(3);

    // Ethernet Type: IPv4 (0x0800) (08 00)
    byte[] ethType = drda.read(2);

    drda.seekFromCurrent(6);

    // Internet Protocol Version 4 (45)
    byte[] IPVersion = drda.read(1);


    print(manufacture, Manufacture.class);
    System.out.printf("unicast = %s\n", bytesToHex(unicast));
    print(ethType, EthernetType.class);
    print(IPVersion, IPTypicalVersion.class);


//    example();

  }

  protected static <E extends Enum<E>> void print(byte[] enumValue, Class<E> clazz) {
    Method methodValueOf = null;
    Method methodGetName = null;

    try {
      methodValueOf = clazz.getMethod("valueOf", Class.class, String.class);
      methodGetName = clazz.getMethod("getName");
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    Object arglist[] = new Object[2];
    arglist[0] = clazz;
    arglist[1] = "x"+bytesToHex(enumValue);

    try {
      assert methodValueOf != null;
      Object enumElement = methodValueOf.invoke(clazz, arglist);
      assert methodGetName != null;
      Object enumName = methodGetName.invoke(enumElement);

      System.out.printf("%s = %s (%s)\n", clazz.getSimpleName(), bytesToHex(enumValue), enumName);

    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

  }


//  701f 9c5f ea2e 0900 [5600 0000 5600 0000]
//  [0800 277c 7e7f ...  <-- frame

  private static void findFrame(DRDA drda) {
    List<Byte> frame = new ArrayList<>();
    int framePosition = -1;
    for (int i = 0; i < 20; i++) {
      byte[] next = drda.read(3);
      if (bytesToHex(next).equals("000000")) {
        drda.seekFromCurrent(-4);
        byte[] first = drda.read(4);    // 5600 0000
        byte[] second = drda.read(4);   // 5600 0000
        byte[] factory = drda.read(3);  // 0800 27
        if (bytesToHex(first).equals(bytesToHex(second)) &&
                !bytesToHex(first).equals("00000000") &&
                Manufacture.getValues().contains(bytesToHex(factory))) {
          drda.seekFromCurrent(-3);
          return;
        } else drda.seekFromCurrent(-4);
      }
    }
  }

/*
  Frame 161

        08 00 27 7c 7e 7f 08 00 27 fd 0e 33 08 00 45 00   ..'|~...'..3..E.
        00 3e f5 30 40 00 40 06 c1 1d c0 a8 01 8b c0 a8   .>.0@.@.........
        01 90 8f d6 c3 50 a8 54 32 06 70 fb 34 03 80 18   .....P.T2.p.4...
        7d 2c 91 f1 00 00 01 01 08 0a c0 9f ce 77 af 4d   },...........w.M
        e2 1b 00 0a d0 01 00 01 00 04 20 0f               . ......... .


        Ethernet II
        Src: PcsCompu_fd:0e:33
        LG bit: Globally unique address (factory default)
        (08:00:27)
        IG bit: Individual address (unicast)
        (fd:0e:33)

        Dst: PcsCompu_7c:7e:7f
        LG bit: Globally unique address (factory default)
        (08:00:27)
        IG bit: Individual address (unicast)
        (7c:7e:7f)

        Type: IPv4 (0x0800) (08 00)
        08 00 27 7c 7e 7f 08 00 27 fd 0e 33 08 00


        Internet Protocol
        Version 4          (45)
        Protocol TCP  (6)  (06)
        Src: 192.168.1.139 (c0 a8 01 8b)
        Dst: 192.168.1.144 (c0 a8 01 90)

        45 00
        00 3e f5 30 40 00 40 06 c1 1d c0 a8 01 8b c0 a8
        01 90


        Transmission Control Protocol
        Src Port: 36822    (8f d6)
        Dst Port: 50000    (c3 50)
        TCP Segment Len:10 (80)
        Seq:
        relative: 4134
        raw: 2824090118  (a8 54 32 06)
        Ack:
        relative: 3985
        raw: 1895511043 (70 fb 34 03)
        Timestamp value:
        3231698551      (c0 9f ce 77)
        Timestamp echo reply:
        2941116955      (af 4d e2 1b)
        Len: 10

        8f d6 c3 50 a8 54 32 06 70 fb 34 03 80 18
        7d 2c 91 f1 00 00 01 01 08 0a c0 9f ce 77 af 4d
        e2 1b


        TCP Payload (DRDA)
        Length: 10        (00 0a)
        Magic: 0xd0       (d0)
        Format: 0x01      (01)
        CorrelId: 1       (00 01)
        Length2: 4        (00 04)
        Code point:
        RDBRLLBCK        (20 0f)

        00 0a d0 01 00 01 00 04 20 0f
*/


  protected static void example() throws IOException {
    String inputFileName = "/home/birdflu/work/drda/db2/test_db2_small.pcap";
    byte[] fileContent = Files.readAllBytes(Path.of(inputFileName));

    int d = 0xd4;
    System.out.println("d = " + d);

    byte bytee = fileContent[0];
    int unsignedInt = Byte.toUnsignedInt(bytee);

    String byteToHex = byteToHex(bytee);
    String unsignedIntToHex = Integer.toHexString(unsignedInt);

//    byte hexToByte = Byte.parseByte(byteToHex, 16);
    int hexToInt = Integer.parseInt(unsignedIntToHex, 16);

    System.out.println("bytee = " + bytee);
    System.out.println("unsignedInt = " + unsignedInt);
//    System.out.println("byteToHex = " + byteToHex);
    System.out.println("unsignedIntToHex = " + unsignedIntToHex);
//    System.out.println("hexToByte = " + hexToByte);
    System.out.println("hexToInt = " + hexToInt);

    System.out.println(Arrays.toString(Arrays.copyOfRange(fileContent, 0, 8)));
    System.out.println(bytesToHex(Arrays.copyOfRange(fileContent, 0, 8)));
  }


  public static String byteToHex(byte num) {
    return Integer.toHexString((num >> 4) & 0xF) + Integer.toHexString(num & 0xF);
  }

  public static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      sb.append(Integer.toHexString((bytes[i] >> 4) & 0xF) + Integer.toHexString(bytes[i] & 0xF));
    }
    return sb.toString();
  }

}

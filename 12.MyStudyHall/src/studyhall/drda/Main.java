package studyhall.drda;

import studyhall.drda.dictionary.EthernetType;
import studyhall.drda.dictionary.IPTypicalVersion;
import studyhall.drda.dictionary.Manufacture;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    String inputFileName = "/home/birdflu/work/drda/db2/test_db2_small.pcap";
    DRDA drda = DRDA.getInstance(inputFileName);

    findFrame(drda);

    // Frame begin
    int frameHeaderPointer = drda.getPointer();

    // head 14 bytes
    // LG bit: Globally unique address (manufacture default)
    byte[] manufacture = drda.read(3);

    // IG bit: Individual address (unicast)
    byte[] unicast = drda.read(3);

    // Ethernet Type: IPv4 (0x0800) (08 00)
    byte[] ethType = drda.read(2);

    drda.seekFromCurrent(6);

    // Internet Protocol
    int ipHeaderPointer = drda.getPointer();

    // Version 4 (45)
    byte[] ipVersion = drda.read(1);

    // Differentiated Services Field: 0x00
    byte[] ipDiffServices = drda.read(1);

    // Total Length
    byte[] totalLengthBytes = drda.read(2);
    int totalLength = Integer.valueOf(bytesToHex(totalLengthBytes), 16);

    // Identification
    byte[] identification = drda.read(2);

    // ipFlags
    byte[] ipFlags = drda.read(2);

    // Time to Live
    byte[] ipTimeToLive = drda.read(1);

    // Protocol  : TCP  (6)  (06)
    byte[] tcpProtocol = drda.read(1);

    drda.seekFromCurrent(2);

    // Source
    byte[] source = drda.read(4);

    // Destination
    byte[] destination = drda.read(4);

    // Protocol TCP begin
    int tcpHeaderPointer = drda.getPointer();

    // TCP Source Port
    byte[] srcPort = drda.read(2);

    // TCP Destination Port
    byte[] destPort = drda.read(2);

    // TCP Sequence number (raw)
    byte[] tcpSeqRaw = drda.read(4);

    // TCP Acknowledge number (raw)
    byte[] tcpAckRaw = drda.read(4);

    // TCP Data offset & Flags
    byte[] tcpOffsetFlags = drda.read(2);

    String tcpOffsetFlagsFirstByte = Integer.toBinaryString(Byte.toUnsignedInt(tcpOffsetFlags[0]));
    String tcpOffsetFlagsSecondByte = addLeadZeros(Integer.toBinaryString(Byte.toUnsignedInt(tcpOffsetFlags[1])));
    String tcpOffsetBinaryStr = tcpOffsetFlagsFirstByte + tcpOffsetFlagsSecondByte;

    String tcpDataOffset = tcpOffsetBinaryStr.substring(0, 4);
    String tcpFlags = tcpOffsetBinaryStr.substring(7, 16);

    // TCP header length
    // tcpDataOffset * 4 (bytes)
    // tcpDataOffset * 32 (bits)
    int tcpHeaderLength = Integer.valueOf(tcpDataOffset, 2) * 4;

    // TCP Window size
    byte[] tcpWindowSize = drda.read(2);

    // TCP checksum
    byte[] tcpChecksum = drda.read(2);

    // TCP urgent pointer
    byte[] tcpUrgentPointer = drda.read(2);

    // go to TCP Payload
    // Payload = [tcpHeaderPointer + tcpHeaderLength, ipHeaderPointer + totalLength]
    drda.seek(tcpHeaderPointer + tcpHeaderLength);
    byte[] tcpPayload = drda.read(ipHeaderPointer + totalLength -
            tcpHeaderPointer - tcpHeaderLength);

    print(manufacture, Manufacture.class);

    System.out.printf("Unicast = %s\n", bytesToHex(unicast));

    print(ethType, EthernetType.class);

    print(ipVersion, IPTypicalVersion.class);

    System.out.printf("DiffServices = %s\n", bytesToHex(ipDiffServices));

    System.out.printf("TotalLength = %s (%d)\n", bytesToHex(totalLengthBytes), totalLength);

    System.out.printf("Identification = %s (%d)\n",
            bytesToHex(identification),
            Integer.parseInt(bytesToHex(identification), 16));

    System.out.printf("IPFlags = %s\n", bytesToHex(ipFlags));

    System.out.printf("IPTimeTolive = %s (%d)\n",
            bytesToHex(ipTimeToLive),
            Integer.parseInt(bytesToHex(ipTimeToLive), 16));

    System.out.printf("ProtocolTCP = %s\n", bytesToHex(tcpProtocol));

    System.out.printf("Source = %s (%s)\n", bytesToHex(source), getIP4(source));

    System.out.printf("Destination = %s (%s)\n", bytesToHex(destination), getIP4(destination));

    System.out.printf("SourcePort = %s (%s)\n",
            bytesToHex(srcPort),
            Integer.parseInt(bytesToHex(srcPort), 16));

    System.out.printf("DestinationPort = %s (%d)\n",
            bytesToHex(destPort),
            Integer.parseInt(bytesToHex(destPort), 16));

    System.out.printf("TCPSeq(raw) = %s (%d)\n",
            bytesToHex(tcpSeqRaw),
            Long.parseLong(bytesToHex(tcpSeqRaw), 16));

    System.out.printf("TCPAck(raw) = %s (%d)\n",
            bytesToHex(tcpAckRaw),
            Long.parseLong(bytesToHex(tcpAckRaw), 16));


    System.out.printf("TCPWindowSize = %s (%d)\n",
            bytesToHex(tcpWindowSize),
            Integer.parseInt(bytesToHex(tcpWindowSize), 16));

    System.out.printf("TCPChecksum = %s\n", bytesToHex(tcpChecksum));

    System.out.printf("TCPUrgentPointer = %s (%d)\n",
            bytesToHex(tcpUrgentPointer),
            Integer.parseInt(bytesToHex(tcpUrgentPointer), 16));

    System.out.printf("TCPHeaderLength (bytes)= %d\n", tcpHeaderLength);

    System.out.printf("TCPFlags = %s\n", tcpFlags);

    System.out.println();
    System.out.printf("FrameHeaderPointer = %d\n", frameHeaderPointer);
    System.out.printf("IPHeaderPointer = %d\n", ipHeaderPointer);
    System.out.printf("TCPHeaderPointer = %d\n", tcpHeaderPointer);
    System.out.printf("TCPPayload = %s\n", bytesToHex(tcpPayload));

  }

  private static String addLeadZeros(String toBinaryString) {
    return ("00000000" + toBinaryString)
            .substring(toBinaryString.length(), toBinaryString.length() + 8);
  }

  protected static String getIP4(byte[] source) {
    StringBuilder ip4 = new StringBuilder();

    for (byte b : source) {
      ip4.append(Byte.toUnsignedInt(b));
      ip4.append('.');
    }
    ip4.deleteCharAt(ip4.length() - 1);
    return ip4.toString();
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

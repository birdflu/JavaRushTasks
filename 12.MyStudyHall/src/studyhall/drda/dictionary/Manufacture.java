package studyhall.drda.dictionary;

/*
 A part of wireshark's manufacture file
 https://github.com/wireshark/wireshark/blob/97dcaf97fc19510548aff917b30e956bb5fd3204/manuf.tmpl
*/

import java.util.Set;
import java.util.stream.Collectors;

public enum Manufacture {
  x080026   ("NorskDat"), // NorskDat	Norsk Data (Nord)
  x080027   ("PcsCompu"), // PcsCompu	PCS Computer Systems GmbH
  x080028   ("TiExplor"); // TiExplor	TI	# Explorer

  private final String name;

  Manufacture(String name) {
    this.name = name;
  }

  public String getHex() {
    return this.name().substring(1);
  }

  public String getName() {
    return name;
  }

  public static Set<String> getValues() {
    return Set.of(Manufacture.values()).stream()
            .map(v -> v.name().substring(1))
            .collect(Collectors.toSet());
  }
}

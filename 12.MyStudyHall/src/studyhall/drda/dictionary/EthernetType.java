package studyhall.drda.dictionary;

import java.util.Set;
import java.util.stream.Collectors;

public enum EthernetType {
  x0800  ("IPV4"),
  x86dd  ("IPV6 ");

  private final String name;

  EthernetType(String name) {
    this.name = name;
  }

  public String getHex() {
    return this.name().substring(1);
  }

  public String getName() { return name; }

  public static Set<String> getValues() {
    return Set.of(Manufacture.values()).stream()
            .map(v -> v.name().substring(1))
            .collect(Collectors.toSet());
  }
}

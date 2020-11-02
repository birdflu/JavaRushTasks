package studyhall.drda.dictionary;

import java.util.Set;
import java.util.stream.Collectors;

public enum IPTypicalVersion {
  x45  ("IP4_TYPICAL_VERSION_LENGTH"),
  x60  ("IP6_TYPICAL_VERSION_AND_TRAFFIC_CLASS");

  private final String name;

  IPTypicalVersion(String name) {
    this.name = name;
  }

  public String getHex() {
    return this.name().substring(1);
  }

  public String getName() { return name; }

  public static Set<String> getValues() {
    return Set.of(IPTypicalVersion.values()).stream()
            .map(v -> v.name().substring(1))
            .collect(Collectors.toSet());
  }
}

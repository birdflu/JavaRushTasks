package studyhall.drda;

public enum EthernetTypes {
  IPV4 ("0800"),
  IPV6 ("86dd");

  private final String hex;

  EthernetTypes(String hex) {
    this.hex = hex;
  }

  public String getHex() {
    return hex;
  }
}

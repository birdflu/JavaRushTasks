package studyhall.drda;

/*
 A part of wireshark's manufacture file
 https://github.com/wireshark/wireshark/blob/97dcaf97fc19510548aff917b30e956bb5fd3204/manuf.tmpl
*/

public enum Factories {
  NorskDat   ("080026"), // NorskDat	Norsk Data (Nord)
  PcsCompu   ("080027"), // PcsCompu	PCS Computer Systems GmbH
  TiExplor   ("080028"); // TiExplor	TI	# Explorer

  private final String hex;

  Factories(String hex) {
    this.hex = hex;
  }

  public String getHex() {
    return hex;
  }
}

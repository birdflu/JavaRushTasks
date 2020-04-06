package studyhall.multithreading.ships;

import java.util.HashMap;
import java.util.Map;

public class Command {
  
  private final String command;
  private final String[] arguments;
  
  public Command(String commandString) {
    arguments = commandString.split(" ");
    command = arguments[0];
    System.out.printf("Command %s\n ", commandString);
  }
  
  public String getCommand() {
    return command;
  }
  
  public Map<String, String> getArgs() {
    Map<String, String> args = new HashMap<>();
    for (int i = 1; i < arguments.length; i = i + 2) {
      args.put(arguments[i].substring(1), i < arguments.length - 1 ? arguments[i + 1] : null);
    }
    return args;
  }
  
  public String getFirstArgumentKey() {
    return arguments.length > 1 ? arguments[1].substring(1) : null;
  }
  
  public String getFirstArgumentValue() {
    return arguments.length > 2 ? arguments[2] : null;
  }
  
  public String getSecondArgumentKey() {
    return arguments.length > 3 ? arguments[3].substring(1) : null;
  }
  
  public String getSecondArgumentValue() {
    return arguments.length > 4 ? arguments[4] : null;
  }
}

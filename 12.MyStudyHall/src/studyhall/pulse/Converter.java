package studyhall.pulse;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Convert seconds to milliseconds
 */
class Converter {
  
  class Person {
    public List<Sample> samples;
    public String description;
    public String dateTime;
    public String hand;
    public boolean is_pulse;
    public boolean is_mobile;
    public String measurement_method;
  }
  
  class Sample {
    public double time;
    public double value;
  }
  
  /**
   * Convert pulse wave seconds to milliseconds
   */
  private static void convert() throws IOException {
    final String dir = System.getProperty("user.dir");
    String inputFileName =  dir + "\\out\\production\\12.MyStudyHall\\studyhall\\pulse\\" + "test.json";
    String outputFileName = dir + "\\12.MyStudyHall\\src\\studyhall\\pulse\\" + "result.json";
    
    System.out.println(inputFileName);
    System.out.println(outputFileName);
    Gson g = new Gson();
    Person person = g.fromJson(Files.readString(Path.of(inputFileName)), Person.class);
    
    for (Sample sample : person.samples) {
      sample.time = sample.time*1000000;
    }
    
    Files.writeString(Path.of(outputFileName), g.toJson(person));
  }
  
  public static void main(String[] args) throws IOException {
    Converter.convert();
  }
}
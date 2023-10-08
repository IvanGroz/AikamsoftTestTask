package jsonprocessing;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

  public class JsonProcessing {
  protected String jsonFilepath;
  protected Gson gson = new Gson();
  protected JsonReader jsonReader;
  public JsonProcessing(String jsonFilepath) throws FileNotFoundException {

    this.jsonFilepath = jsonFilepath;
    this.jsonReader = new JsonReader(new FileReader(new File(jsonFilepath)));
  }

    public <T> T getReadResults(Class<T> tClass) {
      return gson.fromJson(this.jsonReader, tClass);
    }
  }

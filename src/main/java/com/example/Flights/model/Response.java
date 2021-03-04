package com.example.Flights.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Response {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Response)) {
      return false;
    }
    Response response = (Response) o;
    return Objects.equals(getPath(), response.getPath()) && mathRegex(response.getPath());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPath());
  }

  private boolean mathRegex(String path) {
    Pattern pattern = Pattern.compile(".*_.*_.*");
    Matcher matcher = pattern.matcher(path);
    return  matcher.find();
  }

  private String path;

  private String code;

  private int duration;

}

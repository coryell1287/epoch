package com.epoch.activitysystem.exceptions;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@NoArgsConstructor
@Data
public class ErrorEntity {

  private String type;
  private String message;
  private Integer status;
  private String url = "Not available";
  private String method = "Not available";
  private Instant timestamp;

  public ErrorEntity setUrl(String url) {
    if (Strings.isNotBlank(url)) {
      this.url = url;
    }
    return this;
  }

  public ErrorEntity setRequestMethod(String method) {
    if (Strings.isNotBlank(method)) {
      this.method = method;
    }
    return this;
  }

  public ErrorEntity setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
    return this;
  }
}

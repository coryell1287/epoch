package com.epoch.activitysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
  info = @Info(
    title = "Activity notification API",
    version = "1.0.0",
    description = "API for keeping up-to-date on all of the actitivity the user subscribes to in" +
    " the Epoch system.",
    contact = @Contact(
      name = "Jamaal Long",
      url = "www.api.com",
      email = "jamaal.c.long@outlook.com"
    ),
    license = @License(
      url = "http://www.apache.org/licenses/LICENSE-2.0.html",
      name = "Apache 2.0"
    )
  )
)
@SpringBootApplication
public class ActivitySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitySystemApplication.class, args);
	}

}

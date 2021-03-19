package purcio.purcio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class PurcioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurcioApplication.class, args);
	}

}

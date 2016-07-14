package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.undertow.UndertowOptions;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

@SpringBootApplication
@RestController
public class DemoHttp2Application {

	@Bean
	UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
		UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
		factory.addBuilderCustomizers(
				builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
		return factory;
	}

	@RequestMapping
	public void hello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
		PrintWriter writer = response.getWriter();
		for (int i=0; i<10; i++){
			Thread.sleep(500);
			writer.write("This was alreay pushed @ " + Instant.now().toString() + "\n");
			writer.flush();
		}
		Thread.sleep(1000);
		writer.close();

	}

	public static void main(String[] args) {
		SpringApplication.run(DemoHttp2Application.class, args);
	}
}

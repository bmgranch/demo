package demo.tests.service.deck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import demo.tests.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j @Service
public class RestAssuredUtils {

	@Value("${rest.base.url}")
	private String apiBaseUrl;

	public RequestSpecification getReqSpec(){
		return RestAssured.given().baseUri(apiBaseUrl);

	}
}

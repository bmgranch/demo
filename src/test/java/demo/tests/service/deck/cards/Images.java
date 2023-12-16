package demo.tests.service.deck.cards;

import lombok.Data;
import org.testcontainers.shaded.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Images{
	String svg;
	String png;
}
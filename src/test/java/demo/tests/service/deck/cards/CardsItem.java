package demo.tests.service.deck.cards;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.testcontainers.shaded.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardsItem{
	String image;
	Images images;
	String code;
	String suit;
	String value;
}
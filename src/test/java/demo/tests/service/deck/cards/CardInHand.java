package demo.tests.service.deck.cards;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.testcontainers.shaded.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class CardInHand{
	List<CardsItem> cards;
	boolean success;
	String deckId;
	int remaining;
}
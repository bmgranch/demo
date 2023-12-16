package demo.tests.service.deck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeckService {

    public static final String GET_SHUFFLED_CARD="/new/shuffle";
    public static final String GET_A_NEW_DECK="/new";
    public static final String DRAW_CARD="/{deck_id}/draw/";
    public static final String GET_SHUFFLE_NEW_DECK="/{deck_id}/shuffle/";
    public static final String GET_A_NEW_SHUFFLED_DECK="/new/draw/";
}

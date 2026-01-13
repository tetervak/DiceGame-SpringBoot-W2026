package ca.tetervak.dicegame.service;

import ca.tetervak.dicegame.domain.DiceRollData;

public interface CookieDataService {

    String encodeRollData(DiceRollData rollData);

    DiceRollData decodeRollData(String cookieValue);
}

package ca.tetervak.dicegame.service;

import ca.tetervak.dicegame.domain.DiceRollData;

public interface DiceRollerService {
    DiceRollData getRollData(int numberOfDice);
}

package ca.tetervak.dicegame.domain;

import java.util.List;

public interface DiceRollData {
    List<Integer> getValues();

    int getNumberOfDice();

    int getTotal();
}

package ca.tetervak.dicegame.service.impl;

import ca.tetervak.dicegame.domain.DiceRollData;

import java.util.Collections;
import java.util.List;

public class DiceRollDataImpl implements DiceRollData {

    private final List<Integer> values;

    @Override
    public List<Integer> getValues() {
        return values;
    }

    @Override
    public int getNumberOfDice(){
        return values.size();
    }

    private final int total;

    @Override
    public int getTotal() {
        return total;
    }

    public DiceRollDataImpl(List<Integer> values) {
        this.values = Collections.unmodifiableList(values);
        int sum = 0;
        for(int value: values){
            sum += value;
        }
        total = sum;
    }

    @Override
    public String toString(){
        return String.format(
                "RollData{values = %s, total = %d, numberOfDice = %d}",
                values, total, getNumberOfDice()
        );
    }
}

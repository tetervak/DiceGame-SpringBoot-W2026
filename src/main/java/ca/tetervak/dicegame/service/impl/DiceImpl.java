package ca.tetervak.dicegame.service.impl;

import ca.tetervak.dicegame.domain.Dice;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DiceImpl implements Dice {

    public static final int INIT_VALUE = 1;

    private int value = INIT_VALUE;
    private final Random random;

    public DiceImpl() {
        random = new Random();
    }

    public DiceImpl(int value) {
        this();
        setValue(value);
    }

    public DiceImpl(int value, Random random) {
        this.value = value;
        this.random = random;
    }

    public DiceImpl(Random random) {
        this.random = random;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if(value < 1 || value > 6){
            throw new IllegalArgumentException("Value must be between 1 and 6");
        }
        this.value = value;
    }

    @Override
    public void roll(){
        value = random.nextInt(1,7);
    }

}

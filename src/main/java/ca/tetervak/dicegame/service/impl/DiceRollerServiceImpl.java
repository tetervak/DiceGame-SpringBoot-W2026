package ca.tetervak.dicegame.service.impl;

import ca.tetervak.dicegame.domain.Dice;
import ca.tetervak.dicegame.domain.DiceRollData;
import ca.tetervak.dicegame.service.DiceRollerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiceRollerServiceImpl implements DiceRollerService {

    private final Dice dice;

    public DiceRollerServiceImpl(Dice dice) {
        this.dice = dice;
    }

    @Override
    public DiceRollData getRollData(int numberOfDice) {

        if(numberOfDice < 1){
            throw new IllegalArgumentException("Illegal Number of Dice " + numberOfDice);
        }

        List<Integer> list = new ArrayList<>(numberOfDice);
        for(int i = 0; i < numberOfDice; i++){
            int rand;
            synchronized (dice) {
                dice.roll();
                rand = dice.getValue();
            }
            list.add(rand);
        }

        return new DiceRollDataImpl(list);
    }
}

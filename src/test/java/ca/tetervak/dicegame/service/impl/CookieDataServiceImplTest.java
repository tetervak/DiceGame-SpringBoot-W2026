package ca.tetervak.dicegame.service.impl;

import ca.tetervak.dicegame.domain.DiceRollData;
import ca.tetervak.dicegame.service.CookieDataService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class CookieDataServiceImplTest {

    List<Integer> inputList = List.of(2, 6, 2, 1, 7, 1);
    CookieDataService service = new CookieDataServiceImpl();

    @Test
    void encodeRollData() {
        out.println(inputList);
        out.println(service.encodeRollData(new DiceRollDataImpl(inputList)));
    }

    @Test
    void decodeRollData() {
        out.println(inputList);
        String cookieData = service.encodeRollData(new DiceRollDataImpl(inputList));
        DiceRollData rollData = service.decodeRollData(cookieData);
        out.println(rollData.getValues());
        assertEquals(inputList, rollData.getValues());
    }
}
package ca.tetervak.dicegame.service.impl;

import ca.tetervak.dicegame.domain.DiceRollData;
import ca.tetervak.dicegame.service.CookieDataService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CookieDataServiceImpl implements CookieDataService {
    @Override
    public String encodeRollData(DiceRollData rollData) {
        return rollData.getValues().stream()
                .map(Object::toString)
                .collect(Collectors.joining("+"));
    }

    @Override
    public DiceRollData decodeRollData(String cookieValue) {
        List<Integer> values = Arrays.stream(cookieValue.split("\\+"))
                        .map(Integer::parseInt)
                        .toList();
        return new DiceRollDataImpl(values);
    }
}

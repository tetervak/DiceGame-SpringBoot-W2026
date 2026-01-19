package ca.tetervak.dicegame.service.impl;

import ca.tetervak.dicegame.domain.Dice;
import ca.tetervak.dicegame.domain.DiceRollData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DiceRollerServiceImpl Tests")
class DiceRollerServiceImplTest {

    @Mock
    private Dice dice;

    @InjectMocks
    private DiceRollerServiceImpl diceRollerService;

    @Test
    @DisplayName("getRollData returns correct data when number of dice is valid")
    void getRollData_validNumberOfDice_returnsCorrectData() {
        // Arrange
        int numberOfDice = 3;
        when(dice.getValue()).thenReturn(1, 2, 3);

        // Act
        DiceRollData result = diceRollerService.getRollData(numberOfDice);

        // Assert
        verify(dice, times(numberOfDice)).roll();
        verify(dice, times(numberOfDice)).getValue();
        assertEquals(numberOfDice, result.getNumberOfDice());
        assertEquals(List.of(1, 2, 3), result.getValues());
        assertEquals(6, result.getTotal());
    }

    @Test
    @DisplayName("getRollData throws IllegalArgumentException when number of dice is zero")
    void getRollData_invalidNumberOfDice_throwsException() {
        // Arrange
        int numberOfDice = 0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            diceRollerService.getRollData(numberOfDice);
        });
        assertEquals("Illegal Number of Dice " + numberOfDice, exception.getMessage());
    }

    @Test
    @DisplayName("getRollData throws IllegalArgumentException when number of dice is negative")
    void getRollData_negativeNumberOfDice_throwsException() {
        // Arrange
        int numberOfDice = -1;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            diceRollerService.getRollData(numberOfDice);
        });
        assertEquals("Illegal Number of Dice " + numberOfDice, exception.getMessage());
    }
}

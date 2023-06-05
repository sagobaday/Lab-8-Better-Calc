import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private UserInterface userInterface;

    @BeforeEach
    public void setUp() {
        userInterface = new UserInterface();
    }

    @Test
    void testSimpleAdditionAndMultiplication() {
        assertEquals(userInterface.testInput("2 + 3 * 4"), 14);
    }

    @Test
    void testComplexAdditionAndMultiplication() {
        assertEquals(userInterface.testInput("1 + 2 * 3 + 4 * 5"), 27);
    }

    @Test
    void testSimpleParenthesis() {
        assertEquals(userInterface.testInput("(2 + 3) * 4"), 20);
    }

    @Test
    void testComplexParenthesis() {
        assertEquals(userInterface.testInput("((1 + 2) * 3) + (4 * (5 + 6))"), 39);
    }

    @Test
    void testPower() {
        assertEquals(userInterface.testInput("(2 + 3) ^ 2"), 25);
    }

    @Test
    void testModulo() {
        assertEquals(userInterface.testInput("(15 + 8) % 7"), 2);
    }

    @Test
    void testDivision() {
        assertEquals(userInterface.testInput("((12 / 3) + 2) / 2"), 4);
    }

    @Test
    void testCombination() {
        assertEquals(userInterface.testInput("(2 ^ 3 + 5) % 4 / 2"), 1);
    }
}
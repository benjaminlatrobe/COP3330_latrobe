// Code written by Benjamin Latrobe

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BodyMassIndexTest {
    private BodyMassIndex bmi;

    @BeforeEach
    public void init() {
        bmi = new BodyMassIndex(0,0);
    }

    @Test
    public void testCalculateBMI() {
        double answer = 703.00 * 120.00 / (71.00*71.00);
        assertEquals(bmi.calculateBMI(71.00,120.00), answer);
    }

    @Test
    public void testDetermineCategory() {
        assertEquals("Normal weight",bmi.determineCategory(79.99, 215.35));
    }

    @Test
    public void testDetermineCategoryObesity() {
        assertEquals("Obesity",bmi.determineCategory(50.00, 200.00));
    }

    @Test
    public void testDetermineCategoryOverweight() {
        assertEquals("Overweight",bmi.determineCategory(65.00, 160.00));
    }

    @Test
    public void testDetermineCategoryNormalWeight() {
        assertEquals("Normal weight",bmi.determineCategory(70.00, 160.00));
    }

    @Test
    public void testDetermineCategoryUnderweight() {
        assertEquals("Underweight",bmi.determineCategory(71.00, 120.00));
    }

}

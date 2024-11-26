package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleIntegrationTest {

    @Test
    void testFactorialWithGCD() {
        // Calculate factorial
        long fact1 = Factorial.factorial(5);  // 120
        long fact2 = Factorial.factorial(6);  // 720

        // Test GCD with factorial results
        int gcd = GCD.gcd((int)fact1, (int)fact2);
        assertEquals(120, gcd, "GCD of factorial(5) and factorial(6) should be 120");
    }

    @Test
    void testPerfectCubeBasic() {
        assertTrue(PerfectCube.isPerfectCube(27), "27 should be a perfect cube");
        assertFalse(PerfectCube.isPerfectCube(26), "26 should not be a perfect cube");
    }
}
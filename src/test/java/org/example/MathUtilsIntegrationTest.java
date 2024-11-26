package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class MathUtilsIntegrationTest {

    /**
     * Integration test combining GCD and Factorial calculations
     * Tests for IPEX mutations by checking parameter order sensitivity
     */
    @Test
    void testGCDWithFactorials() {
        // Calculate GCD of factorials of different numbers
        long fact5 = Factorial.factorial(5);  // 120
        long fact6 = Factorial.factorial(6);  // 720

        // Test parameter order sensitivity (IPEX)
        int gcdForward = GCD.gcd((int)fact5, (int)fact6);
        int gcdReverse = GCD.gcd((int)fact6, (int)fact5);

        assertEquals(gcdForward, gcdReverse,
                "GCD should be independent of parameter order");
        assertEquals(120, gcdForward,
                "GCD of factorial(5) and factorial(6) should be 120");
    }

    /**
     * Integration test combining PerfectCube and FastExponentiation
     * Tests for IREM mutations by verifying return value consistency
     */
    @Test
    void testPerfectCubeWithFastExponentiation() {
        int base = 5;
        // Using fast exponentiation to calculate cube
        long cube = FastExponentiation.fastExponentiation(base, 6, 3);

        // Verify using both perfect cube methods
        assertTrue(PerfectCube.isPerfectCube((int)cube),
                "Number created by fast exponentiation should be recognized as perfect cube");
        assertTrue(PerfectCube.isPerfectCubeMathCbrt((int)cube),
                "Both perfect cube methods should agree on the result");
    }

    /**
     * Integration test combining GCD and GCDRecursion
     * Tests for IMCD mutations by comparing recursive and iterative implementations
     */
    @Test
    void testGCDImplementations() {
        int a = 48, b = 36;

        // Compare results from both implementations
        int iterativeResult = GCD.gcd(a, b);
        int recursiveResult = GCDRecursion.gcd(a, b);

        assertEquals(iterativeResult, recursiveResult,
                "Both GCD implementations should return the same result");
        assertEquals(12, iterativeResult,
                "GCD of 48 and 36 should be 12");
    }

    /**
     * Integration test combining Factorial implementations
     * Tests for IMCD and IREM mutations
     */
    @Test
    void testFactorialImplementations() {
        int n = 5;

        // Compare results from both implementations
        long iterativeResult = Factorial.factorial(n);
        long recursiveResult = FactorialRecursion.factorial(n);

        assertEquals(iterativeResult, recursiveResult,
                "Both factorial implementations should return the same result");
        assertEquals(120, iterativeResult,
                "Factorial of 5 should be 120");
    }

    /**
     * Integration test combining PalindromeNumber with other calculations
     * Tests for complex integration scenarios and IREM mutations
     */
    @Test
    void testPalindromeWithCalculations() {
        // Test if factorial results can create palindrome numbers
        int n = 4;
        long fact = Factorial.factorial(n);  // 24

        // Test perfect cube of factorial
        long cube = FastExponentiation.fastExponentiation((int)fact, 3, Integer.MAX_VALUE);

        // Verify palindrome properties of various derived numbers
        assertThrows(IllegalArgumentException.class,
                () -> PalindromeNumber.isPalindrome((int)-cube),
                "Negative numbers should throw IllegalArgumentException");

        assertTrue(PalindromeNumber.isPalindrome(11),
                "11 should be recognized as a palindrome");
    }

    /**
     * Integration test for error handling across multiple components
     * Tests for IMCD mutations in error handling paths
     */
    @Test
    void testErrorHandlingIntegration() {
        // Test negative number handling across different implementations
        assertThrows(IllegalArgumentException.class,
                () -> Factorial.factorial(-5),
                "Factorial should throw exception for negative numbers");

        assertThrows(ArithmeticException.class,
                () -> GCD.gcd(-10, 5),
                "GCD should throw exception for negative numbers");

        assertThrows(IllegalArgumentException.class,
                () -> FastExponentiation.fastExponentiation(2, 3, 0),
                "FastExponentiation should throw exception for zero modulus");
    }

    /**
     * Integration test for boundary conditions across components
     * Tests for IPEX and IREM mutations at boundary values
     */
    @Test
    void testBoundaryConditionsIntegration() {
        // Test zero handling across different implementations
        assertEquals(1, Factorial.factorial(0));
        assertEquals(1, FactorialRecursion.factorial(0));

        assertEquals(5, GCD.gcd(0, 5));
        assertEquals(5, GCDRecursion.gcd(0, 5));

        assertTrue(PerfectCube.isPerfectCube(0));
        assertTrue(PerfectCube.isPerfectCubeMathCbrt(0));
    }
}
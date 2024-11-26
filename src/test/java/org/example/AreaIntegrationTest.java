package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AreaIntegrationTest {

    /**
     * Integration test combining Area calculations with Factorial
     * Tests for IPEX mutations by verifying area calculations with factorial results
     */
    @Test
    void testAreaWithFactorial() {
        // Calculate factorial to use as input for area calculations
        int n = 3;
        long fact = Factorial.factorial(n);  // 6

        // Use factorial result as input for different area calculations
        double squareArea = Area.surfaceAreaSquare(fact);
        double circleArea = Area.surfaceAreaCircle(fact);

        // Verify results
        assertEquals(36.0, squareArea, 0.001,
                "Square area with side 6 should be 36");
        assertEquals(113.097, circleArea, 0.001,
                "Circle area with radius 6 should be approximately 113.097");
    }

    /**
     * Integration test combining Area calculations with GCD
     * Tests for IMCD mutations by comparing related shape areas
     */
    @Test
    void testAreaWithGCD() {
        // Calculate areas of related rectangles
        double rect1Area = Area.surfaceAreaRectangle(48, 36);
        double rect2Area = Area.surfaceAreaRectangle(36, 48);

        // Their GCD should relate to a smaller rectangle
        int gcd = GCD.gcd(48, 36);  // 12
        double gcdRectArea = Area.surfaceAreaRectangle(gcd, gcd);

        // Verify relationships
        assertEquals(rect1Area, rect2Area,
                "Rectangle area should be independent of parameter order");
        assertEquals(144.0, gcdRectArea, 0.001,
                "Rectangle area with GCD dimensions should be 144");
    }

    /**
     * Integration test combining Area calculations with PerfectCube
     * Tests for IREM mutations by verifying cubic relationships
     */
    @Test
    void testAreaWithPerfectCube() {
        double sideLength = 8;  // Perfect cube

        // Calculate cube surface area
        double cubeArea = Area.surfaceAreaCube(sideLength);

        // Verify the relationship with perfect cube
        assertTrue(PerfectCube.isPerfectCube((int)sideLength),
                "Side length should be a perfect cube");
        assertEquals(384.0, cubeArea, 0.001,
                "Surface area of cube with side 8 should be 384");
    }

    /**
     * Integration test for complex shape calculations
     * Tests for IPEX and IREM mutations in compound calculations
     */
    @Test
    void testComplexShapeCalculations() {
        double radius = 5;
        double height = 10;

        // Calculate areas of related shapes
        double circleArea = Area.surfaceAreaCircle(radius);
        double cylinderArea = Area.surfaceAreaCylinder(radius, height);
        double coneArea = Area.surfaceAreaCone(radius, height);

        // Verify relationships
        assertTrue(cylinderArea > circleArea,
                "Cylinder surface area should be greater than circle area");
        assertTrue(cylinderArea > coneArea,
                "Cylinder surface area should be greater than cone surface area");
    }

    /**
     * Integration test for error handling across shape calculations
     * Tests for IMCD mutations in error handling paths
     */
    @Test
    void testErrorHandlingAcrossShapes() {
        assertThrows(IllegalArgumentException.class,
                () -> Area.surfaceAreaSquare(-5),
                "Square should throw exception for negative side length");

        assertThrows(IllegalArgumentException.class,
                () -> Area.surfaceAreaCircle(0),
                "Circle should throw exception for zero radius");

        assertThrows(IllegalArgumentException.class,
                () -> Area.surfaceAreaTriangle(-3, 4),
                "Triangle should throw exception for negative base");
    }

    /**
     * Integration test combining multiple shape calculations
     * Tests for consistent behavior across different shapes with same dimensions
     */
    @Test
    void testMultipleShapeRelationships() {
        double side = 6;

        // Calculate areas of different shapes with same characteristic length
        double squareArea = Area.surfaceAreaSquare(side);
        double circleArea = Area.surfaceAreaCircle(side);
        double hemisphereArea = Area.surfaceAreaHemisphere(side);

        // Verify mathematical relationships
        assertTrue(circleArea > squareArea,
                "Circle area should be greater than square area for same radius/side");
        assertTrue(hemisphereArea > circleArea,
                "Hemisphere surface area should be greater than circle area for same radius");
    }

    /**
     * Integration test for boundary conditions across shape calculations
     * Tests for IPEX and IREM mutations at boundary values
     */
    @Test
    void testShapeBoundaryConditions() {
        double smallValue = 0.000001;

        // Test very small positive values
        double triangleArea = Area.surfaceAreaTriangle(smallValue, smallValue);
        double parallelogramArea = Area.surfaceAreaParallelogram(smallValue, smallValue);

        // Verify calculations with very small values
        assertTrue(triangleArea > 0,
                "Triangle area should be positive for small positive inputs");
        assertTrue(parallelogramArea > triangleArea,
                "Parallelogram area should be greater than triangle area for same dimensions");
    }

    /**
     * Integration test for trapezium calculations with other shapes
     * Tests for complex parameter handling and relationships
     */
    @Test
    void testTrapeziumRelationships() {
        double base1 = 4;
        double base2 = 6;
        double height = 5;

        // Calculate related areas
        double trapeziumArea = Area.surfaceAreaTrapezium(base1, base2, height);
        double rectangleArea = Area.surfaceAreaRectangle(base2, height);

        // Verify relationships
        assertTrue(trapeziumArea < rectangleArea,
                "Trapezium area should be less than rectangle with larger base");
        assertEquals(25.0, trapeziumArea, 0.001,
                "Trapezium area should be 25 for given dimensions");
    }
}
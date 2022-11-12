 package org.ahmeteminsaglik.fileoperation;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.List;

class CalculateTest {
    Calculate calculate;

    @BeforeAll
    public static void startingTest() {
        System.out.println("@BeforeAll is worked");
    }

    @AfterAll
    public static void finishingTest() {
        System.out.println("@AfterAll is worked");
    }

    @BeforeEach
    void setUp() {
        calculate = new Calculate();
    }

    @AfterEach
    void tearDown() {
        calculate = null;
    }

    @Test
    @EnabledOnOs(value = OS.WINDOWS)
    public void workedOnWindows() {
        System.out.println("tested in WINDOWS");
    }

    @Test
    @RepeatedTest(value = 3, name = "Repeating Test {currentRepetition} of {totalRepetitions}")
    // {currentRepetition} of {totalRepetitions} shows  1 of 3, 1 of 2, 1 of 3
    @DisplayName("Repeat test will work  3 times")
    public void repeatedTest() {
        System.out.println("repeatedTest is worked");

    }

    @Test
    @EnabledOnOs(value = OS.MAC)
    @Disabled
    public void workedOnMAC() {
        System.out.println("tested in MAC");
    }


    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    public void parameterizedTestIntegers(int number) {
        System.out.println("parameterizedTestIntegers is worked : " + number);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ahmet", "emin", "saglik"})
    public void parameterizedTestStrings(String text) {
        System.out.println("parameterizedTestStrings is worked : " + text);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    @MethodSource("getIntegerList")
    //@CsvSource // I did not understand this annotaion
    public void parameterizedIntegerTestMethodSource(int number) {
        System.out.println("parameterizedTestIntegers is worked : " + number);
    }

    private static List<Integer> getIntegerList() {
        return Arrays.asList(11, 22, 33, 44, 55);
    }

  /*  @Test
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void stringTest(String val) {
        System.out.println(val);
    }
*/
    @Test
    @Disabled
    //@DisplayName("Displayname is added dont know what is it ")// update function name in test results
    public void sumTestCase() {
        int expected = 8;
        int a = 3, b = 5;
        int actual = calculate.sum(a, b);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void minusTestCase() {
        int expected = -2;
        int a = 3, b = 5;
        int actual = calculate.minus(a, b);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void divideTestCase() {
        int expected = 0;
        int a = 3, b = 0;
        int actual = calculate.divide(a, b);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void multiplyTestCase() {
        int expected = 15;
        int a = 3, b = 5;
        int actual = calculate.multiply(a, b);
        Assertions.assertEquals(expected, actual);
    }

}
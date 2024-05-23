package ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MultipathTest {

    MultipathExample multipathExample;

    @BeforeEach
    public void setUp() {
        multipathExample = new MultipathExample();
    }

    @Test
    public void C1_TA01() {
        int a = 6;
        int b = 6;
        int c = 5;
        int expectedResult = 17;

        int result = multipathExample.multiPath1(a, b, c);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void C2_TA02() {
        int a = 4;
        int b = 4;
        int c = 5;
        int expectedResult = 5;

        int result = multipathExample.multiPath1(a, b, c);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void Extra_TC01() {
        int a = 3;
        int b = 6;
        int c = 2;
        int expectedResult = 8;

        int result = multipathExample.multiPath1(a, b, c);

        Assertions.assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("casosDePruebaTD01")
    public void Extra_TD01(int a, int b, int c, int expectedResult) {
        int result = multipathExample.multiPath2(a, b, c);

        Assertions.assertEquals(expectedResult, result);
    }

    public static Stream<Arguments> casosDePruebaTD01() {
        return Stream.of(
            Arguments.of(5, 2, 6, 8),
            Arguments.of(6, 7, 10, 17),
            Arguments.of(6, 4, 4, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("casosDePruebaTE01")
    public void Extra_TE01(int a, int b, int c, int expectedResult) {
        int result = multipathExample.multiPath3(a, b, c);
    }

    public static Stream<Arguments> casosDePruebaTE01() {
        return Stream.of(
                Arguments.of(6, 4, 5, 5),
                Arguments.of(6, 6, 6, 12),
                Arguments.of(4, 4, 6, 10),
                Arguments.of(4, 6, 5, 11)
        );
    }

}

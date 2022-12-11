package racingcar.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

class StandardMovingCarRandomNumberGeneratorTest {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 9;

    @Nested
    @DisplayName("generator 메소드는")
    class DescribeGeneratorMethodTest {

        private final StandardMovingCarRandomNumberGenerator generator =
                new StandardMovingCarRandomNumberGenerator(MIN_VALUE, MAX_VALUE);

        @Nested
        @DisplayName("만약 호출하면")
        class ContextWithoutParameterTest {

            @RepeatedTest(10)
            @DisplayName("지정한 범위 내의 숫자를 무작위로 반환한다")
            void it_returns_randomNumber() {
                int actual = generator.generate();

                assertThat(actual)
                        .isGreaterThanOrEqualTo(MIN_VALUE)
                        .isLessThanOrEqualTo(MAX_VALUE);
            }
        }
    }
}
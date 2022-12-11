package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.helper.stub.StubCarNumberGenerator;
import racingcar.utils.MovingCarNumberGenerator;

class CarTest {

    @Nested
    @DisplayName("String name을 매개변수로 받는 생성자는")
    class DescribeStringConstructorTest {

        @Nested
        @DisplayName("만약 유효한 자동차 이름이 주어지면")
        class ContextWithNameTest {

            @ParameterizedTest
            @ValueSource(strings = {"a", "b", "+", " ", "@"})
            @DisplayName("name을 초기화한 Car를 생성한다")
            void it_returns_car(String name) {
                assertThatCode(() -> new Car(name)).doesNotThrowAnyException();
            }
        }

        @Nested
        @DisplayName("만약 유효하지 않은 자동차 이름이 주어지면")
        class ContextWithInvalidNameTest {

            @Test
            @DisplayName("IllegalArgumentException 예외가 발생한다")
            void it_throws_exception() {
                assertThatThrownBy(() -> new Car(""))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("유효하지 못한 자동차 이름입니다.");
            }
        }
    }

    @Nested
    @DisplayName("racing 메소드는")
    class DescribeRacingMethodTest extends DefaultCarField {

        @Nested
        @DisplayName("만약 자동차가 전진하기 위해 무작위 숫자를 생성하는 generator가 주어지면")
        class ContextWithGeneratorTest {

            @ParameterizedTest
            @MethodSource("racingcar.domain.car.arguments.CarTestArgument#racingMethodTestArgument")
            @DisplayName("주어진 숫자에 따라 자동차의 위치를 변경한다")
            void it_process_carPosition(List<Integer> generatorNumbers, String expected) {
                MovingCarNumberGenerator generator = new StubCarNumberGenerator(generatorNumbers);

                int count = 0;
                while (count++ < generatorNumbers.size()) {
                    car.racing(generator);
                }

                assertThat(car.toString()).contains(expected);
            }
        }
    }

    @Nested
    @DisplayName("isSamePosition 메소드는")
    class DescribeIsSamePositionMethodTest extends DefaultCarField {

        @Nested
        @DisplayName("만약 Car가 주어지면")
        class ContextWithCarTest {

            @ParameterizedTest
            @MethodSource("racingcar.domain.car.arguments.CarTestArgument#isSamePositionMethodTestArgument")
            @DisplayName("주어진 Car의 position과 동일한지 유무를 반환한다")
            void it_returns_samePosition(List<Integer> generatorNumbers, boolean expected) {
                Car otherCar = new Car("other car");
                MovingCarNumberGenerator generator = new StubCarNumberGenerator(generatorNumbers);

                int count = 0;
                while (count++ < generatorNumbers.size()) {
                    otherCar.racing(generator);
                }

                boolean actual = car.isSamePosition(otherCar);
                assertThat(actual).isSameAs(expected);
            }
        }
    }

    @Nested
    @DisplayName("compareTo 메소드는")
    class DescribeCompareToMethodTest extends DefaultCarField {

        @Nested
        @DisplayName("만약 비교할 Car가 주어지면")
        class ContextWithCarTest {

            @ParameterizedTest
            @MethodSource("racingcar.domain.car.arguments.CarTestArgument#compareToMethodTestArgument")
            @DisplayName("두 Car의 position을 비교해 결과를 반환한다")
            void it_returns_compare(List<Integer> generatorNumbers, int expected) {
                Car otherCar = new Car("other car");
                MovingCarNumberGenerator generator = new StubCarNumberGenerator(generatorNumbers);

                int count = 0;
                while (count++ < generatorNumbers.size()) {
                    otherCar.racing(generator);
                }

                int actual = car.compareTo(otherCar);
                assertThat(actual).isSameAs(expected);
            }
        }
    }

    @Nested
    @DisplayName("equals 메소드는")
    class DescribeEqualsMethodTest extends DefaultCarField {

        @Nested
        @DisplayName("만약 Car가 주어지면")
        class ContextWithCarTest {

            @ParameterizedTest
            @CsvSource(
                    value = {
                        "car:true",
                        "other car:false"
                    },
                    delimiter = ':'
            )
            @DisplayName("이름이 같은지 유무를 반환한다")
            void it_returns(String name, boolean expected) {
                Car otherCar = new Car(name);

                boolean actual = car.equals(otherCar);

                assertThat(actual).isSameAs(expected);
            }
        }
    }

    @Nested
    @DisplayName("toString 메소드는")
    class DescribeToStringMethodTest extends DefaultCarField {

        @Nested
        @DisplayName("만약 호출하면")
        class ContextWithoutParameterTest {

            @ParameterizedTest
            @MethodSource("racingcar.domain.car.arguments.CarTestArgument#toStringMethodTestArgument")
            @DisplayName("현재 자동차의 상태를 반환한다")
            void it_returns_carState(List<Integer> generatorNumbers, String expected) {
                MovingCarNumberGenerator generator = new StubCarNumberGenerator(generatorNumbers);
                int count = 0;

                while (count++ < generatorNumbers.size()) {
                    car.racing(generator);
                }
                assertThat(car.toString()).contains(expected);
            }
        }
    }

    private static class DefaultCarField {

        protected final Car car = new Car("car");
    }
}
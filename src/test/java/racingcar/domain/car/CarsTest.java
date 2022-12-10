package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.racing.RacingRoundState;
import racingcar.helper.stub.StubCarNumberGenerator;
import racingcar.utils.MovingCarNumberGenerator;

class CarsTest {

    @Nested
    @DisplayName("String carNames, MovingCarNumberGenerator generator를 매개변수로 받는 생성자는")
    class DescribeStringAndGeneratorConstructorTest extends DefaultGeneratorField {

        @Nested
        @DisplayName("만약 유효한 carNames와 generator가 주어지면")
        class ContextWithCarNamesAndGeneratorTest {

            @ParameterizedTest
            @ValueSource(strings = {"a", "a,b", "a,b,c"})
            @DisplayName("racingCars와 generator를 초기화한 Cars를 생성한다")
            void it_returns_cars(String carNames) {
                assertThatCode(() -> new Cars(carNames, generator)).doesNotThrowAnyException();
            }
        }

        @Nested
        @DisplayName("만약 중복된 carNames와 generator가 주어지면")
        class ContextWithDuplicateCarNamesAndGeneratorTest {

            @ParameterizedTest
            @ValueSource(strings = {"a,a", "b,b"})
            @DisplayName("IllegalArgumentException 예외가 발생한다")
            void it_returns_cars(String invalidCarNames) {
                assertThatThrownBy(() -> new Cars(invalidCarNames, generator))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("중복된 이름이 존재합니다.");
            }
        }

        @Nested
        @DisplayName("만약 유효하지 않은 carNames와 generator가 주어지면")
        class ContextWithInvalidCarNamesAndGeneratorTest {

            @ParameterizedTest
            @ValueSource(strings = {"a,", ",a", ",a,"})
            @DisplayName("IllegalArgumentException 예외가 발생한다")
            void it_returns_cars(String invalidCarNames) {
                assertThatThrownBy(() -> new Cars(invalidCarNames, generator))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("유효하지 못한 자동차 이름입니다.");
            }
        }
    }

    @Nested
    @DisplayName("racing 메소드는")
    class DescribeRacingMethodTest extends DefaultCarsField {

        @Nested
        @DisplayName("만약 호출하면")
        class ContextWithoutParameterTest {

            @RepeatedTest(10)
            @DisplayName("한 번의 레이싱 round를 처리한다")
            void it_process_racing() {
                assertThatCode(() -> cars.racing()).doesNotThrowAnyException();
            }
        }
    }

    @Nested
    @DisplayName("getRacingRoundState 메소드는")
    class DescribeGetRacingRoundStateMethodTest extends DefaultCarsField {

        @Nested
        @DisplayName("만약 호출하면")
        class ContextWithoutParameterTest {

            @Test
            @DisplayName("RacingRoundState를 반환한다")
            void it_returns_racingRoundState() {
                RacingRoundState racingRoundState = cars.getRacingRoundState();

                assertThat(racingRoundState.toString())
                        .contains("a: ")
                        .contains("b: ")
                        .contains("c: ");
            }
        }
    }

    @Nested
    @DisplayName("winningCars 메소드는")
    class DescribeWinningCarsMehtodTest extends DefaultCarsField {

        @Nested
        @DisplayName("만약 호출하면")
        class ContextWithoutParameterTest {

            @Test
            @DisplayName("레이싱에서 승리한 자동차 이름을 반환한다")
            void it_returns_winningCarsName() {
                List<String> actual = cars.winningCars();

                assertThat(actual)
                        .contains("a")
                        .contains("b")
                        .contains("c");
            }
        }
    }

    private static class DefaultGeneratorField {

        protected MovingCarNumberGenerator generator = new StubCarNumberGenerator(Arrays.asList(0, 5));
    }

    private static class DefaultCarsField extends DefaultGeneratorField {

        protected Cars cars = new Cars("a,b,c", generator);
    }
}
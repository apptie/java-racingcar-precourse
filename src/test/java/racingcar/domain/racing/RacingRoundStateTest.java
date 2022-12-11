package racingcar.domain.racing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class RacingRoundStateTest {

    @Nested
    @DisplayName("List<String> carsStatePerRound를 매개변수로 받는 생성자는")
    class DescribeWithListConstructorTest {

        @Nested
        @DisplayName("만약 하나의 라운드에 대한 각 자동차의 상태를 전달하면")
        class ContextCarsStatePerRoundTest {

            @ParameterizedTest
            @MethodSource("racingcar.domain.racing.arguments.RacingRoundStateTestArgument#carsStatePerRoundTest")
            @DisplayName("carsStatePerRound를 초기화한 RacingRoundState를 생성한다")
            void it_returns_racingRound(List<String> carsStatePerRound) {
                assertThatCode(() -> new RacingRoundState(carsStatePerRound)).doesNotThrowAnyException();
            }
        }
    }

    @Nested
    @DisplayName("toString 메소드는")
    class DescribeToStringMethodTest {

        @Nested
        @DisplayName("만약 호출하면")
        class ContextWithoutParameterTest {

            @ParameterizedTest
            @MethodSource("racingcar.domain.racing.arguments.RacingRoundStateTestArgument#carsStatePerRoundTest")
            @DisplayName("carsStatePerRound를 반환한다")
            void it_returns_carsStatePerRound(List<String> carsStatePerRound) {
                RacingRoundState racingRoundState = new RacingRoundState(carsStatePerRound);

                String actual = racingRoundState.toString();

                carsStatePerRound.forEach(carState -> assertThat(actual).contains(carState));
            }
        }
    }
}
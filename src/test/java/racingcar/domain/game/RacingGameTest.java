package racingcar.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.racing.RacingRoundState;
import racingcar.dto.output.PrintRacingResultDto;
import racingcar.helper.common.DefaultGeneratorField;

class RacingGameTest {

    @Nested
    @DisplayName("String carNames, MovingCarNumberGenerator generator를 매개변수로 받는 생성자는")
    class DescribeStringAndGeneratorMethodTest extends DefaultGeneratorField {

        @Nested
        @DisplayName("만약 유효한 carNames와 generator를 전달하면")
        class ContextWithCarNamesAndGeneratorTest {

            @ParameterizedTest
            @ValueSource(strings = {"a", "a,b", "a,b,c"})
            @DisplayName("Cars를 초기화한 RacingGame을 생성한다")
            void it_returns_racingGame(String carsName) {
                assertThatCode(() -> new RacingGame(carsName, generator)).doesNotThrowAnyException();
            }
        }

        @Nested
        @DisplayName("만약 유효하지 않은 carNames과 generator를 전달하면")
        class ContextWithInvalidCarNamesAndGeneratorTest {

            @ParameterizedTest
            @ValueSource(strings = {"a,", ",a,", ",a"})
            @DisplayName("IllegalArgumentException 예외를 반환한다")
            void it_throws_exception(String invalidCarsName) {
                assertThatCode(() -> new RacingGame(invalidCarsName, generator))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("유효하지 못한 자동차 이름입니다.");
            }
        }

        @Nested
        @DisplayName("만약 중복된 carNames과 generator를 전달하면")
        class ContextWithDuplicateCarNamesAndGeneratorTest {

            @ParameterizedTest
            @ValueSource(strings = {"a,a", "a,a,b", "b,a,b"})
            @DisplayName("IllegalArgumentException 예외를 반환한다")
            void it_throws_exception(String invalidCarsName) {
                assertThatCode(() -> new RacingGame(invalidCarsName, generator))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("중복된 이름이 존재합니다.");
            }
        }
    }

    @Nested
    @DisplayName("initRacingRound 메소드는")
    class DescribeInitRacingRoundMethodTest extends DefaultRacingGameField {

        @Nested
        @DisplayName("만약 레이스를 시도하는 횟수 totalRound가 주어지면")
        class ContextWithTotalRoundTest {

            @ParameterizedTest
            @ValueSource(ints = {1, 2, 3, 4, 5})
            @DisplayName("RacingRound를 초기화한다")
            void it_init_racingRound(int totalRound) {
                assertThatCode(() -> racingGame.initRacingRound(totalRound)).doesNotThrowAnyException();
            }
        }

        @Nested
        @DisplayName("만약 유효하지 않은 totalRound가 주어지면")
        class ContextWithInvalidTotalRoundTest {

            @ParameterizedTest
            @ValueSource(ints = {0, -1})
            @DisplayName("IllegalArgumentException 예외가 발생한다")
            void it_init_racingRound(int totalRound) {
                assertThatThrownBy(() -> racingGame.initRacingRound(totalRound))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("시도 횟수는 0 이상이어야 합니다.");
            }
        }
    }

    @Nested
    @DisplayName("racing 메소드는")
    class DescribeRacingMethodTest extends DefaultRacingGameField {

        @Nested
        @DisplayName("만약 호출하면")
        class ContextWithoutParameterTest {

            @Test
            @DisplayName("레이싱을 진행하고 각 라운드 별 자동차 상태와 우승한 자동차 이름을 반환한다")
            void it_returns_racingRoundStates() {
                racingGame.initRacingRound(2);
                PrintRacingResultDto dto = racingGame.racing();

                List<RacingRoundState> racingRoundStates = dto.getRacingRoundStates();
                List<String> winningCarsName = dto.getWinningCarsName();

                racingRoundStates.forEach(racingRoundState ->
                    assertThat(racingRoundState.toString()).contains("a: ").contains("b: ").contains("c: "));
                assertThat(winningCarsName).contains("a").contains("b").contains("c");
            }
        }
    }

    private static class DefaultRacingGameField extends DefaultGeneratorField {

        protected final RacingGame racingGame = new RacingGame("a,b,c", generator);
    }
}
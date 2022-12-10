package racingcar.domain.racing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RacingRoundTest {

    @Nested
    @DisplayName("int totalRound를 매개변수로 받는 생성자는")
    class DescribeIntConstructorTest {

        @Nested
        @DisplayName("만약 유효한 totalRound를 전달하면")
        class ContextWithTotalRoundTest {

            @ParameterizedTest
            @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
            @DisplayName("totalRound와 nowRound를 초기화한 RacingRound를 생성한다")
            void it_returns_racingRound(int totalRound) {
                assertThatCode(() -> new RacingRound(totalRound)).doesNotThrowAnyException();
            }
        }

        @Nested
        @DisplayName("만약 0 이하의 totalRound를 전달하면")
        class ContextWithInvalidTotalRoundTest {

            @ParameterizedTest
            @ValueSource(ints = {0, -1, -2})
            @DisplayName("IllegalArgumentException 예외가 발생한다")
            void it_returns_racingRound(int invalidTotalRound) {
                assertThatCode(() -> new RacingRound(invalidTotalRound))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("시도 횟수는 0 이상이어야 합니다.");
            }
        }
    }

    @Nested
    @DisplayName("isContinueRacing 메소드는")
    class DescribeIsContinueRacingMethodTest {

        @Nested
        @DisplayName("만약 호출하면")
        class ContextWithoutParameterTest {

            @Test
            @DisplayName("진행해야 하는 레이싱 라운드가 있는지 여부를 반환한다")
            void it_returns_continueRacing() {
                int totalRound = 5;

                RacingRound racingRound = new RacingRound(totalRound);

                int index = 0;
                while (index++ < totalRound) {
                    assertThat(racingRound.isContinueRacing()).isTrue();
                }
                assertThat(racingRound.isContinueRacing()).isFalse();
            }
        }
    }
}
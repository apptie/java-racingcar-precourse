package racingcar.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameStatusTest {

    @Nested
    @DisplayName("runnable 메소드는")
    class DescribeRunnableMethodTest {

        @Nested
        @DisplayName("만약 호출하면")
        class ContextWithoutParameterTest {

            @ParameterizedTest
            @CsvSource(
                    value = {
                        "INPUT_CAR_NAMES:true",
                        "INPUT_RACING_ROUND:true",
                        "RACING:true",
                        "RACING_EXIT:false"
                    },
                    delimiter = ':'
            )
            @DisplayName("애플리케이션이 계속 실행될지 여부를 반환한다")
            void it_returns_runnable(GameStatus gameStatus, boolean expected) {
                assertThat(gameStatus.runnable()).isSameAs(expected);
            }
        }
    }
}
package racingcar.domain.racing.arguments;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public final class RacingRoundStateTestArgument {

    private RacingRoundStateTestArgument() {
    }

    static Stream<Arguments> carsStatePerRoundTest() {
        return Stream.of(
                Arguments.of(Arrays.asList("a: --", "b: -", "c: ---")),
                Arguments.of(Arrays.asList("a: --", "b: ", "c: ---")),
                Arguments.of(Arrays.asList("a: --", "b: ---", "c: --"))
        );
    }
}

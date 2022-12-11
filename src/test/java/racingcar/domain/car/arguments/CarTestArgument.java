package racingcar.domain.car.arguments;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public final class CarTestArgument {

    private CarTestArgument() {
    }

    static Stream<Arguments> racingMethodTestArgument() {
        return Stream.of(
                Arguments.of(Arrays.asList(0, 5), "-"),
                Arguments.of(Arrays.asList(5, 5), "--")
        );
    }

    static Stream<Arguments> isSamePositionMethodTestArgument() {
        return Stream.of(
                Arguments.of(Arrays.asList(), true),
                Arguments.of(Arrays.asList(5, 5), false)
        );
    }

    static Stream<Arguments> compareToMethodTestArgument() {
        return Stream.of(
                Arguments.of(Arrays.asList(), 0),
                Arguments.of(Arrays.asList(5), 1)
        );
    }

    static Stream<Arguments> toStringMethodTestArgument() {
        return Stream.of(
            Arguments.of(Arrays.asList(0, 5), "car: -"),
            Arguments.of(Arrays.asList(5, 5), "car: --")
        );
    }
}

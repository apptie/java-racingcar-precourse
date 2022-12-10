package racingcar.helper.common;

import java.util.Arrays;
import racingcar.helper.stub.StubCarNumberGenerator;
import racingcar.utils.MovingCarNumberGenerator;

public class DefaultGeneratorField {

    protected MovingCarNumberGenerator generator = new StubCarNumberGenerator(Arrays.asList(0, 5));
}

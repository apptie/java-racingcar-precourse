package racingcar.helper.stub;

import java.util.List;
import racingcar.utils.MovingCarNumberGenerator;

public class StubCarNumberGenerator implements MovingCarNumberGenerator {

    private final List<Integer> generateNumbers;
    private int index = 0;

    public StubCarNumberGenerator(List<Integer> generateNumbers) {
        this.generateNumbers = generateNumbers;
    }

    @Override
    public int generate() {
        if (index == generateNumbers.size()) {
            index = 0;
        }
        return generateNumbers.get(index++);
    }
}

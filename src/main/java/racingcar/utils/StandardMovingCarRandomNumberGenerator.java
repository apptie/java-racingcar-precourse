package racingcar.utils;

import camp.nextstep.edu.missionutils.Randoms;

public class StandardMovingCarRandomNumberGenerator implements MovingCarNumberGenerator {

    private final int min;
    private final int max;

    public StandardMovingCarRandomNumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public int generate() {
        return Randoms.pickNumberInRange(min, max);
    }
}

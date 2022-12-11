package racingcar.domain.racing;

public class RacingRound {

    private static final String EXCEPTION_RANGE_MESSAGE = "시도 횟수는 0 이상이어야 합니다.";

    private final int totalRound;
    private int nowRound = 0;

    public RacingRound(int totalRound) {
        validateTotalRound(totalRound);

        this.totalRound = totalRound;
    }

    private void validateTotalRound(int totalRound) {
        if (totalRound <= 0) {
            throw new IllegalArgumentException(EXCEPTION_RANGE_MESSAGE);
        }
    }

    public boolean isContinueRacing() {
        return ++nowRound <= totalRound;
    }
}

package racingcar.domain.racing;

import java.util.List;

public class RacingRoundState {

    private static final String LINE_FEED = "\n";

    private final List<String> carsStatePerRound;

    public RacingRoundState(List<String> carsStatePerRound) {
        this.carsStatePerRound = carsStatePerRound;
    }

    @Override
    public String toString() {
        return String.join(LINE_FEED, carsStatePerRound);
    }
}

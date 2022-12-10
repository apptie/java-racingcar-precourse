package racingcar.dto.output;

import java.util.List;
import racingcar.domain.racing.RacingRoundState;

public class PrintRacingResultDto {

    private final List<RacingRoundState> racingRoundStates;
    private final List<String> winningCarsName;

    public PrintRacingResultDto(List<RacingRoundState> racingRoundStates, List<String> winningCarsName) {
        this.racingRoundStates = racingRoundStates;
        this.winningCarsName = winningCarsName;
    }

    public List<RacingRoundState> getRacingRoundStates() {
        return racingRoundStates;
    }

    public List<String> getWinningCarsName() {
        return winningCarsName;
    }
}

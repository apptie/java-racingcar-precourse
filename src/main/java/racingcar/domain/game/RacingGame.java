package racingcar.domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.domain.car.Cars;
import racingcar.domain.racing.RacingRound;
import racingcar.domain.racing.RacingRoundState;
import racingcar.dto.output.PrintRacingResultDto;
import racingcar.utils.MovingCarNumberGenerator;

public class RacingGame {

    private final Cars racingCars;
    private RacingRound racingRound;

    public RacingGame(String carNames, MovingCarNumberGenerator generator) {
        this.racingCars = new Cars(carNames, generator);
    }

    public void initRacingRound(int totalRound) {
        this.racingRound = new RacingRound(totalRound);
    }

    public PrintRacingResultDto racing() {
        List<RacingRoundState> racingRoundStates = processRacingRound();
        List<String> winningCarsName = racingCars.getWinningCars();

        return new PrintRacingResultDto(racingRoundStates, winningCarsName);
    }

    private List<RacingRoundState> processRacingRound() {
        List<RacingRoundState> racingRoundStates = new ArrayList<>();

        while (racingRound.isContinueRacing()) {
            racingCars.racing();
            racingRoundStates.add(racingCars.getRacingRoundState());
        }

        return Collections.unmodifiableList(racingRoundStates);
    }

    public List<String> getWinningCarsName() {
        return racingCars.getWinningCars();
    }
}

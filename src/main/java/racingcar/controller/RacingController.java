package racingcar.controller;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;
import racingcar.domain.game.GameStatus;
import racingcar.domain.game.RacingGame;
import racingcar.dto.input.ReadRacingCarsNameDto;
import racingcar.dto.input.ReadTotalRoundDto;
import racingcar.utils.MovingCarNumberGenerator;
import racingcar.utils.StandardMovingCarRandomNumberGenerator;
import racingcar.view.IOViewResolver;

public class RacingController {

    private static final int MOVE_MIN_VALUE = 0;
    private static final int MOVE_MAX_VALUE = 9;

    private final IOViewResolver ioViewResolver;
    private RacingGame racingGame;
    private Map<GameStatus, Supplier<GameStatus>> gameStatusMappings = new EnumMap<>(GameStatus.class);

    public RacingController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
        initGameMappings();
    }

    private void initGameMappings() {
        gameStatusMappings.put(GameStatus.INPUT_CAR_NAMES, this::initRacingCars);
        gameStatusMappings.put(GameStatus.INPUT_RACING_ROUND, this::initRacingRound);
        gameStatusMappings.put(GameStatus.RACING, this::racing);
    }

    public GameStatus process(GameStatus gameStatus) {
        return gameStatusMappings.get(gameStatus).get();
    }

    private GameStatus initRacingCars() {
        ReadRacingCarsNameDto dto = ioViewResolver.resolveInputView(ReadRacingCarsNameDto.class);
        MovingCarNumberGenerator generator = new StandardMovingCarRandomNumberGenerator(MOVE_MIN_VALUE, MOVE_MAX_VALUE);
        racingGame = new RacingGame(dto.getRacingCarsName(), generator);

        return GameStatus.INPUT_RACING_ROUND;
    }

    private GameStatus initRacingRound() {
        ReadTotalRoundDto dto = ioViewResolver.resolveInputView(ReadTotalRoundDto.class);

        racingGame.initRacingRound(dto.getTotalRound());
        return GameStatus.RACING;
    }

    private GameStatus racing() {
        ioViewResolver.resolveOutputView(racingGame.racing());
        return GameStatus.RACING_EXIT;
    }
}

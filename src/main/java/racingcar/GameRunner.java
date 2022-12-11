package racingcar;

import racingcar.controller.RacingController;
import racingcar.domain.game.GameStatus;
import racingcar.view.IOViewResolver;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public final class GameRunner {

    private GameRunner() {
    }

    public static void run() {
        IOViewResolver ioViewResolver = new IOViewResolver(InputView.getInstance(), OutputView.getInstance());
        RacingController controller = new RacingController(ioViewResolver);
        GameStatus gameStatus = GameStatus.INPUT_CAR_NAMES;

        while (gameStatus.runnable()) {
            gameStatus = controller.process(gameStatus);
        }
    }
}

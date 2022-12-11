package racingcar.domain.game;

public enum GameStatus {
    INPUT_CAR_NAMES,
    INPUT_RACING_ROUND,
    RACING,
    RACING_EXIT;

    public boolean runnable() {
        return this != RACING_EXIT;
    }
}

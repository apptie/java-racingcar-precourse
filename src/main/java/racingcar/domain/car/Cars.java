package racingcar.domain.car;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.racing.RacingRoundState;
import racingcar.utils.MovingCarNumberGenerator;

public class Cars {

    private static final String SEPARATOR = ",";

    private static final String EXCEPTION_DUPLICATE_MESSAGE = "중복된 이름이 존재합니다.";
    private static final String EXCEPTION_NOT_REGISTRY_CARS_EXCEPTION = "레이싱에 참여하는 자동차가 등록되지 않았습니다.";

    private final List<Car> racingCars;
    private final MovingCarNumberGenerator generator;

    public Cars(String carNames, MovingCarNumberGenerator generator) {
        String[] names = carNames.split(SEPARATOR, -1);
        List<Car> racingCars = Arrays.stream(names).map(Car::new).distinct().collect(Collectors.toList());

        validateCars(names, racingCars);

        this.racingCars = racingCars;
        this.generator = generator;
    }

    private void validateCars(String[] names, List<Car> racingCars) {
        if (names.length != racingCars.size()) {
            throw new IllegalArgumentException(EXCEPTION_DUPLICATE_MESSAGE);
        }
    }

    public void racing() {
        racingCars.forEach(car -> car.racing(generator));
    }

    public RacingRoundState getRacingRoundState() {
        return new RacingRoundState(racingCars.stream().map(Car::toString).collect(Collectors.toList()));
    }

    public List<String> winningCars() {
        Car farthestCar = findFarthestPositionCar();

        return racingCars.stream()
                .filter(car -> car.isSamePosition(farthestCar))
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private Car findFarthestPositionCar() {
        return racingCars
                .stream()
                .sorted()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(EXCEPTION_NOT_REGISTRY_CARS_EXCEPTION));
    }
}

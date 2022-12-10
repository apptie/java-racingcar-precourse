package racingcar.domain.car;

import java.util.Objects;
import racingcar.utils.MovingCarNumberGenerator;

public class Car implements Comparable<Car> {

    private static final int STANDARD_MOVING = 4;

    private static final String SEPARATOR = ": ";
    private static final String POSITION_LOG = "-";

    private static final String EXCEPTION_LENGTH_MESSAGE = "유효하지 못한 자동차 이름입니다.";

    private final String name;
    private int position = 0;

    public Car(String name) {
        if (name.length() <= 0) {
            throw new IllegalArgumentException(EXCEPTION_LENGTH_MESSAGE);
        }
        this.name = name;
    }

    public void racing(MovingCarNumberGenerator generator) {
        if (generator.generate() >= STANDARD_MOVING) {
            position++;
        }
    }

    public boolean isSamePosition(Car targetCar) {
        return this.position == targetCar.position;
    }

    @Override
    public int compareTo(Car target) {
        return target.position - this.position;
    }

    @Override
    public boolean equals(Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Car)) {
            return false;
        }
        Car targetCar = (Car) target;
        return Objects.equals(this.name, targetCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder stateBuilder = new StringBuilder(name).append(SEPARATOR);

        appendPositionLog(stateBuilder);
        return stateBuilder.toString();
    }

    private void appendPositionLog(StringBuilder carStateBuilder) {
        int count = 0;

        while (count++ < position) {
            carStateBuilder.append(POSITION_LOG);
        }
    }

    public String getName() {
        return name;
    }
}

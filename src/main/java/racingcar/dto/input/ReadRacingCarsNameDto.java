package racingcar.dto.input;

public class ReadRacingCarsNameDto {

    private final String racingCarsName;

    public ReadRacingCarsNameDto(String racingCarsName) {
        this.racingCarsName = racingCarsName;
    }

    public String getRacingCarsName() {
        return racingCarsName;
    }
}

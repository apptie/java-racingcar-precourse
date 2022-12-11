package racingcar.dto.input;

public class ReadTotalRoundDto {

    private final int totalRound;

    public ReadTotalRoundDto(int totalRound) {
        this.totalRound = totalRound;
    }

    public int getTotalRound() {
        return totalRound;
    }
}

package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.dto.input.ReadRacingCarsNameDto;
import racingcar.dto.input.ReadTotalRoundDto;

public final class InputView {

    private static final String EXCEPTION_NUMBER_FORMAT_MESSAGE = "횟수는 숫자 또는 -2147483648 ~ 2147483647 사이의 값이여야만 합니다.";

    private static final String CARS_NAME_SEPARATOR = ",";

    private InputView() {
    }

    private static class InputViewSingletonHelper {
        private static final InputView INPUT_VIEW = new InputView();
    }

    public static InputView getInstance() {
        return InputViewSingletonHelper.INPUT_VIEW;
    }

    public ReadRacingCarsNameDto readRacingCarsName() {
        print(InputViewMessage.GUIDE_INPUT_CARS_NAME.message);
        String racingCarsName = Console.readLine();

        validateRacingCarsNameFormat(racingCarsName);
        return new ReadRacingCarsNameDto(racingCarsName);
    }

    private void validateRacingCarsNameFormat(String racingCarsName) {
        if (!racingCarsName.contains(CARS_NAME_SEPARATOR)) {
            throw new IllegalArgumentException();
        }
    }

    public ReadTotalRoundDto readTotalRound() {
        print(InputViewMessage.GUIDE_INPUT_ROUND.message);
        String totalRound = Console.readLine();

        try {
            return new ReadTotalRoundDto(Integer.parseInt(totalRound));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(EXCEPTION_NUMBER_FORMAT_MESSAGE, e);
        }
    }

    private void print(String message) {
        System.out.println(message);
    }

    private enum InputViewMessage {
        GUIDE_INPUT_CARS_NAME("경주할 자동차 이름을 입력하세요.(이름은 %s 기준으로 구분)", MessageConst.SEPARATOR),
        GUIDE_INPUT_ROUND("시도할 회수는 몇회인가요?");

        private final String message;

        InputViewMessage(String baseMessage, Object... replaces) {
            message = String.format(baseMessage, replaces);
        }

        private static class MessageConst {
            private static final String SEPARATOR = "쉼표(,)";
        }
    }
}

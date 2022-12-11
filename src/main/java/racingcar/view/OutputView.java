package racingcar.view;

import racingcar.dto.output.PrintRacingResultDto;

public final class OutputView {

    private OutputView() {
    }

    private static class OutputViewSingletonHelper {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }

    public static OutputView getInstance() {
        return OutputViewSingletonHelper.OUTPUT_VIEW;
    }

    public void printRacingResult(PrintRacingResultDto dto) {
        print(OutputViewMessage.GUIDE_RACING_RESULT.findFullMessage());
        dto.getRacingRoundStates().forEach(racingRoundState -> print(racingRoundState.toString()));
        print(OutputViewMessage.WINNING_CARS_NAME_FORMAT.findFullMessage(dto.getWinningCarsName()));
    }

    private void print(String message) {
        System.out.println(message);
    }

    private enum OutputViewMessage {
        GUIDE_RACING_RESULT("실행 결과"),
        WINNING_CARS_NAME_FORMAT("최종 우승자 : %s");

        private final String message;

        OutputViewMessage(String message) {
            this.message = message;
        }

        public String findFullMessage(Object... replaces) {
            return String.format(message, replaces);
        }
    }
}

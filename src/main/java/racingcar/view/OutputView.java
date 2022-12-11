package racingcar.view;

import racingcar.dto.output.PrintRacingResultDto;

public final class OutputView {

    private static final String LINE_FEED = "";
    private static final String CARS_NAME_SEPARATOR = ",";

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
        dto.getRacingRoundStates().forEach(racingRoundState -> {
            print(racingRoundState.toString());
            print(LINE_FEED);
        });
        print(OutputViewMessage.WINNING_CARS_NAME_FORMAT.findFullMessage(
                String.join(CARS_NAME_SEPARATOR, dto.getWinningCarsName())));
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

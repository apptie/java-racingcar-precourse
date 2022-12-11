package racingcar.view;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import racingcar.dto.input.ReadRacingCarsNameDto;
import racingcar.dto.input.ReadTotalRoundDto;
import racingcar.dto.output.PrintRacingResultDto;
import racingcar.view.exception.NotFoundViewException;

public class IOViewResolver {

    private final Map<Class<?>, Consumer<Object>> outputViewMappings = new HashMap<>();
    private final Map<Class<?>, Supplier<Object>> inputViewMappings = new HashMap<>();

    public IOViewResolver(InputView inputView, OutputView outputView) {
        initInputViewMappings(inputView);
        initOutputViewMappings(outputView);
    }

    private void initInputViewMappings(InputView inputView) {
        inputViewMappings.put(ReadRacingCarsNameDto.class, inputView::readRacingCarsName);
        inputViewMappings.put(ReadTotalRoundDto.class, inputView::readTotalRound);
    }

    private void initOutputViewMappings(OutputView outputView) {
        outputViewMappings.put(PrintRacingResultDto.class, dto ->
            outputView.printRacingResult((PrintRacingResultDto) dto));
    }

    public <T> T resolveInputView(Class<T> type) {
        try {
            return type.cast(inputViewMappings.get(type).get());
        } catch (NullPointerException e) {
            throw new NotFoundViewException(e);
        }
    }

    public void resolveOutputView(Object dto) {
        try {
            outputViewMappings.get(dto.getClass()).accept(dto);
        } catch (NullPointerException e) {
            throw new NotFoundViewException(e);
        }
    }
}

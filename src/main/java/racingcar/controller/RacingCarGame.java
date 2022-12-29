package racingcar.controller;

import racingcar.domain.collection.GameResult;
import racingcar.domain.collection.RaceRecord;
import racingcar.domain.collection.RacingCarCollection;
import racingcar.view.RacingCarView;

import java.util.ArrayList;
import java.util.List;

public class RacingCarGame {

    private int remainingRound;

    private final RacingCarCollection cars;

    public RacingCarGame(int remainingRound, RacingCarCollection cars) {
        this.remainingRound = remainingRound;
        this.cars = cars;
    }

    public void start() {
        List<RaceRecord> raceRecords = new ArrayList<>();
        raceRecords.add(RaceRecord.of(cars));
        while (!isGameEnd()) {
            raceRecords.add(race());
        }
        RacingCarView.printGameResult(GameResult.of(raceRecords));
        RacingCarView.printWinners(cars.selectWinners());
    }

    private RaceRecord race() {
        cars.moveAll();
        remainingRound--;
        return RaceRecord.of(cars);
    }

    private boolean isGameEnd() {
        return remainingRound <= 0;
    }
}

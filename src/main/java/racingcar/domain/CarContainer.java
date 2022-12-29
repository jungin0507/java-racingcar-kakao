package racingcar.domain;

import racingcar.domain.model.RacingCar;
import racingcar.util.RandomUtil;

import java.util.List;
import java.util.stream.Collectors;

public class CarContainer {
    private final List<RacingCar> cars;

    public CarContainer(List<RacingCar> cars) {
        this.cars = cars;
        checkEmptyOrNull();
        checkDuplicates();
    }

    public List<RacingCar> getCars() {
        return cars;
    }

    public void moveAll() {
        RandomUtil randomUtil = new RandomUtil();
        cars.forEach(RacingCar::move);
    }

    public List<RacingCar> selectWinners() {
        return getCarsWithMaxPosition();
    }

    private List<RacingCar> getCarsWithMaxPosition() {
        int maxPosition = cars.stream()
                .mapToInt(RacingCar::getPosition)
                .max()
                .getAsInt();
        return cars.stream()
                .filter(e -> e.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private void checkEmptyOrNull() {
        if (cars == null || cars.isEmpty()) {
            throw new RuntimeException();
        }
    }

    private void checkDuplicates() {
        long distinctCount = cars.stream()
                .distinct()
                .count();
        if (distinctCount < cars.size()) {
            throw new RuntimeException();
        }
    }
}

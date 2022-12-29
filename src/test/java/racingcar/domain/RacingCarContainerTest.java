package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import racingcar.domain.model.RacingCar;

import java.util.ArrayList;
import java.util.List;

public class RacingCarContainerTest {

    @Test
    void duplicatedNameException() {
        ArrayList<RacingCar> cars = new ArrayList<>();
        cars.add(new MockRacingCarImpl("lion"));
        cars.add(new MockRacingCarImpl("lion"));
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> new CarContainer(cars));
    }

    @Test
    void selectWinners() {
        //given
        ArrayList<RacingCar> cars = new ArrayList<>();
        cars.add(new MockRacingCarImpl("lion"));
        cars.add(new MockRacingCarImpl("jordy"));
        cars.add(new MockRacingCarImpl("muzi"));
        CarContainer carContainer = new CarContainer(cars);
        cars.get(0)
                .move();

        //when
        List<RacingCar> winner = carContainer.selectWinners();

        //then
        Assertions.assertThat(winner)
                .containsOnly(cars.get(0));
    }
}
package step2.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    private static final int INITIAL_NUM_OF_BOWLING_PINS = 10;

    private List<Integer> frame;

    public NormalFrame() {
        this.frame = new ArrayList<>();
    }

    @Override
    public void knockDown(int numOfPin) {
        frame.add(numOfPin);
        isPossible();
    }

    @Override
    public void isPossible() {
        if (score() > 10) {
            throw new IllegalArgumentException("쓰러뜨린 볼린공의 개수는 10개 이하로 지정하여야 합니다.");
        }
    }

    @Override
    public int score() {
        return frame.stream()
                .reduce(0, Integer::sum);
    }

    @Override
    public boolean isFinish() {
        return score() == INITIAL_NUM_OF_BOWLING_PINS || frame.size() == 2;
    }

    @Override
    public List<Integer> scoreInfo() {
        return frame;
    }
}

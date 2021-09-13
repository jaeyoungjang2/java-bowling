package step3;

import step3.state.Ready;
import step3.state.State;

public class Frame {
    private Frame next;
    private State state;

    public Frame() {
        this.state = new Ready();
    }

    public void bowl(int fallenPins) {
        state = state.bowl(fallenPins);
    }

    public Score getScore() {
        Score score = state.score();
        if (score.canCalculateScore()) {
            return score;
        }

        return next.calculateAdditionalScore(score);
    }

    private Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    public boolean isGameEnd() {
        return false;
    }

    public State getState() {
        return state;
    }
}

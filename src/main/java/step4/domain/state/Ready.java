package step4.domain.state;

import step4.domain.Score;

public class Ready implements State {
    private Score score;

    public Ready() {
        this.score = new Score(0, 2);
    }

    public State throwBowl(int falledPins) {
        if (falledPins == 10) {
            return new Strike();
        }
        return new FirstBowl(falledPins);
    }

    @Override
    public String getScore() {
        return score.getScore();
    }

    @Override
    public Score score() {
        return score;
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        return null;
    }
}

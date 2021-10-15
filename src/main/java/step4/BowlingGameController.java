package step4;

import step4.domain.Frame;
import step4.domain.Frames;
import step4.domain.NormalFrame;
import step4.domain.PlayersFrames;
import step4.exception.NeedAdditionalFrameException;
import step4.view.InputView;
import step4.view.ResultView;

public class BowlingGameController {
    private PlayersFrames playersFrames;

    public BowlingGameController(PlayersFrames playersFrames) {
        this.playersFrames = playersFrames;
    }

    public void run() {
        printZeroFrameResult();
        playBowlingByFrame();
    }

    private void printZeroFrameResult() {
        ResultView.printMainColumn();
        Frame initInfoFrame = new NormalFrame(0);
        Frames frames = new Frames();
        frames.add(initInfoFrame);
        for (String nameOfPerson : this.playersFrames.playerSet()) {
            printEachFrameResult(frames, nameOfPerson);
        }
    }

    private void playBowlingByFrame() {
        for (int i = 1; i <= 10; i++) {
            playBowlingByPlayer();
        }
    }

    private void playBowlingByPlayer() {
        for (String player : this.playersFrames.playerSet()) {
            Frames frames = playersFrames.ofFrames(player);
            Frame frame = frames.ofLast();
            playBowlingUntilFinish(player, frame);
            createNewFrame(frames, frame);
        }
    }

    private void createNewFrame(Frames frames, Frame frame) {
        if (frame.round() != 10 && frame.isFinish()) {
            frame = frame.createFrame(frame.round() + 1);
            frames.add(frame);
        }
    }

    private void playBowlingUntilFinish(String nameOfPerson, Frame frame) {
        while (!frame.isFinish()) {
            int falledPins = InputView.throwBowl(nameOfPerson);
            frame.throwBowl(falledPins);
            printEachFramePlayerResult(playersFrames);
        }
    }

    private static void printEachFramePlayerResult(PlayersFrames playersFrames) {
        ResultView.printMainColumn();
        for (String player : playersFrames.playerSet()) {
            printEachFrameResult(playersFrames.ofFrames(player), player);
        }
    }

    private static void printEachFrameResult(Frames playerFrames, String nameOfPerson) {
        ResultView.printFirstColumn(nameOfPerson);
        printAllFramesSymbol(playerFrames);
        printAllFramesTotalScore(playerFrames);
    }

    private static void printAllFramesSymbol(Frames playerFrames) {
        int round = 0;
        for (Frame frame: playerFrames.frames()) {
            try {
                ResultView.printSymbol(frame);
                round ++;
            } catch (NeedAdditionalFrameException ignored) {

            }
        }
        ResultView.printEmptyColumn(10 - round);
    }

    private static void printAllFramesTotalScore(Frames playerFrames) {
        int round = 0;
        int totalScore = 0;
        ResultView.printFirstColumn("");
        for (Frame frame: playerFrames.frames()) {
            try {
                totalScore += frame.getScore().getScore();
                ResultView.printResult(totalScore);
                round ++;
            } catch (NeedAdditionalFrameException ignored) {

            }
        }
        ResultView.printEmptyColumn(10 - round);
    }
}

package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question")
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void checkDeleteAuthorization(User loginUser) {
//        for (Answer answer: answers) {
//            if (!answer.isOwner(loginUser)) {
//                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
//            }
//        }

//        if (answers.stream().anyMatch(answer -> !answer.isOwner(loginUser))) {
//            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
//        }

        if (!isMatchWithLoginUser(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isMatchWithLoginUser(User loginUser) {
        return answers.stream()
                .anyMatch(answer -> answer.isOwner(loginUser));
    }

    public List<DeleteHistory> deleteAll() {
        return answers.stream()
                .map(Answer::deleteAnswer)
                .collect(Collectors.toList());
    }
}

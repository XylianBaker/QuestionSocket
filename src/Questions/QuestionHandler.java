package Questions;

import java.util.ArrayList;
import java.util.HashSet;

public class QuestionHandler {

    private ArrayList<Question> questions = new ArrayList<Question>();
    private HashSet<Integer> questionIds = new HashSet<Integer>();

    public QuestionHandler(ArrayList<Question> questions) {
        ArrayList<Question> allQuestions = questions;

            while (this.questionIds.size() != 3) {
                this.questionIds.add((int) (Math.random() * (allQuestions.size() - 1)) + 1);
            }

        for(int i : this.questionIds) {
            this.questions.add(allQuestions.get(i));
        }
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public HashSet<Integer> getQuestionIds(int i) {
        return questionIds;
    }
}

package Questions;

import java.util.ArrayList;
import java.util.HashSet;

public class QuestionHandler {

    private ArrayList<Question> questions = new ArrayList<Question>();
    private HashSet<Integer> questionIds = new HashSet<Integer>();

    // Creating a new QuestionHandler object with a list of questions.
    public QuestionHandler(ArrayList<Question> questions) {
        ArrayList<Question> allQuestions = questions;

            // Adding 3 random numbers to the questionIds HashSet.
            while (this.questionIds.size() != 3) {
                this.questionIds.add((int) (Math.random() * (allQuestions.size() - 1)) + 1);
            }

        // Adding the questions to the questions ArrayList.
        for(int i : this.questionIds) {
            this.questions.add(allQuestions.get(i));
        }
    }

    /**
     * > This function returns an ArrayList of Question objects
     *
     * @return An ArrayList of Question objects.
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * This function returns a HashSet of Integers.
     *
     * @param i The index of the question in the list of questions.
     * @return A HashSet of Integers
     */
    public HashSet<Integer> getQuestionIds(int i) {
        return questionIds;
    }
}

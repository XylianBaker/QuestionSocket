package Questions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionReader {
    private ArrayList<Question> questions = new ArrayList<Question>();

    public QuestionReader() {
        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/questions/Questions.csv")) ) {
            for (int i = 0; (line = reader.readLine()) != null; i++)  {
                String[] row = line.split(",");
                Question question = new Question(row[0], row[1]);
                this.questions.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}

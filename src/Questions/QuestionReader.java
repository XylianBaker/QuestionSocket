package Questions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionReader {
    private ArrayList<Question> questions = new ArrayList<Question>();

    // Reading the questions from the csv file and adding them to the questions arraylist.
    public QuestionReader() {
        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("src/questions/Questions.csv")) ) {
            // This is a for loop that reads each line of the csv file and splits it into an array of strings.
            for (int i = 0; (line = reader.readLine()) != null; i++)  {
                String[] row = line.split(",");
                Question question = new Question(row[0], row[1]);
                this.questions.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
}

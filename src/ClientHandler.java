import Questions.Question;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    private final Socket socket;
    private ArrayList<Question> questions = new ArrayList<>();
    private int answered = 0;

    public ClientHandler(Socket socket, ArrayList<Question> questions) {
        this.socket = socket;
        this.questions = questions;
    }

    @Override
    public void run() {
        String clientAnswer = "";

        try (
                socket;
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {

            for (Question question : questions) {
                writer.write(question.question());
                writer.println();
                clientAnswer = reader.readLine();
                if (clientAnswer.equals(question.answer())) {
                    this.answered++;
                }
            }

            writer.println( "You have has answered " + this.answered + "/3 questions!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

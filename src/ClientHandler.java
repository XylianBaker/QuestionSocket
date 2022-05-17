import Questions.Question;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    private final Socket socket;
    private ArrayList<Question> questions = new ArrayList<>();
    private int answered = 0;

    // A constructor that takes a socket and an arraylist of questions.
    public ClientHandler(Socket socket, ArrayList<Question> questions) {
        this.socket = socket;
        this.questions = questions;
    }

    @Override
    public void run() {
        String clientAnswer = "";

        // A try-with-resources statement. It is a try statement that declares one or more resources. A resource is an
        // object that must be closed after the program is finished with it. The try-with-resources statement ensures that
        // each resource is closed at the end of the statement. Any object that implements java.lang.AutoCloseable, which
        // includes all objects which implement java.io.Closeable, can be used as a resource.
        try (
                socket;
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {

            // A for-each loop. It is used to iterate over the elements of a collection.
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

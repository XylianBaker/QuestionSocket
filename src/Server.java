import Questions.Question;
import Questions.QuestionHandler;
import Questions.QuestionReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final int port = 1234;
    private ArrayList<Question> questions = new ArrayList<>();

    // Creating a new instance of the QuestionReader class and calling the getQuestions method on it.
    public Server() {
        questions = new QuestionReader().getQuestions();
    }

    /**
     * The server starts a threadpool of 20 threads, and then listens for incoming connections on the port specified in the
     * constructor. When a connection is made, a new ClientHandler is created and passed to the threadpool to be executed
     */
    public void startServer() {
        ExecutorService threadpool = Executors.newFixedThreadPool(20);
        try (
                ServerSocket serverSocket = new ServerSocket(port);
                ) {
            // Listening for new connections and then creating a new ClientHandler for each connection.
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has conencted 🥳 !");

                QuestionHandler questionHandler = new QuestionHandler(questions);
                ClientHandler clientHandler = new ClientHandler(socket, questionHandler.getQuestions());

                threadpool.execute(clientHandler);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }
}

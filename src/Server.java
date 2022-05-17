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

    public Server() {
        questions = new QuestionReader().getQuestions();
    }

    public void startServer() {
        ExecutorService threadpool = Executors.newFixedThreadPool(20);
        try (
                ServerSocket serverSocket = new ServerSocket(port);
                ) {
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

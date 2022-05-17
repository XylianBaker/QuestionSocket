import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public final static String host = "localhost";
    public final static int port = 1234;

    public static void main(String[] args) throws IOException {
        try (
                Socket socket = new Socket(host, port);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                ) {

            for (int i = 0; i < 3; i++) {
                String response = reader.readLine();
                System.out.println("SERVER: " + response);

                Scanner scanner = new Scanner(System.in);
                String request = scanner.nextLine();
                writer.println(request);
            }

            String finalAnswer = reader.readLine();
            System.out.println("Server: " + finalAnswer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
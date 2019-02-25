import java.util.Scanner;

public class ConsoleView {

    public String getUserInput(String message) {
        System.out.println(message);

        Scanner s = new Scanner(System.in);
        String userInput = s.next();
        return userInput;
    }

    public void print(String print) {
        System.out.println(print);
    }
}

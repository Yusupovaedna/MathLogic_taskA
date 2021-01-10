import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        Scanner in = new Scanner(new File("input.txt")) ;

        String expression = in.next().replaceAll("[\\t\\s\\n~@#$%^*=+]","").replace("->",">");;

        ExpressionParser parser = new ExpressionParser();

        try (PrintWriter out = new PrintWriter("output.txt")) {
            out.print(parser.parse(expression).toTree());
        }
    }
}

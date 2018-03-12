import java.util.*;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
       // TextFormatter.format(Const.TEXT_FOR_ENCRYPT_NATIVE,Const.TEXT_FOR_ENCRYPT);
        long first = System.currentTimeMillis();
            TextFormatter.format(Const.NATIVE_TEXT, Const.FORMATTED_TEXT);
            FileReader readerFormat = new FileReader(Const.FORMATTED_TEXT);
            Scanner scanner = new Scanner(readerFormat);
            String s = scanner.nextLine();

            Analyze.frequencyMono(s);
            Analyze.frequencyBi(s, 1);
            Analyze.frequencyBi(s, 2);

            scanner.close();
            readerFormat.close();
        long second = System.currentTimeMillis() - first;
        System.out.println("Time" + second);
    }

}
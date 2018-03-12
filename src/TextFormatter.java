import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public final class TextFormatter {
    private TextFormatter(){}
    public static void format(String input, String output) throws IOException {
        FileReader reader = new FileReader(input);
        FileWriter writer = new FileWriter(output);
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            s = s.replaceAll("ё", "е")
                    .replaceAll("(?u)[^а-яА-Я]", "_")
                    .replaceAll("^_+", "")
                    .replaceAll("_+",Const.SPACE_REPLACE)
                    .toLowerCase();
            if (!scan.hasNextLine() && !"".equals(Const.SPACE_REPLACE)) s = s.replaceAll(Const.SPACE_REPLACE+"$","");
            writer.write(s);
        }
        scan.close();
        reader.close();
        writer.close();
    }
}

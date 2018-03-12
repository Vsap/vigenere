import java.util.ArrayList;
import java.util.HashMap;

public final class Const {
    public static final HashMap<String, Integer> ALPHABET = getAlpha();
    private static HashMap<String,Integer> getAlpha(){
        HashMap<String,Integer> alpha = new HashMap<>();
        int k = 0;
        for (char i = 'а'; i <= 'я'; i++) {
            alpha.put(i+"",k);
            k++;
        }
        if (!SPACE_REPLACE.equals("")) alpha.put(SPACE_REPLACE,k++);
        return alpha;
    }
    public static final String SPACE_REPLACE = "";
    public static final String NATIVE_TEXT = "res/native_text_2.txt";
    public static final String FORMATTED_TEXT = "res/format_text.txt";
    public static final String TEXT_FOR_ENCRYPTion_NATIVE = "res/about_oak.txt";
    public static final String TEXT_FOR_ENCRYPTion = "res/about_oak_format.txt";
}

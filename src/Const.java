/**
 * Компьютерный практикум №1
 * ФI-52, А. Бодрягина, В. Сапига
 */

import java.io.File;
import java.util.HashMap;

public final class Const {
    public Const(String space){
        SPACE_REPLACE = space;
        ALPHABET_LN = getAlphaLN();
        ALPHABET_NL = getAlphaNL();
        ALPHABET_SIZE = ALPHABET_LN.size();
    }
    public final HashMap<String, Integer> ALPHABET_LN;
    private HashMap<String,Integer> getAlphaLN(){
        HashMap<String,Integer> alpha = new HashMap<>();
        int k = 0;
        for (char i = 'а'; i <= 'я'; i++) {
            alpha.put(i+"",k);
            k++;
        }
        if (!SPACE_REPLACE.equals("")) alpha.put(SPACE_REPLACE,k++);
        return alpha;
    }
    public final HashMap<Integer, String> ALPHABET_NL;
    private HashMap<Integer,String> getAlphaNL(){
        HashMap<Integer,String> alpha = new HashMap<>();
        int k = 0;
        for (char i = 'а'; i <= 'я'; i++) {
            alpha.put(k,""+i);
            k++;
        }
        if (!SPACE_REPLACE.equals("")) alpha.put(k++, SPACE_REPLACE);
        return alpha;
    }
    public final String SPACE_REPLACE;
    public static final File NATIVE_TEXT = new File("res/about_oak.txt");
    public static final File FORMATTED_TEXT_WITH = new File("res/format_text.txt");
    public static final File FORMATTED_TEXT_WITHOUT = new File("res/format_without.txt");

    public static final File CIPHER_TEXT = new File("res/cipher_text.txt");
    public static final File DECRYPTED_TEXT = new File("res/decrypt_text.txt");
    public static final File CIPHER_TEXT2 = new File("res/cipher_text2.txt");
    public static final File DECRYPTED_TEXT2 = new File("res/decrypt_text2.txt");

    public static final int MOST_COMMON_LETTER = 14;
    public final int ALPHABET_SIZE;
}

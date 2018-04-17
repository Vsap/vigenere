import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class VigenereCrypt {
    private ArrayList<Integer> key;
    public Const constant;

    public VigenereCrypt(String key){
        this.key = new ArrayList<>();
        constant = new Const("");
        for(Character x :key.toCharArray()){
            this.key.add(constant.ALPHABET_LN.get(x+""));
        }
    }
    public VigenereCrypt(String key, String space){
        this.key = new ArrayList<>();
        constant = new Const(space);
        for(Character x :key.toCharArray()){
            this.key.add(constant.ALPHABET_LN.get(x+""));
        }
    }

    public VigenereCrypt(ArrayList<Integer> key) {
        this.key = key;
        this.constant = new Const("");
    }

    public void encryptor(String input, File output) throws IOException {
        FileWriter writer = new FileWriter(output);
        int keyLength = key.size();
        StringBuffer encrypttedText = new StringBuffer();
        for (int i = 0; i < input.length(); i++){
            int k = i%keyLength;
            int encrypttedNumber = (constant.ALPHABET_LN.get(input.charAt(i)+"") + key.get(k))%constant.ALPHABET_LN.size();
            encrypttedText.append(constant.ALPHABET_NL.get(encrypttedNumber));
        }
        writer.write(encrypttedText.toString());
        writer.close();
    }

    public void decryptor(String input, File output) throws IOException {
        FileWriter writer = new FileWriter(output);
        int keyLength = key.size();
        StringBuffer decrypttedText = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            int k = i % keyLength;
            int decrypttedNumber = (constant.ALPHABET_LN.size() + constant.ALPHABET_LN.get(input.charAt(i) + "") - key.get(k)) % constant.ALPHABET_LN.size();
            decrypttedText.append(constant.ALPHABET_NL.get(decrypttedNumber));
            if(i%constant.ALPHABET_SIZE ==(constant.ALPHABET_SIZE-1)) decrypttedText.append("\n");
        }
        writer.write(decrypttedText.toString());
        writer.close();
    }
    public void printKey(){
        for (Integer i: key){
            System.out.print(constant.ALPHABET_NL.get(i));
        }
        System.out.println();
    }
    public ArrayList<Integer> getKey(){return key;}
    public ArrayList<Integer> rangedKey(int index, int numberOfLetter, ArrayList<HashMap<String, Double>> eachBlockQuantity){
        ArrayList<Map.Entry<String,Double>> list = new ArrayList<>(eachBlockQuantity.get(index).entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);
        int elemKey = constant.ALPHABET_LN.get(list.get(numberOfLetter).getKey());
        key.set(index,(constant.ALPHABET_SIZE + elemKey - Const.MOST_COMMON_LETTER) % constant.ALPHABET_SIZE);
        return key;
    }
}

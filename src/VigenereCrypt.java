import java.util.ArrayList;

public class VigenereCrypt {
    private ArrayList<Integer> key;
    public VigenereCrypt(){
        key = new ArrayList<>();
        key.add(1);
    }
    public VigenereCrypt(String key){
        this.key = new ArrayList<>();
        for(Character x :key.toCharArray()){
            this.key.add(Const.ALPHABET.get(x));
        }
    }
}

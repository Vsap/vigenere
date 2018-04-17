import java.util.*;

public class VigenereAnalyze {
    private String input;

    public VigenereAnalyze(String input) {
        this.input = input;
    }

    private static final Const constant = new Const("");
    public double indexCoincidence(HashMap<String, Double> quantity, int textLength){
        Double sum = 0.0;
        for (Double N: quantity.values()){
            sum+=N*(N-1);                 //N кількість появ букви t у шифртексті Y
        }
        Double n = new Double(textLength);

        return sum/(n*(n-1));
    }

    public ArrayList<HashMap<String,Double>> quantitiesForEachBlock(int blockLength){
        ArrayList<HashMap<String,Double>> quantityMaps = new ArrayList<>();
        for(int i = 0; i < blockLength; i++){
            quantityMaps.add(new Analyze(constant.SPACE_REPLACE).getMonoMap());
        }
        for (int i = 0; i < input.length(); i++) {
            quantityMaps.get(i%blockLength).compute(Character.toString(input.charAt(i)), (k, v) -> v + 1);
        }
        return quantityMaps;
    }

    public void printTillIndexes(int maxIndex){
        System.out.println("==================================");

        for (int i = 1; i <= maxIndex; i++){
            System.out.println("Block length: " + i);
            ArrayList<HashMap<String,Double>> quantitiesForBlocks = quantitiesForEachBlock(i);
            Double index = 0.0;
            for (int j = 0; j<i; j++){
                index+=indexCoincidence(quantitiesForBlocks.get(j), input.length()/i);
            }
            System.out.println("    # block index: " + index/i);

        }
    }

    public ArrayList<Integer> guessedKey(ArrayList<HashMap<String, Double>> eachBlockQuantity){
        int i = 0;
        ArrayList<Integer> supposedKey = new ArrayList<>();
        for (HashMap<String, Double> map: eachBlockQuantity) {
            ArrayList<Map.Entry<String,Double>> list = new ArrayList<>(map.entrySet());
            list.sort(Comparator.comparing(Map.Entry::getValue));
            int elemKey = constant.ALPHABET_LN.get(list.get(list.size()-1).getKey());
            supposedKey.add((constant.ALPHABET_SIZE + elemKey - Const.MOST_COMMON_LETTER) % constant.ALPHABET_SIZE);
        }
        return supposedKey;
    }
    public HashMap<String,Double> quantityMono(){
        HashMap<String, Double> quantity = new Analyze("").getMonoMap();
        System.out.println("Length = " + input.length());
        for (int i = 0; i < input.length(); i++) {
            quantity.compute(Character.toString(input.charAt(i)), (k, v) -> v + 1);
        }
        return quantity;
    }
}

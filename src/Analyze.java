import java.util.*;

public final class Analyze {
    private Analyze(){}

    public static void frequencyMono(String input) {
        HashMap<String, Double> frequencies = new HashMap<>();
        for (String c: Const.ALPHABET.keySet()) {
            frequencies.put(c, 0.0);
        }
        System.out.println("Length = " + input.length());
        for (int i = 0; i < input.length(); i++) {
            frequencies.compute(Character.toString(input.charAt(i)), (k, v) -> v + 1);
        }
        for (String c : frequencies.keySet()) {
            frequencies.compute(c, (k, v) -> v * 100 / input.length());
        }
        printMono(frequencies);
        //return frequencies;
    }

    private static void printMono(HashMap<?,Double> hashMap){
        ArrayList<Map.Entry<?,Double>> list = new ArrayList<>(hashMap.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);
        Double sum = 0.0;
        for(Map.Entry<?,Double> x: list){
            sum+=x.getValue();
            System.out.println(x);
        }
        System.out.println("Sum(p) = " + sum);
    }

    public static void frequencyBi(String input, int flag) {
        HashMap<String, Double> frequencies = new HashMap<>();
        for (String i: Const.ALPHABET.keySet()) {
            for (String j: Const.ALPHABET.keySet())
                frequencies.put(i + j, 0.0);
        }
        for (int i = 0; i < input.length() - 1; i += flag) {
            frequencies.compute("" + input.charAt(i) + input.charAt(i + 1), (k, v) -> v + 1);
        }
        final int quantity = input.length() / flag - (flag % 2);
        for (String c : frequencies.keySet()) {
            frequencies.compute(c, (k, v) -> v*100 / quantity);
        }
        printBi(frequencies);
        //return frequencies;
    }

    private static void printBi(HashMap<String, Double> map) {
        System.out.print("  |    ");
        for (String i: Const.ALPHABET.keySet()) {
            System.out.print(i + "    |    ");
        }
        System.out.println();
        double sum = 0.0;
        for (String i: Const.ALPHABET.keySet()) {
            System.out.print(i + " | ");
            for (String j: Const.ALPHABET.keySet()) {
                double frequency = map.get(i + j);
                System.out.print(String.format("%(.5f | ", frequency));
                sum += frequency;
            }
            System.out.println();
        }
        System.out.println("Sum(p) = " + sum);
    }

}

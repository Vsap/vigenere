/**
 * Компьютерный практикум №1
 * ФI-52, А. Бодрягина, В. Сапига
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public final class Analyze {
    public Analyze(String space){
        constant = new Const(space);
    }

    public Const constant;
    public void format(File input, File output) throws IOException {
        FileReader reader = new FileReader(input);
        FileWriter writer = new FileWriter(output);
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            s = s.replaceAll("ё", "е")
                    .replaceAll("(?u)[^а-яА-Я]", "_")
                    .replaceAll("^_+", "")
                    .replaceAll("_+",constant.SPACE_REPLACE)
                    .toLowerCase();
            if (!scan.hasNextLine() && !"".equals(constant.SPACE_REPLACE)) s = s.replaceAll(constant.SPACE_REPLACE+"$","");
            writer.write(s);
        }
        scan.close();
        reader.close();
        writer.close();
    }

    public HashMap<String, Double> getMonoMap(){
        HashMap<String, Double> monoMap = new HashMap<>();
        for (String c: constant.ALPHABET_LN.keySet()) {
            monoMap.put(c, 0.0);
        }
        return monoMap;
    }

    public HashMap<String,Double> quantityMono(String input){
        HashMap<String, Double> quantity = getMonoMap();
        System.out.println("Length = " + input.length());
        for (int i = 0; i < input.length(); i++) {
            quantity.compute(Character.toString(input.charAt(i)), (k, v) -> v + 1);
        }
        return quantity;
    }

    public HashMap<String, Double> frequencyMono(String input) {
        HashMap<String,Double> frequencies = quantityMono(input);
        for (String c : frequencies.keySet()) {
            frequencies.compute(c, (k, v) -> v  / input.length());
        }
        printMono(frequencies);
        return frequencies;
    }

    public static void printMono(HashMap<?,Double> hashMap){
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

    public HashMap<String,Double> quantityBi(String input, int flag){
        HashMap<String, Double> quantity = new HashMap<>();
        for (String i: constant.ALPHABET_LN.keySet()) {
            for (String j: constant.ALPHABET_LN.keySet())
                quantity.put(i + j, 0.0);
        }
        for (int i = 0; i < input.length() - 1; i += flag) {
            quantity.compute("" + input.charAt(i) + input.charAt(i + 1), (k, v) -> v + 1);
        }
        return quantity;
    }

    public HashMap<String, Double> frequencyBi(String input, int flag) {
        HashMap<String, Double> frequencies = quantityBi(input, flag);
        final int quantity = input.length() / flag - (flag % 2);
        for (String c : frequencies.keySet()) {
            frequencies.compute(c, (k, v) -> v / quantity);
        }
        printBi(frequencies);
        return frequencies;
    }

    public static Double entropy(HashMap<?,Double> map, int n){
        Double sum = 0.0;
        for (Double pi: map.values()){
            if (pi!=0) sum+=pi*log2(pi);
        }
        return -sum/n;
    }

    public Double redundancy(Double entropy, int flag){
        Double H_max = (constant.SPACE_REPLACE.equals("")) ? log2(Math.pow(32.0,flag)) : log2(33.0);
        return (H_max - entropy)/H_max;
    }

    private static Double log2(Double i){
        return (Math.log(i)/Math.log(2));
    }

    private void printBi(HashMap<String, Double> map) {
        System.out.print("  |    ");
        for (String i: constant.ALPHABET_LN.keySet()) {
            System.out.print(i + "    |    ");
        }
        System.out.println();
        double sum = 0.0;
        for (String i: constant.ALPHABET_LN.keySet()) {
            System.out.print(i + " | ");
            for (String j: constant.ALPHABET_LN.keySet()) {
                double frequency = map.get(i + j);
                System.out.print(String.format("%(.5f | ", frequency));
                sum += frequency;
            }
            System.out.println();
        }
        System.out.println("Sum(p) = " + sum);
    }

}

/**
 * Компьютерный практикум №1
 * ФI-52, А. Бодрягина, В. Сапига
 */

import java.io.File;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        long first = System.currentTimeMillis();
            lab2Analyze(Const.CIPHER_TEXT,Const.DECRYPTED_TEXT, 14);
            lab2Analyze(Const.CIPHER_TEXT2,Const.DECRYPTED_TEXT2, 15);
            lab2Crypt();
        long second = System.currentTimeMillis() - first;
        System.out.println("Time" + second);
    }
    public static void lab2Analyze(File input, File output, int keyLength) throws IOException {
        Analyze without = new Analyze("");
        without.format(input, Const.FORMATTED_TEXT_WITHOUT);

        FileReader readerFormatWithout = new FileReader(Const.FORMATTED_TEXT_WITHOUT);
        Scanner scannerWithout = new Scanner(readerFormatWithout);

        String sWithout = scannerWithout.nextLine();

        scannerWithout.close();
        readerFormatWithout.close();
        VigenereAnalyze analyzer = new VigenereAnalyze(sWithout);
        analyzer.printTillIndexes(30);
        ArrayList<HashMap<String,Double>> quantities = analyzer.quantitiesForEachBlock(keyLength);
        int i = 0;
        for (HashMap<String,Double> map: quantities){
            System.out.println(i);
            i++;
            Analyze.printMono(map);
            System.out.println("========================================");
        }

        ArrayList<Integer> key = analyzer.guessedKey(quantities);
        VigenereCrypt crypt1 = new VigenereCrypt(key);
        System.out.println(key);
        crypt1.printKey();
        VigenereCrypt crypt = new VigenereCrypt(key);
        System.out.println(crypt.getKey());
        crypt.printKey();
        crypt1.decryptor(sWithout, output);
    }
    public static void range1(ArrayList<Integer> key){
        key.set(0,15);
        key.set(3,11);
        key.set(5,4);
        key.set(6,13);
        key.set(7,8);
        key.set(8,9);
    }public static void range2(ArrayList<Integer> key){

    }
    public static void lab2Crypt() throws IOException {
        Crypt("аб");
        Crypt("кек");
        Crypt("привет");
        Crypt("политехническийинститут");

    }

    public static void Crypt(String s) throws IOException {
        Analyze without = new Analyze("");
        without.format(Const.NATIVE_TEXT, Const.FORMATTED_TEXT_WITHOUT);

        FileReader readerFormatWithout = new FileReader(Const.FORMATTED_TEXT_WITHOUT);
        Scanner scannerWithout = new Scanner(readerFormatWithout);

        String sNative = scannerWithout.nextLine();

        scannerWithout.close();
        readerFormatWithout.close();

        VigenereCrypt c1 = new VigenereCrypt(s);

        c1.encryptor(sNative,Const.FORMATTED_TEXT_WITHOUT);
        FileReader readerFormat = new FileReader(Const.FORMATTED_TEXT_WITHOUT);
        Scanner scanner = new Scanner(readerFormat);

        String sCipher = scanner.nextLine();

        scanner.close();
        readerFormat.close();
        c1.decryptor(sCipher,Const.NATIVE_TEXT);
        System.out.println(" Key length " + s.length());
        VigenereAnalyze analyzeNative = new VigenereAnalyze(sNative);
        System.out.print("Index for plain text: ");
        System.out.println(analyzeNative.indexCoincidence(analyzeNative.quantityMono(),sNative.length()));
        VigenereAnalyze analyzeCipher = new VigenereAnalyze(sCipher);
        System.out.print("Index for cipher text: ");
        System.out.println(analyzeCipher.indexCoincidence(analyzeCipher.quantityMono(),sCipher.length()));
    }
    public static void lab1() throws IOException {
        Analyze with = new Analyze(" ");
        with.format(Const.NATIVE_TEXT, Const.FORMATTED_TEXT_WITH);

        FileReader readerFormatWith = new FileReader(Const.FORMATTED_TEXT_WITH);
        Scanner scannerWith = new Scanner(readerFormatWith);

        String sWith = scannerWith.nextLine();

        scannerWith.close();
        readerFormatWith.close();

        Analyze without = new Analyze("");
        without.format(Const.NATIVE_TEXT, Const.FORMATTED_TEXT_WITHOUT);

        FileReader readerFormatWithout = new FileReader(Const.FORMATTED_TEXT_WITHOUT);
        Scanner scannerWithout = new Scanner(readerFormatWithout);

        String sWithout = scannerWithout.nextLine();

        scannerWithout.close();
        readerFormatWithout.close();

        Double entropy1With = Analyze.entropy(with.frequencyMono(sWith),1);
        Double entropy2_1With = Analyze.entropy(with.frequencyBi(sWith,1),2);
        Double entropy2_2With = Analyze.entropy(with.frequencyBi(sWith,2),2);
        System.out.println("==========================================");
        Double entropy1Without = Analyze.entropy(without.frequencyMono(sWithout),1);
        Double entropy2_1Without = Analyze.entropy(without.frequencyBi(sWithout,1),2);
        Double entropy2_2Without = Analyze.entropy(without.frequencyBi(sWithout,2),2);

        System.out.println("==========================================");
        System.out.println("H1 = " + entropy1With);
        System.out.println("H2(flag 1) = " + entropy2_1With);
        System.out.println("H2(flag 2) = " + entropy2_2With);
        System.out.println("R1 = " + with.redundancy(entropy1With, 1));
        System.out.println("R2 = " + with.redundancy(entropy2_1With, 2));
        System.out.println("==========================================");
        System.out.println("H1 = " + entropy1Without);
        System.out.println("H2(flag 1) = " + entropy2_1Without);
        System.out.println("H2(flag 2) = " + entropy2_2Without);
        System.out.println("R1 = " + without.redundancy(entropy1Without, 1));
        System.out.println("R2 = " + without.redundancy(entropy2_1Without, 2));
    }

}
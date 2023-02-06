package chucknorris;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input operation (encode/decode/exit):");
        String order = scanner.nextLine();
        while (!Objects.equals(order, "exit")) {
            if(Objects.equals(order, "encode")) {
                System.out.println("Input string:");
                String input = scanner.nextLine();
                System.out.println("Encoded string:");
                char last = '2';
                for (int index = 0; index < input.length(); index++) {
                    char wordChar = input.charAt(index);
                    // for the 7-bit
                    String result = String.format("%7s", Integer.toBinaryString(wordChar));
                    String binary = result.replace(" ", "0");
                    for (int i = 0; i < binary.length(); i++) {
                        char current = binary.charAt(i);
                        if (current == last) {
                            System.out.print("0");
                        } else {
                            if (current == '1' && index == 0 && i == 0) {
                                System.out.print("0 0");
                            } else if (current == '0' && index == 0 && i == 0) {
                                System.out.print("00 0");
                            } else if (current == '1') {
                                System.out.print(" 0 0");

                            } else if (current == '0') {
                                System.out.print(" 00 0");
                            }
                        }
                        last = current;
                    }
                }

            } else if (Objects.equals(order, "decode")) {
                System.out.println("Input encoded string:");
                String encoded = scanner.nextLine();
                String[] seperator = encoded.split(" ");
                Boolean checkerOne = true;
                Boolean checkerTwo = true;
                Boolean checkerThr = true;
                Boolean checkerFou = true;
                int indexCounter = 0;
                for (String word : seperator) {

                    String[] sepList = word.split("");
                    for (int o = 0; o < word.length(); o++) {
                        if (!Objects.equals(sepList[o], "0")) {
                            checkerOne = false;
                            //System.out.println(word);
                            //System.out.println("1");
                        }
                    }
                    if (indexCounter % 2 == 0) {
                        //System.out.println("index:" + indexCounter);
                        if (word.length() > 2) {
                            checkerTwo = false;
                            //System.out.println(word);
                            //System.out.println("2");
                        }
                    }
                    if (seperator.length % 2 != 0) {
                        checkerThr = false;
                        //System.out.println(word);
                        //System.out.println("3");
                    }
                    String[] encodedArray = encoded.split(" ");
                    String now = "1";
                    String returnedBinary = "";
                    for (int i = 0; i < encodedArray.length; i++) {
                        if (i % 2 == 0) {
                            if (encodedArray[i].equalsIgnoreCase("0")) {
                                now = "1";
                            } else {
                                now = "0";
                            }
                        } else {
                            for (int k = 0; k < encodedArray[i].length(); k++) {
                                returnedBinary += now;
                            }
                        }
                    }
                    if (returnedBinary.length() % 7 != 0) {
                        checkerFou = false;
                        //System.out.println(word);
                        //System.out.println("4");
                    }
                    indexCounter += 1;
                }

                if (checkerFou && checkerOne && checkerThr && checkerTwo) {

                    String[] encodedArray = encoded.split(" ");
                    String now = "1";
                    String returnedBinary = "";
                    for (int i = 0; i < encodedArray.length; i++) {
                        if (i % 2 == 0) {
                            if (encodedArray[i].equalsIgnoreCase("0")) {
                                now = "1";
                            } else {
                                now = "0";
                            }
                        } else {
                            for (int k = 0; k < encodedArray[i].length(); k++) {
                                returnedBinary += now;
                            }
                        }
                    }
                    String[] Binarys = new String[returnedBinary.length() / 7];

                    for (int i = 0; i < Binarys.length; i++) {
                        Binarys[i] = returnedBinary.substring(0, 7);
                        returnedBinary = returnedBinary.substring(7, returnedBinary.length());
                    }
                    System.out.println("Decoded string:");

                    for (int g = 0; g < Binarys.length; g++) {
                        System.out.print((char) Integer.parseInt(Binarys[g], 2));
                    }
                }
                else {
                    System.out.print("Encoded string is not valid.");
                }
            } else if (!Objects.equals(order, "exit")) {
                System.out.print("There is no '" + order + "' operation");
            }

            System.out.println();
            System.out.println("Please input operation (encode/decode/exit):");
            order = scanner.nextLine();


        }
        System.out.println("Bye!");
    }
}
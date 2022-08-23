package asciimirror;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String fp = scanner.nextLine();
        List<String> animalList = new ArrayList<>();

        try {
            File file = new File(fp);

            if (file.isFile()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    animalList.add(str);
                }
            } else {
                System.out.println("File not found!");
                System.exit(0);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int biggestStr = biggestInList(animalList);

        for (int i = 0; i < animalList.size(); i++) {
            StringBuilder sb = new StringBuilder();
            String currentStr = animalList.get(i);

            if (currentStr.length() < biggestStr) {
                int diff = biggestStr - currentStr.length();
                sb.append(currentStr).append(" ".repeat(diff));
                animalList.set(i, sb.toString());
            }
        }

        for (String s : animalList) {
            String modStr = s;
            modStr = swapParens(modStr, '(', ')');
            modStr = swapParens(modStr, '[', ']');
            modStr = swapParens(modStr, '{', '}');
            modStr = swapParens(modStr, '<', '>');
            modStr = swapParens(modStr, '/', '\\');
            System.out.println(s + " | " +reverse(modStr));
        }

    }

    static String swapParens(String s, char oldChar, char newChar) {
        return s.replace(oldChar, '\0').replace(newChar, oldChar).replace('\0', newChar);
    }
    static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
    static int biggestInList(List<String> list) {
        int biggestStr = 0, temp = 0;
        for (String s : list) {
            temp = s.length();
            if (temp > biggestStr) {
                biggestStr = temp;
            }
        }
        return biggestStr;
    }
}

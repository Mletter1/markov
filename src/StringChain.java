import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by matthewletter on 10/4/14.
 */
public class StringChain {

    private ArrayList<String> tokenArray = null;
    private final int ORDER;
    private HashMap<Prefix, Suffix> map;
    private final String NOT_A_WORD = "NOT A WORD";

    public StringChain(int order) {
        this.ORDER = order;
    }

    public String[] generate(int count, Random rand) {
        String[] returnString = new String[count];
        Prefix currentKey;
        String keyString = "";
        for (int i = 0; i < ORDER; i++) {
            keyString += NOT_A_WORD;
        }
        currentKey = new Prefix(keyString);
        returnString[0] = map.get(currentKey).getSuffix(rand);

        for (int i = 1; i<count && i < ORDER; i++) {
            keyString = "";
            for (int j = 0; j < ORDER-i ; j++) {
                keyString += NOT_A_WORD;
            }
            for (int j = 0; j < i; j++) {
                keyString += returnString[j];
            }
            currentKey = new Prefix(keyString);
            returnString[i] = map.get(currentKey).getSuffix(rand);
        }

        for (int i = ORDER; i < count; i++) {
            keyString = "";
            for (int j = i-ORDER; j < i; j++) {
                keyString += returnString[j];
            }
            currentKey = new Prefix(keyString);
            returnString[i] = map.get(currentKey).getSuffix(rand);
        }
        return returnString;
    }

    public void addItems(Scanner sc) {
        if(tokenArray == null)
            tokenArray = new ArrayList<String>();
        while(sc.hasNext())
            tokenArray.add(sc.next());
        buildMap();
    }

    private void buildMap() {
        if(this.map == null)
            map = new HashMap<Prefix, Suffix>();
        for (int i = 0; i < ORDER; i++) {
            tokenArray.add(0, NOT_A_WORD);
        }
        ArrayList<String> suffixArray;
        Prefix currentPrefix;
        Suffix currentSuffix;
        String currentKey = "";
        for (int i = 0; i+this.ORDER < tokenArray.size(); i++) {
            currentKey = "";
            for (int j = i; j < this.ORDER + i; j++) {
                currentKey += tokenArray.get(j);
            }
            currentPrefix = new Prefix(currentKey);
            if(map.containsKey(currentPrefix)){
                map.get(currentPrefix).addSuffixValue(tokenArray.get(this.ORDER + i));
            }
            else{
                suffixArray = new ArrayList<String>();
                suffixArray.add(tokenArray.get(this.ORDER + i));
                currentSuffix = new Suffix(currentKey,suffixArray);
                map.put(currentPrefix,currentSuffix);
            }
        }
    }
    private class Prefix {
        private String prefix;
        Prefix(String prefix){
            this.prefix = prefix;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Prefix prefix1 = (Prefix) o;

            if (!prefix.equals(prefix1.prefix)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return prefix.hashCode();
        }
    }
    private class Suffix {
        private ArrayList<String> suffixArray;
        private String prefix;

        private Suffix(String prefix, ArrayList<String> suffixArray) {
            this.prefix = prefix;
            this.suffixArray = suffixArray;
        }

        public String getSuffix(Random rnd){
            return suffixArray.get(rnd.nextInt((suffixArray.size() - 1)));
        }

        public void addSuffixValue(String newPrefix){
            this.suffixArray.add(newPrefix);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Suffix suffix = (Suffix) o;

            if (!prefix.equals(suffix.prefix)) return false;
            if (!suffixArray.equals(suffix.suffixArray)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = suffixArray.hashCode();
            result = 31 * result + prefix.hashCode();
            return result;
        }
    }
}

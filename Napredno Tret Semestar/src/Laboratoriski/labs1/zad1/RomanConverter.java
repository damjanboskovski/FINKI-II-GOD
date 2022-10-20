package Laboratoriski.labs1.zad1;

public class RomanConverter {
    public static String toRoman(int n) {
        String[] thousand = {"","M","MM","MMM","MMMM"};
        String[] hundreds = {"","C","CC","CCC","CD","D","DC","DCC","CM"};
        String[] tens = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] singles = {"","I","II","III","IV","V","VI","VII","VIII","IX"};

        return thousand[n/1000] + hundreds[(n%1000)/100] + tens[(n%100)/10] + singles[n%10];
    }
}

package lab1;
import java.util.Scanner;

class QuarterlySales {

    private int numOfSales;
    private int [] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }

    public int getTotalRevenue() {
        int sum = 0;
        for(int num : revenues){
            sum += num;
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Quarter" + quarterNo + ": " + getTotalRevenue();
    }

}

class SalesPerson {

    private String name;
    private QuarterlySales [] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    public String getName() {
        return name;
    }

    public int totalAnualSales(){
        int sum = 0;
        for(QuarterlySales quarter : quarters){
            sum += quarter.getTotalRevenue();
        }
        return sum;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + "    ");
        for(QuarterlySales quarter : quarters){
            sb.append(quarter.getTotalRevenue()).append("    ");
        }
        sb.append("Total: ").append(totalAnualSales());
        return sb.toString();
    }

}



public class Main {

    public static int sumSales(SalesPerson sp){
        return sp.totalAnualSales();
    }

    public static SalesPerson salesChampion(SalesPerson [] arr)
    {
        SalesPerson n = arr[0];
        for(SalesPerson sp: arr){
            if(sumSales(sp) > sumSales(n)){
                n = sp;
            }
        }
        return n;
    }
    public static void table(SalesPerson [] arr)
    {
        System.out.println("SP    1    2    3    4    Total\n");
        for(SalesPerson sp : arr){
            System.out.println(sp);
        }

    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        SalesPerson [] arr = new SalesPerson[n];

        for(int i=0;i<n;i++)
        {
            String name = input.nextLine();

            QuarterlySales[] quarters = new QuarterlySales[4];
            for(int j=0;j<4;j++){
                int numOfSales = input.nextInt();
                int[] revenues = new int[numOfSales];
                for(int k=0;k<numOfSales;k++){
                    revenues[k] = input.nextInt();
                }
                quarters[j] = new QuarterlySales(numOfSales, revenues, j + 1);
            }
            input.nextLine();
            arr[i] = new SalesPerson(name, quarters);
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());

    }
}
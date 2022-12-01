package Auditoriski.Two.Second;

public class MatrixTester {
    public static double sum(double[][] a){
        double sum = 0;
        for(int i=0; i<a.length; i++){
            for(int j = 0; j<a[i].length; j++){
                sum+=a[i][j];
            }
        }
        return sum;
    }
    public static double avg(double[][] a){
        return sum(a) / (a.length * a[0].length);
    }

    public static void main(String[] args) {
        double[][] matrix = {{1.4,2.5},{5.2,2.1}};
        System.out.println("SUM: " + sum(matrix));
        System.out.println("AVERAGE: " + avg(matrix));
    }
}

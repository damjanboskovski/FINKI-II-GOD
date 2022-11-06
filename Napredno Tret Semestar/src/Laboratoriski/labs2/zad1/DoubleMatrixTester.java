package Laboratoriski.labs2.zad1;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.*;

public class DoubleMatrixTester {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        DoubleMatrix fm = null;

        double[] info = null;

        DecimalFormat format = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            String operation = scanner.next();

            switch (operation) {
                case "READ": {
                    int N = scanner.nextInt();
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    double[] f = new double[N];

                    for (int i = 0; i < f.length; i++)
                        f[i] = scanner.nextDouble();

                    try {
                        fm = new DoubleMatrix(f, R, C);
                        info = Arrays.copyOf(f, f.length);

                    } catch (InsufficientElementsException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }

                    break;
                }

                case "INPUT_TEST": {
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    StringBuilder sb = new StringBuilder();

                    sb.append(R + " " + C + "\n");

                    scanner.nextLine();

                    for (int i = 0; i < R; i++)
                        sb.append(scanner.nextLine() + "\n");

                    fm = MatrixReader.read(new ByteArrayInputStream(sb
                            .toString().getBytes()));

                    info = new double[R * C];
                    Scanner tempScanner = new Scanner(new ByteArrayInputStream(sb
                            .toString().getBytes()));
                    tempScanner.nextDouble();
                    tempScanner.nextDouble();
                    for (int z = 0; z < R * C; z++) {
                        info[z] = tempScanner.nextDouble();
                    }

                    tempScanner.close();

                    break;
                }

                case "PRINT": {
                    System.out.println(fm.toString());
                    break;
                }

                case "DIMENSION": {
                    System.out.println("Dimensions: " + fm.getDimensions());
                    break;
                }

                case "COUNT_ROWS": {
                    System.out.println("Rows: " + fm.rows());
                    break;
                }

                case "COUNT_COLUMNS": {
                    System.out.println("Columns: " + fm.columns());
                    break;
                }

                case "MAX_IN_ROW": {
                    int row = scanner.nextInt();
                    try {
                        System.out.println("Max in row: "
                                + format.format(fm.maxElementAtRow(row)));
                    } catch (InvalidRowNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "MAX_IN_COLUMN": {
                    int col = scanner.nextInt();
                    try {
                        System.out.println("Max in column: "
                                + format.format(fm.maxElementAtColumn(col)));
                    } catch (InvalidColumnNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "SUM": {
                    System.out.println("Sum: " + format.format(fm.sum()));
                    break;
                }

                case "CHECK_EQUALS": {
                    int val = scanner.nextInt();

                    int maxOps = val % 7;

                    for (int z = 0; z < maxOps; z++) {
                        double work[] = Arrays.copyOf(info, info.length);

                        int e1 = (31 * z + 7 * val + 3 * maxOps) % info.length;
                        int e2 = (17 * z + 3 * val + 7 * maxOps) % info.length;

                        if (e1 > e2) {
                            double temp = work[e1];
                            work[e1] = work[e2];
                            work[e2] = temp;
                        }

                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(work, fm.rows(),
                                fm.columns());
                        System.out
                                .println("Equals check 1: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    if (maxOps % 2 == 0) {
                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(new double[]{3.0, 5.0,
                                7.5}, 1, 1);

                        System.out
                                .println("Equals check 2: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    break;
                }

                case "SORTED_ARRAY": {
                    double[] arr = fm.toSortedArray();

                    String arrayString = "[";

                    if (arr.length > 0)
                        arrayString += format.format(arr[0]) + "";

                    for (int i = 1; i < arr.length; i++)
                        arrayString += ", " + format.format(arr[i]);

                    arrayString += "]";

                    System.out.println("Sorted array: " + arrayString);
                    break;
                }

            }

        }

        scanner.close();
    }
}
class InsufficientElementsException extends Exception{
    public InsufficientElementsException(String msg){ super(msg); }
}

class InvalidRowNumberException extends Exception{
    public InvalidRowNumberException(String msg){ super(msg); }
}

class InvalidColumnNumberException extends Exception{
    public InvalidColumnNumberException(String msg){ super(msg); }
}


class DoubleMatrix{
    private double[][] arr;
    private int m, n;

    public DoubleMatrix(double[] arr, int m, int n) throws InsufficientElementsException{
        this.m = m; this.n = n;

        if( arr.length < m * n ){ throw new InsufficientElementsException("Insufficient number of elements"); }
        else if( arr.length > m * n ){
            int cnt = arr.length - (m * n); this.arr = new double[m][n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){ this.arr[i][j] = arr[cnt++]; }
            }
        }
        else{
            int cnt=0; this.arr = new double[m][n];
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){ this.arr[i][j] = arr[cnt++]; }
            }
        }
    }

    public String getDimensions(){ return String.format("[%d x %d]", m, n); }
    public int rows(){ return m; } public int columns(){ return n; }

    public double maxElementAtRow(int row) throws InvalidRowNumberException {
        if(row > m || row < 1) throw new InvalidRowNumberException("Invalid row number");
        return Arrays.stream(this.arr[row-1]).max().getAsDouble();
    }

    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
        if ( column > n || column < 1 ) throw new InvalidColumnNumberException("Invalid column number");
        double[] maxArr = new double[m];
        for(int i=0; i<m; i++){ maxArr[i] = this.arr[i][column - 1]; }

        return Arrays.stream(maxArr).max().getAsDouble();
    }

    public double sum(){
        double sum = 0.00;
        for(int i=0; i<m; i++){ for(int j=0; j<n; j++){ sum += arr[i][j]; } }
        return sum;
    }

    public double[] toSortedArray(){
        double[] fullArr = new double[m*n]; int cnt=0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                fullArr[cnt++] = arr[i][j];
            }
        }
        Arrays.sort(fullArr);
        for(int i=0; i<fullArr.length/2; i++){
            double temp = fullArr[i];
            fullArr[i] = fullArr[fullArr.length - i - 1];
            fullArr[fullArr.length - i - 1] = temp;
        }
        return fullArr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleMatrix that = (DoubleMatrix) o;
        return Arrays.deepEquals(arr, that.arr);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(arr);
    }

    @Override
    public String toString() {
        String finalString = "";
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                finalString += String.format("%.2f", arr[i][j]);
                if(j != n-1){ finalString += "\t"; }
            }
            if(i != m-1){ finalString += "\n"; }
        }
        return finalString;
    }
}

class MatrixReader{
    public static DoubleMatrix read(InputStream in) throws InsufficientElementsException{
        Scanner sc = new Scanner(in);
        int m = sc.nextInt(); int n = sc.nextInt();
        double[] matrix = new double[m*n];
        for(int i=0; i<m*n; i++){ matrix[i] = sc.nextDouble(); }
        return new DoubleMatrix(matrix, m, n);
    }
}
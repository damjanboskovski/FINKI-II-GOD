package Prv_Kolok.zad3;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Shapes1Test {
    public static void main(String[] args) throws IOException {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);
    }
}

class ShapesApplication{
    private List<Canvas> canvases;

    public ShapesApplication() {
    }

    public int readCanvases(InputStream in) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        canvases = bf.lines().map(x -> new Canvas(x)).collect(Collectors.toList());
        int cnt = 0;
        for (Canvas c : canvases){ cnt += c.count(); }
        bf.close();
        return cnt;
    }

    public void printLargestCanvasTo(OutputStream out){
        PrintWriter pw = new PrintWriter(new PrintStream(out));
        pw.println(max().toString());
        pw.close();
    }

    public Canvas max(){
        return canvases.stream().max(Comparator.naturalOrder()).get();
    }

}

class Canvas implements Comparable<Canvas>{
    private String id;
    private List<Integer> size;

    public Canvas(String str){
        String[] content = str.split("\\s+");
        id = content[0];
        size = new ArrayList<Integer>();
        for(int i=1; i<content.length; i++){
            size.add(Integer.parseInt(content[i]));
        }
    }

    public int count(){ return  size.size(); }

    public int sum(){
        int sum=0;
        for (int size : size){ sum += size; }
        return  sum*4;
    }

    @Override
    public int compareTo(Canvas o) {
        return Integer.compare(sum(), o.sum());
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", id, count(), sum());
    }
}
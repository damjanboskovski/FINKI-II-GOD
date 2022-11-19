package Prv_Kolok.zad1;
import java.util.Arrays;
import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class F1Test {
    public static void main(String[] args) throws IOException {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }
}

class F1Race{
    private List<Racer> racer;

    public F1Race() {

    }

    public void readResults(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        racer = br.lines().map(x -> new Racer(x)).collect(Collectors.toList());
        br.close();
    }

    public void printSorted(OutputStream out){
        PrintWriter pw = new PrintWriter(new PrintWriter(out)); sort();
        for(int i=0; i<racer.size(); i++){
            pw.println((i+1) + ". " + racer.get(i));
        }
        pw.close();
    }

    public void sort(){ racer.sort(Comparator.naturalOrder()); }
}

class Racer implements Comparable<Racer>{
    private String name, time;

    public Racer(String in){
        String[] content = in.split("\\s+");
        name = content[0];
        int timeRaw = Integer.MAX_VALUE, pos=0;
        for(int i=1; i<content.length; i++){
            int localTime = getRawTime(content[i]);
            if(localTime < timeRaw){
                timeRaw = localTime;
                pos = i;
            }
        }
        time = content[pos];
    }
    private int getRawTime(String time){
        String[] rawTime = time.split(":");
        return Integer.parseInt(rawTime[0])*60000 + Integer.parseInt(rawTime[1])*1000 + Integer.parseInt(rawTime[2]);
    }

    @Override
    public String toString() {
        String strName = name;
        while (strName.length()<10){ strName += " "; }
        String strTime = time;
        while (strTime.length()<10){ strTime = " " + strTime; }
        return strName + strTime;
    }

    @Override
    public int compareTo(Racer o) { return Integer.compare(getRawTime(time), o.getRawTime(o.time)); }
}
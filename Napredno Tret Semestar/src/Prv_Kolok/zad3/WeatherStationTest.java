package Prv_Kolok.zad3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.*;

public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurment(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}

class WeatherStation{
    private int days;
    List<Measurement> arr;

    public WeatherStation(int days) {
        this.days = days;
        this.arr = new ArrayList<Measurement>();
    }

    public void addMeasurment(float temp, float wind, float hum, float vis, Date date){
        deleteOldMeasurement(date);
        if(!isRecent(date)){
            arr.add(new Measurement(temp, wind, hum, vis, date));
        }
    }

    private void deleteOldMeasurement(Date newDate){
        long millsec = days * 24 * 3600 * 1000;
        List<Measurement> toRemove = new ArrayList<Measurement>();
        for(Measurement m : arr){
            long diff = newDate.getTime()-m.getDate().getTime();
            if(diff > millsec){ toRemove.add(m); }
        }
        toRemove.forEach(x -> arr.remove(x));
    }

    private boolean isRecent(Date newDate){
        if(arr.size() == 0){ return false; }
        return newDate.getTime() - arr.get(arr.size()-1).getDate().getTime() < 2.5 * 60 * 1000;
    }

    public int total(){ return arr.size(); }

    public void status(Date from, Date to){
        arr.sort(Comparator.naturalOrder());
        float temp=0; int size = 0;
        for (Measurement m : arr){
            if(m.getDate().compareTo(from) != -1 && m.getDate().compareTo(to) != 1){
                temp += m.getTemperature();
                System.out.println(m.toString());
                size++;
            }
        }
        if(size == 0) throw new RuntimeException();
        System.out.println(String.format("Average temperature: %.2f", temp/size));
    }
}

class Measurement implements Comparable<Measurement>{
    private float temperature, wind, humidity, visibility;
    Date date;

    public Measurement(float temperature, float wind, float humidity, float visibility, Date date) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    public Date getDate(){ return date; }
    public float getTemperature(){ return temperature; }

    @Override
    public String toString() {
        return temperature + " " + wind + " km/h " + humidity + "% " + visibility + " km " + date.toString();
    }

    @Override
    public int compareTo(Measurement o) {
        return date.compareTo(o.date);
    }
}
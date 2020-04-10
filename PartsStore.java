import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.*;

public class PartsStore {
    private List<List<String>> records = new ArrayList<>();
    public PartsStore(){
        ParseFile();
    }
    public void FindPartsWithBrand(String type, String brand){
        records.stream().filter(n -> n.get(1).equals(brand) && (type == null || n.get(0).equals(type))).map(n -> {
            String temp = String.join(",",n);
            return temp;
        }).forEach(System.out::println);
    }

    public void TotalPrice(String type, String brand, String model){
        Double temp = (records.stream().filter(n -> !(type == null && brand == null && model == null) && (model == null || n.get(2).equals(model)) && (brand == null || n.get(1).equals(brand))  && (type == null || n.get(0).equals(type)))
                .mapToDouble(n -> Double.parseDouble(n.get(n.size()-1)
                        .replaceAll("USD", "")))
                .reduce(0,(a,b) -> a+b));
        System.out.println(temp + " USD");
    }

    public void UpdateStock(){
        long a = records.stream().filter(n -> n.get(n.size()-1).equals("0.00 USD")).count();
        records.removeIf(first -> first.get(first.size()-1).equals("0.00 USD"));
        System.out.println(a + " items removed.");
    }

    public void FindCheapestMemory(int capacity){
        System.out.println(records.stream().filter(n -> n.get(0).equals("Memory") && Integer.parseInt(n.get(4).replaceAll("[^0-9]", ""))>=capacity)
                .min(Comparator.comparing(n -> Double.parseDouble(n.get(n.size()-1)
                        .replaceAll(" USD", "")))).map(n -> {
            String temp = String.join(",",n);
            return temp;
        }).get());
    }

    public void FindFastestCPU(){
        System.out.println(records.stream().filter(n -> n.get(0).equals("CPU"))
                .max(Comparator.comparing(n -> Double.parseDouble(n.get(3))*Double.parseDouble(n.get(4).replaceAll("GHz", "")))).map(n -> {
            String temp = String.join(",",n);
            return temp;
        }).get());
    }

    public void ParseFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("pcparts.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        } catch (IOException e) {
            System.out.print("IOException");
        }
    }
}

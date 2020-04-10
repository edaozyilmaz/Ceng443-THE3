import java.io.IOException;
public class Main{
    public static void main(String[] args) throws IOException{
        PartsStore obj = new PartsStore();
        obj.FindPartsWithBrand(null,"Lenovo");
        obj.FindCheapestMemory(16);
        obj.UpdateStock();
        //obj.FindFastestCPU();
        obj.FindCheapestMemory(16);
        obj.TotalPrice("GPU", "Asus", "GeForce RTX 2080");
        obj.FindFastestCPU();
    }

}
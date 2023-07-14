import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class JSONData {
    private JSONArray products;

    public JSONData(JSONArray products) {
        this.products = products;
    }

    public void displayData() {
        System.out.println("Data JSON:");
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            System.out.println("ID: " + product.getInt("ID"));
            System.out.println("Name: " + product.getString("Name"));
            System.out.println("Rating: " + product.getDouble("Rating"));
            System.out.println("Price: " + product.getDouble("Price"));
            System.out.println("------------------------");
        }
    }

    public void searchByRating(double rating) {
        System.out.println("Data dengan rating " + rating + ":");
        boolean found = false;
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            if (product.getDouble("Rating") == rating) {
                System.out.println("ID: " + product.getInt("ID"));
                System.out.println("Name: " + product.getString("Name"));
                System.out.println("Rating: " + product.getDouble("Rating"));
                System.out.println("Price: " + product.getDouble("Price"));
                System.out.println("------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Data dengan rating " + rating + " tidak ditemukan.");
        }
    }

    public void sortByRating() {
        for (int i = 0; i < products.length() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < products.length(); j++) {
                JSONObject product1 = products.getJSONObject(j);
                JSONObject product2 = products.getJSONObject(minIndex);
                if (product1.getDouble("Rating") < product2.getDouble("Rating")) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                JSONObject temp = products.getJSONObject(i);
                products.put(i, products.getJSONObject(minIndex));
                products.put(minIndex, temp);
            }
        }
        System.out.println("Data diurutkan berdasarkan rating:");
        displayData();
    }

    public static void main(String[] args) {
        // Sample JSON data
        String jsonData = "{\"products\":[{\"ID\":1,\"Name\":\"Laptop A\",\"Rating\":4.5,\"Price\":1000.0},{\"ID\":2,\"Name\":\"Laptop B\",\"Rating\":4.2,\"Price\":800.0},{\"ID\":3,\"Name\":\"Laptop C\",\"Rating\":4.8,\"Price\":1200.0},{\"ID\":4,\"Name\":\"Laptop D\",\"Rating\":4.0,\"Price\":900.0}]}";

        // Parse JSON data
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray products = jsonObject.getJSONArray("products");

        // Create JSONData object
        JSONData jsonDataObj = new JSONData(products);

        // Display menu
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Tampilkan semua data");
            System.out.println("2. Cari data berdasarkan rating");
            System.out.println("3. Urutkan data berdasarkan rating");
            System.out.println("4. Keluar");
            System.out.print("Pilihan: ");
            choice = scanner.nextInt();
            System.out.println("------------------------");
            switch (choice) {
                case 1:
                    jsonDataObj.displayData();
                    break;
                case 2:
                    System.out.print("Masukkan rating yang ingin dicari: ");
                    double rating = scanner.nextDouble();
                    jsonDataObj.searchByRating(rating);
                    break;
                case 3:
                    jsonDataObj.sortByRating();
                    break;
                case 4:
                    System.out.println("Keluar dari aplikasi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
            System.out.println("------------------------");
        } while (choice != 4);
    }
}


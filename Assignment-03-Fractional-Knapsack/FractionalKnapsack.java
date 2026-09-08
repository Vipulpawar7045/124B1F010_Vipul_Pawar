import java.io.*;
import java.util.*;

public class FractionalKnapsack {

    static class Item {
        String name;
        double weight;
        double price;
        double ratio;

        Item(String name, double weight, double price) {
            this.name = name;
            this.weight = weight;
            this.price = price;
            this.ratio = price / weight;
        }
    }

    static void sortItems(Item[] items) {
        Arrays.sort(items, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return Double.compare(b.ratio, a.ratio);
            }
        });
    }

    static String[] parseCSV(String line) {
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean quotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                quotes = !quotes;
            } else if (c == ',' && !quotes) {
                fields.add(field.toString().trim());
                field.setLength(0);
            } else {
                field.append(c);
            }
        }

        fields.add(field.toString().trim());

        return fields.toArray(new String[0]);
    }

    public static void main(String[] args) {

        String fileName = "shipping_data.csv";
        double capacity = 1000.0;

        ArrayList<Item> itemList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = parseCSV(line);

                if (data.length < 3) {
                    continue;
                }

                try {
                    String name = data[0];
                    double price = Double.parseDouble(data[1]);
                    double weight = Double.parseDouble(data[2]);

                    if (weight > 0 && price > 0) {
                        itemList.add(new Item(name, weight, price));
                    }

                } catch (NumberFormatException e) {
                    continue;
                }
            }

            br.close();

            Item[] items = itemList.toArray(new Item[0]);

            sortItems(items);

            double remainingCapacity = capacity;
            double totalValue = 0.0;
            double totalWeight = 0.0;

            System.out.println();
            System.out.println("Fractional Knapsack - Cargo Shipment");
            System.out.println();
            System.out.printf("Container Capacity: %.2f kg%n", capacity);
            System.out.println();

            System.out.printf("%-30s %-12s %-12s %-15s %-12s%n",
                    "Product Name", "Weight", "Price", "Price/Weight", "Taken");

            int count = 0;

            for (Item item : items) {

                if (remainingCapacity <= 0 || count == 15) {
                    break;
                }

                double takenWeight;
                double obtainedValue;
                String taken;

                if (item.weight <= remainingCapacity) {

                    takenWeight = item.weight;
                    obtainedValue = item.price;
                    taken = "Full";

                } else {

                    takenWeight = remainingCapacity;
                    obtainedValue = item.price * (remainingCapacity / item.weight);
                    taken = "Partial";
                }

                totalWeight += takenWeight;
                totalValue += obtainedValue;
                remainingCapacity -= takenWeight;

                System.out.printf("%-30s %-12.2f %-12.2f %-15.2f %-12s%n",
                        item.name,
                        takenWeight,
                        obtainedValue,
                        item.ratio,
                        taken);

                count++;
            }

            System.out.println();
            System.out.printf("Total Weight Used: %.2f kg%n", totalWeight);
            System.out.printf("Remaining Capacity: %.2f kg%n", remainingCapacity);
            System.out.printf("Maximum Total Value: %.2f%n", totalValue);

        } catch (FileNotFoundException e) {
            System.out.println("shipping_data.csv file not found.");

        } catch (IOException e) {
            System.out.println("Error while reading the dataset.");
        }
    }
}
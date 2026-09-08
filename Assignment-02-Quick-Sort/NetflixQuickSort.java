import java.io.*;
import java.util.*;

public class NetflixQuickSort {

    static class Show {
        String showId;
        String type;
        String title;
        int releaseYear;

        Show(String showId, String type, String title, int releaseYear) {
            this.showId = showId;
            this.type = type;
            this.title = title;
            this.releaseYear = releaseYear;
        }
    }

    static void swap(Show[] shows, int i, int j) {
        Show temp = shows[i];
        shows[i] = shows[j];
        shows[j] = temp;
    }

    static int partition(Show[] shows, int low, int high) {
        int pivot = shows[high].releaseYear;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (shows[j].releaseYear <= pivot) {
                i++;
                swap(shows, i, j);
            }
        }

        swap(shows, i + 1, high);
        return i + 1;
    }

    static void quickSort(Show[] shows, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(shows, low, high);

            quickSort(shows, low, pivotIndex - 1);
            quickSort(shows, pivotIndex + 1, high);
        }
    }

    static String[] parseCSVLine(String line) {
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                if (insideQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    insideQuotes = !insideQuotes;
                }
            } else if (c == ',' && !insideQuotes) {
                fields.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        fields.add(current.toString().trim());
        return fields.toArray(new String[0]);
    }

    public static void main(String[] args) {

        String fileName = "netflix_titles.csv";
        ArrayList<Show> showList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = parseCSVLine(line);

                if (data.length < 9) {
                    continue;
                }

                String showId = data[0];
                String type = data[1];
                String title = data[2];

                if (data[7].isEmpty()) {
                    continue;
                }

                int releaseYear;

                try {
                    releaseYear = Integer.parseInt(data[7]);
                } catch (NumberFormatException e) {
                    continue;
                }

                showList.add(new Show(showId, type, title, releaseYear));
            }

            br.close();

            Show[] shows = showList.toArray(new Show[0]);

            quickSort(shows, 0, shows.length - 1);

            System.out.println();
            System.out.println("Netflix Content Sorted by Release Year");
            System.out.println();

            System.out.printf("%-12s %-12s %-45s %-12s%n",
                    "Show ID", "Type", "Title", "Release Year");

            for (Show show : shows) {
                System.out.printf("%-12s %-12s %-45s %-12d%n",
                        show.showId,
                        show.type,
                        show.title,
                        show.releaseYear);
            }

        } catch (FileNotFoundException e) {
            System.out.println("netflix_titles.csv file not found.");
        } catch (IOException e) {
            System.out.println("Error while reading the dataset.");
        }
    }
}
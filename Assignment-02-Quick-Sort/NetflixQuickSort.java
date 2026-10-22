```java
import java.util.Scanner;

class NetflixShow {
    String showId;
    String type;
    String title;
    int releaseYear;

    NetflixShow(String showId, String type, String title, int releaseYear) {
        this.showId = showId;
        this.type = type;
        this.title = title;
        this.releaseYear = releaseYear;
    }
}

public class NetflixQuickSort {

    static void quickSort(NetflixShow[] shows, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(shows, low, high);
            quickSort(shows, low, pivotIndex - 1);
            quickSort(shows, pivotIndex + 1, high);
        }
    }

    static int partition(NetflixShow[] shows, int low, int high) {
        int pivot = shows[high].releaseYear;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (shows[j].releaseYear <= pivot) {
                i++;

                NetflixShow temp = shows[i];
                shows[i] = shows[j];
                shows[j] = temp;
            }
        }

        NetflixShow temp = shows[i + 1];
        shows[i + 1] = shows[high];
        shows[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        NetflixShow[] shows = {
            new NetflixShow("s100", "Movie", "The Lost World", 1925),
            new NetflixShow("s200", "Movie", "The Great Dictator", 1940),
            new NetflixShow("s300", "Movie", "Double Indemnity", 1944),
            new NetflixShow("s400", "Movie", "Seven Samurai", 1954),
            new NetflixShow("s500", "Movie", "Pather Panchali", 1955),
            new NetflixShow("s600", "Movie", "The Ten Commandments", 1956),
            new NetflixShow("s700", "Movie", "Vertigo", 1958),
            new NetflixShow("s800", "Movie", "North by Northwest", 1959),
            new NetflixShow("s900", "Movie", "Psycho", 1960),
            new NetflixShow("s1000", "Movie", "Breakfast at Tiffany's", 1961)
        };

        System.out.print("Enter the column to sort by: ");
        String column = sc.nextLine().trim();

        if (!column.equalsIgnoreCase("Release Year")) {
            System.out.println("Invalid column. Please enter Release Year.");
            sc.close();
            return;
        }

        quickSort(shows, 0, shows.length - 1);

        System.out.println("\nNetflix Content Sorted by Release Year\n");

        System.out.printf("%-12s %-12s %-45s %-12s%n",
                "Show ID", "Type", "Title", "Release Year");

        for (NetflixShow show : shows) {
            System.out.printf("%-12s %-12s %-45s %-12d%n",
                    show.showId, show.type, show.title, show.releaseYear);
        }

        sc.close();
    }
}
```

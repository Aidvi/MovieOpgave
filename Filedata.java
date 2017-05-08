import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class Filedata {

    File movieData = new File("Netlix.txt");
    ArrayList<Movie> movies = new ArrayList<>();

    ObjectInputStream ois = null;

    ObjectOutputStream oos = null;
    FileOutputStream fos = null;

    public Filedata() {
    }

    public void printMovies() {
        for (int i = 0; i < this.movies.size(); i++) {
            Movie currentMovie = this.movies.get(i);
            System.out.println(currentMovie.getTitle() + " / " + currentMovie.getProductionYear() + " / " + currentMovie.getMainActor() + " / " + currentMovie.getSubActor());
        }
    }

    public void createFile() {
        try {
            movieData.createNewFile();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @SuppressWarnings("unchecked")
    public void fileToArray() {
        try {
            FileInputStream fis = new FileInputStream(movieData);
            ObjectInputStream ois = new ObjectInputStream(fis);
            movies = (ArrayList<Movie>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error fileToArray");
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (Exception e) {
                    System.out.println("Error fileToArray");
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void arrayToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(movieData);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(movies);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error ArrayToFile");
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (Exception e) {
                    System.out.println("ErrorArraytoFile");
                }
            }
        }
    }

    public void createMovie() {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Please type the Title of the movie you want to create: ");
            String title = scan.nextLine();

            System.out.println("Please type the production year of the movie: ");
            int year = Integer.parseInt(scan.nextLine());

            System.out.println("Please type the MainActor of the movie: ");
            String mainActor = (scan.nextLine());

            System.out.println("Please type the SubActor of the movie: ");
            String subActor = (scan.nextLine());

            Movie newMovie = new Movie(title, year, mainActor, subActor);

            movies.add(newMovie);

            arrayToFile();
        } catch (Exception e) {
            System.out.println("Error createMovie");
        }
    }
    public Movie searchMovie(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Search for a movie you want to see:");
        String input = scan.nextLine();

        for(int i = 0; i < this.movies.size(); i++){
            Movie currentMovie = this.movies.get(i);
                if(currentMovie.getTitle().contains(input)){
                    return currentMovie;
            }
        }

        System.out.println("Invalid movie:");
        return this.searchMovie();
    }
    public void deleteMovie(){
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < this.movies.size(); i++){
            Movie currentMovie = this.movies.get(i);
            System.out.print("Index:" + i + " : ");
            System.out.println(currentMovie.getTitle());
        }
        System.out.println("Which movie do you want to delete: ");
        int index = scan.nextInt();
        movies.remove(index);

        this.arrayToFile();
    }
    public void watchMovie(User user){
        Scanner scan = new Scanner(System.in);
        Movie currentMovie = this.searchMovie();
        System.out.println("The search found this movie: " + currentMovie.toString());
        System.out.println("Do you want to watch this movie? y/n");
        String answer = scan.next();
            if(answer.equalsIgnoreCase("y")){
                System.out.println("Movie loading......");
                System.out.println("You are now watching this Briliant movie: " + currentMovie.getTitle());
                System.out.println("With these terific actors: " + currentMovie.getMainActor() + " / " + currentMovie.getSubActor());
                System.out.println("Produced in the " + currentMovie.getProductionYear() + ". What a amazing year for Filming Movies!!");
                System.out.println();

                user.addFavorite(currentMovie);
                user.addHistory(currentMovie);
            } else { 
                System.out.println("Searching for a movie again then.....");
                this.watchMovie(user);
        }
    }




    /*public void EfileToArray(){
        try{
            Scanner scan = new Scanner(movieData);

            while(scan.hasNextLine()){
                movies.add(new Movie(scan.nextLine(), Integer.parseInt(scan.nextLine()), scan.nextLine(), scan.nextLine()));
            }

        } catch(Exception e)
        {
            System.out.println("Error FileToArray");
        }
    }
   /* public void EarrayToFile(){
        try {
            PrintStream moviePrint = new PrintStream(movieData);
            for(int i = 0; i < movies.size(); i++){
                moviePrint.print(movies.get(i).getTitle() + " " + movies.get(i).getProductionYear() + " " + movies.get(i).getMainActor() + " " + movies.get(i).getSubActor());
                
                if(i != movies.size() -1){
                    moviePrint.println();
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error 404");
        }
    }*/
}

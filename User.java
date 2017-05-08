import java.io.Serializable;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
public class User implements Serializable {

    private String email;
    private String password;
    ArrayList<Movie> favorites = new ArrayList<>();
    ArrayList<Movie> history = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }
    public String toString(){
        return this.email + this.password;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFileName() {
        return "data/users/" + this.email + ".txt";
    }

    @SuppressWarnings("unchecked")
    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(getFileName());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error ArrayToFile");
            e.printStackTrace();
        }
    }

    public void addFavorite(Movie movie) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want to add " + movie.getTitle() + " to your favorite list? y/n");
        String answer = scan.next();
            if(answer.equalsIgnoreCase("y")){
                this.favorites.add(movie);
                this.saveToFile();
            } else {
                System.out.println("The movie wont be added to your favorites");
        }
    }
    public void addHistory(Movie movie){
      this.history.add(movie);
      this.saveToFile();
    }
    public void printFavorite(){
        for (int i = 0; i < this.favorites.size(); i++) {
            Movie currentMovie = this.favorites.get(i);
            System.out.println(currentMovie.getTitle() + "," + currentMovie.getProductionYear() + "," + currentMovie.getMainActor() + "," + currentMovie.getSubActor());
        }
    }
    public void printHistory(){
        for (int i = 0; i < this.history.size(); i++) {
            Movie currentMovie = this.history.get(i);
            System.out.println(currentMovie.getTitle() + "," + currentMovie.getProductionYear() + "," + currentMovie.getMainActor() + "," + currentMovie.getSubActor());
        }
    }
}

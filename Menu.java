import java.util.Scanner;

public class Menu {
    Filedata data = new Filedata();
    User user = null;
    public void mainMenu(){
        this.data.fileToArray();
        Scanner scan = new Scanner(System.in);
        System.out.println("Press 1 if you like to <<<Login>>>");
        System.out.println("Press 2 if you like to <<<Register>>>");
        System.out.println("Press 3 if you like to <<<Create Movie>>>");
        System.out.println("Press 4 if you like to <<<Delete Movie>>>");

        String input = scan.nextLine();

        switch(input) {
            case "1":
               this.user = Login.login();
               this.subMenu();
               break;
            case "2":
                Login.createUser();
                this.mainMenu();
                break;
            case "3":
                this.data.createMovie();
                this.mainMenu();
                break;
            case "4":
                this.data.deleteMovie();
                this.mainMenu();
                break;
        }
    }
     public void subMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("What would you like to do?");
        System.out.println("Press 1 if you like to <<<Watch Movie>>>"); 
        System.out.println("Press 2 if you like to <<<Search Movie>>>"); 
        System.out.println("Press 3 if you like to <<<Display Movies>>>");
        System.out.println("Press 4 if you like to <<<Display favorites>>>");
        System.out.println("Press 5 if you like to <<<Display history>>>");
        System.out.println("Press 6 if you like to <<<Exit program>>>");

        String input = scan.nextLine();

        switch(input) {
            case "1":
                System.out.println();
                this.data.watchMovie(this.user);
                System.out.println();
                this.subMenu();
                break;
            case "2":
                System.out.println();
                Movie foundMovie = this.data.searchMovie();
                System.out.println(foundMovie.toString());
                System.out.println();
                this.subMenu();
                break;
            case "3":
                System.out.println();
                this.data.printMovies();
                System.out.println();
                this.subMenu();
                break;
            case "4":
                System.out.println();
                this.user.printFavorite();
                System.out.println();
                this.subMenu();
                break;
            case "5":
                System.out.println();
                this.user.printHistory();
                System.out.println();
                this.subMenu();
                break;
            case "6":
                System.out.println();
                System.out.println("Thank you for using Netlix - good bye!");
                this.data.arrayToFile();
                return;
            default:
                System.out.println();
                System.out.println("Invalid input");
                System.out.println();
                this.subMenu();
        }
     }
}
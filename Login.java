import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Login {

    private static ArrayList<User> users = new ArrayList<User>();

    public Login() {
    }

    /*public void usersToFile(){
        for(int i = 0; i < this.users.size(); i++){
            User currentUser = this.users.get(i);
            try {
                FileOutputStream fos = new FileOutputStream(currentUser.getEmail() + ".txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(currentUser);
                oos.close();
            } catch (Exception e) {
                System.out.println("Error ArrayToFile");
                e.printStackTrace();
            }
        }
    }*/
    public static void createUser() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter email:");
        String inputEmail = scan.nextLine();

        System.out.print("Create Password:");
        String inputPassword = scan.nextLine();

        User user = new User(inputEmail, inputPassword);

        try {
            String path = "data/users/" + inputEmail + ".txt";
            File userFile = new File(path);
            userFile.createNewFile();
        } catch (Exception e) {
            System.out.println("Error createUser");
        }
        user.saveToFile();
    }

    @SuppressWarnings("unchecked")
    public static User login() {
        Scanner scan = new Scanner(System.in);
        User currentUser = null;
        String inputPassword = null;
            
        System.out.print("Enter email:");
        String inputEmail = scan.nextLine();
        try {
            String path = "data/users/" + inputEmail + ".txt";
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            currentUser = (User) ois.readObject();
            ois.close();
            Login.users.add(currentUser);
        } catch (Exception e) {
            System.out.println("Error Login");
            return Login.login();
        }   

        while(!currentUser.getPassword().equalsIgnoreCase(inputPassword)){
            System.out.println("Enter Password");
            inputPassword = scan.nextLine();
        }
        return currentUser;
    }

}


import java.io.Serializable;

public class Movie implements Serializable {

    private String title;
    private int productionYear;
    private String mainActor;
    private String subActor;
    private static final long serialVersionUID = 2L;

    public Movie(String title, int productionYear, String mainActor, String subActor) {
        this.title = title;
        this.productionYear = productionYear;
        this.mainActor = mainActor;
        this.subActor = subActor;
    }
    public String toString(){
        return this.title + " / " + Integer.toString(this.productionYear) + " / " + this.mainActor + " / " + this.subActor;
    }
    public String getTitle() {
        return title;
    }

    public String getMainActor() {
        return mainActor;
    }

    public String getSubActor() {
        return subActor;
    }

    public int getProductionYear() {
        return productionYear;
    }
}

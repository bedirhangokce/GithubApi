/**
 * Representation of user for printing
 */
public class UserInfo {
    String repo;
    String name;
    String location;
    String company;
    int contributions;

    public UserInfo(String repo, String name, String location, String company, int contributions) {
        this.repo = repo;
        this.name = name;
        this.location = location;
        this.company = company;
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        return "repo:" + repo +
                ", name:" + name +
                ", location:" + location +
                ", company:" + company +
                ", contributions:" + contributions + "\n";
    }
}

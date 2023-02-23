import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GithubApi {
    private static final String apiUrl = "https://api.github.com";
    private static final String userEndpoint = "/users/";
    private static final String contributorsEndpoint = "/contributors";
    private static final String repoEndpoint = "/repos/apache/";
    private static final int numOfUsers = 10;
    // 5 Most starred(probably most downloaded) repositories created by apache
    private static final String[] repoNames = {"echarts", "superset", "dubbo", "spark", "airflow"};


    /**
     * Method that creates and returns UserInfo object with given parameters and response taken with username.
     * @param repo
     * @param username
     * @param contributions
     * @return
     * @throws IOException
     */
    private static UserInfo getUserInfo(String repo,String username, int contributions) throws IOException {
        String userUrl = apiUrl + userEndpoint + username;
        JSONObject response = new JSONObject(Connector.connect(userUrl));
        return new UserInfo(repo, username, response.get("location").toString(), response.get("company").toString(), contributions);
    }

    /**
     * Method that finds most contributed users with given repo name.
     * @param repoName
     * @return
     * @throws IOException
     */
    private static List<UserInfo> getMostContributedUsers(String repoName) throws IOException {
        List<UserInfo> contributions = new ArrayList<>();
        String repoUrl = apiUrl + repoEndpoint + repoName + contributorsEndpoint;

        JSONArray users = new JSONArray(Connector.connect(repoUrl));
        //add first 10 or less(in case there is less contributors than we want to get) users to list
        for (int i = 0; i < Math.min(numOfUsers,users.length()); i++){
            JSONObject user = (JSONObject) users.get(i);
            // Adds UserInfo object, which is creating with "getUserInfo" method, to the list
            contributions.add(getUserInfo(repoName,user.get("login").toString(), Integer.parseInt(user.get("contributions").toString())));
        }
        return contributions;
    }

    public static void main(String[] args) throws IOException {
        try {
            // File to write info
            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // Iterate through repo names
            for (String repo : repoNames){
                // Iterate through users list which is returned from getMostContributedUsers method
                for (UserInfo user : getMostContributedUsers(repo)){
                    // Write user info to the file
                    bufferedWriter.write(user.toString());
                }
            }
            bufferedWriter.close();
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        System.out.println("Number of created connections:" + Connector.numberOfConnections);

        System.out.println("Press any key for close");
        new Scanner(System.in).nextLine();
    }


}
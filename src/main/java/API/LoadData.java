package API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.opencsv.CSVReader;

import org.json.JSONArray;
import org.json.JSONTokener;


// A class used to load the data from a file to a Map object
/**
 * used for loading data from the file and deserializing it into objects
 * @author Sarunas
 */
class LoadData {
    private static Map<String, Team> teams = new HashMap<>();
    
    // Method which loads data from csv file
    static Map<String, Team> loadCSV(){
        List<String[]> allRows = null;

        // Reading the whole file to a List of String arrays
        try (CSVReader reader = new CSVReader(new FileReader("src\\main\\resources\\webscraper\\data\\teamsDataCSV.csv"))) {
            allRows = reader.readAll();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        // Using each line of the file to create team objects and put them into the Map. The key of a team gets incremented every time a new player is created.
        for(String[] row : allRows){
            List<Player> players = new ArrayList<>();
            int j = 0;
            for(int i = 4; i < row.length-4; i++){
                if(j%17 == 0){
                    Stats stats = new Stats(row[i+5], row[i+6], row[i+7], row[i+8], row[i+9], row[i+10], row[i+11], row[i+12], row[i+13], row[i+14], row[i+15], row[i+16]);
                    Player player = new Player(row[i], row[i+1], row[i+2], row[i+3], row[i+4], stats);
                    if(!(player.getName().equals(""))){
                        players.add(player);
                    }
                }
                j++;
            }
            int pos = row[0].indexOf(" ") + 1;
            String urlPath = row[0].substring(pos).toLowerCase();
            teams.put(urlPath, new Team(row[0], row[1], row[2], row[3], players));           
        }

        return teams;
    }

    // Method which loads data from json file
    /**
        * Reads the json file, stores its data in a JSONArray and deserializes it into team objects.
        * Then it stores the teams in a map
        * @return {@literal Map<String ,ArrayList<Team>>}
        *    - a map of all the teams of both conferences
     */
    static Map<String ,ArrayList<Team>> loadJSON(){
        Map<String ,ArrayList<Team>> teams = new HashMap<>();
        ArrayList<Team> eastTeams = new ArrayList<>();
        ArrayList<Team> westTeams = new ArrayList<>();
        JSONArray object = null;
        try {
            InputStream is = new FileInputStream("src" + File.separator + "main" + File.separator + "resources" + File.separator + "webscraper" + File.separator + "data" + File.separator + "teamsData.json");
            JSONTokener tokener = new JSONTokener(is);
            object = new JSONArray(tokener); 
            Gson gson = new Gson();
            for (Object item : object.getJSONObject(0).getJSONArray("eastTeams")) {
                Team team = gson.fromJson(item.toString(), Team.class);
                eastTeams.add(team);
            }
            for (Object item : object.getJSONObject(1).getJSONArray("westTeams")) {
                Team team = gson.fromJson(item.toString(), Team.class);
                westTeams.add(team);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        teams.put("eastTeams", eastTeams);
        teams.put("westTeams", westTeams);
        return teams;
    }


}

package API;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/**
* DataController is the class we use to scrape the needed data, create the objects and routes of the API
* @author Simon
* @author Sarunas
*/
@RestController
@EnableScheduling
public class DataController {

    // Teams object that holds the basketball data in a Map object
    // Teams.getTeams() method returns that Map object
    Teams teams = new Teams();

    /** Scheduled task which calls a method every 6 hours.
     *  That method calls Teams class' update method which scrapes the basketball data thus updating it
     */

    // Scheduled fixed delay (every 6 hours) task re-scrapping the data
    @Scheduled(fixedDelay = 21600000) 
    public void updateTeamsData(){
        teams.update();
    }

    // Test route to test if the routes return an appropriate HTTP error header if there is no data to return

    Map<String, ArrayList<Team>> testNull = null;
    /**
     * Test route to test if the routes return an appropriate HTTP error header if there is no data to return
     * @return {@literal Map<String, ArrayList<Team>>} a null object
     */
    @GetMapping("/testNull")
    public Map<String, ArrayList<Team>> testNull(){
        if (testNull == null ) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        return testNull;
    }
    
    /**
     * Returns all teams in the league with all of their data
     * @return {@literal Map<String, ArrayList<Team>>} map that has a conference name as a key and the list of it's teams as a value
     */
    @GetMapping("/teams")
    public Map<String, ArrayList<Team>> teams(){
        if(teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        return teams.getTeams();
    }

    /**
     * Displays the data of all the teams in the specified conference
     * @param confName
     *  The name of the requested conference
     * @return {@literal ArrayList<Team>}
     *    - a list of all the teams in the conference
     * @throws ResponseStatusException
     *   If the list of teams is not yet loaded or the entered conference name doesn't exist
     */
    @GetMapping("/teams/{confName}")   
    public ArrayList<Team> team(@PathVariable String confName){
        if (teams.getTeams() == null)
        {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        } 
        if(!teams.getTeams().containsKey(confName)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong parameter entered. Try again");
        }
        return teams.getTeams().get(confName);
    }

    /**
     * Displays the data of the specified team in a specified conference
        * @param confName
        *  the name of the requested conference
        * @param team
        *   - the name of the requested team
        * @return Team
        *   - the requested team
        * @throws ResponseStatusException
        *   If the list of teams is not yet loaded or the entered conference name doesn't exist
     */
    @GetMapping("/teams/{confName}/{team}")
    public Team player(@PathVariable String confName, @PathVariable String team) {
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        for (Team t : teams.getTeams().get(confName)) {
            if(t.getName().replace(" ", "").toLowerCase().equals(team)){
                return t;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong parameter entered. Try again");
    }

    // Returns all teams in an array
    /**
        * Creates one list of both conferences' teams
        * @return {@literal ArrayList<Team>}
        *    - a list of all the teams
     */
    public ArrayList<Team> allTeams(){
        ArrayList<Team> allTeams = new ArrayList<>();
        for (Team team : teams.getTeams().get("eastTeams")) {
            allTeams.add(team);
        }
        for (Team team : teams.getTeams().get("westTeams")) {
            allTeams.add(team);
        }
        return allTeams;
    }
    
    /**
        * Displays the teams sorted by the amount of won games
        * @return {@literal List<Team>}
        *  - a list teams sorted by the amount of won games
    */
    @GetMapping("/teams/wins")
    public List<Team> wins(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Team> allTeams = allTeams();
        List <Team> sortedTeams = allTeams.stream().sorted((Team t1, Team t2)->t2.getWins().compareTo(t1.getWins())).collect(Collectors.toList());
        return sortedTeams;
    }

    /**
        * Displays the teams sorted by the amount of lost games
        * @return {@literal List<Team>}
        *  - a list teams sorted by the amount of lost games
    */
    @GetMapping("/teams/losses")
    public List<Team> losses(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Team> allTeams = allTeams();
        List <Team> sortedTeams = allTeams.stream().sorted((Team t1, Team t2)->t1.getWins().compareTo(t2.getWins())).collect(Collectors.toList());
        return sortedTeams;
    }

    /**
        * Displays the teams sorted by the average amount of points scored
        * @return {@literal List<Team>}
        *  - a list teams sorted by the average amount of points scored
    */
    @GetMapping("/teams/ppg")
    public List<Team> ppg(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Team> allTeams = allTeams();
        List <Team> sortedTeams = allTeams.stream().sorted((Team t1, Team t2)->t2.getPpg().compareTo(t1.getPpg())).collect(Collectors.toList());
        return sortedTeams;
    }

    /**
        * Creates one list of all players in the league
        * @return {@literal ArrayList<Player>}
        *    - a list of all players
     */
    @GetMapping("/players")
    public ArrayList<Player> allPlayers(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (Team team : allTeams()) {
            for (Player player : team.getPlayers()) {
                allPlayers.add(player);
            }
        }
        return allPlayers;
    }

    /**
        * Displays the players sorted by the average amount of points scored
        * @return {@literal List<Player>}
        *  - a list players sorted by the average amount of points scored
    */
    @GetMapping("/players/pts")
    public List<Player> pts(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getPts().compareTo(p1.getStats().getPts())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by the average amount of assists recorded
        * @return {@literal List<Player>}
        *  - a list players sorted by the average amount of assists recorded
    */
    @GetMapping("/players/ast")
    public List<Player> ast(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getAst().compareTo(p1.getStats().getAst())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by the average amount of rebounds recorded
        * @return {@literal List<Player>}
        *  - a list players sorted by the average amount of rebounds recorded
    */
    @GetMapping("/players/trb")
    public List<Player> trb(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getTrb().compareTo(p1.getStats().getTrb())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by the average amount of steals recorded
        * @return {@literal List<Player>}
        *  - a list players sorted by the average amount of steals recorded
    */
    @GetMapping("/players/stl")
    public List<Player> stl(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getStl().compareTo(p1.getStats().getStl())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by the average amount of blocks recorded
        * @return {@literal List<Player>}
        *  - a list players sorted by the average amount of blocks recorded
    */
    @GetMapping("/players/blk")
    public List<Player> blk(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getBlk().compareTo(p1.getStats().getBlk())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by the average amount of turnovers recorded
        * @return {@literal List<Player>}
        *  - a list players sorted by the average amount of turnovers recorded
    */
    @GetMapping("/players/tov")
    public List<Player> tov(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getTov().compareTo(p1.getStats().getTov())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by the average amount of personal fouls recorded
        * @return {@literal List<Player>}
        *  - a list players sorted by the average amount of personal fouls recorded
    */
    @GetMapping("/players/pf")
    public List<Player> pf(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getPf().compareTo(p1.getStats().getPf())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by the average amount of minutes played
        * @return {@literal List<Player>}
        *  - a list players sorted by the average amount of minutes played
    */
    @GetMapping("/players/mpg")
    public List<Player> mpg(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getMinPerGame().compareTo(p1.getStats().getMinPerGame())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by their field goal percentage
        * @return {@literal List<Player>}
        *  - a list players sorted by their field goal percentage
    */
    @GetMapping("/players/fgPerc")
    public List<Player> fgPerc(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getFgPerc().compareTo(p1.getStats().getFgPerc())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by their three point percentage
        * @return {@literal List<Player>}
        *  - a list players sorted by their three point percentage
    */
    @GetMapping("/players/threePerc")
    public List<Player> threePerc(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getThreePerc().compareTo(p1.getStats().getThreePerc())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by their two point percentage
        * @return {@literal List<Player>}
        *  - a list players sorted by their two point percentage
    */
    @GetMapping("/players/twoPerc")
    public List<Player> twoPerc(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getTwoPerc().compareTo(p1.getStats().getTwoPerc())).collect(Collectors.toList());
        return sortedPlayers;
    }

    /**
        * Displays the players sorted by their free throw percentage
        * @return {@literal List<Player>}
        *  - a list players sorted by their free throw percentage
    */
    @GetMapping("/players/freePerc")
    public List<Player> freePerc(){
        if (teams.getTeams() == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such data available at the moment. Try again in a few minutes");
        ArrayList<Player> players = allPlayers();
        List <Player> sortedPlayers = players.stream().sorted((Player p1, Player p2)->p2.getStats().getFreePerc().compareTo(p1.getStats().getFreePerc())).collect(Collectors.toList());
        return sortedPlayers;
    }



}
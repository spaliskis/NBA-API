package API;

import java.util.List;

// Class which represents an NBA team and it's data
/**
	* Class that stores team's data
	* @author Sarunas
*/
public class Team {
    
    private final String name;
    private final String wins;
    private final String losses;
    private final String ppg;
    private List<Player> players;

    /**
        * Initializes every variable of the class
    */
    public Team(String name, String wins, String losses, String ppg, List<Player> players){
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ppg = ppg;
        this.players = players;
    }

    /**
        * Returns the name of the team
        * @return String
        *  - the name of the team
    */
    public String getName(){
        return name;
    }

    /**
        * Returns the amount of wins in the season by the team
        * @return String
        *  - the amount of wins in the season by the team
    */
    public String getWins(){
        return wins;
    }

    /**
        * Returns the amount of losses in the season by the team
        * @return String
        *  - the amount of losses in the season by the team
    */
    public String getLosses(){
        return losses;
    }

    /**
        * Returns the average amount of points scored per game by the team
        * @return String
        *  - the average amount of points scored per game by the team
    */
    public String getPpg(){
        return ppg;
    }

    /**
        * Returns all players of the team
        * @return {@literal List<Player>}
        *  - all players of the team
    */
    public List<Player> getPlayers(){
        return players;
    }

}

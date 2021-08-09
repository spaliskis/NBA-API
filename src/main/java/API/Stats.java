package API;


// a class which represents NBA player's statistics
/**
	* Class that stores player's stats
	* @author Sarunas
*/
public class Stats {
    private final String minPerGame;
    private final String fgPerc;
    private final String threePerc;
    private final String twoPerc;
    private final String freePerc;
    private final String trb;
    private final String ast;
    private final String stl;
    private final String blk;
    private final String tov;
    private final String pf;
    private final String pts;

    /**
        * Initializes every variable of the class
    */
    public Stats(String minPerGame, String fgPerc, String threePerc, String twoPerc, String freePerc, String trb, String ast, String stl, String blk, String tov, String pf, String pts){
        this.minPerGame = minPerGame;
        this.fgPerc = "0" + fgPerc;
        this.threePerc = "0" + threePerc;
        this.twoPerc = "0" + twoPerc;
        this.freePerc = "0" + freePerc;
        this.trb = trb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.tov = tov;
        this.pf = pf;
        this.pts = pts;
    }

    /**
        * Returns average of minutes played per game of the player
        * @return String
        *  - player's average of minutes played
    */
    public String getMinPerGame(){
        return minPerGame;
    }

    /**
        * Returns field goal percentage of the player
        * @return String
        *  - player's field goal percentage
    */
    public String getFgPerc(){
        return fgPerc;
    }

    /**
        * Returns three point percentage of the player
        * @return String
        *  - player's three point percentage
    */
    public String getThreePerc(){
        return threePerc;
    }

    /**
        * Returns two point percentage of the player
        * @return String
        *  - player's two point percentage
    */
    public String getTwoPerc(){
        return twoPerc;
    }

    /**
        * Returns free throw percentage of the player
        * @return String
        *  - player's free throw percentage
    */
    public String getFreePerc(){
        return freePerc;
    }

    /**
        * Returns average amount of rebounds per game of the player
        * @return String
        *  - player's average amount of rebounds per game
    */
    public String getTrb(){
        return trb;
    }

    /**
        * Returns average amount of assists per game of the player
        * @return String
        *  - player's average amount of assists per game
    */
    public String getAst(){
        return ast;
    }

    /**
        * Returns average amount of steals per game of the player
        * @return String
        *  - player's average amount of steals per game
    */
    public String getStl(){
        return stl;
    }

    /**
        * Returns average amount of blocks per game of the player
        * @return String
        *  - player's average amount of blocks per game
    */
    public String getBlk(){
        return blk;
    }

    /**
        * Returns average amount of turnovers per game of the player
        * @return String
        *  - player's average amount of turnovers per game
    */
    public String getTov(){
        return tov;
    }

    /**
        * Returns average amount of personal fouls per game of the player
        * @return String
        *  - player's average amount of personal fouls per game
    */
    public String getPf(){
        return pf;
    }

    /**
        * Returns average amount of points per game of the player
        * @return String
        *  - player's average amount of points per game
    */
    public String getPts(){
        return pts;
    }
}

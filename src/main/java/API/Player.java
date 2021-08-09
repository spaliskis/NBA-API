package API;

// Class which represents an NBA player and his data
/**
    * Class that stores all information about a player
    * @author Sarunas
*/
public class Player {
    
    private final String name;
    private final String position;
    private final String height;
    private final String weight;
    private final String birthDate;
    private final Stats stats;

    /**
        * Initializes every variable of the class
    */
    public Player(String name, String position, String height, String weight,  String birthDate, Stats stats){
        this.name = name;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
        this.stats = stats;
    }

    /**
        * Returns name of the player
        * @return String
        *  - player's name
    */
    public String getName(){
        return name;
    }

    /**
        * Returns position of the player
        * @return String
        *  - player's position
    */
    public String getPosition(){
        return position;
    }

    /**
        * Returns height of the player
        * @return String
        *  - player's height
    */    
    public String getHeight(){
        return height;
    }

    /**
        * Returns weight of the player
        * @return String
        *  - player's weight
    */
    public String getWeight(){
        return weight;
    }

    /**
        * Returns birthdate of the player
        * @return String
        *  - player's birthdate
    */    
    public String getBirthdate(){
        return birthDate;
    }

    /**
        * Returns stats of the player
        * @return String
        *  - player's stats
    */
    public Stats getStats(){
        return stats;
    }



}

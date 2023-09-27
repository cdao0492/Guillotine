/**
 * Class that represents "Person" cards that will
 * be collected by the players in Guillotine
 *
 * @author Vo Linh Chi Dao
 */
public class Card{

    //stores the name/occupation of the card
    private final String name;

    //stores the group of the card
    private final String group;

    //stores the point value of the card
    private String point;

    /**
     * Constructor that initializes fields for Card
     * @param name the name/occupation of the card
     * @param group the group the card belongs to
     * @param point the point value of the card
     */
    public Card(String name, String group, String point){
        this.name = name;
        this.group = group;
        this.point = point;
    }

    /**
     * Returns the name/occupation of the card
     * @return the name/occupation of the card
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the group of the card
     * @return the group the card belongs to
     */
    public String getGroup(){
        return group;
    }

    /**
     * Returns the point of the card
     * @return the point value of the card
     */
    public String getPoint(){
        return point;
    }

}

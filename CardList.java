/**
 * A class to represent a list of cards for Guillotine
 *
 * @author Vo Linh Chi Dao
 */
public class CardList extends LinkedList<Card>{

    /**
     * Creates an initially empty CardList
     */
    public CardList(){
        super();
    }

    /**
     * Moves the first node of the list back by the indicated place
     * @param n the number of place the first node being moved back
     */
    public void moveBack(int n){
        /* throws exception when the n is larger than or equal to the length of the linked list*/
        if(n >= length()){
            throw new IllegalArgumentException(" Your list is not long enough to move " + n + " spaces");
        }
        else{
            //store the current node of the list
            LLNode<Card> nodeptr = getFirstNode();
            //store the counter
            int counter = 0;

            //trace for the n place to move the first node to
            while(nodeptr.getNext() != null && counter < n){
                nodeptr = nodeptr.getNext();
                counter++;
            }

            /* the first node will now in the place of the current n node and link
            to the remaining nodes
             */
            nodeptr.setNext(new LLNode<Card>(getFirstNode().getElement(), nodeptr.getNext()));
            removeFromFront();
        }
    }

    /**
     * Moves the first node to the last of the list
     */
    public void moveFirstToLast(){
        //move the first node back length - 1 place
        moveBack(length() - 1);
    }

    /**
     * Moves the last node to the front of the list
     */
    public void moveLastToFirst(){
        //store the first node
        LLNode<Card> cardptr = getFirstNode();

        //if the card after the next card is not null
        while(cardptr.getNext().getNext() != null){
            //store the next card
            cardptr = cardptr.getNext();
        }

        //add the last card to the front
        addToFront(cardptr.getNext().getElement());
        //remove the last card
        cardptr.setNext(null);
    }

    /**
     * Reverses all the nodes of the linked list
     */
    public void reverseList(){
        reverseFirstK(length());
    }

    /**
     * Reverses the first k nodes of the list
     * @param k indicate the number of cards at the beginning of the list
     */
    public void reverseFirstK(int k){
        /* throws exception if k exceeds the length of the CardList*/
        if(k > length()){
            throw new IllegalArgumentException("Your linked list does not have " + k + " spaces");
        }
        else{
            //store the head node
            LLNode<Card> head = null;
            //store the middle node
            LLNode<Card> mid = getFirstNode();
            //store the tail node
            LLNode<Card> tail = getFirstNode();
            //store the counter
            int counter = 0;

            //when tail does not reach the end of the original linked list
            while(tail != null && counter < k){
                tail = tail.getNext();
                //link middle node to the preceding node(head)
                mid.setNext(head);
                //the head now point to the mid node
                head = mid;
                //the mid now point to the head node
                mid = tail;
                counter++;
            }

            //the original first node is now the last node of the segment
            getFirstNode().setNext(tail);
            //the head node is now the current first node of the list
            setFirstNode(head);
        }
    }

    /**
     * Calculates the total points of the cards the player have drawn
     * @return total points the player have received so far
     */
    public int getTotalPoints(){
        //store the total points
        int total = 0;

        //indicate if count/countess has been collected
        boolean checkCount = false;
        //indicate if lord/lady has been collected
        boolean checkLord = false;

        //number of church people collected so far
        int numChurch = 0;
        //number of civic people collected so far
        int numCivic = 0;
        //number of palace guards collected so far
        int numGuard = 0;
        //number of commoners collected so far
        int numCommoner = 0;

        //traces through the card list of the player
        for(Card m : this){
            //people card that has specific value
            if(! m.getPoint().equals("*")){
                switch (m.getGroup()){
                    //if people card is from group church
                    case "Church":
                        total += Integer.parseInt(m.getPoint());
                        numChurch++;
                        break;
                    //if people card is from group civic
                    case "Civic":
                        total += Integer.parseInt(m.getPoint());
                        numCivic++;
                        break;
                    //if people card is from group commoner
                    case "Commoner":
                        total += Integer.parseInt(m.getPoint());
                        numCommoner++;
                        break;
                    default: total += Integer.parseInt(m.getPoint());
                }
            }
        }

        //traces through the card list of the player
        for(Card m: this){
            //people card that holds special value
            if(m.getPoint().equals("*")){
                //if count/countess is collected for the first time
                if(!checkCount && (m.getName().equals("Count") || m.getName().equals("Countess"))){
                    checkCount = true;
                    //add the character original point
                    total += 2;
                }
                //if count and countess is collected by the same player
                else if(checkCount && (m.getName().equals("Count") || m.getName().equals("Countess"))){
                    //add for a total of 8 points
                    total += 6;
                }
                //if lord/lady is collected for the first time
                else if(!checkLord && (m.getName().equals("Lord") || m.getName().equals("Lady"))){
                    checkLord = true;
                    //add the character original point
                    total += 2;
                }
                //if lord and lady is collected by the same player
                else if(checkLord && (m.getName().equals("Lord") || m.getName().equals("Lady"))){
                    //add for a total of 8 points
                    total += 6;
                }
                //heretic has point value equal to current numChurch
                else if(m.getName().equals("Heretic")){
                    numChurch++;
                    total += numChurch;
                }
                //tax collector has point value equal to current numCivic
                else if(m.getName().equals("Tax Collector")){
                    numCivic++;
                    total += numCivic;
                }
                //tragic figure has point value equal to current numCommoner
                else if(m.getName().equals("Tragic Figure")){
                    numCommoner++;
                    total -= numCommoner;
                }
                //palace guard has point value equal to current numGuard
                else if(m.getName().equals("Palace Guard")){
                    numGuard++;
                    //add the difference between the square of current and previous numGuard
                    total += (numGuard*numGuard) - (numGuard-1)*(numGuard-1);
                }
            }
        }

        return total;
    }

    /**
     * Returns the string representation of the cards list
     * @return the string representation of the cards list
     */
    @Override
    public String toString(){
        //store the string representation
        StringBuilder sb = new StringBuilder();
        sb.append("cards list:");
        //append each card name into sb
        for(Card m: this){
            sb.append(' ');
            sb.append(m.getName());
        }

        return sb.toString();
    }

    /**
     * Shuffles the cards
     * @return Array that stores 40 cards in random positions
     */
    public static Card[] shuffleCard() {
        /* 40 possible person cards of the game*/
        Card kingLouisXVI = new Card("King Louis XVI", "Royal", "5");
        Card marieAntoinette = new Card("Marie Antoinette", "Royal", "5");
        Card regent = new Card("Regent", "Royal", "4");
        Card duke = new Card("Duke", "Royal", "3");
        Card baron = new Card("Baron", "Royal", "3");
        Card count = new Card("Count", "Royal", "*");
        Card countess = new Card("Countess", "Royal", "*");
        Card lord = new Card("Lord", "Royal", "*");
        Card lady = new Card("Lady", "Royal", "*");
        Card cardinal = new Card("Cardinal", "Church", "5");
        Card archbishop = new Card("Archbishop", "Church", "4");
        Card nun = new Card("Nun", "Church", "3");
        Card bishop = new Card("Bishop", "Church", "2");
        Card priest1 = new Card("Priest", "Church", "1");
        Card priest2 = new Card("Priest", "Church", "1");
        Card heretic = new Card("Heretic", "Church", "*");
        Card governor = new Card("Governor", "Civic", "4");
        Card mayor = new Card("Mayor", "Civic", "3");
        Card councilMan = new Card("Councilman", "Civic", "3");
        Card judge1 = new Card("Judge", "Civic", "2");
        Card judge2 = new Card("Judge", "Civic", "2");
        Card taxCollector = new Card("Tax Collector", "Civic", "*");
        Card sheriff1 = new Card("Sheriff", "Civic", "1");
        Card sheriff2 = new Card("Sheriff", "Civic", "1");
        Card palaceGuard1 = new Card("Palace Guard", "Military", "*");
        Card palaceGuard2 = new Card("Palace Guard", "Military", "*");
        Card palaceGuard3 = new Card("Palace Guard", "Military", "*");
        Card palaceGuard4 = new Card("Palace Guard", "Military", "*");
        Card palaceGuard5 = new Card("Palace Guard", "Military", "*");
        Card general = new Card("General", "Military", "4");
        Card colonel = new Card("Colonel", "Military", "3");
        Card captain = new Card("Captain", "Military", "2");
        Card lieutenant1 = new Card("Lieutenant", "Military", "1");
        Card lieutenant2 = new Card("Lieutenant", "Military", "1");
        Card tragicFigure = new Card("Tragic Figure", "Commoner", "*");
        Card heroicFigure = new Card("Heroic Figure", "Commoner", "-3");
        Card student1 = new Card("Student", "Commoner", "-1");
        Card student2 = new Card("Student", "Commoner", "-1");
        Card student3 = new Card("Student", "Commoner", "-1");
        Card student4 = new Card("Student", "Commoner", "-1");

        //array that stores 40 cards
        Card[] array = new Card[]{kingLouisXVI, marieAntoinette, regent, duke, baron, count, countess, lord, lady, cardinal
                , archbishop, nun, bishop, priest1, priest2, heretic, governor, mayor, councilMan, judge1, judge2, taxCollector, sheriff1, sheriff2
                , palaceGuard1, palaceGuard2, palaceGuard3, palaceGuard4, palaceGuard5, general, colonel, captain, lieutenant1, lieutenant2,
                tragicFigure, heroicFigure, student1, student2, student3, student4};

        //shuffle the card array
        for (int i = array.length - 1; i >= 0; i--){
            //find the random index in the array
            int j = (int) (Math.random() * (i + 1));
            //switching the element of index i and j
            Card temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return array;
    }

    /**
     * Creates a random "person" cards list
     * @param m total numbers of card in the game
     * @return a card list for the game
     */
    public static CardList randomCardList(int m){
        //Generate an array with randomize cards
        Card[] list = shuffleCard();
        //Creates an empty cardList
        CardList cardList = new CardList();

        //add the cards from the array to cardList
        for(int i = 0; i < m; i++){
            cardList.addToFront(list[i]);
        }

        return cardList;
    }

}

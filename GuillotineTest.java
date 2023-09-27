import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing for Guillotine
 *
 * @author Vo Linh Chi Dao
 */
public class GuillotineTest{

    //possible person cards of the game
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
    Card heretic = new Card("Heretic", "Church", "*");
    Card mayor = new Card("Mayor", "Civic", "3");
    Card councilMan = new Card("Councilman", "Civic", "3");
    Card judge1 = new Card("Judge", "Civic", "2");
    Card taxCollector = new Card("Tax Collector", "Civic", "*");
    Card sheriff1 = new Card("Sheriff", "Civic", "1");
    Card sheriff2 = new Card("Sheriff", "Civic", "1");
    Card palaceGuard1 = new Card("Palace Guard", "Military", "*");
    Card palaceGuard2 = new Card("Palace Guard", "Military", "*");
    Card palaceGuard3 = new Card("Palace Guard", "Military", "*");
    Card palaceGuard5 = new Card("Palace Guard", "Military", "*");
    Card general = new Card("General", "Military", "4");
    Card colonel = new Card("Colonel", "Military", "3");
    Card captain = new Card("Captain", "Military", "2");
    Card lieutenant1 = new Card("Lieutenant", "Military", "1");
    Card tragicFigure = new Card("Tragic Figure", "Commoner", "*");
    Card heroicFigure = new Card("Heroic Figure", "Commoner", "-3");
    Card student1 = new Card("Student", "Commoner", "-1");
    Card student2 = new Card("Student", "Commoner", "-1");
    Card student3 = new Card("Student", "Commoner", "-1");
    Card student4 = new Card("Student", "Commoner", "-1");

    /**
     * Test moveBack method
     */
    @Test (expected = IllegalArgumentException.class)
    public void testMoveBack(){
        CardList list = new CardList();

        //throws exception when n is larger than or equal to the list length
        list.moveBack(2);

        //Test 0 - move back an empty cardList or move back 0 place
        list.moveBack(0);

        //Test 1 - list with one card or move back 1 place
        list.addToFront(regent);
        list.moveBack(1);

        list.addToFront(bishop);
        assertEquals("cards list: Bishop Regent", list.toString());
        list.moveBack(1);
        assertEquals("cards list: Regent Bishop", list.toString());

        //Test many - list with many card or move back many places
        list.addToFront(judge1);
        list.addToFront(sheriff1);
        assertEquals("cards list: Sheriff Judge Regent Bishop", list.toString());
        list.moveBack(1);
        assertEquals("cards list: Judge Sheriff Regent Bishop", list.toString());
        list.moveBack(3);
        assertEquals("cards list: Sheriff Regent Bishop Judge", list.toString());

        //Test first - first card remains at beginning of the list
        list.moveBack(0);
        assertEquals("cards list: Sheriff Regent Bishop Judge", list.toString());

        //Test middle - move back to somewhere in the middle of the list
        list.moveBack(2);
        assertEquals("cards list: Regent Bishop Sheriff Judge", list.toString());

        //Test last - move back to the end of the list
        list.moveBack(3);
        assertEquals("cards list: Bishop Sheriff Judge Regent", list.toString());
    }

    /**
     * Test moveFirstToLast method
     */
    @Test
    public void testMoveFirstToLast(){
        CardList list = new CardList();
        list.addToFront(baron);
        list.addToFront(priest1);
        list.addToFront(councilMan);
        list.addToFront(mayor);
        list.addToFront(marieAntoinette);
        list.addToFront(student2);

        list.moveFirstToLast();
        assertEquals("cards list: Marie Antoinette Mayor Councilman Priest Baron Student", list.toString());
    }

    /**
     * Test moveLastToFirst method
     */
    @Test
    public void testMoveLastToFirst(){
        CardList list = new CardList();
        list.addToFront(duke);
        list.addToFront(captain);
        list.addToFront(lieutenant1);
        list.addToFront(archbishop);
        list.addToFront(heroicFigure);
        list.addToFront(taxCollector);

        list.moveLastToFirst();
        assertEquals("cards list: Duke Tax Collector Heroic Figure Archbishop Lieutenant Captain", list.toString());
    }

    /**
     * Test reverseList method
     */
    @Test
    public void testReverseList(){
        CardList list = new CardList();
        list.addToFront(kingLouisXVI);
        list.addToFront(lord);
        list.addToFront(cardinal);
        list.addToFront(student3);
        list.addToFront(palaceGuard3);
        list.addToFront(palaceGuard5);

        list.reverseList();
        assertEquals("cards list: King Louis XVI Lord Cardinal Student Palace Guard Palace Guard", list.toString());
    }

    /**
     * Test reverseFirstK method
     */
    @Test (expected = IllegalArgumentException.class)
    public void testReverseFirstK(){
        CardList list = new CardList();

        //Throws an exception if k exceed the length of the list
        list.reverseFirstK(10);

        //Test 0 - reverse empty card list
        list.reverseFirstK(1);

        //Test 1 - card list with one card
        list.addToFront(count);
        list.reverseFirstK(2);
        list.reverseFirstK(1);
        assertEquals("cards list: Count", list.toString());

        //Test many - card list with many cards or reverse many cards
        list.addToFront(heretic);
        list.addToFront(general);
        list.addToFront(tragicFigure);
        list.addToFront(priest1);
        list.addToFront(student4);
        list.reverseFirstK(3);
        assertEquals("cards list: Tragic Figure Priest Student General Heretic Count", list.toString());

        //Test first - reverse the first card
        list.reverseFirstK(1);
        assertEquals("cards list: Tragic Figure Priest Student General Heretic Count", list.toString());
        //Test middle - reverse between the first and middle cards of the list
        list.reverseFirstK(3);
        assertEquals("cards list: Student Priest Tragic Figure General Heretic Count", list.toString());
        //Test last - reverse between the first and last cards of the list
        list.reverseFirstK(list.length());
        assertEquals("cards list: Count Heretic General Tragic Figure Priest Student", list.toString());
    }

    /**
     * Test getTotalPoints method
     */
    @Test
    public void testGetTotalPoints(){
        CardList list = new CardList();

        //Test 0 - no cards in the list
        assertEquals(0, list.getTotalPoints());

        //Test 1 - one card in the list
        list.addToFront(marieAntoinette);
        assertEquals(5, list.getTotalPoints());

        //Test many - many cards in the list
        list.addToFront(duke);
        list.addToFront(lieutenant1);
        assertEquals(9, list.getTotalPoints());

        list = new CardList();
        //Case 1: Church group card is collected and account for heretic
        list.addToFront(nun);
        list.addToFront(heretic);
        assertEquals(5, list.getTotalPoints());

        list = new CardList();
        //Case 2: Civic group card is collected and account for tax collector
        list.addToFront(mayor);
        list.addToFront(judge1);
        list.addToFront(taxCollector);
        assertEquals(8, list.getTotalPoints());

        list = new CardList();
        //Case 3: Commoner group card is collected and account for tragic figure
        list.addToFront(tragicFigure);
        list.addToFront(student2);
        list.addToFront(heroicFigure);
        assertEquals(-7, list.getTotalPoints());

        list = new CardList();
        //Case 4: Card in other groups with specific value is collected
        list.addToFront(general);
        list.addToFront(kingLouisXVI);
        list.addToFront(baron);
        assertEquals(12, list.getTotalPoints());

        list = new CardList();
        //Case 5: Palace guard card is collected
        list.addToFront(palaceGuard1);
        assertEquals(1, list.getTotalPoints());
        list.addToFront(palaceGuard2);
        list.addToFront(palaceGuard3);
        assertEquals(9, list.getTotalPoints());

        list = new CardList();
        //Case 6: Account for count and countess
        list.addToFront(count);
        assertEquals(2, list.getTotalPoints());
        list.addToFront(countess);
        assertEquals(8, list.getTotalPoints());

        list = new CardList();
        //Case 7: Account for lord and lady
        list.addToFront(lord);
        assertEquals(2, list.getTotalPoints());
        list.addToFront(lady);
        assertEquals(8, list.getTotalPoints());

        //Case 8: Combination of different cards
        list.addToFront(mayor);
        list.addToFront(marieAntoinette);
        list.addToFront(student3);
        list.addToFront(taxCollector);
        list.addToFront(sheriff2);
        assertEquals(19, list.getTotalPoints());
    }

    /**
     * Test the toString method
     */
    @Test
    public void testToString(){
        CardList list = new CardList();

        //Test 0 - an empty card list
        assertEquals("cards list:", list.toString());

        //Test 1 - card list with one card
        list.addToFront(student1);
        assertEquals("cards list: Student", list.toString());

        //Test many - card list with many cards
        list.addToFront(colonel);
        list.addToFront(palaceGuard1);
        list.addToFront(nun);
        list.addToFront(lady);
        assertEquals("cards list: Lady Nun Palace Guard Colonel Student", list.toString());
    }

    /**
     * Test shuffleCard method
     */
    @Test
    public void testShuffleCard(){
        //contains 40 ordered cards
        Card[] list1 = CardList.shuffleCard();

        //contains 40 shuffled cards
        Card[] list2 = CardList.shuffleCard();

        //The cards of two decks are in different positions
        assertNotEquals(list1, list2);
    }

    /**
     * Test randomCardList method
     */
    @Test
    public void testRandomCardList(){
        System.out.println("RandomCardList method testing");
        //Test 0 - Generate two decks with no card
        CardList list1 = CardList.randomCardList(0);
        CardList list2 = CardList.randomCardList(0);
        System.out.println("1. " + list1);
        System.out.println("2. " + list2);
        assertEquals(list1.toString(),list2.toString());

        //Test 1 - Generate two decks with one card
        CardList list3 = CardList.randomCardList(1);
        CardList list4 = CardList.randomCardList(1);
        System.out.println("3. " + list3);
        System.out.println("4. " + list4);
        assertNotEquals(list3.toString(),list4.toString());

        //Test many - Generate two decks with many cards
        CardList list5 = CardList.randomCardList(5);
        CardList list6 = CardList.randomCardList(5);
        System.out.println("5. " + list5);
        System.out.println("6. " + list6);
        assertNotEquals(list5.toString(),list6.toString());
    }

}

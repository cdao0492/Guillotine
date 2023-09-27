import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

/**
 * Display of the game Guillotine
 *
 * @author Vo Linh Chi Dao
 */

public class Guillotine extends Application{

    //stores the card list of the game
    private CardList cardList;

    //stores the cards collected by player 1
    private CardList listPlayer1;

    //stores the cards collected by player 2
    private CardList listPlayer2;

    //vbox displays score and info of player 1
    private VBox player1;

    //vbox displays score and info of player 2
    private VBox player2;

    //vbox displays cards collected by player 1
    private VBox cardCollected1;

    //vbox displays cards collected by player 2
    private VBox cardCollected2;

    //vbox displays the cards list of the game
    private VBox cardLine;

    //vbox displays action cards of player 1
    private final VBox action1 = createActionCards();

    //vbox displays action cards of player 2
    private final VBox action2 = createActionCards();

    //scroll pane of the card list of the game
    private ScrollPane scrollPane;

    //scroll pane of the card list of player 1
    private ScrollPane sp1 = new ScrollPane(cardCollected1);

    //scroll pane of the card list of player 2
    private ScrollPane sp2 = new ScrollPane(cardCollected2);

    //label for the cards collected by player 1
    private final Label lb1 = new Label("Cards collected: ");

    //label for the cards collected by player 2
    private final Label lb2 = new Label("Cards collected: ");

    //borderpane displays the layout of the game
    private BorderPane layout;

    //indicates player 1's turn
    private boolean p1Turn = false;

    /**
     * The default display of Guillotine.
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage){
        //Generate card list for the game
        cardList = CardList.randomCardList(Integer.valueOf(getParameters().getRaw().get(0)));

        //Initializes cards collected by 2 players
        listPlayer1 = new CardList();
        listPlayer2 = new CardList();

        //Initializes vbox display cards collected by 2 players
        cardCollected1 = new VBox();
        cardCollected2 = new VBox();

        //Initializes vbox for the player 1
        player1 = new VBox(createPlayer1(),action1,lb1,sp1);
        //Initializes vbox for the player 2
        player2 = new VBox(createPlayer2(),action2,lb2,sp2);
        //Initializes vbox for the cards line
        cardLine = createButtonsList(cardList);
        
        //add the cards line to a scroll pane
        scrollPane = new ScrollPane(cardLine);
        scrollPane.setMaxHeight(1000);

        //add nodes to the layout
        layout = new BorderPane();
        layout.setRight(player2);
        layout.setLeft(player1);
        layout.setCenter(scrollPane);

        //set to player 1's turn
        updatePlayerTurn();

        //scene of the stage
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CSDS 132 Guillotine!");
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Generates VBox contains list of card for Guillotine
     * @param cardList is the cards list of the game or player
     * @return VBox contains list of cards
     */
    public VBox createButtonsList(CardList cardList){
        //Return empty VBox if cardList is empty
        if(cardList.isEmpty()){
            return new VBox();
        }
        else{
            //vbox that stores the list of buttons
            VBox vb = new VBox();
            vb.setPrefWidth(150);

            //Generate a button for each card in the list
            for (Card a : cardList){
                Button b = new Button(a.getName() + '\n' + a.getPoint() + '\n' + a.getGroup());
                b.setWrapText(true);
                b.setMaxWidth(vb.getPrefWidth());
                b.setTextAlignment(TextAlignment.CENTER);
                //add the button to VBox
                vb.getChildren().add(b);
            }

            return vb;
        }
    }

    /**
     * Creates VBox to display player 1 and score
     * @return VBox to show player 1 information
     */
    public VBox createPlayer1(){
        return new VBox(new Label("Player 1" + '\n' + "Score: "));
    }

    /**
     * Creates VBox to display the player 2 and score
     * @return VBox to show player 2 information
     */
    public VBox createPlayer2(){
        return new VBox(new Label("Player 2" + '\n' + "Score: "));
    }

    /**
     * Generates the actions cards for each player.
     * Implements and displays the action for each card
     * when it is clicked.
     * @return VBox to show each player action cards
     */
    public VBox createActionCards(){
        //Initializes 10 action cards
        Button b1 = new Button("Move First Back 4");
        Button b2 = new Button("Move First Back 3");
        Button b3 = new Button("Move First Back 2");
        Button b4 = new Button("Move First Back 1");
        Button b5 = new Button("Move First To Last");
        Button b6 = new Button("Move Last To First");
        Button b7 = new Button("Reverse All Cards");
        Button b8 = new Button("Reverse First 5 Cards");
        Button b9 = new Button("Skip Turn");
        Button b10 = new Button("Take First Card Of The Line");

        //Set the size of the buttons
        b1.setMinWidth(150);
        b2.setMinWidth(150);
        b3.setMinWidth(150);
        b4.setMinWidth(150);
        b5.setMinWidth(150);
        b6.setMinWidth(150);
        b7.setMinWidth(150);
        b8.setMinWidth(150);
        b9.setMinWidth(150);
        b10.setMinWidth(150);

        //vbox that stores the action cards
        VBox vb = new VBox(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10);

        /*
        The first card should move back 4 places
        and the button is disabled
         */
        b1.setOnAction( e -> {
            cardList.moveBack(4);
            updateCardsLine(cardList);
            b1.setDisable(true);
        });

        /*
        The first card should move back 3 places
        and the button is disabled
         */
        b2.setOnAction( e -> {
            cardList.moveBack(3);
            updateCardsLine(cardList);
            b2.setDisable(true);
        });

        /*
        The first card should move back 2 places
        and the button is disabled
         */
        b3.setOnAction( e -> {
            cardList.moveBack(2);
            updateCardsLine(cardList);
            b3.setDisable(true);
        });

        /*
        The first card should move back 1 places
        and the button is disabled
         */
        b4.setOnAction( e -> {
            cardList.moveBack(1);
            updateCardsLine(cardList);
            b4.setDisable(true);
        });

        /*
        The first card should move to the end
        and the button is disabled
         */
        b5.setOnAction( e -> {
            cardList.moveFirstToLast();
            updateCardsLine(cardList);
            b5.setDisable(true);
        });

        /*
        The last card should move to the beginning
        and the button is disabled
         */
        b6.setOnAction( e -> {
            cardList.moveLastToFirst();
            updateCardsLine(cardList);
            b6.setDisable(true);
        });

        /*
        The cardLine is reversed and the button
        is disabled
         */
        b7.setOnAction( e -> {
            cardList.reverseList();
            updateCardsLine(cardList);
            b7.setDisable(true);
        });

        /*
        The first 5 cards are reversed and
        the button is disabled
         */
        b8.setOnAction( e -> {
            cardList.reverseFirstK(5);
            updateCardsLine(cardList);
            b8.setDisable(true);
        });

        /*
        The player turn is skipped and
        the button is disabled
         */
        b9.setOnAction( e -> {
            updatePlayerTurn();
            b9.setDisable(true);
        });

        /*
        The player draws the card and their
        score is updated, then switch to next player
         */
        b10.setOnAction( e -> {
            //the current player actions are disabled
            vb.setDisable(true);
            //their cards list is updated
            updateCardsCollected();
            //their score is updated
            updatePlayerVBox();
            //if cardList is now empty
            if(cardList.isEmpty()){
                //announce the winner
                updateWinner();
            }
            else{
                //switch to next player
                updatePlayerTurn();
            }
        });

        return vb;
    }

    /**
     * Updates the player turn to the next player after the current
     * player draw the first card from the line.
     * Disables the action cards of the previous player.
     */
    public void updatePlayerTurn(){
        //check the player's turn
        if(p1Turn){
            //switch to player 2 turn
            p1Turn = false;
            //disable player 1's action cards
            player1.getChildren().get(1).setDisable(true);
            //enable player 2's action cards
            player2.getChildren().get(1).setDisable(false);
        }
        else{
            //switch to player 1 turn
            p1Turn = true;
            //enable player 1's action cards
            player1.getChildren().get(1).setDisable(false);
            //disable player 2's action cards
            player2.getChildren().get(1).setDisable(true);
        }
    }

    /**
     * Updates the cards collected when the current player
     * draws the first card of the line
     */
    public void updateCardsCollected(){
        //store the currently removed card
        Card save = cardList.removeFromFront();
        //update the cardLine of the game
        updateCardsLine(cardList);

        //update player 1 if it is their turn
        if(p1Turn){
            //add collected card to player 1 list
            listPlayer1.addToEnd(save);
            //create button for the card
            Button b = new Button(save.getName() + '\n' + save.getPoint() + '\n' + save.getGroup());
            b.setWrapText(true);
            b.setPrefWidth(150);
            b.setTextAlignment(TextAlignment.CENTER);
            //add to cardCollected1 and sp1
            cardCollected1.getChildren().add(b);
            sp1 = new ScrollPane(cardCollected1);
            sp1.setMaxHeight(550);
        }
        //update player 2 if it is their turn
        else{
            //add collected card to player 2 list
            listPlayer2.addToEnd(save);
            //create button for the card
            Button b = new Button(save.getName() + '\n' + save.getPoint() + '\n' + save.getGroup());
            b.setWrapText(true);
            b.setPrefWidth(150);
            b.setTextAlignment(TextAlignment.CENTER);
            //add to cardCollected2 and sp2
            cardCollected2.getChildren().add(b);
            sp2 = new ScrollPane(cardCollected2);
            sp2.setMaxHeight(550);
        }
    }

    /**
     * Updates the cards line of the game
     * @param cardList the most updated cards list
     */
    public void updateCardsLine(CardList cardList){
        //clear the original cardLine
        cardLine.getChildren().clear();
        //update the latest version
        cardLine = createButtonsList(cardList);
        //update the scroll pane
        scrollPane = new ScrollPane(cardLine);
        scrollPane.setMaxHeight(1000);
        //update the layout
        layout.setCenter(scrollPane);
    }

    /**
     * Updates the points of the player
     * @return VBox that contains the player current points
     */
    public VBox updatePlayerPoints(){
        //update player 1 score if is their turn
        if(p1Turn){
            return new VBox(new Label("Player 1" + '\n' + "Score: " + listPlayer1.getTotalPoints()));
        }
        //update player 2 score if is their turn
        else{
            return new VBox(new Label("Player 2" + '\n' + "Score: " + listPlayer2.getTotalPoints()));
        }
    }

    /**
     * Updates the VBox for current player.
     * Occurs when they draw the first card of the line.
     */
    public void updatePlayerVBox(){
        //update player 1 VBox if is their turn
        if(p1Turn){
            player1 = new VBox(updatePlayerPoints(),action1,lb1,sp1);
            layout.setLeft(player1);
        }
        //update player 2 VBox if is their turn
        else{
            player2 = new VBox(updatePlayerPoints(),action2,lb2,sp2);
            layout.setRight(player2);
        }
    }

    /**
     * Announces the winner of the game when
     * the last card of the line is drawn.
     * Prints the player's scores.
     * Disables action cards of two players
     */
    public void updateWinner(){
        /* Check the points of the two players*/
        //Player 1 won when they have higher scores
        if(listPlayer1.getTotalPoints() > listPlayer2.getTotalPoints()){
            //Disable the action cards of two players
            action1.setDisable(true);
            action2.setDisable(true);
            //Print the winner and the players scores
            System.out.println("PLAYER 1 WON!" + '\n' + "Player 1: " + listPlayer1.getTotalPoints() +
                    '\n' + "Player 2: " + listPlayer2.getTotalPoints());
        }
        //Player 2 won when they have higher scores
        else if(listPlayer1.getTotalPoints() < listPlayer2.getTotalPoints()){
            //Disable the action cards of two players
            action1.setDisable(true);
            action2.setDisable(true);
            //Print the winner and the players scores
            System.out.println("PLAYER 2 WON!" + '\n' + "Player 1: " + listPlayer1.getTotalPoints() +
                    '\n' + "Player 2: " + listPlayer2.getTotalPoints());
        }
        //A draw when their scores are equal
        else{
            //Disable the action cards of two players
            action1.setDisable(true);
            action2.setDisable(true);
            //Print the winner and the players scores
            System.out.println("IT'S A DRAW!" + '\n' + "Player 1: " + listPlayer1.getTotalPoints() +
                    '\n' + "Player 2: " + listPlayer2.getTotalPoints());
        }
    }

    /**
     * Launch the game. The default will be 20 cards.
     * @param args represents the number of cards in Guillotine
     *             input by the user
     */
    public static void main(String[] args){

        //set default card for the game is 20
        if(args.length == 0){
            Application.launch("20");
        }
        //if the input exceeds the number possible cards
        else if(Integer.valueOf(args[0]) < 2 || Integer.valueOf(args[0]) > 40) {
            //an error message is thrown
            throw new IllegalArgumentException("The number of cards is not appropriate!");
        }
        //launch the game with the number of cards input by the user
        else{
            Application.launch(args);
        }
    }

}

import chariot.Client;
import chariot.model.Puzzle;
import chariot.model.Result;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.List;

public class DailyCommand {

    private Client client;
    private EmbedBuilder embedBuilder;
    private String gameID;


    public DailyCommand(Client client) {
        this.client = client;
        this.gameID = "";
    }



    public EmbedBuilder getPuzzleData() {


        try {

            Result<Puzzle> dailypuzzle = client.puzzles().dailyPuzzle();


            if (dailypuzzle.isPresent()) { // check if the puzzle is present
                Puzzle puzzle1 = dailypuzzle.get();

                this.gameID = puzzle1.game().id();

                String puzzleID = puzzle1.puzzle().id();
                String puzzleImg = "https://lichess1.org/training/export/gif/thumbnail/"  + puzzleID + ".gif";
                

                Puzzle.PuzzleInfo puzzleInfo = puzzle1.puzzle();

                int puzzleRating = puzzleInfo.rating();
            
                

                this.embedBuilder = new EmbedBuilder();
                this.embedBuilder.setColor(Color.blue);
                this.embedBuilder.setTitle("\uD83E\uDDE9 Puzzle of The Day \uD83E\uDDE9");
                this.embedBuilder.setImage(puzzleImg);
                this.embedBuilder.setDescription("**" + "Puzzle Rating: " + puzzleRating+ "**");


              return this.embedBuilder;
            }



        } catch (ExceptionInInitializerError ex) {

            Throwable throwable = ex.getException();
            if (throwable != null) {
                throwable.printStackTrace();
            } else {
                System.out.println("Throwable not present");
            }


        }

        return this.embedBuilder;

    }

    

    
}

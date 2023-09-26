/*******************************************************************************
 * Data Structures Post-AP
 *******************************************************************************/

import java.util.Scanner;

public class DuckDuckGoose
{
   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);
      
      while (true)
      {
         System.out.println();
         System.out.println("Welcome To...");
         System.out.println("  _____             _      _____             _       _____                       ");
         System.out.println(" |  __ \\           | |    |  __ \\           | |     / ____|                      ");
         System.out.println(" | |  | |_   _  ___| | __ | |  | |_   _  ___| | __ | |  __  ___   ___  ___  ___  ");
         System.out.println(" | |  | | | | |/ __| |/ / | |  | | | | |/ __| |/ / | | |_ |/ _ \\ / _ \\/ __|/ _ \\ ");
         System.out.println(" | |__| | |_| | (__|   <  | |__| | |_| | (__|   <  | |__| | (_) | (_) \\__ \\  __/ ");
         System.out.println(" |_____/ \\__,_|\\___|_|\\_\\ |_____/ \\__,_|\\___|_|\\_\\  \\_____|\\___/ \\___/|___/\\___| ");
         System.out.println("   _      _      _      _      _      _      _      _      _      _      _      _    ");
         System.out.println(" >(.)__ <(.)__ =(.)__ =(.)__ =(.)__ =(.)__ =(.)__ =(.)__ =(.)__ =(.)__ =(.)__ =(.)__ ");
         System.out.println("  (___/  (___/  (___/  (___/  (___/  (___/  (___/  (___/  (___/  (___/  (___/  (___/ ");
         System.out.println();
                                                                                
                                                                                
         // Create and start a new Duck Duck Goose simulation.
         Game game = new Game();
         game.run();
         
         System.out.println("\n============= GAME OVER! =============");
         System.out.print("Press ENTER to start a new Duck Duck Goose simulation:");
         String input = scanner.nextLine();
      }
   }
}

class Player
{
   private String name;
   
   public Player(String name)
   {
      this.name = name;
   }
   
   public String toString()
   {
      return name;
   }
}

class Game
{
   private CircularDoublyLinkedList<Player> data;
   private Scanner input = new Scanner(System.in);
   private int numPlayers;
   private int numRounds;
      
   public Game()
   {
      data = new CircularDoublyLinkedList<>();
   }
   
   /* Set number of players, player names, and number of rounds **/
   public void setValues()
   {
      System.out.print("[?] How many players are in the game: ");
      numPlayers = input.nextInt();
      
      System.out.print("[?] What are the players' names (spaces in between names, no commas): ");
      for (int i = 0; i < numPlayers; i++)
      {
         data.add(new Player(input.next()));
      }
      
      System.out.print("[?] How many rounds should there be: ");
      numRounds = input.nextInt();
      input.nextLine();
   }
   
   /* Main function for the Duck Duck Goose simulation **/
   public void run()
   {
      // Set values of players, player names, and number of rounds
      setValues();
      
      // Begin simulation rounds
      Player picker = data.getFirst();
      for (int round = 1; round <= numRounds; round++)
      {            
         Player p = simulateRound(round, picker);
         picker = p;
         
         if (round < numRounds)
         {
            System.out.print("\nPress ENTER to continue to the next round:");
            input.nextLine();
         }
      }
   }
   
   /* Simulate a round, given round number and current picker **/
   public Player simulateRound(int round, Player picker)
   {
      System.out.println();
      System.out.println("Round: " + round);
      System.out.println("======================================");
      System.out.println("Starting Circle: " + data);
      System.out.println("Picker: " + picker);
   
      int playersUntilPick = (int)((Math.random() * (numPlayers - 1)) + 1);
      System.out.println("# of Ducks until Goose is picked: " + playersUntilPick);
       
      int moneyIndex;
      
      int direction = (int)(Math.random() * 2);
      if (direction == 0)
      {
         System.out.println("Direction: clockwise" );
         moneyIndex = 0;
         moneyIndex += playersUntilPick;
      }
      else
      {
         System.out.println("Direction: counter-clockwise" );
         moneyIndex = data.size() - 1;
         moneyIndex -= playersUntilPick;
      }
      
      Player goose = data.remove(moneyIndex); 
      System.out.println("Goose: " + goose);
      
      int winner = (int)(Math.random() * 2);
      if (winner == 0)
      {
         System.out.println("Winner: picker (" + picker + ")");
         data.add(moneyIndex, data.getFirst());
         data.removeFirst();
         data.addFirst(goose);
         picker = goose; 
      }
      else
      {
         System.out.println("Winner: goose (" + goose + ")");
         data.add(moneyIndex, goose);
         picker = picker;
      }
      System.out.println("New Circle: " + data);
      
      return picker;
   }   
}  
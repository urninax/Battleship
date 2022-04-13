package practicing.Battleship;

public class Main{
    public static void main(String[] args){
        Person person1 = new Person();
        Person person2 = new Person();
        person1.fillDecks();
        person2.fillDecks();
        System.out.println("Lest start to place Player 1 ships");
        person1.fillTheField();
        for(int i = 0; i<4; i++){
            setShips(person1, i+1, 4-i);
        }

        System.out.println("Lets start to place Player 2 ships");
        person2.fillTheField();
        for(int i = 0; i<4; i++){
            setShips(person2, i+1, 4-i);
        }
        // booledInt: if "NO" - damaged/killed/wrong coordinates, if "YES" - missed,if "THEEND" - the end of game
        String result;
        String winner;
        while(true){
            result = "NO";
            while(result.equals("NO")){
                System.out.println("Player 1 turn");
                result = person1.attack(person2);
            }
            if(result.equals("THEEND")){
                winner = "Player 1";
                break;
            }
            result = "NO";
            while(result.equals("NO")){
                System.out.println("Player 2 turn");
                result = person2.attack(person1);
            }
            if(result.equals("THEEND")){
                winner = "Player 2";
                break;
            }
        }
        System.out.println("Game over! Winner: " + winner);
    }
    public static void setShips(Person person, int count, int decks){
        for(int i = 0; i < count; i++){
            boolean identity = false;
            while(!identity){
                identity = person.shipsPlacing(decks, i);
            }
        }
    }
}

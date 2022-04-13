package practicing.Battleship;

import java.util.Scanner;

public class Person extends Ships{

    public String attack(Person person){
        String[][] gotD4 = person.getFourdeck();
        String[][] gotD3 = person.getThreedeck();
        String[][] gotD2 = person.getTwodeck();
        String[][] gotD1 = person.getOnedeck();

        int x;
        int y;
        String attackedShip = null;
        int attackedShipNumber = 0;
        boolean isNotDamaged = true;

        System.out.println("Enter the coordinate to hit:");
        Scanner scanner = new Scanner(System.in);
        String coordinate = scanner.nextLine();
        if(coordinate.split(",").length != 2){
            System.out.println("One coordinate is empty!");
            return "NO";
        }

        try{
            x = Integer.parseInt(coordinate.split(",")[0]);
            y = Integer.parseInt(coordinate.split(",")[1]);
        }catch(NumberFormatException e){
            System.out.println("You entered something different from digits into the coordinates");
            return "NO";
        }

        if(x >= 10 || y >= 10 || x < 0 || y < 0){
            System.out.println("Some coordinate greater than 10 or less than 0");
            return "NO";
        }

        for(int i = 0; i < 1; i++){
            for(int j = 0; j<4; j++){
                if(gotD4[i][j].equals(coordinate)){
                    gotD4[i][j] = "FIRE";
                    person.setFourdeck(gotD4);
                    attackedShip = "D4";
                    attackedShipNumber = i;
                    isNotDamaged = false;
                }
            }
        }
        for(int i = 0; i < 2; i++){
            for(int j = 0; j<3;j++){
                if(gotD3[i][j].equals(coordinate)){
                    gotD3[i][j] = "FIRE";
                    person.setThreedeck(gotD3);
                    attackedShip = "D3";
                    attackedShipNumber = i;
                    isNotDamaged = false;
                }
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j<2;j++){
                if(gotD2[i][j].equals(coordinate)){
                    gotD2[i][j] = "FIRE";
                    person.setTwodeck(gotD2);
                    attackedShip = "D2";
                    attackedShipNumber = i;
                    isNotDamaged = false;
                }
            }
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j<1;j++){
                if(gotD1[i][j].equals(coordinate)){
                    gotD1[i][j] = "FIRE";
                    person.setOnedeck(gotD1);
                    attackedShip = "D1";
                    attackedShipNumber = i;
                    isNotDamaged = false;
                }
            }
        }

        if(isNotDamaged){
            System.out.println("Missed!");
            return "YES";
        }

        switch(attackedShip){
            case "D4":
                checkShip(4, attackedShipNumber, gotD4);
                break;
            case "D3":
                checkShip(3, attackedShipNumber, gotD3);
                break;
            case "D2":
                checkShip(2, attackedShipNumber, gotD2);
                break;
            case "D1":
                checkShip(1, attackedShipNumber, gotD1);
                break;
        }
        return CheckAllShips(gotD4,gotD3,gotD2,gotD1);
    }

    public void checkShip(int decks, int number, String[][] ship){
        int counterOfFires = 0;
        for(int i = 0; i < decks; i++){
            if(ship[number][i].equals("FIRE")){
                counterOfFires++;
            }
        }
        if(counterOfFires == decks){
            System.out.println("Killed!\n");
        }else{
            System.out.println("Hit!\n");
        }
    }

    public String CheckAllShips(String[][] D4, String[][] D3, String[][] D2, String[][] D1){
        String fire = "FIRE";
        for(int i = 0; i<1; i++){
            for(int j = 0; j<4; j++){
                if(!D4[i][j].equals(fire)){
                    return "NO";
                }
            }
        }
        for(int i = 0; i<2; i++){
            for(int j = 0; j<3; j++){
                if(!D3[i][j].equals(fire)){
                    return "NO";
                }
            }
        }for(int i = 0; i<3; i++){
            for(int j = 0; j<2; j++){
                if(!D2[i][j].equals(fire)){
                    return "NO";
                }
            }
        }for(int i = 0; i<4; i++){
            for(int j = 0; j<1; j++){
                if(!D1[i][j].equals(fire)){
                    return "NO";
                }
            }
        }
        return "THEEND";
    }
}

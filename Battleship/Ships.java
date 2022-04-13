package practicing.Battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Ships{
    private final String[][] ships = new String[10][10];
    private String[][] fourdeck = new String[1][4];
    private String[][] threedeck = new String[2][3];
    private String[][] twodeck = new String[3][2];
    private String[][] onedeck = new String[4][1];
    // "\ud83d\udee5"; -- ship
    // "\ud83d\udfe6"; -- zone

    public void fillTheField(){
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                this.ships[i][j] = "⬜";
            }
        }
    }

    public boolean shipsPlacing(int decks, int number){
        System.out.println("\nEnter coordinates of "+(number+1)+" "+decks+" decks ship: ");
        Scanner scanner = new Scanner(System.in);
        String coordinates = scanner.nextLine();
        String[] splitCoordinates = coordinates.split(";");
        if(splitCoordinates.length != decks){
            System.out.println("You entered the wrong coordinates!");
            return false;
        }
        int[] xes = new int[decks];
        int[] ys = new int[decks];
        for(int i = 0; i < decks; i++){
            if(splitCoordinates[i].split(",").length != 2){
                System.out.println("One coordinate is empty!");
                return false;
            }
            try{
                xes[i] = Integer.parseInt(splitCoordinates[i].split(",")[0]);
                ys[i] = Integer.parseInt(splitCoordinates[i].split(",")[1]);
            }catch(NumberFormatException e){
                System.out.println("You entered something different from digits into the coordinates");
                return false;
            }
        }
        boolean isVertical = false;
        boolean isHorizontal = false;
        for(int i = 0; i < decks; i++){
            if(xes[i] >= 10 || ys[i] >= 10 || xes[i] < 0 || ys[i] < 0){
                System.out.println("Some coordinate greater than 10 or less than 0");
                return false;
            }
        }
        if(decks != 1){
            for(int i = 0; i < decks-1; i++){
                isVertical = xes[0] == xes[i+1];
                isHorizontal = ys[0] == ys[i+1];
            }
        }else{
            isHorizontal = true;
        }
        if(isVertical==isHorizontal){
            System.out.println("Invalid coordinates");
            return false;
        }
        if(isVertical){
            ys = arrSorting(ys);
            if(coordinatesOrderCheck(ys)){
                return false;
            }
        }
        if(isHorizontal){
            xes = arrSorting(xes);
            if(coordinatesOrderCheck(xes)){
                return false;
            }
        }
        for(int i = 0; i < decks; i++){
            if(this.ships[xes[i]][ys[i]].equals("\ud83d\udee5") || this.ships[xes[i]][ys[i]].equals("\ud83d\udfe6")){
                System.out.println("A ship intersects with another ship's zone");
                return false;
            }else{
                this.ships[xes[i]][ys[i]] = "\ud83d\udee5";
            }
        }
        //printField();
        fillArea(xes, ys, isHorizontal);
        switch(decks){
            case 4:
                fourdeck[number] = splitCoordinates;
                break;
            case 3:
                threedeck[number] = splitCoordinates;
                break;
            case 2:
                twodeck[number] = splitCoordinates;
                break;
            case 1:
                onedeck[number] = splitCoordinates;
                break;
        }
        return true;
    }
    public void fillDecks(){
        Arrays.fill(fourdeck[0], "void");
        for(int i = 0; i < 2; i++){
            Arrays.fill(threedeck[i], "void");
        }
        for(int i = 0; i < 3; i++){
            Arrays.fill(twodeck[i], "void");
        }
        for(int i = 0; i < 4; i++){
            Arrays.fill(onedeck[i], "void");
        }
    }
    public int[] arrSorting(int[] array1){
        for(int i = array1.length-1; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                if(array1[j] > array1[j+1]){
                    int tmp = array1[j];
                    array1[j] = array1[j+1];
                    array1[j+1] = tmp;
                }
            }
        }
        return array1;
    }

    public boolean coordinatesOrderCheck(int[] array){
        for(int i = 0; i < array.length-1; i++){
            if((array[i]+1)!=array[i+1]){
                System.out.println("Wrong coordinate sequence");
                return true;
            }
        }
        return false;
    }

    public void printField(){
        for(int i = 0; i<ships.length; i++){
            System.out.println();
            for(int j = 0; j<ships[i].length; j++){
                System.out.print(ships[j][i] + " ");
            }
        }
    }

    public void fillArea(int[] xes, int[] ys, boolean orientation){ //area logic
        // orientation - if isHorizontal = true
        // orientation - if isVertical = false
        boolean upperside = true;
        boolean downside = true;
        boolean rightside = true;
        boolean leftside = true;
        boolean ul = true;
        boolean dl = true;
        boolean ur = true;
        boolean dr = true;
        if(xes[0] == 0){
            ul = false;
            dl = false;
            leftside = false;
        }
        if(xes[xes.length-1] == 9){
            ur = false;
            dr = false;
            rightside = false;
        }
        if(ys[0] == 0){
            upperside = false;
            ul = false;
            ur = false;
        }
        if(ys[ys.length-1] == 9){
            downside = false;
            dl = false;
            dr = false;
        }
        if(upperside){
            if(orientation){
                for(int i = 0; i < xes.length; i++){
                    this.ships[xes[i]][ys[i]-1] = "\ud83d\udfe6";
                }
            }else{
                this.ships[xes[0]][ys[0]-1] = "\ud83d\udfe6";
            }
        }
        if(downside){
            if(orientation){
                for(int i = 0; i < xes.length; i++){
                    this.ships[xes[i]][ys[i]+1] = "\ud83d\udfe6";
                }
            }else{
                this.ships[xes[xes.length-1]][ys[ys.length-1]+1] = "\ud83d\udfe6";
            }
        }
        if(leftside){
            if(orientation){
                this.ships[xes[0]-1][ys[0]] = "\ud83d\udfe6";
            }else{
                for(int i = 0; i < ys.length; i++){
                    this.ships[xes[i]-1][ys[i]] = "\ud83d\udfe6";
                }
            }
        }
        if(rightside){
            if(orientation){
                this.ships[xes[xes.length-1]+1][ys[0]] = "\ud83d\udfe6";
            }else{
                for(int i = 0; i < ys.length; i++){
                    this.ships[xes[i]+1][ys[i]] = "\ud83d\udfe6";
                }
            }
        }
        if(ul){
            this.ships[xes[0]-1][ys[0]-1] = "\ud83d\udfe6";
        }
        if(dl){
            if(orientation){
                this.ships[xes[0]-1][ys[0]+1] = "\ud83d\udfe6";
            }else{
                this.ships[xes[0]-1][ys[ys.length-1]+1] = "\ud83d\udfe6";
            }
        }
        if(ur){
            if(orientation){
                this.ships[xes[xes.length-1]+1][ys[0]-1] = "\ud83d\udfe6";
            }else{
                this.ships[xes[0]+1][ys[0]-1] = "\ud83d\udfe6";
            }
        }
        if(dr){
            if(orientation){
                this.ships[xes[xes.length-1]+1][ys[0]+1] = "\ud83d\udfe6";
            }else{
                this.ships[xes[0]+1][ys[ys.length-1]+1] = "\ud83d\udfe6";
            }
        }
    }

    public String[][] getFourdeck(){
        return fourdeck;
    }

    public String[][] getThreedeck(){
        return threedeck;
    }

    public String[][] getTwodeck(){
        return twodeck;
    }

    public String[][] getOnedeck(){
        return onedeck;
    }

    public void setFourdeck(String[][] fourdeck){
        this.fourdeck = fourdeck;
    }

    public void setThreedeck(String[][] threedeck){
        this.threedeck = threedeck;
    }

    public void setTwodeck(String[][] twodeck){
        this.twodeck = twodeck;
    }

    public void setOnedeck(String[][] onedeck){
        this.onedeck = onedeck;
    }
}
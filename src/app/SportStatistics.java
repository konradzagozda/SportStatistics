package app;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class SportStatistics {

    static boolean played(String team, Match match){
        if(match.getHomeTeam().equals(team) || match.getVisitingTeam().equals(team)){
            return true;
        }
        return false;
    }

    // checks if team passed to method won this match
    static boolean hasWon(String team, Match match){
        if(match.getHomeTeam().equals(team)){
            return match.getHomePoints() > match.getVisitingPoints();
        } else if(match.getVisitingTeam().equals(team)){
            return match.getVisitingPoints() > match.getHomePoints();
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("File:");
        String file = scan.nextLine();

        ArrayList<Match> matches= new ArrayList<>();

        try(Scanner fileScanner = new Scanner(Paths.get(file))){
            while(fileScanner.hasNextLine()){
                String[] match = fileScanner.nextLine().split(",");
                String home = match[0];
                String guest =match[1];
                int homePoints = Integer.valueOf(match[2]);
                int visitingPoints = Integer.valueOf(match[3]);
                matches.add(new Match(home,guest,homePoints,visitingPoints));
            }
        }catch(Exception e){
            System.out.println("Error " + e.getMessage());
        }

        System.out.println("Team:");
        String team = scan.nextLine();
        int gamesPlayed = 0;
        int wonGames = 0;
        int lostGames = 0;
        for(Match m : matches){
            if (played(team,m)){
                gamesPlayed++;
                if(hasWon(team,m)){
                    wonGames++;
                } else {
                    lostGames++;
                }
            }
        }
        System.out.println("Games: " + gamesPlayed);
        System.out.println("Wins: " + wonGames);
        System.out.println("Losses: " + lostGames);
    }

}

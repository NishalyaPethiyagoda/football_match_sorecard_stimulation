public class Game {

    public static void main( String[] args ) {

        System.out.println("\n\n------= Football match between Team 1 vs Team 2 =--------\n\n");

        //initializing team 1 players
        // (T1_1 : team 1's player 1)
        // (T1_2 : team 1's player 2)
        String[] team_1 = new String[]{"T1_1","T1_2","T1_3","T1_4","T1_5","T1_6","T1_7","T1_8","T1_9","T1_10",
                "T1_11","T1_12","T1_13","T1_14","T1_15","T1_16","T1_17","T1_18"};

        //initializing team 2 players
        String[] team_2 = new String[]{"T2_1","T2_2","T2_3","T2_4","T2_5","T2_6","T2_7","T2_8","T2_9","T2_10",
                "T2_11","T2_12","T2_13","T2_14","T2_15","T2_16","T2_17","T2_18"};

        int noOfPlayers = 18;
        
        //initiating team objects
        team t_1 = new team();
        t_1.teamInstantiate("Team1", 0, team_1, noOfPlayers);

        team t_2=new team();
        t_2.teamInstantiate("Team2", 0, team_2, noOfPlayers);

        Scoreboard s1=new Scoreboard();

        s1.matchPlay(t_1, t_2);
    }
}

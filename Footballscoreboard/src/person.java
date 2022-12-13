import java.util.*;

//parent class 'person'
class person
{
    String name, title;

    void setData(String na, String ti)    //set data method
    {
        name = na ;
        title = ti;
    }
}

//child class for player
class player extends person
{
    private int personalScore;
    String teamName;
    boolean ballStatus;

    player()
    {
        personalScore = 0;
        teamName = " ";
        ballStatus = false;
    }

    void playerAtFirst( int pS,boolean bS,String tN,String na,String ti)
    {
        setData( na, ti) ;
        personalScore = pS;
        teamName = tN;
        ballStatus = bS;
    }
    void goal()                       //increase when goal scored method
    {
        personalScore++;
    }
    void ballstatus(boolean stat)    //ball possession status among palyers method
    {
        ballStatus = stat;
    }

}

//class for team
class team
{
    String teamName;
    private int teamScore;
    List<player> players = new ArrayList<player>();

    void selectingPlayer()         //selecting 11 players from the 18 players for the tema
    {
        Collections.shuffle(players);
    }
    void teamInstantiate(String na, int tS, String pN[], int size)       //teamInstantiate a team method
    {
        teamName = na;
        teamScore = tS;

        for(int i = 0; i < size; i++)
        {
            player player_ob = new player();
            player_ob.playerAtFirst(0,false , na ,  pN[i] ,"player");
            players.add(player_ob);
        }
    }
    void scoreIncrease()     //increase the team score
    {
        teamScore++;
    }
    int getScore()           //get the team score
    {
        return teamScore;
    }
}

//child class for coach
class coach extends person
{
    coach()
    {
    };

    coach(String na)
    {
        name = na;
        title = "Coach";
    }
}

//child class for referee
class referee extends person
{
    referee()
    {
    };

    referee(String na)
    {
        name = na;
        title = "Referee";
    }
}


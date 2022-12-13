import java.util.Random;

 class Scoreboard {

     //variables
     boolean ballStatus;       //ball on field or out of field
     player ballPlayer;        //player that holds the ball

     team tossTeam,            //team won the toss
             ballTeam,         //team that hold the ball
             opponentTeam;

     String lastGoal,          // name of the player who scorde the previous goal
             period;           //current time

     int timeLeft;            //time left till end

     //methods
     void delay(int x)       //to stimulate moments
     {
         try
         {
             Thread.sleep(2000);
         }
         catch (InterruptedException ex)
         {
             Thread.currentThread().interrupt();
         }
     }
     void toss(team team1, team team2)         //toss
     {
         System.out.println("\n____putting the toss____\n");

         Random rand = new Random();
         int toss = rand.nextInt(2);

         if (toss == 1)  //  team 1 wins the toss
         {
             System.out.println("\t" + team1.teamName + " won the toss");
             tossTeam = team1;

             team1.players.get(0).ballstatus(true);
             ballPlayer = team1.players.get(0);
             ballTeam = team1;
             opponentTeam = team2;

         }
         else       //  team 2 wins the toss
         {
             System.out.println("\t" + team2.teamName + " won the toss");
             tossTeam = team2;

             team2.players.get(0).ballstatus(true);
             ballPlayer = team2.players.get(0);
             ballTeam = team2;
             opponentTeam = team1;
         }
     }

     void changeBallPocessionT()      //randomly chnaging ball pocessing team
     {
         Random rand = new Random();

         int playerRankNum = rand.nextInt(10);
         ballPlayer.ballstatus(false);

         team temp_ob = opponentTeam;
         opponentTeam = ballTeam;
         ballTeam = temp_ob;

         ballPlayer = ballTeam.players.get(playerRankNum);   //changing player number
         ballPlayer.ballstatus(true);
     }

     void changeBallPocessionPla()              //method for if ball passed to same team
     {
         Random rand = new Random();
         int playerRankNum = rand.nextInt(10);

         while (ballTeam.players.get(playerRankNum).ballStatus) {
             playerRankNum = rand.nextInt(10);
         }

         System.out.println("\tball passed to : " + ballTeam.players.get(playerRankNum).name);

         ballPlayer.ballstatus(false);

         ballPlayer = ballTeam.players.get(playerRankNum);

         ballPlayer.ballstatus(true);
     }

     void lineOut_Throw()                   //check for ball is out of bounds
     {
         ballStatus = false;
         System.out.println("\tline out");

         changeBallPocessionT();

         System.out.println("\tline out thrown by : " + ballPlayer.name);
     }

     void penalties(team team1, team team2)   // penalty kicks. each team 5 kicks
     {
         System.out.println("\tMatch Drawn..... Penalties scheduled");

         Random rand = new Random();

         for (int i = 0; i < 5; i++)         //team 1 penalties
         {
             System.out.println("\n\n_________________________________________________________________________________________________________");
             System.out.println("\t" + team1.teamName + " is kicking penalties");
             System.out.println("\t" + team1.teamName + " score " + team1.getScore() + "\t\t\t" + team2.teamName + " score " + team2.getScore() + "\n\n");

             System.out.println("\t5 penalty kicks");

             int Penalties = 5 - i;
             System.out.println("\tremaining penalties : " + Penalties);

             int scoredOrNot = rand.nextInt(2);     // if goal scored or not

             if (scoredOrNot == 1) {
                 System.out.println("\t didn't score");
             } else {
                 int playerRankNum = rand.nextInt(10);
                 System.out.println("\t" + team1.players.get(playerRankNum).name + "scores a goal");
                 team1.scoreIncrease();
                 team1.players.get(playerRankNum).goal();
             }
             delay(500);
         }

         for (int i = 0; i < 5; i++)          //team 1 penalties
         {
             System.out.println("\n\n_________________________________________________________________________________________________________");
             System.out.println(team1.teamName + " score " + team1.getScore() + "\t\t\t" + team2.teamName + " Score " + team2.getScore() + "\n\n");
             System.out.println(team2.teamName + " is kicking penalties");

             System.out.println("\t5 penalty kicks");

             int Penalties = 5 - i;
             System.out.println("\tremaining penalties : " + Penalties);

             int scoredOrNot = rand.nextInt(2);         // if goal scored or not

             if (scoredOrNot == 1) {
                 System.out.println("\t didn't score");
             } else {
                 int playerRankNum = rand.nextInt(10);
                 System.out.println("\t" + team2.players.get(playerRankNum).name + "scores a goal ");
                 team2.scoreIncrease();
                 team2.players.get(playerRankNum).goal();
             }
             delay(500);
         }

     }

     void matchPlay(team team1, team team2)  // main match function
     {
         team1.selectingPlayer();           //initiating teams
         team2.selectingPlayer();

         Random rand = new Random();

         toss(team1, team2);               // calling the toss

         lastGoal = "0";
         timeLeft = 105;                    //total time
         period = "First Half";

         System.out.println("***************= whistle blows and the match is on the way =**************");
         while (true) {
             delay(1000);
             System.out.println("\n\n--------------------------------------------------------------------------------");//display the details
             System.out.println(period);
             System.out.println("\t" + team1.teamName + " score " + team1.getScore() + "\t\t\t" + team2.teamName + " Score " + team2.getScore() + "\n\n");
             System.out.println("\tlast goal by : " + lastGoal);
             System.out.println("\ttime count down :" + (timeLeft));
             System.out.println("\tplayer with the ball : " + ballPlayer.name);

             int event;
             event = rand.nextInt(8); //get 0-7 random numbers

             if (event >= 0 && event <= 2)        //ball passed to one of their own player
             {
                 changeBallPocessionPla();
             } else if (event >= 3 && event <= 5)   // ball taken by an opponent player
             {
                 changeBallPocessionT();
                 System.out.println("\tBall Taken by  : " + ballPlayer.name);
             } else if (event == 6)     // outline
             {
                 lineOut_Throw();
                 int throwIn = rand.nextInt(5);

                 if (throwIn >= 0 || throwIn <= 3)             //thrown to same team player
                 {
                     changeBallPocessionPla();
                 } else                                           //thrown to oponent team player
                 {
                     changeBallPocessionT();
                 }
             } else                                 // shooting a goal
             {
                 int goalChance = rand.nextInt(3);

                 if (goalChance == 0 || goalChance == 1)    //goal stopped or ball gone out
                 {
                     System.out.println("\tgoal attempt failed");
                     changeBallPocessionT();
                     System.out.println("\tgoalie kicks the ball to " + ballPlayer.name);
                 } else                                      //goal scored
                 {
                     System.out.println("\tgoal scored by " + ballPlayer.name);

                     ballTeam.scoreIncrease();
                     ballPlayer.goal();

                     lastGoal = ballPlayer.name;

                     System.out.println("restart by " + ballPlayer.name);
                 }
             }

             if (timeLeft > 0) {
                 timeLeft--;
             }

             if (timeLeft > 45 && timeLeft <= 60)    //interval
             {
                 timeLeft -= 15;
                 delay(3000);

                 System.out.println("\n\n\n*********************= Interval =*************************");

                 System.out.println("\n\nmatch restarted for second Half");
                 period = "Second Half";

                 if (ballTeam == tossTeam) //restart done by team that didnt win the toss
                 {
                     changeBallPocessionT();
                     System.out.println("\tmatch restarted by : " + ballTeam.teamName);
                 } else {
                     System.out.println("\tmatch restarted by : " + ballTeam.teamName);
                 }
             }
/*
             int overtimeLimit = 0, overtimehalf = 0;

             // at end of the 105 minutes
             if(timeLeft == 0 && (team1.getScore() == team2.getScore()))   //if scores tied
             {
                 if (overtimeLimit < 2 && overtimehalf == 0)        //check for game has gone for 2 overtime halfs
                 {
                     overtimehalf++;
                     period = "Overtime";
                     toss(team1, team2);

                 }
                 else if(overtimeLimit < 2 && overtimehalf == 1)
                 {
                     overtimehalf++;
                     period = "Overtime";
                     changeBallPocessionT();
                     timeLeft = 15;//15 mins added to game ending time
                 }
                 else
                 {
                     break;
                 }
                 overtimeLimit++;
             }*/

             if (timeLeft == 0 && (team1.getScore() == team2.getScore()))
             {
                 penalties(team1, team2);   //shooting penalties

                 //at end of penalties
                 if ( (team1.getScore() > team2.getScore()))         //team 1 victory
                 {
                     System.out.println("\n\n************************************************");
                     System.out.println("\nMatch Won by: " + team1.teamName + "-------");
                     System.out.println("Final Score is :");
                     System.out.println(team1.teamName + " : " + team1.getScore());
                     System.out.println(team2.teamName + " : " + team2.getScore());
                 } else if ( (team1.getScore() < team2.getScore()))    //team 2 victory
                 {
                     System.out.println("\n\n************************************************");
                     System.out.println("\nMatch Won by: " + team2.teamName + "-------");
                     System.out.println("Final Score is :");
                     System.out.println(team1.teamName + " : " + team1.getScore());
                     System.out.println(team2.teamName + " : " + team2.getScore());
                 }
             }
             else if (timeLeft == 0 && (team1.getScore() > team2.getScore()))        //team 1 victory
             {
                 System.out.println("\n\n************************************************");
                 System.out.println("\nMatch Won by: " + team1.teamName + "-------");
                 System.out.println("Final Score is :");
                 System.out.println(team1.teamName + " : " + team1.getScore());
                 System.out.println(team2.teamName + " : " + team2.getScore());
                 break;
             }
             else if (timeLeft == 0 && (team1.getScore() < team2.getScore()))    //team 2 victory
             {
                 System.out.println("\n\n************************************************");
                 System.out.println("\nMatch Won by: " + team2.teamName + "-------");
                 System.out.println("Final Score is :");
                 System.out.println(team1.teamName + " : " + team1.getScore());
                 System.out.println(team2.teamName + " : " + team2.getScore());
                 break;
             }


         }
    }
}

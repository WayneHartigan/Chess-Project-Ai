import java.util.*;

public class AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  public Move nextBestMove(Stack whiteMoves, Stack blackMoves){
    Stack whiteBackup = (Stack) whiteMoves.clone();
    Stack blackMove = (Stack) blackMoves.clone();
    Move whiteMove, standardMove, attackMove;
    Square blackPosition;
    int point = 0;
    int chosenPiece = 0;
    attackMove = null;

    while(!whiteMoves.empty()){
      whiteMove = (Move) whiteMoves.pop();
      standardMove = whiteMove;

      if ((standardMove.getStart().getYC() < standardMove.getLanding().getYC())
      && (standardMove.getLanding().getXC() == 3) && (standardMove.getLanding().getYC() == 3)
      || (standardMove.getLanding().getXC() == 4) && (standardMove.getLanding().getYC() == 3)
      || (standardMove.getLanding().getXC() == 3) && (standardMove.getLanding().getYC() == 4)
      || (standardMove.getLanding().getXC() == 4) && (standardMove.getLanding().getYC() == 4)) {
        point = 0;

        // set best move
        if (point > chosenPiece) {
          chosenPiece = point;
          attackMove = standardMove;
        }
      }
      while(!blackMove.isEmpty()){
        point = 0;
        blackPosition = (Square) blackMove.pop();
        if ((standardMove.getLanding().getXC() == blackPosition.getXC()) && (standardMove.getLanding().getYC() == blackPosition.getYC())){
          // set value to pieces
          if(blackPosition.getName().equals("BlackPawn")){
            point = 1;
          }
          else if(blackPosition.getName().equals("BlackKnight") || blackPosition.getName().equals("BlackBishop")){
            point = 3;
          }
          else if(blackPosition.getName().equals("BlackRook")){
            point = 5;
          }
          else if(blackPosition.getName().equals("BlackQueen")){
            point = 9;
          }
          else if(blackPosition.getName().equals("BlackKing")){
            point = 1000;
          }
        }
        if(point > chosenPiece){
          chosenPiece = point;
          attackMove = standardMove;
        }
      }
      blackMove = (Stack) blackMoves.clone();
    }
    if(chosenPiece > 0){
      System.out.println("The best move selected by the AI is: " + chosenPiece);
      return attackMove;
    }
    return randomMove(whiteBackup);

  }

  public Move twoLevelsDeep(Stack whiteMoves, Stack blackMoves){
    Stack whiteBackup = (Stack) whiteMoves.clone();
    Stack blackMove = (Stack) blackMoves.clone();
    Move whiteMove, standardMove, attackMove;
    Square blackPosition;
    int point = 0;
    int chosenPiece = 0;
    attackMove = null;

    while(!whiteMoves.empty()){
      whiteMove = (Move) whiteMoves.pop();
      standardMove = whiteMove;

      if ((standardMove.getStart().getYC() < standardMove.getLanding().getYC())
      && (standardMove.getLanding().getXC() == 3) && (standardMove.getLanding().getYC() == 3)
      || (standardMove.getLanding().getXC() == 4) && (standardMove.getLanding().getYC() == 3)
      || (standardMove.getLanding().getXC() == 3) && (standardMove.getLanding().getYC() == 4)
      || (standardMove.getLanding().getXC() == 4) && (standardMove.getLanding().getYC() == 4)) {
        point = 0;

        // set best move
        if (point > chosenPiece) {
          chosenPiece = point;
          attackMove = standardMove;
        }
      }
      while(!blackMove.isEmpty()){
        point = 0;
        blackPosition = (Square) blackMove.pop();
        if ((standardMove.getLanding().getXC() == blackPosition.getXC()) && (standardMove.getLanding().getYC() == blackPosition.getYC())){
          // set value to pieces
          if(blackPosition.getName().equals("BlackPawn")){
            point = 1;
          }
          else if(blackPosition.getName().equals("BlackKnight") || blackPosition.getName().equals("BlackBishop")){
            point = 3;
          }
          else if(blackPosition.getName().equals("BlackRook")){
            point = 5;
          }
          else if(blackPosition.getName().equals("BlackQueen")){
            point = 9;
          }
          else if(blackPosition.getName().equals("BlackKing")){
            point = 1000;
          }
        }
        if(point > chosenPiece){
          chosenPiece = point;
          attackMove = standardMove;
        }
      }
      blackMove = (Stack) blackMoves.clone();
    }
    if(chosenPiece > 0){
      System.out.println("The best move selected by the AI is: " + chosenPiece);
      return attackMove;
    }
    return randomMove(whiteBackup);

  }
}

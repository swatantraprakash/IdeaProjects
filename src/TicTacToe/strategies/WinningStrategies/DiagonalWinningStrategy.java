package TicTacToe.strategies.WinningStrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Symbol;
import TicTacToe.strategies.Botplaying.MediumBotPlayingStrategy;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    Map<Symbol,Integer> leftDiaCount=new HashMap<>();
    Map<Symbol,Integer> rightDiaCount=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
       int row=move.getCell().getRow();
       int col=move.getCell().getCol();
       Symbol symbol=move.getPlayer().getSymbol();

       //check left diagonal
        if(row==col){
            if(!leftDiaCount.containsKey(symbol)){
                leftDiaCount.put(symbol,0);
            }

            leftDiaCount.put(symbol,leftDiaCount.get(symbol)+1);
        }

        //check for right diagonal
        if(row+col==board.getDimension()-1){
            if(!rightDiaCount.containsKey(symbol)){
                rightDiaCount.put(symbol,0);
            }

            rightDiaCount.put(symbol,rightDiaCount.get(symbol)+1);
        }
        if(leftDiaCount.get(symbol)==board.getDimension()){
            return true;
        }
        if(rightDiaCount.get(symbol)==board.getDimension()){
            return true;
        }

        return false;
    }
}

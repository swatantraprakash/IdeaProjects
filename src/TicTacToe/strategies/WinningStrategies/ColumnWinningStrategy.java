package TicTacToe.strategies.WinningStrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    Map<Integer, Map<Symbol,Integer>> countMap=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getSymbol();

        if(!countMap.containsKey(col)){
            countMap.put(col,new HashMap<>());
        }

        Map<Symbol,Integer> colMap=countMap.get(col);

        if ((!colMap.containsKey(symbol))){
            colMap.put(symbol,0);
        }

        colMap.put(symbol,colMap.get(symbol)+1);

        if(colMap.get(symbol) == board.getDimension()){
            return true;
        }

        return false;
    }
}

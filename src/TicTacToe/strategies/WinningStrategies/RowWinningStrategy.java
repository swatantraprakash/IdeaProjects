package TicTacToe.strategies.WinningStrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    Map<Integer,Map<Symbol,Integer>> countMap=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();

        if(!countMap.containsKey(row)){
            countMap.put(row,new HashMap<>());
        }

        Map<Symbol,Integer> rowMap=countMap.get(row);

        if ((!rowMap.containsKey(symbol))){
            rowMap.put(symbol,0);
        }

        rowMap.put(symbol,rowMap.get(symbol)+1);

        if(rowMap.get(symbol) == board.getDimension()){
            return true;
        }

        return false;
    }
}

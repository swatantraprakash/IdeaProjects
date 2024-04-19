package TicTacToe.models;

import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private PlayerType playerType;
    private Long id;
    private Scanner scanner;
public Player(Long id,String name,Symbol symbol,PlayerType playerType){
           this.id=id;
           this.name=name;
           this.symbol=symbol;
           this.playerType=playerType;
           this.scanner=new Scanner(System.in);
}
    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Move makeMove(Board board){
        System.out.println("Please enter the row number you want make move");
        int row=scanner.nextInt();
        System.out.println("Please enter the col number you want make move");
        int col=scanner.nextInt();
        return new Move(new Cell(row,col),this);
    }

//    public static void main(String[] args) {
//        int i = change(5,new int[]{1,2,5});
//        System.out.println(i);
//    }
//    public static int change(int amount, int[] coins) {
//        int dp[][] =new int[coins.length+1][amount+1];
//        for(int [] arr:dp){
//            Arrays.fill(arr,-1);
//        }
//        return numberOfCoins(coins,dp,coins.length-1,amount);
//    }
//    public static int numberOfCoins(int[]c,int [][]dp,int size,int amount){
//        if(amount==0){
//            return 1;
//        }
//        if(size==0){
//            return 0;
//        }
//        if(dp[size][amount]!=-1){
//            return dp[size][amount];
//        }
//        dp[size][amount]=numberOfCoins(c,dp,size-1,amount)+numberOfCoins(c,dp,size,amount-c[size-1]);
//        return dp[size][amount];
//    }
    
}
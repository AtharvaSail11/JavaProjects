import java.util.*;

public class TicTacToeGame {
    static String sameChar = "";
    static String moves = "012345678";
    static int moveCount = 8;
    static int dashCount=0;
    static boolean Xwins=false;
    static boolean Owins=false;

    // It is used to update the game board.
    static void boardPrint(char[] board, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(board[i] + " ");
            // for printing numbers on next line
            if (i == 2 || i == 5) {
                System.out.println();
            }
        }
        System.out.println();

    }

    // it is a gameLoop
    static void gameLoop(char[] board, int size, int Option) {
        int turnX = 0;
        int turnO = 1;
        while (true) {
            boardPrint(board, size);
            if (turnX == 1) {
                turnX = 0;
                turnO = 1;
            } else {
                turnX = 1;
                turnO = 0;
            }

            if (turnX == 1) {
                System.out.println("X's Turn");
            }
            if (turnO == 1) {
                System.out.println("O's Turn");
            }
            boardInput(board, size, turnX, turnO, Option);
            win(board, size);
        }
    }

    // It is used for counting dashes. Dash count can be used to determine if game
    // is a tie or not.
    static int dashCounter(char[] board, int size) {
        int Count = 0;
        for (int i = 0; i < size; i++) {
            if (board[i] == '-') {
                Count++;
            }
        }
        return Count;
    }

    static void win(char[] board, int size) {
        int dashCount = dashCounter(board, size);
        int[][] winConditions = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                { 0, 4, 8 }, { 2, 4, 6 } };
        int num1, num2, num3;
        for (int i = 0; i < winConditions.length; i++) {
            for (int j = 0; j < winConditions[i].length; j++) {
                num1 = winConditions[i][0];
                num2 = winConditions[i][1];
                num3 = winConditions[i][2];
                if (board[num1] == 'X' && board[num2] == 'X' && board[num3] == 'X') {
                    System.out.println("X wins");
                    Xwins=true;
                    for (int k = 0; k < size; k++) {
                        board[k] = '-';
                    }
                    moves = "012345678";
                    moveCount = 8;
                    break;
                }

                if (board[num1] == 'O' && board[num2] == 'O' && board[num3] == 'O') {
                    System.out.println("O wins");
                    Owins=true;
                    for (int k = 0; k < size; k++) {
                        board[k] = '-';
                    }
                    moves = "012345678";
                    moveCount = 8;
                    break;
                }
            }
        }
        if (dashCount == 0 && Xwins==true) {
            System.out.println("X wins");
            for (int i = 0; i < size; i++) {
                board[i] = '-';
            }
            moves = "012345678";
            moveCount = 8;
            Xwins=false;
        }
        else if(dashCount ==0 && Owins==true){
            System.out.println("O wins");
            for (int i = 0; i < size; i++) {
                board[i] = '-';
            }
            moves = "012345678";
            moveCount = 8;
            Owins=false;
        }
        else if(dashCount==0 && Owins==false && Xwins==false ){
            System.out.println("It is a Tie");
            for (int i = 0; i < size; i++) {
                board[i] = '-';
            }
            moves = "012345678";
            moveCount = 8;
        }
    }

    static void boardInput(char[] board, int size, int turnX, int turnO, int option) {
        if (option == 1) {
            if (turnX == 1) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the position number:");
                int input = sc.nextInt();
                int index = 0;
                if (input == 0) {
                    System.exit(0);
                }
                board[input - 1] = 'X';
                char moveChar = Integer.toString(input - 1).charAt(0);
                for (int i = 0; i < moves.length(); i++) {
                    if (moves.charAt(i) == moveChar) {
                        index = i;
                        moveCount--;
                        break;
                    }
                }
                String newString1 = moves.substring(0, index);
                String newString2 = moves.substring(index + 1);
                moves = newString1 + newString2;
                System.out.println(moves);
            }
            if (turnO == 1) {
                int random = (int) Math.floor(Math.random() * moveCount);
                int index = 0;
                char moveChar = moves.charAt(random);
                String move = "" + moveChar;
                board[Integer.parseInt(move)] = 'O';
                moveCount--;
                for (int i = 0; i < moves.length(); i++) {
                    if (moves.charAt(i) == moveChar) {
                        index = i;
                        break;
                    }
                }
                String newString1 = moves.substring(0, index);
                String newString2 = moves.substring(index + 1);
                moves = newString1 + newString2;
                System.out.println(moves);
            }
        } else if (option == 2) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the position number:");
            int input = sc.nextInt();
            if (input == 0) {
                System.exit(0);
            }
            if (turnX == 1) {
                board[input - 1] = 'X';
            }
            if (turnO == 1) {
                board[input - 1] = 'O';
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("which Game mode to play:");
        System.out.println("1:Singleplayer, 2:Multiplayer");
        int option = sc.nextInt();
        int turnX = 1;
        int turnO = 0;
        char[] board = { '-', '-', '-', '-', '-', '-', '-', '-', '-' };
        int size = board.length;
        gameLoop(board, size, option);

    }
}

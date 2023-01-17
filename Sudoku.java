import javafx.application.Application;
import java.util.Arrays;

public class Sudoku {
    private static boolean completed = false;
    static int[] grid;
    boolean keepGoing;
    public static void main(String[] args) {
        grid = new int[81];
        
        grid[5] = 5; //manually set pre-determined squares for the puzzle
        grid[76] = 8;
        grid[22] = 2;

        Application.launch(GUI.class, args);
    }

    public static int[] row(int[] grid, int rowNum) {
        int[] row = new int[9];
        for (int i = 0; i < 9; i++)
            row[i] = grid[9*rowNum + i];

        return row;
    }

    public static int[] column(int[] grid, int colNum) {
        int[] col = new int[9];
        for (int i = 0; i < 9; i++)
            col[i] = grid[9*i + colNum];

        return col;
    }

    public static int[] box(int[] grid, int boxNum) {
        int[] box = new int[9];
        switch (boxNum) {
            case 0:
            case 1:
            case 2:
                for (int i = 0; i < 9; i++) {
                    if (i > 5) {
                        box[i] = grid[3 * boxNum + i + 12];
                    } else if (i > 2) {
                        box[i] = grid[3*boxNum + i + 6];
                    } else {
                        box[i] = grid[3*boxNum + i];
                    }
                }
                break;
            case 3:
            case 4:
            case 5:
                for (int i = 0; i < 9; i++) {
                    if (i > 5) {
                        box[i] = grid[3 * boxNum + i + 30];
                    } else if (i > 2) {
                        box[i] = grid[3*boxNum + i + 24];
                    } else {
                        box[i] = grid[3*boxNum + i + 18];
                    }
                }
                break;
            case 6:
            case 7:
            case 8:
                for (int i = 0; i < 9; i++) {
                    if (i > 5) {
                        box[i] = grid[3 * boxNum + i + 48];
                    } else if (i > 2) {
                        box[i] = grid[3*boxNum + i + 42];
                    } else {
                        box[i] = grid[3*boxNum + i + 36];
                    }
                }
        }
        return box;
    }

    public static void display(int[] grid) {
        System.out.print(" |-----------+-----------+-----------|\n");
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (i % 3 == 0) {
                    System.out.print(" |  "+grid[9*j+i]+" ");
                } else
                    System.out.print(" "+grid[9*j+i]+" ");
            }
            if ((j+1) % 3 == 0) {
                System.out.print(" |\n |-----------+-----------+-----------|\n");
            } else
                System.out.print(" | \n");
        }
    }

    public static boolean contains(int[] toBeChecked, int value) {
        for (int i = 0; i < 9; i++) {
            if (toBeChecked[i] == value)
                return true;
        }
        return false;
    }
    

    public static boolean valid(int[] grid, int index, int boxNum, int value) {
        int[] row = row(grid, index/9);
        int[] col = column(grid, index%9);
        int[] box = box(grid, boxNum);

        boolean valid = !contains(row, value) && !contains(col, value) && !contains(box, value);

        if (valid) {
            grid[index] = value;
            int[] copy = new int[81];
            for (int i = 0; i < 81; i++)
                copy[i] = grid[i];
            Arrays.sort(copy);

            if (copy[0] != 0)
                completed = true;
        }
        
        return (valid);
        }

        public static boolean getCompleted() {
            return completed;
        }
}
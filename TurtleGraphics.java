import java.util.Arrays;
import java.util.Scanner;

public class TurtleGraphics{
    Scanner sc = new Scanner(System.in);

    private final int GRID_ROWS = 20;
    private final int GRID_COLS = 20;
    private enum Direction{UP, DOWN, LEFT, RIGHT};

    private int[][] arrGrid = new int[GRID_ROWS][GRID_COLS];
    private int[] coords2D = new int[2];
    private boolean penDown = false;
    private int endPoint;
    private char pen = '*';

    private Direction drawDir = Direction.RIGHT;

    public TurtleGraphics(){
        reset();
    }
    public void setPen(){
        System.out.printf("\nCurrent pen: %c\n", pen);
        System.out.print("Enter new pen character: ");
        pen = sc.next().charAt(0);
    }
    public void penUp(){
        if(penDown == true)
            penDown = false;
    }
    public void penDown(){
        if(penDown == false)
            penDown = true;

        arrGrid[coords2D[0]][coords2D[1]] = 1;
    }
    public void turnLeft(){
        switch(drawDir){
            case RIGHT:
                drawDir = Direction.UP;
                break;
            case UP:
                drawDir = Direction.LEFT;
                break;
            case LEFT:
                drawDir = Direction.DOWN;
                break;
            case DOWN:
                drawDir = Direction.RIGHT;
                break;
        }
    }
    public void turnRight(){
        switch(drawDir){
            case RIGHT:
                drawDir = Direction.DOWN;
                break;
            case DOWN:
                drawDir = Direction.LEFT;
                break;
            case LEFT:
                drawDir = Direction.UP;
                break;
            case UP:
                drawDir = Direction.RIGHT;
                break;
        }
    }
    public void getLocationDirection(){
         System.out.printf("\nPen is at location (%d,%d)", coords2D[0], coords2D[1]);
         System.out.printf("\nDrawing direction is %s\n", drawDir);
         System.out.println("**********************\n");
    }
    public void forward(int distance){
        switch(drawDir){
            case RIGHT:
                endPoint = coords2D[1] + distance;

                if(endPoint >= GRID_COLS)
                    endPoint = GRID_COLS - 1;

                if(penDown){
                    for(int col=coords2D[1]; col<endPoint; col++){
                        arrGrid[coords2D[0]][col] = 1;
                    }
                }

                coords2D[1] = endPoint;
                break;
            case LEFT:
                endPoint = coords2D[1] - distance;

                if(endPoint <= 0)
                    endPoint = 0;

                if(penDown){
                    for(int col=coords2D[1]; col>=endPoint; col--){
                        arrGrid[coords2D[0]][col] = 1;
                    }
                }

                coords2D[1] = endPoint;
                break;
            case UP:
                endPoint = coords2D[0] - distance;

                if(endPoint <= 0)
                    endPoint = 0;

                if(penDown){
                    for(int row=coords2D[0]; row>=endPoint; row--){
                        arrGrid[row][coords2D[1]] = 1;
                    }
                }

                coords2D[0] = endPoint;
                break;
            case DOWN:
                endPoint = coords2D[0] + distance;

                if(endPoint >= GRID_ROWS)
                    endPoint = GRID_ROWS - 1;

                if(penDown){
                    for(int row=coords2D[0]; row<endPoint; row++){
                        arrGrid[row][coords2D[1]] = 1;
                    }
                }

                coords2D[0] = endPoint;
                break;
        }
    }
    public void showGrid(){
        System.out.println();
        for(int row=0; row<arrGrid.length; row++){
            // border
            System.out.print("| ");
            for(int column=0; column<arrGrid[row].length; column++){
                System.out.printf("%c",
                        (arrGrid[row][column] == 1) ? pen : ' ');
            }
            System.out.print(" |");
            System.out.println();
        }
    }
    public void reset(){
        for(int[] row : arrGrid)
            Arrays.fill(row, 0);

        coords2D[0] = 9;
        coords2D[1] = 9;
    }
    public void printMenu(){
        getLocationDirection();
        System.out.println("0. Change Pen\n1. Pen Up\n2. Pen Down\n3. Turn Right\n4. Turn Left");
        System.out.println("5 n. Move forward n spaces");
        System.out.println("6. Display the Drawing\n8. Reset Drawing\n9. Finish Drawing");
        System.out.print("> ");
    }
}
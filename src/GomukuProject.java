/**
 * Created by Steven on 4/27/2017.
 */
        import processing.core.*;
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.util.*;
        import java.util.concurrent.ThreadLocalRandom;

        import java.util.ArrayList;

public class GomukuProject extends PApplet {

    public static void main(String... args) {
        PApplet.main("GomukuProject");
    }
    class point {
        int x; int y; int value; int turn;
        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }
       // public String toString() {return x + " " + y + " " + value;}
    }
    ////////////////////
    point[][] grid = new point[10][10];
    Integer turnNumber = 0;
    // HashMap<point,Integer> values = new HashMap<>();
    PImage board;
    PImage whiteStone;
    PImage blackStone;
    String gameover = "";
    ///////////////////
    public void settings() {
        smooth(3);
        size(500, 500);

        for (int i = 0; i < width; i += width / 10) {
            for (int w = 0; w < height; w += height / 10) {
                point p = new point(i, w);
                grid[i / 50][w / 50] = p;
            }
        }

    }
    public void setup() {

        String url1 = "https://woodflooringtrends.files.wordpress.com/2012/02/natural-maple-unfinished.jpg";
        String url2 = "https://raw.githubusercontent.com/zpmorgan/gostones-render/master/b.png";
        String url3 = "https://opengameart.org/sites/default/files/w_1.png";
        board = loadImage(url1, "jpg");
        blackStone = loadImage(url2, "png");
        whiteStone = loadImage(url3, "png");
        image(board, 0, 0);
    }


    public void draw() {
        int widthSpace = width / 10;
        int heightSpace = height / 10;
        for (int i = 0; i < width; i += widthSpace) {
            strokeWeight((float) 1.1);
            line(i, 0, i, height);
        }
        for (int w = 0; w < height; w += heightSpace) {
            strokeWeight((float) 1.1);
           line(0, w, width, w);
        }
        textSize(45);
        fill(255);


//        for(int i = 0;  i < grid.length; i ++) {
//            for(int a = 0; a < grid.length; a++) {
//                if(grid[i][a].value != 0) {
//                    text(grid[i][a].turn, grid[i][a].x,grid[i][a].y);
//                }
//            }
//        }
       CPU();
        text(gameover, (width / 2) , height / 2);
        if(!gameover.equals("")) {
            stop();
        }
    }
    public void CPU() {
        if(turnNumber %2 +1 == 2) {
            System.out.println(turnNumber);
            ArrayList<Boolean> threats = new ArrayList<>();
            int x = (ThreadLocalRandom.current().nextInt(0, grid.length));
            int y = (ThreadLocalRandom.current().nextInt(0, grid.length));
            if(grid[x][y].value == 0) {
                image(blackStone, x *50, y*50, 45, 45);
                grid[x][y].value = turnNumber %2 +1;
                System.out.println("Black Stone Value: " + grid[x][y].value);
                grid[x][y].turn = turnNumber;
                textSize(25);
                text((Integer.toString(turnNumber)),x*50,y*50,45,45);
                System.out.println(Integer.toString(turnNumber));
                turnNumber++;
                if(winner(x,y,5)) {
                    gameover = "Black Wins";
                    System.out.println("Winner");
                } else {
                  //  gameover = "White Wins";
                }
            } else {
                CPU();
            }
        } else {
            return;
        }



        for(int i = 0; i < grid.length; i++) {
            for(int a = 0; a < grid.length; a++) {
           //    if( winner(i,a,3)) {



            }
        }

    }


    public void mousePressed() {

        int x = mouseX / 50;
        int y = (mouseY / 50);
        int cirx = grid[x][y].x;
        int ciry = grid[x][y].y;
        if (grid[x][y].value == 0) {
            String turn = String.valueOf(turnNumber);
            if (turnNumber % 2 + 1 == 1) {
               image(whiteStone, cirx, ciry, 45, 45);
                textSize(25);
                fill(0);
                textAlign(CENTER);
                text(turn,cirx,ciry,45,45);
                grid[x][y].value = turnNumber % 2 + 1;
                grid[x][y].turn = turnNumber;

                turnNumber++;
            } else {
//                image(blackStone, cirx, ciry, 45, 45);
//                stroke(125,0,0);
//                strokeWeight(3);
                return;
            }

            if (winner(x, y,5)) {
                if (turnNumber % 2 + 1 == 2) {
                    gameover = "White Wins";
                } else {
                    gameover = "Black Wins";
                }

                //drawWinLine();
            }

        }


    }//end mousePressed
    int count(int player, int row, int col, int dirX, int dirY) {
        int ct = 1;
        int r, c;
        r = row + dirX;
        c = col + dirY;
        while (r >= 0 && r < 10 && c >= 0 && c < 10 && grid[r][c].value == player) {
            ct++;
            r += dirX;
            c += dirY;
        }
        r = row - dirX;
        c = col - dirY;
        while (r >= 0 && r < 10 && c >= 0 && c < 10 && grid[r][c].value == player) {
            ct++;
            r -= dirX;
            c -= dirY;
        }
        return ct;

    }  // end count()
    boolean winner(int row, int col, int length) {
        if (count(grid[row][col].value, row, col, 1, 0) >= length)
            return true;
        if (count(grid[row][col].value, row, col, 0, 1) >= length)
            return true;
        if (count(grid[row][col].value, row, col, 1, -1) >= length)
            return true;
        if (count(grid[row][col].value, row, col, 1, 1) >= length)
            return true;
        return false;

    }  // end winner()
}

/**
 * Created by Steven on 4/27/2017.
 */
/**
 * Created by block7 on 4/7/17.
 */

//file:///Users/block7/Downloads/allis1994.pdf
import java.util.*;
import processing.core.*;
public class ProcessingTest extends PApplet {

    public static void main(String... args) {

        PApplet.main("ProcessingTest");
    }
    class point {
        int x;
        int y;
        int value;

        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public String toString() {
            return x + " " + y + " " + value;
        }
    }
    ////////////////////
    point[][] grid = new point[10][10];
    int turnNumber = 0;
    // HashMap<point,Integer> values = new HashMap<>();
    PImage board;
    PImage whiteStone;
    PImage blackStone;
    String gameover = "";
    int win_r1, win_c1, win_r2, win_c2;

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
        text(gameover, (width / 2) - 110, height / 2);
        if(!gameover.equals("")) {
            stop();
        }
    }

    public void mousePressed() {
        int x = mouseX / 50;
        int y = (mouseY / 50);
        System.out.println(grid[1][1].x);
        int cirx = grid[x][y].x;
        int ciry = grid[x][y].y;
        if (grid[x][y].value == 0) {
            turnNumber++;
            if (turnNumber % 2 + 1 == 1) {
                image(whiteStone, cirx, ciry, 45, 45);
            } else {
                image(blackStone, cirx, ciry, 45, 45);
            }
            // ellipse(cirx, ciry, 45, 45);
            grid[x][y].value = turnNumber % 2 + 1;
            //System.out.println(turnNumber%2);
            //  win(grid[x][y]);

            if (winner(x, y)) {
                if (turnNumber % 2 + 1 == 1) {
                    gameover = "White Wins";
                } else {
                    gameover = "Black Wins";
                }
                return;


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
    boolean winner(int row, int col) {
        if (count(grid[row][col].value, row, col, 1, 0) >= 5)
            return true;
        if (count(grid[row][col].value, row, col, 0, 1) >= 5)
            return true;
        if (count(grid[row][col].value, row, col, 1, -1) >= 5)
            return true;
        if (count(grid[row][col].value, row, col, 1, 1) >= 5)
            return true;
        return false;

    }  // end winner()
}

//    public void getNeighors(point p){
//        int x = p.x/50;
//        int y = p.y/50;
//          if(isInBounds(x+1,y)) {
//              if(p.value == grid[x + 1][y].value) {
//                  p.neighbors.add(grid[x + 1][y]);
//              }
//          }
//          if(isInBounds(x+1,y+1)) {
//              if(p.value == grid[x + 1][y+1].value) {
//              p.neighbors.add(grid[x + 1][y + 1]);
//          }
//          if(isInBounds(x+1,y-1)) {
//              if(p.value == grid[x + 1][y-1].value) {
//                  p.neighbors.add(grid[x + 1][y - 1]);
//              }
//          }
//
//          }
//          if(isInBounds(x-1,y)) {
//              if(p.value == grid[x - 1][y].value) {
//                  p.neighbors.add(grid[x - 1][y]);
//          }
//
//          }
//          if(isInBounds(x-1,y+1)) {
//              if(p.value == grid[x -1][y+ 1].value) {
//                  p.neighbors.add(grid[x - 1][y + 1]);
//          }
//
//          }
//          if(isInBounds(x-1,y-1)) {
//              if(p.value == grid[x - 1][y-1].value) {
//                  p.neighbors.add(grid[x - 1][y - 1]);
//          }
//
//          }
//          if(isInBounds(x,y+1)) {
//              if(p.value == grid[x][y+1].value) {
//                  p.neighbors.add(grid[x][y + 1]);
//          }
//
//          }
//          if(isInBounds(x,y-1)) {
//              if(p.value == grid[x][y-1].value) {
//                  p.neighbors.add(grid[x][y - 1]);
//          }
//
//          }
//    }//end Get Neighbors
//    public boolean isInBounds(int x, int y) {
//        return x >= 0 && x < grid.length && y >= 0 && y < grid.length;
//    }
//
//}

//    public void win(point p) {
//        int numInRow = 0;
//        List<point> returned = new LinkedList<>();
//        Set<point> peeked = new HashSet<>();
//        Deque<point> remaining = new LinkedList<>();
//        int thisvalue = p.value;
//        peeked.add(p);
//        remaining.push(p);
//        while(!remaining.isEmpty()) {
//            point a = remaining.pop();
//            returned.add(a);
//            for(point neighboir: a.neighbors)  {
//                if(!peeked.contains(neighboir)) {
//                    if(a.value == thisvalue) {
//                        numInRow++;
//                        System.out.println(numInRow);
//                    }
//                    peeked.add(neighboir);
//                    remaining.push(neighboir);
//                }
//            }
//            if(numInRow == 4) {
//                if (thisvalue == 0) {
//                    gameover ="Black Wins";
//                } else {
//                    gameover = "White Wins";
//                }
//
//            }
//        }
//
//    }
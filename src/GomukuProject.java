/**
 * Created by Steven on 4/27/2017.
 */
import processing.core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;
public class GomukuProject extends PApplet {

    public static void main(String... args) {

        PApplet.main("ProcessingTest");
        drawMenu();
    }
    public static void drawMenu() {
        JFrame window = new JFrame("Menu");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.pack();
        window.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JButton button1 = new JButton("Restart");
        JButton button2 = new JButton("Restart");

        panel.add(button1);
        window.add(panel);
    }
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton btn = (JRadioButton) e.getSource();
        }
    };

    class point {
        int x; int y; int value;
        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return x + " " + y + " " + value;
        }
    }

    class display extends JPanel {

    }
    ////////////////////
    point[][] grid = new point[10][10];
    int turnNumber = 0;
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
        text(gameover, (width / 2) - 110, height / 2);
    }

    public void mousePressed() {
        int x = mouseX / 50;
        int y = (mouseY / 50);
     //   System.out.println(grid[1][1].x);
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

                //drawWinLine();
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

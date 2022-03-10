package jogodosoito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Game {

    JFrame mainFrame;
    JPanel boardPanel;
    GridBagLayout boardLayout;

    static int SIZE = 3;
    static int SCREEN_SIZE = 300;
    Tile[][] board = new Tile[SIZE][SIZE];
    Tile tile0;

    public void start() {
        load();
        show();
    }

    public void load() {
        loadComponents();
        fillBoard();
        show();
        do {
            shuffleBoard();
        } while (notSolvable());
    }

    public void show() {
        mainFrame.setVisible(true);
    }

    public void fillBoard() {
        int counter = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                Tile tile = new Tile(counter, j, i, this);
                board[i][j] = tile;
                boardPanel.add(tile.getTilePanel(), gbc);
                if (counter == 0) {
                    tile0 = tile;
                }
                counter++;
            }
        }
    }

    public void shuffleBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int m = ((int) (Math.random() * 10)) % 3;
                int n = ((int) (Math.random() * 10)) % 3;
                rearrange2Tiles(board[i][j], board[m][n]);
            }
        }
    }

    public int getInvCount() {
        int inv_count = 0;
        Tile[] array = Stream.of(board).flatMap(Stream::of).toArray(Tile[]::new);
        for (int i = 0; i < array.length - 1; i++)
            for (int j = i + 1; j < array.length; j++)
                if (array[i].getNumber() > 0 && array[j].getNumber() > 0 && array[i].getNumber() > array[j].getNumber())
                    inv_count++;
        return inv_count;
    }

    public boolean notSolvable() {
        int invCount = getInvCount();
        return !(invCount % 2 == 0);
    }

    public boolean itsSolved() {
        int count = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].getNumber() != count) {
                    return false;
                } else if (count == 8) {
                    return true;
                }
                count++;
            }
        }
        return false;
    }

    public void switchPositions(Tile tile) {
        int distanceX = tile.getPosx() - tile0.getPosx();
        int distanceY = tile.getPosy() - tile0.getPosy();
        if ((Math.abs(distanceX) == 1 && distanceY == 0) || (Math.abs(distanceY) == 1 && distanceX == 0)) {
            rearrange2Tiles(tile, tile0);
            if (itsSolved())
                JOptionPane.showMessageDialog(null, "You WON!");
        }
    }

    public void rearrange2Tiles(Tile tile, Tile tile2) {

        int temp;
        temp = tile.getNumber();
        tile.setNumber(tile2.getNumber());
        tile2.setNumber(temp);

        if (tile.getNumber() == 0)
            this.tile0 = tile;
        if (tile2.getNumber() == 0)
            this.tile0 = tile2;

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public void loadComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainFrame = new JFrame("Jogo dos Oito");
        boardPanel = new JPanel();

        boardPanel.setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));

        boardPanel.setBackground(Color.WHITE);
        boardPanel.setBorder(null);

        boardLayout = new GridBagLayout();
        mainFrame.setLayout(new GridBagLayout());
        boardPanel.setLayout(boardLayout);

        mainFrame.getContentPane().add(boardPanel, gbc);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

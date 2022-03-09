package jogodosoito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Game {

    JFrame mainFrame;
    JPanel boardPanel;
    GridBagLayout boardLayout;

    static int SIZE = 3;
    int[][] board = new int[SIZE][SIZE];
    Tile tile0;

    public void start() {
        load();
        show();
    }

    public void show() {
        mainFrame.setVisible(true);
    }

    public void load() {
        fillBoard();
        shuffleBoard();
        loadComponents();
    }

    public void fillBoard() {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = counter;
                counter++;
            }
        }
    }

    public void shuffleBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                var temp = 0;
                int m = ((int) (Math.random() * 10)) % 3;
                int n = ((int) (Math.random() * 10)) % 3;
                temp = board[m][n];
                board[m][n] = board[i][j];
                board[i][j] = temp;
            }
        }
    }

    public void switchPositions(Tile tile) {
        int distanceX = tile.getPosx() - tile0.getPosx();
        int distanceY = tile.getPosy() - tile0.getPosy();
        GridBagConstraints gbc = new GridBagConstraints();
        if ((Math.abs(distanceX) == 1 && distanceY == 0) || (Math.abs(distanceY) == 1 && distanceX == 0)) {
            int aux = 0;
            aux = tile0.getPosx();
            tile0.setPosx(tile.getPosx());
            tile.setPosx(aux);

            aux = tile0.getPosy();
            tile0.setPosy(tile.getPosy());
            tile.setPosy(aux);

            gbc.gridx = tile.getPosx();
            gbc.gridy = tile.getPosy();

            boardLayout.setConstraints(tile.getTilePanel(), gbc);

            gbc.gridx = tile0.getPosx();
            gbc.gridy = tile0.getPosy();
            boardLayout.setConstraints(tile0.getTilePanel(), gbc);

            boardPanel.revalidate();
            boardPanel.repaint();
        }
    }

    public void loadComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainFrame = new JFrame("Jogo dos Oito");
        boardPanel = new JPanel();

        boardPanel.setPreferredSize(new Dimension(300, 300));

        boardPanel.setBackground(Color.CYAN);
        boardPanel.setBorder(null);

        boardLayout = new GridBagLayout();
        mainFrame.setLayout(new GridBagLayout());
        boardPanel.setLayout(boardLayout);

        mainFrame.getContentPane().add(boardPanel, gbc);

        loadTiles();
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void loadTiles() {
        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gbc.gridx = i;
                gbc.gridy = j;
                Tile tile = new Tile(board[i][j], i, j, this);
                boardPanel.add(tile.getTilePanel(), gbc);
                if (board[i][j] == 0) {
                    tile0 = tile;
                }
            }
        }
    }

}

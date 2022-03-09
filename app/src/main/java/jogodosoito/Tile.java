package jogodosoito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputAdapter;

public class Tile {

    private JPanel tilePanel;
    private JLabel tileLabel;
    private int number;
    private int posx;
    private int posy;
    Game game;

    public Tile(int number, int posx, int posy, Game game) {
        this.number = number;
        this.posx = posx;
        this.posy = posy;
        this.game = game;
        this.tilePanel = new JPanel();
        this.tileLabel = new JLabel(number + "");
        setScreenConfig();
        setActions();
    }

    public Tile getTile() {
        return this;
    }

    public JPanel getTilePanel() {
        return this.tilePanel;
    }

    public void setTilePanel(JPanel tilePanel) {
        this.tilePanel = tilePanel;
    }

    public JLabel getTileLabel() {
        return this.tileLabel;
    }

    public void setTileLabel(JLabel tileLabel) {
        this.tileLabel = tileLabel;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPosx() {
        return this.posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return this.posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public void setActions() {
        this.tileLabel.addMouseListener(new MouseInputAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                tilePanel.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tilePanel.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                game.switchPositions(getTile());
            }
        });
    }

    public void setScreenConfig() {
        this.tileLabel.setPreferredSize(new Dimension(100, 100));
        this.tilePanel.setPreferredSize(new Dimension(100, 100));

        this.tileLabel.setBorder(null);
        this.tilePanel.setBorder(null);

        this.tilePanel.setBackground(Color.LIGHT_GRAY);
        this.tileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.tileLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.tilePanel.add(this.tileLabel);
    }

    public static void main(String[] args) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        Tile tile = new Tile(6, 1, 2, new Game());
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(tile.getTilePanel(), gbc);
        frame.setVisible(true);
    }
}

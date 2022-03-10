package jogodosoito;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
        this.tileLabel.setText(number + "");
        this.number = number;
        if (number == 0)
            this.tileLabel.setVisible(false);
        else
            this.tileLabel.setVisible(true);
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
                tilePanel.setBackground(Color.LIGHT_GRAY);
                tileLabel.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tilePanel.setBackground(Color.WHITE);
                tileLabel.setForeground(Color.BLACK);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                game.switchPositions(getTile());
            }
        });
    }

    public void setScreenConfig() {
        int tile_size = Game.SCREEN_SIZE / Game.SIZE;
        this.tileLabel.setPreferredSize(new Dimension(tile_size, tile_size));
        this.tilePanel.setPreferredSize(new Dimension(tile_size, tile_size));

        this.tileLabel.setBorder(null);
        this.tilePanel.setBorder(null);

        this.tilePanel.setBackground(Color.WHITE);
        this.tileLabel.setForeground(Color.BLACK);
        if (number == 0) {
            this.tileLabel.setVisible(false);
            this.tilePanel.setOpaque(false);
        }
        this.tileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.tileLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.tilePanel.add(this.tileLabel);
    }
}

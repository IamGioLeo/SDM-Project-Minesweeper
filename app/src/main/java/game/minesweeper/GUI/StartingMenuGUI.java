package game.minesweeper.GUI;

import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.GridOfSquares;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartingMenuGUI {

    private JFrame frame;

    public void startMenu() {
        SwingUtilities.invokeLater(this::buildMenu);
    }

    public void buildMenu() {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(difficultyButtonsPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel difficultyButtonsPanel() {
        JPanel difficultyButtonsPanel = new JPanel();

        difficultyButtonsPanel.add(buildDifficultyButton("Easy", 9, 9, 10));
        difficultyButtonsPanel.add(buildDifficultyButton("Medium", 16, 16, 40));
        difficultyButtonsPanel.add(buildDifficultyButton("Hard", 16, 30, 99));

        return difficultyButtonsPanel;

    }

    private JButton buildDifficultyButton(String difficultyLabel, int rows, int columns, int mines) {
        JButton btn = new JButton(difficultyLabel);

        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setVerticalAlignment(SwingConstants.CENTER);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                startGame(rows, columns, mines);
            }
        });

        return btn;
    }


    private void startGame(int rows, int columns, int mines) {

        frame.dispose();

        GridOfSquares grid = new GridOfSquares(rows, columns);
        new GridInitializer(grid).initialize(mines);

        Game game = new Game(grid);
        GameController controller = new GameController(game);

        new SwingUI().start(controller, grid, mines);

    }


}

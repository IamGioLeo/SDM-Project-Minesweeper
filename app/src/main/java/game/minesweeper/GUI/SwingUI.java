package game.minesweeper.GUI;

import game.minesweeper.engine.Game;
import game.minesweeper.engine.GameController;
import game.minesweeper.engine.GameState;
import game.minesweeper.engine.GridInitializer;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.minesweeper.engine.CellView;

public class SwingUI {

    private GridOfSquares grid;
    private GameController controller;
    private JButton[][] buttons;
    private GameState gameState;
    private JFrame frame;
    private int mineCount;
    private TimerGUI timer;
    private JLabel unflaggedCounter;
    private int flagCount;


    public void start(GameController controller, GridOfSquares grid, int mineCount) {
        this.grid = grid;
        this.controller = controller;
        this.gameState = controller.getGameState();
        this.mineCount = mineCount;
        this.flagCount = 0;
        this.timer = new TimerGUI();
        SwingUtilities.invokeLater(this::buildUI);
    }

    private void buildUI() {
        frame = new JFrame("Minesweeper");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(buildHeader(), BorderLayout.NORTH);

        JPanel gridPanel = buildGrid();
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();

        Rectangle usableScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        Dimension frameSize = frame.getSize();

        if (frameSize.width > usableScreen.width || frameSize.height > usableScreen.height) {
            frame.setResizable(true);
            int cappedWidth = Math.min(frameSize.width, usableScreen.width);
            int cappedHeight = Math.min(frameSize.height, usableScreen.height);
            frame.setSize(cappedWidth, cappedHeight);
        } else {
            frame.setResizable(false);
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel buildGrid() {
        int rows = grid.getNumberOfRows();
        int columns = grid.getNumberOfColumns();
        buttons = new JButton[rows + 1][columns + 1];

        JPanel panel = new JPanel(new GridLayout(rows, columns));

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                JButton btn = createCellButton(i, j);
                buttons[i][j] = btn;
                panel.add(btn);
            }
        }

        return panel;
    }


    private JButton createCellButton(int row, int column) {
        JButton btn = new JButton("");

        btn.setPreferredSize(new Dimension(24, 24));

        // this is to "enforce" the text inside to appear, and to center it
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setVerticalAlignment(SwingConstants.CENTER);

        btn.addMouseListener(new MouseAdapter() {

            // here there is something that might be handled in a different way,
            // maybe an error con be thrown by the game controller when cell is already flagged or open
            @Override
            public void mouseClicked(MouseEvent e) {

                if (gameState != GameState.RUNNING) return;

                if (SwingUtilities.isLeftMouseButton(e)) {

                    timer.start();

                    CellView cell = controller.getCellView(row, column);

                    if (cell.flagged()) return;

                    controller.open(row, column);
                    refreshBoard();
                    checkEndgame();

                } else if (SwingUtilities.isRightMouseButton(e)) {

                    timer.start();

                    CellView cell = controller.getCellView(row, column);

                    if (cell.revealed()) return;

                    if (cell.flagged()) flagCount--;
                    else flagCount++;

                    controller.toggleFlag(row, column);
                    refreshBoard();

                }
            }
        });

        return btn;
    }


    private void checkEndgame() {

        gameState = controller.getGameState();

        if (gameState == GameState.RUNNING) return;

        if (gameState == GameState.WON) {
            showEndgameDialog("YOU WON!", "Congratulations, all mines cleared!");
        } else {
            showEndgameDialog("GAME OVER", "You hit a mine. Better luck next time!");
        }
    }


    private void showEndgameDialog(String title, String message) {

        timer.stop();

        JDialog dialog = new JDialog(frame, title, true);
        dialog.setUndecorated(true);

        JPanel content = new JPanel();

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel msgLabel = new JLabel(message);
        msgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playAgain = new JButton("Play Again");
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgain.addActionListener(e -> {
            dialog.dispose();
            frame.dispose();
            restartGame();
        });

        JButton quit = new JButton("Quit");
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.addActionListener(e -> System.exit(0));

        content.add(msgLabel);

        content.add(playAgain);

        content.add(quit);

        dialog.add(content, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    // this method might be too much dependent on Grid class
    // might not be compatible for different kind of grids

    private void refreshBoard() {

        int rows = grid.getNumberOfRows();
        int cols = grid.getNumberOfColumns();

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {

                CellView cell = controller.getCellView(r, c);
                JButton button = buttons[r][c];

                if (cell.revealed()) {

                    if (cell.mine()) {
                        button.setText("*");
                        button.setBackground(Color.RED);

                    } else {
                        button.setBackground(Color.GREEN);

                        if (cell.neighborMineCount() == 0) {
                            button.setText("");
                        } else {
                            button.setText(String.valueOf(cell.neighborMineCount()));
                        }
                    }

                } else {

                    button.setBackground(null);
                    button.setText(cell.flagged() ? "⚑" : "");
                }
            }
        }

        unflaggedCounter.setText(
                "Unflagged Mines: " + (mineCount - flagCount)
        );
    }


    private JPanel buildHeader() {

        JPanel header = new JPanel(new BorderLayout(5, 5));

        header.setBorder(new EmptyBorder(10,10,10,10));

        header.add(timer, BorderLayout.CENTER);

        unflaggedCounter = new JLabel("Unflagged Mines: " + (mineCount - flagCount));
        unflaggedCounter.setBackground(new Color(0, 255, 255));
        unflaggedCounter.setOpaque(true);
        header.add(unflaggedCounter, BorderLayout.EAST);

        return header;
    }


    private void restartGame() {
        GridOfSquares newGrid = new GridOfSquares(grid.getNumberOfRows(), grid.getNumberOfColumns());
        new GridInitializer<>(newGrid).initialize(mineCount);
        Game<Coordinate> newGame = new Game<>(newGrid);
        GameController newController = new GameController(newGame);
        start(newController, newGrid, mineCount);
    }
}

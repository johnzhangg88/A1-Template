import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Maze {
    private char[][] grid;
    private int startX;
    private int startY;

    public Maze(String filePath) {
        try {
            loadMaze(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load maze: " + filePath, e);
        }
    }

    private void loadMaze(String filePath) throws IOException {
        var lines = Files.readAllLines(Paths.get(filePath));
        if (lines.isEmpty()) {
            throw new RuntimeException("Maze file is empty.");
        }
        grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        // Identify starting position (assume it's on the west border)
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == ' ') {
                startX = i;
                startY = 0;
                return;
            }
        }
        throw new RuntimeException("No valid entry point found.");
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}


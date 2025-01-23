import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Maze {
    private char[][] grid;

    public Maze(String filePath) {
        try {
            loadMaze(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load maze from file: " + filePath, e);
        }
    }

    private void loadMaze(String filePath) throws IOException {
        var lines = Files.readAllLines(Paths.get(filePath));
        grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }
    }

    public char[][] getGrid() {
        return grid;
    }
}

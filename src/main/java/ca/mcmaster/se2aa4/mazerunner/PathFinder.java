public class PathFinder {
    private final Maze maze;
    private final char[][] grid;
    private int x, y; // Current position
    private int dx, dy; // Direction (e.g., 1, 0 = east)

    public PathFinder(Maze maze) {
        this.maze = maze;
        this.grid = maze.getGrid();
        this.x = maze.getStartX();
        this.y = maze.getStartY();
        this.dx = 0; // Facing east initially
        this.dy = 1;
    }

    public String findPath() {
        StringBuilder path = new StringBuilder();
        while (!isExit()) {
            if (canMoveRight()) {
                turnRight();
                path.append("R ");
            } else if (canMoveForward()) {
                path.append("F ");
                moveForward();
            } else {
                turnLeft();
                path.append("L ");
            }
        }
        return path.toString().trim();
    }

    private boolean isExit() {
        return y == grid[0].length - 1 && grid[x][y] == ' ';
    }

    private boolean canMoveForward() {
        return isPassable(x + dx, y + dy);
    }

    private boolean canMoveRight() {
        return isPassable(x + dy, y - dx);
    }

    private void turnRight() {
        int temp = dx;
        dx = dy;
        dy = -temp;
    }

    private void turnLeft() {
        int temp = dx;
        dx = -dy;
        dy = temp;
    }

    private void moveForward() {
        x += dx;
        y += dy;
    }

    private boolean isPassable(int newX, int newY) {
        return newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == ' ';
    }
}

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "Maze input file");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("i")) {
                String mazeFile = cmd.getOptionValue("i");
                logger.info("Maze file provided: " + mazeFile);

                // Load maze
                Maze maze = new Maze(mazeFile);

                // Perform pathfinding
                PathFinder pathFinder = new PathFinder(maze);
                String path = pathFinder.findPath();

                // Output canonical path
                logger.info("Path found: " + path);
                System.out.println("Canonical Path: " + path);
            } else {
                logger.error("No maze file provided. Use -i to specify the input file.");
                System.out.println("Usage: java -jar mazerunner.jar -i <maze_file>");
            }
        } catch (ParseException e) {
            logger.error("Error parsing arguments: ", e);
            System.out.println("Error: Invalid command-line arguments.");
        } catch (RuntimeException e) {
            logger.error("Runtime error: ", e);
            System.out.println("Error: " + e.getMessage());
        }
    }
}

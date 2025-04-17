/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 * //jhn floyd
 */

 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.Stack;
 
 public class MazeSolver {
     private Maze maze;
 
     public MazeSolver() {
         this.maze = null;
     }
 
     public MazeSolver(Maze maze) {
         this.maze = maze;
     }
 
     public void setMaze(Maze maze) {
         this.maze = maze;
     }
 
     public boolean isInBounds(int row, int col) {
         return row >= 0 && row < maze.getNumRows() && col >= 0 && col < maze.getNumCols();
     }
 
     /**
      * Backtracks from the end cell to the start using parent pointers.
      * @return Ordered list of MazeCells from start to end.
      */
     public ArrayList<MazeCell> getSolution() {
         Stack<MazeCell> stack = new Stack<>();
         MazeCell current = maze.getEndCell();
         while (current != null) {
             stack.push(current);
             current = current.getParent();
         }
         ArrayList<MazeCell> solution = new ArrayList<>();
         while (!stack.isEmpty()) {
             solution.add(stack.pop());
         }
         return solution;
     }
 
     /**
      * Solves the maze using Depth-First Search.
      * @return Path from start to end.
      */
     public ArrayList<MazeCell> solveMazeDFS() {
         Stack<MazeCell> stack = new Stack<>();
         stack.push(maze.getStartCell());
         maze.getStartCell().setExplored(true);
 
         while (!stack.isEmpty()) {
             MazeCell cell = stack.pop();
             int row = cell.getRow();
             int col = cell.getCol();
 
             if (cell == maze.getEndCell()) break;
 
             // North
             if (isInBounds(row - 1, col) && maze.isValidCell(row - 1, col)) {
                 MazeCell next = maze.getCell(row - 1, col);
                 if (!next.isExplored()) {
                     next.setParent(cell);
                     next.setExplored(true);
                     stack.push(next);
                 }
             }
             // East
             if (isInBounds(row, col + 1) && maze.isValidCell(row, col + 1)) {
                 MazeCell next = maze.getCell(row, col + 1);
                 if (!next.isExplored()) {
                     next.setParent(cell);
                     next.setExplored(true);
                     stack.push(next);
                 }
             }
             // South
             if (isInBounds(row + 1, col) && maze.isValidCell(row + 1, col)) {
                 MazeCell next = maze.getCell(row + 1, col);
                 if (!next.isExplored()) {
                     next.setParent(cell);
                     next.setExplored(true);
                     stack.push(next);
                 }
             }
             // West
             if (isInBounds(row, col - 1) && maze.isValidCell(row, col - 1)) {
                 MazeCell next = maze.getCell(row, col - 1);
                 if (!next.isExplored()) {
                     next.setParent(cell);
                     next.setExplored(true);
                     stack.push(next);
                 }
             }
         }
         return getSolution();
     }
 
     /**
      * Solves the maze using Breadth-First Search.
      * @return Path from start to end.
      */
     public ArrayList<MazeCell> solveMazeBFS() {
         Queue<MazeCell> queue = new LinkedList<>();
         queue.add(maze.getStartCell());
         maze.getStartCell().setExplored(true);
 
         while (!queue.isEmpty()) {
             MazeCell cell = queue.remove();
             int row = cell.getRow();
             int col = cell.getCol();
 
             if (cell == maze.getEndCell()) break;
 
             // North
             if (isInBounds(row - 1, col) && maze.isValidCell(row - 1, col)) {
                 MazeCell next = maze.getCell(row - 1, col);
                 if (!next.isExplored()) {
                     next.setParent(cell);
                     next.setExplored(true);
                     queue.add(next);
                 }
             }
             // East
             if (isInBounds(row, col + 1) && maze.isValidCell(row, col + 1)) {
                 MazeCell next = maze.getCell(row, col + 1);
                 if (!next.isExplored()) {
                     next.setParent(cell);
                     next.setExplored(true);
                     queue.add(next);
                 }
             }
             // South
             if (isInBounds(row + 1, col) && maze.isValidCell(row + 1, col)) {
                 MazeCell next = maze.getCell(row + 1, col);
                 if (!next.isExplored()) {
                     next.setParent(cell);
                     next.setExplored(true);
                     queue.add(next);
                 }
             }
             // West
             if (isInBounds(row, col - 1) && maze.isValidCell(row, col - 1)) {
                 MazeCell next = maze.getCell(row, col - 1);
                 if (!next.isExplored()) {
                     next.setParent(cell);
                     next.setExplored(true);
                     queue.add(next);
                 }
             }
         }
         return getSolution();
     }
 
     public static void main(String[] args) {
         Maze maze = new Maze("Resources/maze3.txt");
         MazeSolver ms = new MazeSolver(maze);
 
         ArrayList<MazeCell> sol = ms.solveMazeDFS();
         maze.printSolution(sol);
 
         maze.reset();
 
         sol = ms.solveMazeBFS();
         maze.printSolution(sol);
     }
 }
 

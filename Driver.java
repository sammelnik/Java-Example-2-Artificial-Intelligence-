package sam;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.uninformed.DepthFirstSearch;

public class Driver {
	public static void main(String[] args) {
		userInterface();
	}

	private static void AStarDemo(gameBoard board) {
		try {
			board.printBoard();
			Problem problem = new Problem(board,
					FunctionFactory.getActionsFunction(),
					FunctionFactory.getResultFunction(), new goalTest(),
					FunctionFactory.getCostFunction());
			Search search = new AStarSearch(new TreeSearch(),
					new heuristicFunction());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void DFS(gameBoard board) {
		try {
			// gameBoard board = new gameBoard();
			board.printBoard();
			Problem problem = new Problem(board,
					FunctionFactory.getActionsFunction(),
					FunctionFactory.getResultFunction(), new goalTest(),
					FunctionFactory.getCostFunction());
			Search search = new DepthFirstSearch(new GraphSearch());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
	}

	public static void userInterface() {
		System.out.println("Sam Melnik UIN:652620936");
		System.out.println("Brian Herman UIN:675462479");
		instructions();
		String input = ""; // user input
		gameBoard x = new gameBoard();// creates the board
		Scanner user_input = new Scanner(System.in);
		int size = 8;
		while (true) {
			input = user_input.next();// get user input
			if (input.equalsIgnoreCase("q")) {
				System.exit(0);
			} else if (input.equalsIgnoreCase("a")) {
				AStarDemo(x);// run search
			} else if (input.equalsIgnoreCase("n")) {
				x = new gameBoard(size);
				System.out.println("new board generated");
			} else if (input.equalsIgnoreCase("d")) {
				System.out.println("Enter a value:");
				size = user_input.nextInt();
				x = new gameBoard(size);
			} else if (input.equalsIgnoreCase("p")) {
				x.printBoard();
			} else if (input.equalsIgnoreCase("F")) {
				DFS(x);
			} else if (input.equalsIgnoreCase("O")) {
				x.oneforth = true;
				System.out.println("One fourth on");
			} else if (input.equalsIgnoreCase("K")) {
				x.oneforth = false;
				System.out.println("three eights on");
			}
		}
	}

	private static void instructions() {
		System.out
				.println("Please enter a Value\n"
						+ "Enter \"A\" for Astar.\n"
						+ "Enter \"N\" for a new test.\n"
						+ "Enter \"D\" for a new size of board.\n"
						+ "Enter \"P\" to print the board. \n"
						+ "Enter \"F\" for DFS. \n"
						+ "Enter \"O\" for 1/4 of the board covered with X's. \n"
						+ "Enter \"K\" for 3/8 of the board covered with X'sfor. \n"
						+ "Enter \"Q\" to quit. ");
	}
}

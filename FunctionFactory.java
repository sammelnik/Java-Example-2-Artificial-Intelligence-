package sam;


import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.StepCostFunction;

/**
 * @author Sam Melnik
 */
public class FunctionFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;
	private static StepCostFunction _SCFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	public static StepCostFunction getCostFunction() {
		if (null == _SCFunction) {
			_SCFunction = new EPCostFunction();
		}
		return _SCFunction;
	}
	private static class EPCostFunction implements StepCostFunction{
		@Override
		public double c(Object state1, Action a, Object state2) {
			//System.out.println(a);
			if(!a.isNoOp()){
				return 1.0;
			}
			return 0.0;
		}
		
	}
	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			gameBoard board = (gameBoard) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (board.canMove(gameBoard.UP)) {
				actions.add(gameBoard.UP);
			}
			if (board.canMove(gameBoard.DOWN)) {
				actions.add(gameBoard.DOWN);
			}
			if (board.canMove(gameBoard.LEFT)) {
				actions.add(gameBoard.LEFT);
			}
			if (board.canMove(gameBoard.RIGHT)) {
				actions.add(gameBoard.RIGHT);
			}

			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			gameBoard board = (gameBoard) s;
			//System.out.println(board.getX() + " " + board.getY() );
			if (gameBoard.UP.equals(a)
					&& board.canMove(gameBoard.UP)) {
				gameBoard newBoard = new gameBoard(board);
				newBoard.moveUp();
				return newBoard;
			} else if (gameBoard.DOWN.equals(a)
					&& board.canMove(gameBoard.DOWN)) {
				gameBoard newBoard = new gameBoard(board);
				newBoard.moveDown();
				return newBoard;
			} else if (gameBoard.LEFT.equals(a)
					&& board.canMove(gameBoard.LEFT)) {
				gameBoard newBoard = new gameBoard(board);
				newBoard.moveLeft();
				return newBoard;
			} else if (gameBoard.RIGHT.equals(a)
					&& board.canMove(gameBoard.RIGHT)) {
				gameBoard newBoard = new gameBoard(board);
				newBoard.moveRight();
				return newBoard;
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}

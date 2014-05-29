
import aima.core.search.framework.HeuristicFunction;
import java.lang.Math;

public class heuristicFunction implements HeuristicFunction {
	public double h(Object state) {
		gameBoard board = (gameBoard) state;
		double retVal = 0.0;
		double end = getDistance(board.getX(), board.getY(), board.endX,
				board.endY);
		double door1 = getDistance(board.getX(), board.getY(), board.door1X,
				board.door1Y);
		double door2 = getDistance(board.getX(), board.getY(), board.door2X,
				board.door2Y);
		retVal = Math.min(door1, door2);
		retVal = Math.min(retVal, end);
		return retVal;
	}

	public double getDistance(int x1, int y1, int x2, int y2) {
		double X = Math.pow((x2 - x1), 2);
		double Y = Math.pow((y2 - y1), 2);
		return Math.sqrt(X + Y);
	}
}

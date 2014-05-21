import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MatrixDispatcher {
	
    public static Ball[][] generateMatrix(String difficulty)
    {
    	int matrixRows;
    	int matrixCols;
    	int ballCount;
    	
        if (difficulty == "easy") {
            matrixRows = 4;
            matrixCols = 5;
            ballCount = 3;
        }
        else if (difficulty == "medium") {
            matrixRows = 6;
            matrixCols = 8;
            ballCount = 4;
        }
        else if (difficulty == "hard") {
            matrixRows = 9;
            matrixCols = 9;
            ballCount = 5;
        }
        else {
			matrixRows = 6;
			matrixCols = 6;
			ballCount = 4;
		}

        Ball[][] matrix = new Ball[matrixRows][matrixCols];
        Random randNumber = new Random();

//        for (int r = 0, y = 0; r < matrixRows; y += 50, r++)
//        {
//            for (int c = 0, x = 0; c < matrixCols; x += 50, c++)
//            {
//                int colorIndex = randNumber.nextInt(ballCount);
//                matrix[r][c] = new Ball(
//                		x + 400 - matrixCols * 50 / 2,
//                		y, BallColor.values()[colorIndex]);
//            }
//        }
        for (int r = matrixRows - 1, y = 50 * matrixRows; r >= 0; y -= 50, r--)
        {
            for (int c = 0, x = 0; c < matrixCols; x += 50, c++)
            {
                int colorIndex = randNumber.nextInt(ballCount);
                matrix[r][c] = new Ball(
                		x + 400 - matrixCols * 50 / 2,
                		y, BallColor.values()[colorIndex]);
            }
        }
        return matrix;
    }
}



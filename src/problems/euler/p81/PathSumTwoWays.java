package problems.euler.p81;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Moves through matrix and builds cost matrix as it reads in the file.
 * Last entry, matrix[79][79] holds the min path cost for the matrix
 * @author riley
 *
 */
public class PathSumTwoWays {
	public static void main(String[] args){
		int[][] matrix = new int[80][80];
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/problems/misc/euler/p81/matrix.txt"));
			for(int i=0; i<80; i++){
				final String[] lineIn = br.readLine().split(",");
				for(int j=0; j<80; j++){
					if(i == 0 && j == 0){
						matrix[i][j] = Integer.parseInt(lineIn[j]);
					}else if(i == 0){
						matrix[i][j] = Integer.parseInt(lineIn[j])+matrix[i][j-1];
					}else if(j == 0){
						matrix[i][j] = Integer.parseInt(lineIn[j])+matrix[i-1][j];
					}else{
						matrix[i][j] = Integer.parseInt(lineIn[j])+(matrix[i-1][j] <= matrix[i][j-1]? matrix[i-1][j]: matrix[i][j-1]);
					}
				}
			}
			System.out.println(matrix[79][79]);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

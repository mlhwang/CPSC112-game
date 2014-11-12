package com.siondream.freegemas;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.math.MathUtils;

public class Board {
	private Square[][] _squares;
	public final int size = 8;

	// Aux 
	private ListOfMatches _matches = new ListOfMatches();
	private Coord[] foundSolutions = new Coord[0];

	public Board() {
		_squares = new Square[size][size];
	}

	//TODO
	public void swap(int x1, int y1, int x2, int y2) {

	}

	//TODO
	public void fillInitialBoard() {
		for (int y = 0; y < size; ++y) {
			for (int x = 0; x < size; ++x) {
				_squares[x][y] = new Square(Square.numToType(MathUtils.random(1, 7)));
				_squares[x][y].fallStartPosY = y-size;
				_squares[x][y].fallDistance = size;
			}
		}
	}


	//return an array of arrays of matching locations
	public ListOfMatches find_matches() {
		_matches.clear();

		//check for matches in each row
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				int lastMatchPosition = buildPossibleMatchHorizontal(x, y);
				x=lastMatchPosition-1;
			}
		}		
		//check for matches in each column
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int lastMatchPosition = buildPossibleMatchVertical(x, y);
				y=lastMatchPosition-1;
			}
		}

		return _matches;
	}
	
	public Boolean has_matches(){
		return find_matches().size()!=0;
	}

	//given x and y (a position of a square),
	//iterate down the row looking for matches
	//return the position of first square that doesnt match the given square
	public int buildPossibleMatchHorizontal(int x, int y) {
		Coord[] possibleMatch = new Coord[1];

		return x+possibleMatch.length;
	}

	public int buildPossibleMatchVertical(int x, int y) {
		Coord[] possibleMatch = new Coord[1];

		return y+possibleMatch.length;
	}
	
	//TODO
	//return an array of positions that could be swapped in some direction to create a match 
	public Coord[] find_solutions() {
		//asking for a hint will crash the program until you write this method
		return new Coord[1];
	}
	
	//return a new array with all the same elements, but one extra space
	private Coord[] expandArray(Coord[] originalArray) {
		return null;
	}


	//TODO
	public void deleteMatches() {
		ListOfMatches matches = find_matches();
		for (int i = 0; i < matches.size(); ++i) {
			for (int j = 0; j < matches.get(i).size(); ++j) {
				if(j==3 && matches.get(i).size()>=4){
					makeSpecialSquare(matches.get(i).get(j).x,
									  matches.get(i).get(j).y);
				}
				else{
					deleteSquare(matches.get(i).get(j).x,
								matches.get(i).get(j).y);
				}
			}
		}		
	}
	
	public void deleteSquare(int x, int y) {	
		_squares[x][y].setType(Square.Color.sqEmpty);
	}

	public void makeSpecialSquare(int x, int y) {	
		_squares[x][y].setType(Square.getDualType(_squares[x][y].getType()));
	}
	
	
	
	//NO NEED TO EDIT BELOW THIS LINE
	//FEEL FREE TO CHECK THESE OUT AS EXAMPLES IF YOU LIKE

	public void fillSpaces() {
		for(int x = 0; x < size; ++x){
			// Count how many jumps do we have to fall
			int jumps = 0;

			for(int y = 0; y < size; ++y){
				if(!_squares[x][y].equals(Square.Color.sqEmpty)) {
					break;
				}
				jumps++;
			}

			for(int y = 0; y < size; ++y){
				if(_squares[x][y].equals(Square.Color.sqEmpty)) {
					_squares[x][y].setType(Square.numToType(MathUtils.random(1, 7)));
					_squares[x][y].mustFall = true;
					_squares[x][y].fallStartPosY = y - jumps;
					_squares[x][y].fallDistance = jumps;
				}       
			}
		}   
	}

	public void calcFallMovements() {
		for (int x = 0; x < size; ++x) {
			// From bottom to top
			for (int y = size-1; y >= 0; --y) {
				_squares[x][y].fallStartPosY = y;

				// If square is empty, make all the squares above it fall
				if (_squares[x][y].equals(Square.Color.sqEmpty)) {
					for (int k = y - 1; k >= 0; --k) {
						_squares[x][k].mustFall = true;
						_squares[x][k].fallDistance++;

						if (_squares[x][k].fallDistance > size-1)
						{
							System.out.println("WARNING");
						}
					}
				}
			}
		}
	}

	public void applyFall() {
		for (int x = 0; x < size; ++x) {
			// From bottom to top in order not to overwrite squares
			for (int y = size-1; y >= 0; --y) {
				if (_squares[x][y].mustFall == true &&
						!_squares[x][y].equals(Square.Color.sqEmpty)) {
					int y0 = _squares[x][y].fallDistance;

					if (y + y0 > size-1)
					{
						System.out.println("WARNING");
					}

					_squares[x][y + y0] = _squares[x][y];
					_squares[x][y] = new Square(Square.Color.sqEmpty);
				}
			}
		}
	}

	private Match convert(Coord[] originalAsArray) {
		ArrayList<Coord> newAsArrayList = (new ArrayList<Coord>(Arrays.asList(originalAsArray)));
		newAsArrayList.trimToSize();
		return new Match(newAsArrayList);
	}
	
	public void endAnimation() {
		for(int x = 0; x < size; ++x){
			for(int y = 0; y < size; ++y){
				_squares[x][y].mustFall = false;
				_squares[x][y].fallStartPosY = y;
				_squares[x][y].fallDistance = 0;
			}
		}
	}
	
	public Square getSquare(int x, int y) {
		return _squares[x][y];
	}

	public Square[][] getSquares() {
		return _squares;
	}

	public String toString() {
		String output = "";

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				//output += "(" + _squares[i][j].fallStartPosY + ", " + _squares[i][j].fallDistance + ")  ";
				output += "["+_squares[i][j].toString()+"] ";
			}
			output += "\n";
		}
		output += "\n";

		return output;
	}

}

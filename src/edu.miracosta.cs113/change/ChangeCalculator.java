package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ChangeCalculator {
	public static PrintWriter comboFile;
	public static ArrayList<String> combos = new ArrayList<String>();
	public static int numOfCombos = 0;
	public static int[] coinValues = { 25, 10, 5, 1 };
	private static ArrayList<Integer> list1;

	/**
	 * Wrapper method for determining all possible unique combinations of quarters,
	 * dimes, nickels, and pennies that equal the given monetary value in cents.
	 *
	 * In addition to returning the number of unique combinations, this method will
	 * print out each combination to the console. The format of naming each
	 * combination is up to the user, as long as they adhere to the expectation that
	 * the coins are listed in descending order of their value (quarters, dimes,
	 * nickels, then pennies). Examples include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
	 *
	 * @param cents a monetary value in cents
	 * @return the total number of unique combinations of coins of which the given
	 *         value is comprised
	 */
	public static int calculateChange(int cents) {
		// TODO:
		// Implement a recursive solution following the given documentation.
		list1 = new ArrayList<Integer>();
		for(int i = 0, i< cents+1, i++) {
			list1.add(0);
		}
		list1.set(0,1);
		for (int i = 0; i< coinValues.length; i++) {
			for(int j = o; j < cents+1; j++) {
				if(coinValues[i]<= j)
					list1.set(j,list1.get(j)+list1.get(j-coinValues[i]));
			}
		}
		
		makeChange(cents, 0, 0, 0, cents);
		for(String output: combos) {
			System.out.println(output)
		}
		return list1.get(cents);
	}

	public static void makeChange(int total, int nQuarter, int nDime, int nNickel, int nPenny) {
		/**
		 * Calls upon calculateChange(int) to calculate and print all possible unique
		 * combinations of quarters, dimes, nickels, and pennies that equal the given
		 * value in cents.
		 *
		 * Similar to calculateChange's function in printing each combination to the
		 * console, this method will also produce a text file named
		 * "CoinCombinations.txt", writing each combination to separate lines.
		 *
		 * @param cents a monetary value in cents
		 */

		final int QUARTER = 25, DIME = 10, NICKEL = 5, PENNY = 1;

		if (nQuarter * QUARTER + nDime * DIME + nNickel * NICKEL + nPenny * PENNY > total) {
			return;
		}

		// Creating a string for combination
		String combination = "[" + nQuarter + ", " + nDime + ", " + nNickel + ", " + nPenny + "]";

		if (!combos.contains(combination)) {
			combos.add(combination);
			numOfCombos++;
		}

		if (nPenny >= 5)
			makeChange(total, nQuarter, nDime, nNickel + 1, nPenny - 5);
		if (nPenny >= 10)
			makeChange(total, nQuarter, nDime + 1, nNickel, nPenny - 10);
		if (nPenny >= 25)
			makeChange(total, nQuarter + 1, nDime, nNickel, nPenny - 25);

	}

	public static void printCombinationsToFile(int cents) {
		// TODO:
		// This when calculateChange is complete. Note that the text file must be
		// created within this directory.

		try {
			comboFile = new PrintWriter(new File("CoinCombinations.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		calculateChange(cents);
		comboFile.println(combos);

		comboFile.close();

	}
}
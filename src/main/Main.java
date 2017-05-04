package main;

import java.util.*;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static ArrayList<Pest> pestList = new ArrayList<Pest>(Constants.STARTING_PEST_COUNT);
	static Pesticide pesticide;
	static Researcher researcher;
	static int generation = 0;

	static int killedByPesticide = 0;
	static int killedByNature = 0;

	public static void main(String[] args) {
		init();

		for (int i = 0; i < 100; i++) {
			// scanner.nextLine(); // Wait for [ENTER] to proceed
			System.out.println("");
			periodic();
		}

		System.out.println("");
		System.out.println("Killed By Pesticide: " + killedByPesticide);
		System.out.println("Killed By Nature: " + killedByNature);
	}

	private static void init() {
		for (int i = 0; i < Constants.STARTING_PEST_COUNT; i++) {
			pestList.add(new Pest(new int[Constants.DNA_LENGTH]));
		}

		for (Pest p : pestList) {
			p.formativeMutation();
		}

		pesticide = new Pesticide(new int[Constants.TARGET_DNA_LENGTH]);
		researcher = new Researcher(pestList);
		System.out.print(" "); // Ensures that the Console opens during runtime
	}

	private static void periodic() {
		generation++;
		pesticideResearch();
		pesticideSeason();
		System.out.print("Survivors: " + pestList.size());
		checkEnd();
		matingSeason();
		carryingCapacity();
		checkEnd();
	}

	private static void pesticideSeason() {
		ArrayList<Pest> pestsToRemoveList = new ArrayList<Pest>(pestList.size());

		// determine which pests should be killed by the pesticide
		for (Pest p : pestList) {
			if (p.applyPesticide(pesticide)) {
				pestsToRemoveList.add(p);
			}
		}

		// kill pests
		for (Pest p : pestsToRemoveList) {
			pestList.remove(p);
		}

		killedByPesticide += pestsToRemoveList.size();

		if (pestList.size() <= 0) {
			System.out.println("OP");
		}
	}

	private static void checkEnd() {
		if (pestList.size() > Constants.PEST_CAP) {
			System.out.println("");
			System.out.println("The pests have taken over in " + generation + " generations and "
					+ ((generation % Constants.RESEARCH_TIME)) + " research cycles!");
			System.out.println("Killed By Pesticide: " + killedByPesticide);
			System.out.println("Killed By Nature: " + killedByNature);
			System.exit(0);
		} else if (pestList.size() <= 0) {
			System.out.println("");
			System.out.println("You have eradicated pests in " + generation + " generations and "
					+ ((int) (generation / Constants.RESEARCH_TIME)) + " research cycles!");
			System.out.println("Killed By Pesticide: " + killedByPesticide);
			System.out.println("Killed By Nature: " + killedByNature);
			System.exit(0);
		}
	}

	private static void matingSeason() {
		for (int i = 0; i < Constants.MATING_CYCLES_PER_PESTICIDE_APPLICATION; i++) {
			ArrayList<Pest> newPestList = new ArrayList<Pest>(pestList.size());
			for (Pest p : pestList) {
				// if (pestList.size() + newPestList.size() < Constants.PEST_CARRYING_CAPACITY) {
				newPestList.add(new Pest(p.getDNA()));
				p.radioactiveMutation();
				// }
			}
			for (Pest p : newPestList) {
				pestList.add(p);
			}
		}

	}

	private static void pesticideResearch() {
		if (generation != 0 && generation % Constants.RESEARCH_TIME == 0) {
			pesticide = researcher.developPesticide();
			researcher.setResearchSample(pestList);
		}
	}

	private static void carryingCapacity() {
		ArrayList<Pest> pestsToRemoveList = new ArrayList<Pest>(pestList.size());

		// determine which pests should be killed by the pesticide
		for (int i = 0; i < pestList.size(); i++) {

			/*
			 * Original Carrying Capacity Equation
			 *
			 * 9^{\left(\frac{#}{\frac{C}{2}}-02\right)}
			 */

			if (randomize(60. * (Math.log((double) i / Constants.PEST_CARRYING_CAPACITY)) / (Math.log(2)))) {
				/*
				 * New Carrying Capacity Equation (custom by Andy =D)
				 *
				 * 100\log _2\left(\frac{x}{C}\right)
				 */
				pestsToRemoveList.add(pestList.get(i));
			}
		}

		// kill pests
		for (Pest p : pestsToRemoveList) {
			pestList.remove(p);
		}

		killedByNature += pestsToRemoveList.size();
	}

	public static boolean randomize(double numerator, double denominator) {
		return Math.random() <= numerator / denominator;
	}

	public static boolean randomize(double percent) {
		return randomize(percent, 100);
	}

}
package main;

public class Pest{
	
	private int[] dna = new int[Constants.DNA_LENGTH];

	public Pest(int[] dna) {
		setDNA(dna);
		radioactiveMutation();
	}
	
	public int[] getDNA() {
		return dna;
	}
	
	public void setDNA(int[] dna) {
		for (int i = 0; i < Constants.DNA_LENGTH; i++) {
			// This handles the case where the Pest constructor gets too large of an array
			this.dna[i] = dna[i];
		}
	}

	public void formativeMutation() {
		for (int i = 0; i < dna.length; i++) {
			if(Main.randomize(Constants.FORMATION_MUTATION_RATE, 1000)) {
				int dnaDegreesOfChange = 1;
				while(Main.randomize(Constants.MUTATION_INTENSITY, 100)) {
					dnaDegreesOfChange++;
				}
				
				if(Main.randomize(1, 2)) {
					dna[i] = Math.abs((dna[i] + dnaDegreesOfChange) % 10);
				} else {
					dna[i] = Math.abs((dna[i] - dnaDegreesOfChange) % 10);
				}
			}
		}
	}

	public void radioactiveMutation() {
		for (int i = 0; i < dna.length; i++) {
			if(Main.randomize(Constants.RADIACTIVE_MUTATION_RATE, 1000000)) {
				int dnaDegreesOfChange = 1;
				while(Main.randomize(Constants.MUTATION_INTENSITY, 100)) {
					dnaDegreesOfChange++;
				}
				
				if(Main.randomize(1, 2)) {
					dna[i] = Math.abs((dna[i] + dnaDegreesOfChange) % 10);
				} else {
					dna[i] = Math.abs((dna[i] - dnaDegreesOfChange) % 10);
				}
			}
		}
	}

	// returns if the pest is killed
	public boolean applyPesticide(Pesticide pesticide) {
		boolean isKilled = false;
		
		int[] targetDNA = pesticide.getTargetDNA();

		for(int i = 0; i < Constants.DNA_LENGTH - Constants.TARGET_DNA_LENGTH + 1; i++) {
			isKilled = true; // True until disproven
			for(int j = 0; j < Constants.TARGET_DNA_LENGTH; j++) {
				if(Math.abs(dna[i + j] - targetDNA[j]) > 1) {
					isKilled = false;
					break; // leaves the j for loop
				}
			}
			if(isKilled) {
				return true;
			}
		}
		
		return false;
	}
}

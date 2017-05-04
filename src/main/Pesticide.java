package main;

public class Pesticide {
	
	private int[] targetDNA = new int[Constants.TARGET_DNA_LENGTH];
	
	public Pesticide(int[] targetDNA) {
		setTargetDNA(targetDNA);
	}
	
	public int[] getTargetDNA() {
		return targetDNA;
	}
	
	public void setTargetDNA(int[] targetDNA) {
		for (int i = 0; i < Constants.TARGET_DNA_LENGTH; i++) {
			// This handles the case where the Pesticide constructor gets too large of an array
			this.targetDNA[i] = targetDNA[i];
		}
	}
}

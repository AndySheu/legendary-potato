package main;

import java.util.*;

public class Researcher {
	Pest[] researchSample = new Pest[Main.pestList.size() / Constants.RESEARCHER_PEST_SAMPLE_RATIO];

	public Researcher(ArrayList<Pest> pestList) {
		setResearchSample(pestList);
	}

	public Pest[] getResearchSample() {
		return researchSample;
	}

	public void setResearchSample(ArrayList<Pest> researchSample) {
		this.researchSample = new Pest[Main.pestList.size() / Constants.RESEARCHER_PEST_SAMPLE_RATIO];
//		for (int i = 0; i < Constants.RESEARCHER_PEST_SAMPLE_SIZE; i++) {
			for (int i = 0; i < Main.pestList.size() / Constants.RESEARCHER_PEST_SAMPLE_RATIO; i++) {
			// This handles the case where the Research constructor gets too large of an array
			this.researchSample[i] = researchSample.get(i % researchSample.size());
		}
	}

	public Pesticide developPesticide() {
		int[] pesticideRatings = new int[(int) Math.pow(10, Constants.TARGET_DNA_LENGTH)];
		for (Pest p : researchSample) {
			int[] dna = p.getDNA();

			int value = 0;

			for (int i = 0; i < Constants.DNA_LENGTH - Constants.TARGET_DNA_LENGTH + 1; i++) {
				value = 0;
				for (int j = 0; j < Constants.TARGET_DNA_LENGTH; j++) {
					value = (value * 10) + dna[i + j];
				}
				pesticideRatings[value]++;
			}
		}

		int bestPesticide = (int) Math.pow(10, Constants.TARGET_DNA_LENGTH - 1);
		for (int i = (int) Math.pow(10, Constants.TARGET_DNA_LENGTH - 1); i < (int) Math.pow(10,
				Constants.TARGET_DNA_LENGTH); i++) {
			if (pesticideRatings[i] >= pesticideRatings[bestPesticide]) {
				bestPesticide = i;
			}
		}

		int[] pesticideValues = new int[Constants.TARGET_DNA_LENGTH];

		for (int i = 0; i < Constants.TARGET_DNA_LENGTH; i++) {
			pesticideValues[Constants.TARGET_DNA_LENGTH - i - 1] = bestPesticide % 10;
			bestPesticide /= 10;
		}

		return new Pesticide(pesticideValues);
	}
}

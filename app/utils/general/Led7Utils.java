package utils.general;

import java.util.ArrayList;
import java.util.List;

import web.db.models.led7.Led7_Data;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class Led7Utils {

	public static List<Led7_Data> csvToLed7DataList(final String stateFilePath, final String probabilitiesFilePath) {
		List<String> segmentStateList = readStates(stateFilePath);
		List<ProbType> probabilityList = readProbabilities(probabilitiesFilePath);
		int limit = probabilityList.size();
		List<Led7_Data> dataList = new ArrayList<>();
		
		for(int i = 0; i < segmentStateList.size(); i++) {
			String segment = segmentStateList.get(i);
			int j = i;
			while(j < limit) {
				Led7_Data data = new Led7_Data();
				data.setSegmentState(segment);
				data.setProbabilities(probabilityList.get(j).probs);
				data.setType(probabilityList.get(j).type);
				dataList.add(data);
				j += segmentStateList.size();
			}
			
		}
		
		return dataList;
	}
	
	public static List<String> readStates(final String stateFilePath) {
		CSVReader reader = null;
		List<String> segmentStates = new ArrayList<>();
		boolean headerExcluder = true;
        try {
            reader = new CSVReader(new FileReader(stateFilePath));
            String[] line;
            while ((line = reader.readNext()) != null) {
            	if(headerExcluder) {
            		headerExcluder = false;
            		continue;
            	}
            	
            	String state = null;
            	for(String s : line) {
            		if(state == null) {
            			state = "";
            			continue;
            		}
            		
            		state += s;
            	}
            	
            	segmentStates.add(state);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return segmentStates;
	}
	
	public static List<ProbType> readProbabilities(final String probabilitiesFilePath) {
		CSVReader reader = null;
		List<ProbType> probabilityStates = new ArrayList<>();
		boolean headerExcluder = true;
        try {
            reader = new CSVReader(new FileReader(probabilitiesFilePath));
            String[] line;
            while ((line = reader.readNext()) != null) {
            	if(headerExcluder) {
            		headerExcluder = false;
            		continue;
            	}
            	List<Float> probabilities = new ArrayList<>();
            	for(int indx = 0; indx < line.length - 1; indx++) {
            		String s = line[indx];
            		probabilities.add(Float.parseFloat(s));
            	}

            	ProbType probType = new ProbType(probabilities, line[line.length - 1]);
            	probabilityStates.add(probType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return probabilityStates;
	}
	
	
	public static class ProbType {
		public List<Float> probs;
		public String type;
		
		public ProbType() {}
		
		public ProbType(List<Float> probs, String type) {
			this.probs = probs;
			this.type = type;
		}

	}
	
}

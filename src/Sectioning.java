import java.util.ArrayList;
import java.util.List;

public class Sectioning {


	/***
	 * Create new double array with peaks
	 * @param sensorData
	 * @return array with identified peaks
	 */
	public static double[] IdentifyPeaks(double[][] sensorData){
		ArrayList<Double> timeOfPeaks = new ArrayList<Double>();
		double[] magnitudes = StepCounter.calculateMagnitudesFor(sensorData);

		int peaks = 0;
		for (int i = 0; i < magnitudes.length - 2; i++) {
			double firstValue = magnitudes[i];
			double secondValue = magnitudes[i + 1];
			double thirdValue = magnitudes[i + 2];
			if (firstValue < secondValue && thirdValue < secondValue) {
				peaks++;
				timeOfPeaks.add(secondValue);
			}
		}
		double[] peakValues = new double[peaks];
		
		for (int i = 0; i < timeOfPeaks.size() ; i++) {
			peakValues[i] = timeOfPeaks.get(i);
		}
		return peakValues;
	}

	public static double calculateThresholdMax(double[] arr) {
		double mean = StepCounter.calculateMean(arr);
		return mean + (StepCounter.calculateStandardDeviation(arr, mean)*2);

	}

	/***
	 * parem@ Take in data double arr[]
	 * Identify two SD (min and max)
	 */
	public static void SDofParts(){

		
	}
	/***
	 * If next few peaks are less then SD min and max, create new SD min ans max of the few peaks  
	 */
	public static void NextSD(){
		
	}
}

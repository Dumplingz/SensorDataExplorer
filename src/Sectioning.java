import java.util.ArrayList;
import java.util.List;

public class Sectioning {

	/***
	 * Create new double array with peaks
	 * 
	 * @param sensorData
	 * @return array with identified peaks
	 */
	public static double[] IdentifyPeaks(double[][] sensorData) {
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

		for (int i = 0; i < timeOfPeaks.size(); i++) {
			peakValues[i] = timeOfPeaks.get(i);
		}
		return peakValues;
	}
	/***
	 * Use max to identify the highest threshold to define the point as a section
	 * @param arr
	 * @return the ThresholdMax
	 */
	public static double calculateThresholdMax(double[] arr) {
		double mean = StepCounter.calculateMean(arr);
		return mean + (StepCounter.calculateStandardDeviation(arr, mean) * 2);

	}

	/**
	 * n = section window
	 *  section data into n parts.
	 *  Identify threshold per section and save different thresholds 
	 * @param sensorData
	 * @param n
	 * @return array of ThresholdByNSections
	 */
	public static double[] NSectionsByThresholds(double[][] sensorData, int n) {
		int CopyOfN = n;
		double[] arr = IdentifyPeaks(sensorData);
		double[] ThresholdByNSections = new double[(int)(arr.length/n)];
		double[] ArrayWithinArrayOfNParts = new double[arr.length];
		double[] newArr = new double[n];
		int j = 0;
		for (int i = 0; i < arr.length - n; i = i + n) {
			for(int h = 0; h < n; h++){
			newArr[h] = arr[i+h];
			}
			ArrayWithinArrayOfNParts[i] = newArr[CopyOfN];
			CopyOfN = n*2;
			double Threshold = StepCounter.calculateThreshold(newArr);
			ThresholdByNSections[j] = Threshold;
			j++;
		}
		return ArrayWithinArrayOfNParts;
	}
	
	public static double[] CalculateThresholdOfNSections(double[][] sensorData, int n){
		double[] arr = IdentifyPeaks(sensorData);
		double[] ThresholdByNSections = new double[(int)(arr.length/n)];
		double[] newArr = new double[n];
		int j = 0;
		for (int i = 0; i < arr.length - n; i = i + n) {
			for(int h = 0; h < n; h++){
			newArr[h] = arr[i+h];
			}
			double Threshold = StepCounter.calculateThreshold(newArr);
			ThresholdByNSections[j] = Threshold;
			j++;
		}
		return ThresholdByNSections;
	}
	/***
	 * If next few peaks are less then SD min and max, create new SD min ans max
	 * of the few peaks
	 * @param sensorData
	 * @param n
	 * @return new Threshold of data section of n
	 */
	public static boolean NextSD(double[][] sensorData, int n) {
		double[] arr = CalculateThresholdOfNSections(sensorData,n);
		double Threshold = arr[0];
		for(int i = 0; i < (int)(sensorData.length/n); i++){
			if(arr[i] < (arr[i+1])*2 || arr[i] > (arr[i+1])*2){
				Threshold = arr[i+1];
				  
				return true;
			}
		}
		return false;
	}
	/***
	 * copy form StepCounter for reference
	 * @param sensorData
	 * @return
	 */
	public static int countSteps(double[][] sensorData) {
		double[] magnitudes = StepCounter.calculateMagnitudesFor(sensorData);
		int timesPassedStandardDeviation = 0;
		double stepThreshold = StepCounter.calculateThreshold(magnitudes);
		for (int i = 0; i < magnitudes.length - 1; i++) {
			double firstValue = magnitudes[i];
			double secondValue = magnitudes[i + 1];
			if (firstValue < secondValue && firstValue < stepThreshold && secondValue > stepThreshold) {
				timesPassedStandardDeviation++;
			}
		}
		return timesPassedStandardDeviation * 2;
	}
	/***
	 * Count Steps using the newArr of Sections
	 * @param sensorData
	 * @param n
	 * @return number of steps
	 */
	public static int CountsStepsOfSections(double[][] sensorData, int n){
		int StepsCounted = 0;
		return StepsCounted;
}
}

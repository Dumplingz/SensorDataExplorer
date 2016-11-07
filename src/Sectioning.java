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

	public static double calculateThresholdMax(double[] arr) {
		double mean = StepCounter.calculateMean(arr);
		return mean + (StepCounter.calculateStandardDeviation(arr, mean) * 2);

	}

	/***
	 * @param Take in data double arr[][]
	 * Identify two SD (min and max)
	 */
	public static double[] TakeofPartsData(double[][] sensorData, int n){
		double[] arr = IdentifyPeaks(sensorData);
		double maxThreshold = calculateThresholdMax(arr);
		for(int j = 0; j < arr.length; j++){
		for(int i = 0; i < (int)(n/2); i++){
			double Threshold = StepCounter.calculateThreshold(arr);	
			if(Threshold > arr[i+1] && maxThreshold < arr[i+1] && Threshold > arr[i] && maxThreshold < arr[i] && Threshold > arr[((int)(n/2))-i] && maxThreshold < arr[((int)(n/2))-i]){
				double[] NewSection = new double[n];
				NewSection[i] = 
				return NewSection;
			}
		}
		}
 		return null;
	}

	public static double[] getStillSections(double[][] sensorData, int n){
		//double leftvalue;
		//leftvalue = rightvalue(peaksection)
		double[] arr = NSectionsByThresholds(sensorData, n);
		for(int i = 0; i < arr.length - 1; i++){
			if(arr[i] > arr[i++]*2 || arr[i] < arr[i++]*2){
				/**
				 * TODO delete complete identifying sections in this area
				 * https://github.com/Dumplingz/SensorDataExplorer.git
				 */
			}
		}
		return arr;
	}

	/**
	 * n = section window
	 *  section data into n parts.
	 *  Identify threshold per section and save different thresholds 
	 * @param sensorData
	 * @param n
	 * @return
	 */
	public static double[] NSectionsByThresholds(double[][] sensorData, int n) {

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
	 */
	public static void NextSD(double[][] sensorData, int n) {
		double[] arr = NSectionsByThresholds(sensorData,n);
		for(int i = 0; i < (int)(sensorData.length/n); i++){
			if(arr[i] < (arr[i+1])*2 || arr[i] > (arr[i+1])*2){
				
			}
		}
	}
}

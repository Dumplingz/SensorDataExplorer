
public class StepCounter {

	/***
	 * An object that assists in counting the number of steps given a set of
	 * data.
	 */
	public StepCounter() {

	}

	/***
	 * Finds, using standard deviation, the number of steps walked given (x,y,z)
	 * vectors of acceleration.
	 * 
	 * @param sensorData
	 *            the array containing the (x,y,z) components of acceleration
	 * @return the number of steps walked
	 */
	public static int countSteps(double[][] sensorData) {
		double[] magnitudes = calculateMagnitudesFor(sensorData);
		int timesPassedStandardDeviation = 0;
		double stepThreshold = calculateThreshold(magnitudes);
		for (int i = 0; i < magnitudes.length - 1; i++) {
			double firstValue = magnitudes[i];
			double secondValue = magnitudes[i + 1];
			if (firstValue < secondValue && firstValue < stepThreshold && secondValue > stepThreshold) {
				timesPassedStandardDeviation++;
			}
		}
		return timesPassedStandardDeviation * 2;
	}
	
	public static int getGyroSteps(){
		return 0;
	}

	/***
	 * Calculates the threshold to use to count steps
	 * 
	 * @param arr
	 *            an array with magnitudes
	 * @return the threshold
	 */
	public static double calculateThreshold(double[] arr) {
		double mean = calculateMean(arr);
		return mean + calculateStandardDeviation(arr, mean);

	}

	/***
	 * Finds the magnitude of a vector given its components
	 * 
	 * @param x
	 *            the x component
	 * @param y
	 *            the y component
	 * @param z
	 *            the z component
	 * @return the magnitude of the resultant
	 */
	public static double calculateMagnitude(double x, double y, double z) {
		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	/***
	 * Finds the magnitudes when given a 2d array representing vector components
	 * (x,y,z).
	 * 
	 * @param sensorData
	 *            the array containing the (x,y,z) components
	 * @return the final magnitudes of the resultant vector
	 */
	public static double[] calculateMagnitudesFor(double[][] sensorData) {
		double[] vectors = new double[sensorData.length];
		for (int i = 0; i < sensorData.length; i++) {
			vectors[i] = calculateMagnitude(sensorData[i][0], sensorData[i][1], sensorData[i][2]);
		}
		return vectors;
	}

	/***
	 * Finds the standard deviation of a given double array, given the mean of
	 * the array
	 * 
	 * @param arr
	 *            the array to find the standard deviation
	 * @param mean
	 *            the mean of the array inputted
	 * @return the standard deviation
	 */
	private static double calculateStandardDeviation(double[] arr, double mean) {
		double value = 0;
		for (int i = 0; i < arr.length; i++) {
			value += (arr[i] - mean) * (arr[i] - mean);
		}
		value /= arr.length - 1;
		value = Math.sqrt(value);
		return value;
	}

	/***
	 * Finds the mean of all the values in an array
	 * 
	 * @param arr
	 *            the array of doubles to find the mean
	 * @return Calculates the mean of an array of doubles
	 */
	private static double calculateMean(double[] arr) {
		double total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		return total / ((double) (arr.length));
	}

	/***
	 * Replaces the absolute time in column 0 with elapsed time, with the first
	 * row starting at 0.
	 * 
	 * @param sensorData
	 *            the data to be changed
	 * @return the replaced data
	 */
	public static double[] replaceAbsoluteTime(double[][] sensorData) {
		double[] replacedTime = new double[sensorData.length];
		double prevValue = sensorData[0][0];
		replacedTime[0] = prevValue;
		sensorData[0][0] = 0;
		for (int i = 1; i < sensorData.length; i++) {
			replacedTime[i] = sensorData[i][0];
			double dataValue = sensorData[i][0];
			sensorData[i][0] = sensorData[i - 1][0] + dataValue - prevValue;
			prevValue = dataValue;
		}
		return replacedTime;
	}
}

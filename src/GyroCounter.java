
public class GyroCounter {
	/***
	 * Finds, using standard deviation, the number of steps walked given (x,y,z)
	 * vectors of acceleration.
	 * 
	 * @param sensorData
	 *            the array containing the (x,y,z) components of acceleration
	 * @return the number of steps walked
	 */
	public static int countSteps(double[][] gyroData) {
		double[] xGyroData = ArrayHelper.extractColumn(gyroData, 2);
		int timesPassedStandardDeviation = 0;
		double stepThreshold = StepCounter.calculateThreshold(xGyroData);
		for (int i = 0; i < xGyroData.length - 1; i++) {
			double firstValue = xGyroData[i];
			double secondValue = xGyroData[i + 1];
			if (firstValue < stepThreshold && secondValue > stepThreshold) {
				timesPassedStandardDeviation++;
			}
		}
		return timesPassedStandardDeviation * 2;
	}

}


public class GyroCounter {
	/***
	 * Finds, using standard deviation, the number of steps walked given (x,y,z)
	 * vectors of acceleration.
	 * 
	 * @param sensorData
	 *            the array containing the (x,y,z) components of acceleration
	 * @param columnToCount
	 *            the column to use for counting the steps
	 * @return the number of steps walked
	 */
	public static int countSteps(double[][] gyroData, int columnToCount) {
		double[] xGyroData = ArrayHelper.extractColumn(gyroData, columnToCount);
		int timesPassedStandardDeviation = 0;
		double stepThreshold = StepCounter.calculateThreshold(xGyroData, false);
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

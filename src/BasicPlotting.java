import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

public class BasicPlotting {
	public static String datafile = "data/36StepsWalkAndPause2Female.csv";

	public static void main(String[] args) {
		CSVData data = CSVData.readCSVFile(datafile, 1);

		double[][] sample1 = data.getColumns(new String[] {"accelerometerAccelerationX", "accelerometerAccelerationY","accelerometerAccelerationZ", "gyroRotationX", "gyroRotationY", "gyroRotationZ" });
		int zGyroColumn = 5;

		double[][] accelerations = ArrayHelper.extractColumns(sample1, 0, 3);
		double[] magnitudes = StepCounter.calculateMagnitudesFor(accelerations);
		
		double[] time = data.getColumn(0);
		double[] zGyroData = ArrayHelper.extractColumn(sample1, zGyroColumn);
		double threshold = StepCounter.calculateThreshold(zGyroData, false);
		double[] thresholds = new double[zGyroData.length];
		for (int i = 0; i < thresholds.length; i++) {
			thresholds[i] = threshold;
		}

		// double[][] graph = ArrayHelper.combineAsColumns(time, magnitudes,
		// thresholds);
		System.out.println(GyroCounter.countSteps(zGyroData));
		System.out.println(StepCounter.countSteps(accelerations));

		Plot2DPanel plot = new Plot2DPanel();

		// add a line plot to the PlotPanel
		// plot.addLinePlot("Magnitudes", ArrayHelper.extractColumn(sample1,
		// 0));
		// plot.addLinePlot("Magnitudes", ArrayHelper.extractColumn(sample1,
		// 1));
		plot.addLinePlot("Gyro", zGyroData);
		plot.addLinePlot("Threshold", thresholds);

		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("Results");
		frame.setSize(800, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}

	private static void addNoise(double[] sample, int max) {
		for (int i = 0; i < sample.length; i++) {
			sample[i] += (-max + Math.random() * 2 * max);
		}
	}

	public static void displayArray(double[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
}

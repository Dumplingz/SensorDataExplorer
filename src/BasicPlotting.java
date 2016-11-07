import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

public class BasicPlotting {
	public static String datafile = "data/Trial8.csv";

	public static void main(String[] args) {
		CSVData data = CSVData.readCSVFile(datafile, 1);

		double[][] sample1 = data.getColumns(new String[] { "gyroRotationX", "gyroRotationY", "gyroRotationZ" });

		double[] time = data.getColumn(0);
		double[] xGyroData = ArrayHelper.extractColumn(sample1, 2);
		double threshold = StepCounter.calculateThreshold(xGyroData, false);
		double[] thresholds = new double[xGyroData.length];
		for (int i = 0; i < thresholds.length; i++) {
			thresholds[i] = threshold;
		}

		// double[][] graph = ArrayHelper.combineAsColumns(time, magnitudes,
		// thresholds);
		System.out.println(GyroCounter.countSteps(sample1));

		Plot2DPanel plot = new Plot2DPanel();

		// add a line plot to the PlotPanel
		// plot.addLinePlot("Magnitudes", A rrayHelper.extractColumn(sample1,
		// 0));
		// plot.addLinePlot("Magnitudes", ArrayHelper.extractColumn(sample1,
		// 1));
		plot.addLinePlot("Magnitudes", ArrayHelper.extractColumn(sample1, 2));

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

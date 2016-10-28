import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

public class BasicPlotting {
	public static String datafile = "data/walkingSampleData-out.csv";
	public static String videofile = "data/walkingSampleData.mp4";

	public static void main(String[] args) {
		String[] columnNames = { "time", "xg", "yg", "zg", "g" };
		CSVData data = CSVData.readCSVFile("data/walkingSampleData-out.csv", columnNames, 1);

		double[][] sample1 = data.getColumns(new String[] { "xg", "yg", "zg" });
		double[][] sample2 = data.getColumns(new String[] { "time", "xg", "yg", "zg" });

		StepCounter.replaceAbsoluteTime(sample2);

		double[] time = ArrayHelper.extractColumn(sample2, 0);
		double[] magnitudes = StepCounter.calculateMagnitudesFor(sample1);
		double threshold = StepCounter.calculateThreshold(magnitudes);
		double[] thresholds = new double[magnitudes.length];
		for (int i = 0; i < thresholds.length; i++) {
			thresholds[i] = threshold;
		}

		double[][] graph = ArrayHelper.combineAsColumns(time, magnitudes, thresholds);
		System.out.println(StepCounter.countSteps(sample1));
		
		Plot2DPanel plot = new Plot2DPanel();

		// add a line plot to the PlotPanel
		plot.addLinePlot("Magnitudes", magnitudes);
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

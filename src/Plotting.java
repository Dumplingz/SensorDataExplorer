import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

public class Plotting {
	public static String datafile = "data/64StepsInPocketJogging-out.csv";
	public static String videofile = "data/walkingSampleData.mp4";

	public static void main(String[] args) {
		CSVData data = CSVData.readCSVFile(datafile, 1);

		double[][] sample1 = data.getColumns(new String[] { "  gryo x", "  gyro y", "  gyro z" });

		double[] time = data.getColumn(0);
		double[] zGyroData = ArrayHelper.extractColumn(sample1, 2);
		double threshold = StepCounter.calculateThreshold(zGyroData, true);
		double[] thresholds = new double[zGyroData.length];
		for (int i = 0; i < thresholds.length; i++) {
			thresholds[i] = threshold;
		}

		// double[][] graph = ArrayHelper.combineAsColumns(time, magnitudes,
		// thresholds);
		System.out.println(GyroCounter.countSteps(sample1));

		
		double[][] graph = ArrayHelper.combineAsColumns(time, zGyroData, thresholds);

		DataExplorer.runDataExplorer(graph, new String[] { "time", "x", "y", "z" }, videofile);
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

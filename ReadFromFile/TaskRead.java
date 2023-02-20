package ReadFromFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskRead {
    
    public double[][] ReadMatrix(){

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("./data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = scanner.nextInt();
        double[][] x = new double[n][n]; // коэффициенты перед x
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x[i][j] = scanner.nextDouble();
            }
        }

        return x;
    }

}

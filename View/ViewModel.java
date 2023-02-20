package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Graph.*;
import Logger.Logger;
import ReadFromFile.TaskRead;

public class ViewModel {

    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    
    public void start(){
        menu();
    }

    private void menu(){

        //// Я подумал о том, что не буду долго думать над пользовательским консольным меню, а пока просто сделаю так
        //// Чтобы получить кратчайший путь м-ду точками, нужно ввести первую точку и вторую соответственно. Затем получаем путь.
        //// Сама матрица находится в файле data.txt
        //// В любом случае, эту задачу можно переложить на то, чтобы вводить всё из файла или с консоли, тут никаких проблем
        //// Основная задача - алгоритм нахождения кратчайшего пути
        //// Я выбрал для этого алгоритм Флойда-Уоршелла
        //// Конечно, можно было выбрать Дейкстру, но Дейкстра считает только для одной вершины, алгоритм Флойда-Уоршелла считает один раз, но для всех
        //// При желании можно сохранить данные, и использовать их в дальнейшем без перерассчётов.        

        Integer first = 0;
        Integer second = 0;
        
        System.out.print("Insert number of first node ->");

        first = readFromUser();

        System.out.print("Insert number of second node ->");
        
        second = readFromUser();
        

        if(first > 0 && second > 0){

            double[][] matrix = readFromFile();

            if(first <= matrix.length && second <= matrix.length){

                Model graphModel = new Model();

                graphModel.initiateGraph(matrixToArrayList(matrix));

                String resultStr = graphModel.findShortestPath(first, second);

                System.out.println(resultStr);

                Logger logger = new Logger();

                logGraphMatrix(graphModel.getGraphMatrix(), logger);

                logger.writeToLog("Shortest path from " + first.toString() + " to " + second.toString() + " is: " + resultStr);
        
            }
        }
        
    }

    private void logGraphMatrix(double[][] matrix, Logger logger){

        logger.writeToLog("Graph Matrix:");

        for(int i = 0; i < matrix.length; i++){
            String res = "";

            for(int j = 0; j < matrix.length; j++){
                res += matrix[i][j];
                if(j != matrix.length - 1) res += " ";
            }
            logger.writeToLog(res);
        }

    }

    private int readFromUser(){
        int value = 0;

        try {
        
            value = Integer.parseInt(br.readLine());
        
        } catch (NumberFormatException | IOException e) {
        
            e.printStackTrace();
        
        }


        return value;
    }

    private double[][] readFromFile(){
        TaskRead readMatrixFromFile = new TaskRead();

        double matrix[][] = readMatrixFromFile.ReadMatrix();

        return matrix;
    }

    private ArrayList<ArrayList<Double>> matrixToArrayList(double[][] matrix){
        ArrayList<ArrayList<Double>> graphArrayList = new ArrayList<>();

        int i = matrix.length;
        int j = i;

        for(int tempI = 0; tempI < i; tempI++){
            ArrayList<Double> row = new ArrayList<>();
            for(int tempJ = 0; tempJ < j; tempJ++){
                row.add(matrix[tempI][tempJ]);
            }
            graphArrayList.add(row);

        }

        return graphArrayList;
    }

}

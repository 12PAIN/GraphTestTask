package Graph;

import java.util.ArrayList;

public class Model {
    
    private Graph graph;

    public void initiateGraph(ArrayList<ArrayList<Double>> graph){
        this.graph = new Graph();
        this.graph.setGraph(graph);
    }

    public String findShortestPath(Integer firstNumber, Integer secondNumber){

        firstNumber--;
        secondNumber--;

        //// Задание изначальных матриц по алгоритму Флойда-Уоршелла

        ArrayList<ArrayList<Double>> Weight = new ArrayList<>();
        ArrayList<ArrayList<Double>> History = new ArrayList<>();

        for(Integer i = 0; i < graph.getGraph().size(); i++){

            ArrayList<Double> currentWeightRow = new ArrayList<>();
            ArrayList<Double> currentHistoryRow = new ArrayList<>();

            for(Integer j = 0; j < graph.getGraph().get(i).size(); j++){

                if(graph.getGraph().get(i).get(j) == 0) currentWeightRow.add(200000000.0);
                else currentWeightRow.add(graph.getGraph().get(i).get(j));

                if(graph.getGraph().get(i).get(j) == 0) currentHistoryRow.add(-1.0);
                else currentHistoryRow.add(Double.valueOf(j));

            }
            
            Weight.add(currentWeightRow);
            History.add(currentHistoryRow);
        }

        //Поиск всех кратчайших путей между всеми вершинами

        for(Integer i = 1; i < Weight.size()+1; i++){

            for(Integer j = 0; j < Weight.get(i-1).size(); j++){

                if(Weight.get(j).get(i-1) != 200000000.0){

                    for(int k = 0; k < Weight.size(); k++){

                        if(Weight.get(j).get(k) > (Weight.get(j).get(i-1) + Weight.get(i-1).get(k))){

                            if(k != j) Weight.get(j).set(k, Weight.get(j).get(i-1) + Weight.get(i-1).get(k));
                            if(k != j) History.get(j).set(k, History.get(j).get(i-1));

                        }
                    }
                }
            }
        }  

        //// Вывод кратчайшего пути

        String resultStr = "";

        if(History.get(firstNumber).get(secondNumber) != 0.0){

            Integer tmpFirstNumber = firstNumber.intValue() + 1;
            resultStr = tmpFirstNumber.toString() + " -> ";

            Double k = Double.valueOf(firstNumber);

            while(k != -1.0){
                k = History.get(k.intValue()).get(secondNumber);

                Integer tmpK = k.intValue() + 1;

                if(k != -1.0) resultStr += tmpK.toString();

                if(k != -1.0 && History.get(k.intValue()).get(secondNumber) != -1.0){
                    
                    resultStr += " -> ";
                }

            }

            resultStr += "  Total lenght: " + Weight.get(firstNumber).get(secondNumber).toString();

        }else{
            resultStr = "No path or same node!";
        }

        return resultStr;

    }

    public double[][] getGraphMatrix(){
        
        ArrayList<ArrayList<Double>> currentGraph = graph.getGraph();

        double[][] graphMatrix = new double[currentGraph.size()][currentGraph.size()];

        for(int i = 0; i < currentGraph.size(); i++){
            for(int j = 0; j < currentGraph.size(); j++){
                graphMatrix[i][j] = currentGraph.get(i).get(j);
            }
        }

        return graphMatrix;
    }

}

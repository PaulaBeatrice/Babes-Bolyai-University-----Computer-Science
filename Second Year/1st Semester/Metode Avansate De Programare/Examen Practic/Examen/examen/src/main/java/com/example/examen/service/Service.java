package com.example.examen.service;

import com.example.examen.domain.City;
import com.example.examen.domain.TrainStation;
import com.example.examen.repository.Repository;

import java.util.*;

public class Service {
    private Repository<String, City> cityRepo;
    private Repository<String, TrainStation> trainStationRepo;

    public Service(Repository<String, City> cityRepo, Repository<String, TrainStation> trainStationRepo) {
        this.cityRepo = cityRepo;
        this.trainStationRepo = trainStationRepo;
    }

    public Iterable<City> getAllCities() {
        return cityRepo.getAll();
    }

    public Iterable<TrainStation> getAllTrainStations() {
        return trainStationRepo.getAll();
    }

    public String getNameCityById(String id) {
        for (City c : getAllCities()) {
            if (c.getId().equals(id))
                return c.getName();
        }
        return id;
    }

    public String getIdCityByName(String name) {
        for (City c : getAllCities()) {
            if (c.getName().equals(name))
                return c.getId();
        }
        return name;
    }

    public ArrayList<String> trainIds() {
        // RETURNEAZA ID-URILE UNICE ALE TRENURILE
        List<String> trainIds = new ArrayList<String>();
        for (TrainStation t : getAllTrainStations()) {
            String train = t.getId();
            int gasit = 0;
            for (String tr : trainIds) {
                if (tr.equals(train)) { // EXISTA DEJA
                    gasit = 1;
                }
            }
            if (gasit == 0) {
                trainIds.add(train);
            }
        }
        return (ArrayList<String>) trainIds;
    }


    //// GENERARE DRUMURI PRIN TRENUL T
    HashMap<String, List<String>> adjacencyList;

    public HashMap<String, List<String>> initList(String T) {
        HashMap<String, List<String>> adjList = new HashMap<String, List<String>>();
        for (City c : getAllCities()) { /// AFLAM LISTA DE ORASE UNDE POATE AJUNGE ORASUL C PRIN TRENUL T
            List<String> stations = new ArrayList<>();
            for (TrainStation train : getAllTrainStations()) {
                if (train.getDeparture_city().equals(c.getId()) && train.getId().equals(T)) {
                    stations.add(train.getDestination_city());
                }
            }
            adjList.put(c.getId(), stations);
        }
        return adjList;
    }

//    void DFSUtil(String v, HashMap<String, Boolean> visited, String T) {
//        visited.put(v, true);
//        System.out.print(getNameCityById(v) + "-" + T + "->");
//        if (adjacencyList.containsKey(v)) {
//            for (String x : adjacencyList.get(v))
//                if (!visited.containsKey(x))
//                    DFSUtil(x, visited, T);
//        }
//    }

    String result ="";

    String DFSUtil2(String v, HashMap<String, Boolean> visited, String T, String destination) {
        visited.put(v, true);
        result = result + getNameCityById(v) ;
        if (adjacencyList.containsKey(v)) {
            if(v.equals(destination)){ // v = id-ul orasului destinatie
                return result;
            }
            result = result + "-" + T + "->";
            for (String x : adjacencyList.get(v))
                if (!visited.containsKey(x))
                    DFSUtil2(x, visited, T,destination);
        }

        return result;
    }


    public String connectedComponents(String T, String start_city, String destination_city) {

        adjacencyList = new HashMap<String, List<String>>();
        adjacencyList = initList(T);
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        String result = "";
        //DFSUtil(start_city, visited, T);
        return DFSUtil2(start_city, visited, T,destination_city);
       // return result;
    }
}



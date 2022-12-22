package pl.ibobek.iterator;

import pl.ibobek.observer.FireStation;

import java.util.ArrayList;

public class FireStationsCollection implements IterableCollection {

    private final ArrayList<FireStation> fireStations = new ArrayList<>();

    public FireStationsCollection() {
        this.fireStations.add(new FireStation(50.05996320939823, 19.943112837114477, "JRG 1"));
        this.fireStations.add(new FireStation(50.033339335674505, 19.93587276108738, "JRG 2"));
        this.fireStations.add(new FireStation(50.07575918105756, 19.887136077633894,"JRG 3"));
        this.fireStations.add(new FireStation(50.037737960215395, 20.00577424344124,"JRG 4"));
        this.fireStations.add(new FireStation(50.09168980786977, 19.920070661017718,"JRG 5"));
        this.fireStations.add(new FireStation(50.016145481645054, 20.015628160211936,"JRG 6"));
        this.fireStations.add(new FireStation(50.0941024267609, 19.977440467482054,"JRG 7"));
        this.fireStations.add(new FireStation(50.07712583090622, 20.0326736333839,"JRG SA PSP"));
        this.fireStations.add(new FireStation(49.96845211111406, 19.799479380600516,"JRG Skawina"));
        this.fireStations.add(new FireStation(50.073378713832206, 19.785816024791263,"JRG LSP"));
    }

    @Override
    public Iterator createIterator() {
        return new FireStationsIterator(this);
    }

    public ArrayList<FireStation> getFireStations() {
        return fireStations;
    }

}

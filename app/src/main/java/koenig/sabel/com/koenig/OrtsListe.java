package koenig.sabel.com.koenig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrtsListe implements Serializable {

    private List<Ort> orte;

    public OrtsListe() {
        orte = new ArrayList<>();

    }

    public void add(Ort ort) {
        if (ort != null) {
            orte.add(ort);
        }
    }


    public Ort get(int index) {
        if(index < 0 || index >= orte.size()){
            return null;
        }
        return orte.get(index);
    }

    public int size(){
        return orte.size();
    }

    public List<Ort> getListe() {
        return orte;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Ort ort : orte) {
            sb.append(ort.toString());
            sb.append(String.format("%n"));
        }
        return sb.toString();
    }
}

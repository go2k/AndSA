package koenig.sabel.com.koenig;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Ort implements Serializable {

    private String ort;
    private Date timestamp;
    private float lux;

    public Ort() {
    }

    public Ort(String ort, Date timestamp, int lux) {
        this.ort = ort;
        this.timestamp = timestamp;
        this.lux = lux;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public float getLux() {
        return lux;
    }

    public void setLux(float lux) {
        this.lux = lux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ort ort1 = (Ort) o;
        return lux == ort1.lux &&
                Objects.equals(ort, ort1.ort) &&
                Objects.equals(timestamp, ort1.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ort, timestamp, lux);
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return simpleDateFormat.format(timestamp) + " " + ort + "\n" + String.valueOf(lux);
    }
}

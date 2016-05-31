package com.app.equaker;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class KuakeLocation {

    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("earthquakes")
    @Expose
    private List<Earthquake> earthquakes = new ArrayList<Earthquake>();

    /**
     *
     * @return
     * The count
     */
    public String getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The earthquakes
     */
    public List<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    /**
     *
     * @param earthquakes
     * The earthquakes
     */
    public void setEarthquakes(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

}
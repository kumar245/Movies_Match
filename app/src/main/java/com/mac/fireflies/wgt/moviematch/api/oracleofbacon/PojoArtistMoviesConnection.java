package com.mac.fireflies.wgt.moviematch.api.oracleofbacon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

/**
 * Created by User on 10/19/2016.
 */

@Generated("org.jsonschema2pojo")
public class PojoArtistMoviesConnection {

    @SerializedName("link")
    @Expose
    public List<String> link = new ArrayList<String>();
    @SerializedName("status")
    @Expose
    public String status;

}

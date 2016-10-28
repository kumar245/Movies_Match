package com.mac.fireflies.wgt.moviematch.model;

import com.mac.fireflies.wgt.moviematch.api.oracleofbacon.PojoArtistMoviesConnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10/19/2016.
 */

public class SuggestedNames implements Serializable{
    public String status;
    public List<String> link = new ArrayList<String>();
    public List<String> matches = new ArrayList<String>();
    public String nameArtist1, nameArtist2;
    public boolean isNameFirst;

    public SuggestedNames(PojoArtistMoviesConnection pojo, String name, boolean isFirst){

        status = pojo.status;
        link = pojo.link;
        matches = pojo.matches;
        if (isFirst) {
            nameArtist1 = pojo.name;
            nameArtist2 = name;
            isNameFirst = true;
        }
        else {
            nameArtist1 = name;
            nameArtist2 = pojo.name;
            isNameFirst = false;
        }
    }


}

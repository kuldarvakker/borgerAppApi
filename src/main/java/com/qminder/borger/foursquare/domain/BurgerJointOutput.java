package com.qminder.borger.foursquare.domain;

import java.util.List;

public class BurgerJointOutput {

    public List<BJEntry> results;

    public class BJEntry {

        public String fsq_id;
        public Geocodes geocodes;
        public String name;


        public class Geocodes {

            public GeocodesMain main;

            public class GeocodesMain {

                public Double latitude;
                public Double longitude;
            }
        }
    }
}


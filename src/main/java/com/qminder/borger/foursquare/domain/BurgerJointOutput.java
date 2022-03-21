package com.qminder.borger.foursquare.domain;

import lombok.Data;

import java.util.List;

@Data
public class BurgerJointOutput {

    private List<BJEntry> results;

    @Data
    public class BJEntry {

        private String fsq_id;
        private Geocodes geocodes;
        private String name;

        @Data
        public class Geocodes {

            private GeocodesMain main;

            @Data
            public class GeocodesMain {

                private Double latitude;
                private Double longitude;
            }
        }
    }
}


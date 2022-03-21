package com.qminder.borger.foursquare.domain;

import lombok.Data;

@Data
public class PhotoOutput {

    private String id;
    private String created_at;
    private String prefix;
    private String suffix;
    private String width;
    private String height;
}

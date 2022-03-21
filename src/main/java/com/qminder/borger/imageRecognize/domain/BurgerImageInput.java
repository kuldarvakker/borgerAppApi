package com.qminder.borger.imageRecognize.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BurgerImageInput {

    private List<String> urls = new ArrayList<>();
}

package com.example.ntsoumoucarel.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Geometry {
    private String type;
    private List<Double> coordinates;
}

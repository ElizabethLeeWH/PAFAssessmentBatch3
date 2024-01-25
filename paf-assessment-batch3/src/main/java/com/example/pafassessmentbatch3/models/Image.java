package com.example.pafassessmentbatch3.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Image {
    private String thumbnail;
    private String medium;
    private String picture;
    private String xlPicture;
}

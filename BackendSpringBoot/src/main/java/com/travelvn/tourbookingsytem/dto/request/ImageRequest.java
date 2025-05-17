package com.travelvn.tourbookingsytem.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ImageRequest {
    private Integer id;

    private String imageName;

    private String url;

    private TourRequest tour;

}
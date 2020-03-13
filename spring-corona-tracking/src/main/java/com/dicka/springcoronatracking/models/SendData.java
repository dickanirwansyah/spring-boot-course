package com.dicka.springcoronatracking.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendData {

    private String data;
    private String data1;
    private String data2;
    private String[] theDatas;
}

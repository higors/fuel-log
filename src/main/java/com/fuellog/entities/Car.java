package com.fuellog.entities;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Document(collection = "cars")
public class Car implements Serializable {
    @Id
    private String id;
    private String brand;
    private String model;
    private BigDecimal efficiency;
}


package com.fuellog.entities;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Document(collection = "trips")
public class Trip implements Serializable {

    @Id
    private String id;
    private String carModel;
    private BigDecimal distance;
    private BigDecimal liters;
    private BigDecimal valuePerLiter;
    private Instant createdOn;
}

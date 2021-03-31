package com.fuellog.entities;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity
@Table(name = "t_person")
public class Person {
    @Id
    @SequenceGenerator(name = "person_id_sequence_generator", sequenceName = "sqperson", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_sequence_generator")
    private Long personId;
    private String firstName;
    private String lastName;
    private Long age;
}

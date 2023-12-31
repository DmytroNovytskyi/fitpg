package com.fitpg.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    private Exercise exercise;

    @Column(nullable = false)
    private Double weight;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Unit unit;

    @Column(nullable = false)
    private Integer repetitions;

    /**
     * Checks if the specified object is equal to this exercise set.
     *
     * @param o The object to compare to.
     * @return true if object is an exercise set and IDs are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExerciseSet exerciseSet = (ExerciseSet) o;
        return id != null && Objects.equals(id, exerciseSet.id);
    }

    /**
     * Generates a hash code for this exercise set.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

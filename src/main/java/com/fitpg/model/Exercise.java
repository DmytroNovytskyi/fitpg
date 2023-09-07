package com.fitpg.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Workout workout;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ExerciseInfo exerciseInfo;

    @OrderColumn(nullable = false)
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ExerciseSet> exerciseSets;

    private String note;

    /**
     * Checks if the specified object is equal to this exercise.
     *
     * @param o The object to compare to.
     * @return true if object is an exercise and IDs are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Exercise exercise = (Exercise) o;
        return id != null && Objects.equals(id, exercise.id);
    }

    /**
     * Generates a hash code for this exercise.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

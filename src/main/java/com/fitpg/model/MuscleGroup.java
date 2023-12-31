package com.fitpg.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "muscleGroups")
    @ToString.Exclude
    private Set<ExerciseInfo> exercises;

    /**
     * Checks if the specified object is equal to this muscle group.
     *
     * @param o The object to compare to.
     * @return true if object is a muscle group and IDs are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MuscleGroup muscleGroup = (MuscleGroup) o;
        return id != null && Objects.equals(id, muscleGroup.id);
    }

    /**
     * Generates a hash code for this muscle group.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void clearExerciseInfosRelationship() {
        for (ExerciseInfo exerciseInfo :
                exercises) {
            exerciseInfo.getMuscleGroups().remove(this);
        }
        exercises.clear();
    }
}

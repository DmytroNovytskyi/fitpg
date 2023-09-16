package com.fitpg.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExerciseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "exerciseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Exercise> exercises;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "exercise_infos_muscle_groups",
            joinColumns = @JoinColumn(name = "exercise_info_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_group_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<MuscleGroup> muscleGroups;

    /**
     * Checks if the specified object is equal to this exercise name.
     *
     * @param o The object to compare to.
     * @return true if object is an exercise name and IDs are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExerciseInfo exerciseInfo = (ExerciseInfo) o;
        return id != null && Objects.equals(id, exerciseInfo.id);
    }

    /**
     * Generates a hash code for this exercise name.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

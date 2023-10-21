package com.fitpg.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NutrientsTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private UserEntity user;

    @Column(nullable = false)
    private Integer fats;

    @Column(nullable = false)
    private Integer carbohydrates;

    @Column(nullable = false)
    private Integer protein;

    @Column(nullable = false)
    private Integer calories;

    @Column(nullable = false)
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NutrientsTrack nutrientsTrack = (NutrientsTrack) o;
        return id != null && Objects.equals(id, nutrientsTrack.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

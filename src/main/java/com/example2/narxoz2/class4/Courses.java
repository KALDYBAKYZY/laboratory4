package com.example2.narxoz2.class4;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int price;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<ApplicationRequest> requests;
}


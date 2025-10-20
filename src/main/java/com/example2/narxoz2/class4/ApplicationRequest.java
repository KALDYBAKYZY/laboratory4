package com.example2.narxoz2.class4;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.example2.narxoz2.class4.Courses;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String commentary;
    private String phone;
    private boolean handled;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;

    @ManyToMany
    @JoinTable(
            name = "request_operators",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "operator_id")
    )
    private List<Operators> operators;
}


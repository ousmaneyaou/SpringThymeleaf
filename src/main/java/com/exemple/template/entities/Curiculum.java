package com.exemple.template.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curiculum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String nom;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String prenom;
    @NotNull
    @Min(value = 18, message = "L'âge doit être supérieur ou égal à 10 ans")
    @Max(value = 120, message = "L'âge doit être inférieur ou égal à 120 ans")
    private Integer age;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String adresse;
    @NotEmpty
    @Size(min = 2, max = 50)
    //@Column(unique = true, nullable = false)
    private String email;
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dateNaissance;
    @DecimalMin("1")
    private String telephone;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String niveauetude;
    @NotEmpty
    @Size(min = 2, max = 100)
    private String experiencepro;
    @ManyToOne
    private  Category category;
}

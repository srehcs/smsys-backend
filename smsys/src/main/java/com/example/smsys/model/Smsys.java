package com.example.smsys.model;

import com.example.smsys.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;

/**
 * @author srehcs
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Smsys {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(unique = true) // Constraint --> Entry is associated with unique email
    @NotEmpty(message = "Email cannot be or null") // Displays error
    private String email;
    @Column(unique = true) // Constraint --> Entry is associated with unique IP
    @NotEmpty(message = "IP Address cannot be empty or null")
    private String ipAddress;
    private String name;
    private String grade;
    private String classes;
    private String imageUrl;
    private Status status;
}

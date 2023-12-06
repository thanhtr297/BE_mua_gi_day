package com.example.tmdt.model;
import com.example.tmdt.security.model.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "message")
@EqualsAndHashCode(callSuper = false)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Account fromUser;
    @ManyToOne
    private Account toUser;
    private String content;
    private LocalDateTime time;

}
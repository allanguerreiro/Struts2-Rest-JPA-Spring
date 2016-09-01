package br.struts2.rest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by allan on 31/08/16.
 */
@Entity
@Table(name = "message",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"id"}))
public class Message {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Getter
    @Setter
    @Column(name = "text")
    private String text;

    @Getter
    @Setter
    @Column(name = "author")
    private String author;
}

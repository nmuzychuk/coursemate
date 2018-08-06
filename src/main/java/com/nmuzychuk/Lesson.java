package com.nmuzychuk;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Lesson {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Lob
    private String content;
    @ManyToOne
    @JoinColumn
    private Course course;

    public Lesson() {
    }

    public Lesson(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, "course");
    }
}

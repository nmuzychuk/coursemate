package com.nmuzychuk;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bonus_lessons")
public class BonusLesson extends Lesson {
    private String bonusContent;

    public BonusLesson() {
    }

    public BonusLesson(String name, String content, String bonusContent) {
        super(name, content);
        this.bonusContent = bonusContent;
    }

    public String getBonusContent() {
        return bonusContent;
    }

    public void setBonusContent(String bonusContent) {
        this.bonusContent = bonusContent;
    }
}

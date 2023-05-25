package com.sourcery.sprint.task.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EducationTask extends Task {

    @Override
    public String getKeyValue() {
        return "Education";
    }

    @Override
    public String getKeyColor() {
        return "#FFFFFF";
    }

    @Override
    public String getDescription() {
        return "Education";
    }

    @Override
    public String getType() {
        return "Education";
    }
}


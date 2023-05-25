package com.sourcery.sprint.task.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DefaultTask extends Task {

    @Override
    public String getKeyValue() {
        return "Vacation";
    }

    @Override
    public String getKeyColor() {
        return "#FFFFFF";
    }

    @Override
    public String getDescription() {
        return "Vacation";
    }

    @Override
    public String getType() {
        return "Vacation";
    }
}


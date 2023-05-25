package com.sourcery.sprint.exceptions;

public class CustomExceptions extends RuntimeException {
    public CustomExceptions(String message) {
        super(message);
    }

    public static class InvalidCredentialsException extends CustomExceptions {
        public InvalidCredentialsException() {
            super("Invalid credentials");
        }
    }

    public static class MemberExistsException extends CustomExceptions {
        public MemberExistsException() {
            super("Member already exists");
        }
    }

    public static class MemberNotFoundException extends CustomExceptions {
        public MemberNotFoundException() {
            super("Member not found");
        }
    }

    public static class SprintExistsException extends CustomExceptions {
        public SprintExistsException() {
            super("Sprint already exists");
        }
    }

    public static class SprintNotFoundException extends CustomExceptions {
        public SprintNotFoundException() {
            super("Sprint not found");
        }
    }

    public static class TaskNotAddedException extends CustomExceptions {
        public TaskNotAddedException() {
            super("Task must be added");
        }
    }

    public static class MemberNotAddedException extends CustomExceptions {
        public MemberNotAddedException() {
            super("Member must be added");
        }
    }

    public static class WorkingDaysNotAddedException extends CustomExceptions {
        public WorkingDaysNotAddedException() {
            super("Working days must be added");
        }
    }

    public static class TeamNotFoundException extends CustomExceptions {
        public TeamNotFoundException() {
            super("Team not found");
        }
    }
}



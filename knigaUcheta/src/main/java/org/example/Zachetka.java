package org.example;

import java.util.List;

public class Zachetka {

    private String firstName;
    private String lastName;
    private String middleName;
    private int course;
    private String group;

    private List<SessionRecord> sessions;
    public static class SessionRecord {
        private int sessionNumber;
        private List<Subject> subjects;

        public static class Subject {
            private String name;
            private String type;
            private int grade;
            private boolean passed;

            public String getName() { return name; }
            public String getType() { return type; }
            public int getGrade() { return grade; }
            public boolean isPassed() { return passed; }
        }

        public int getSessionNumber() { return sessionNumber; }
        public List<Subject> getSubjects() { return subjects; }
    }

    public boolean isGreatStudent() {

        if (sessions == null) return false;

        for (SessionRecord session : sessions) {
            if (session.getSubjects() == null) continue;

            for (SessionRecord.Subject subject : session.getSubjects()) {

                if ("экзамен".equalsIgnoreCase(subject.getType())
                        && subject.getGrade() < 9)
                    return false;

                if ("зачет".equalsIgnoreCase(subject.getType())
                        && !subject.isPassed())
                    return false;
            }
        }
        return true;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public int getCourse() { return course; }
    public String getGroup() { return group; }
    public List<SessionRecord> getSessions() { return sessions; }
}

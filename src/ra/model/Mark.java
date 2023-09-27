package ra.model;

import ra.util.InputMethods;

public class Mark {
    private Long markId;
    private Student student;

    private Subject subject;

    private double point;

    public Mark() {
    }

    public Mark(Long markId, Student student, Subject subject, double point) {
        this.markId = markId;
        this.student = student;
        this.subject = subject;
        this.point = point;
    }

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public void inputData(){
        while (true) {
            System.out.println("Nhap diem (0.0 - 10.0): ");
            double point = InputMethods.getDouble();
            if (point >= 0.0 && point <= 10.0) {
                this.point = point;
                break;
            } else {
                System.out.println("Diem phai tu 0 - 10!");
            }
        }
    }
    public void displayData(){
        System.out.println("ID : " + this.markId);
        System.out.println("Student : " + this.student.getStudentName());
        System.out.println("Subject : " + this.subject.getSubjectName());
        System.out.println("Point : " + this.point);
    }
}

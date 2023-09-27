package ra.model;

import ra.util.InputMethods;

public class Subject {
    private final String REGEX_ID = "^MH[0-9]{3}$";
    private String subjectId;
    private String subjectName;

    public Subject() {
    }

    public Subject(String subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void inputData(){
        while (true) {
            System.out.println("Nhap ma mon hoc (VD: MHxxx)");
            String id = InputMethods.getString();
            if (id.matches(REGEX_ID)) {
                this.subjectId = id;
                break;
            } else {
                System.err.println("Ma mon hoc chua hop ly!");
            }
        }
        System.out.println("Nhap ten mon hoc");
        this.subjectName = InputMethods.getString();
    }
    public void displayData(){
        System.out.println("Ma mon hoc : " + this.subjectId);
        System.out.println("Ten mon hoc : " + this.subjectName);
    }
    public String getREGEX_ID(){
        return this.REGEX_ID;
    }

}

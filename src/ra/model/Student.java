package ra.model;

import ra.util.InputMethods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private final String REGEX_PHONE = "^0[0-9]{9,10}$";
    private Long studentId;
    private String studentName;
    private Date birthDay;

    private String address;

    private boolean gender;

    private String phone;

    public Student() {
    }

    public Student(Long studentId, String studentName, Date birthDay, String address, boolean gender, String phone) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.birthDay = birthDay;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void inputData() throws ParseException {
        System.out.println("Nhap Ten hoc sinh : ");
        this.studentName = InputMethods.getString();
        while (true) {
            System.out.println("Nhap ngay thang nam sinh cua hoc sinh (dd/MM/yyyy) :");
            String inputDate = InputMethods.getString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            try {
                this.birthDay = sdf.parse(inputDate);
                break;
            } catch (ParseException e) {
                System.err.println("Ngay thang chua hop le" + e.getMessage());
            }
        }
        System.out.println("Nhap dia chi");
        this.address = InputMethods.getString();
        System.out.println("Nhap gioi tinh (Nam : true / Nu : false)");
        this.gender = InputMethods.getBoolean();
        while (true) {
            System.out.println("Nhap so dien thoai");
            String phone = InputMethods.getString();
            if (phone.matches(REGEX_PHONE)) {
                this.phone = phone;
                break;
            } else {
                System.err.println("So dien thoai chua hop le!");
            }
        }
    }
    public void displayData(){
        System.out.println("StudentId: " + this.studentId);
        System.out.println("StudentName: " + this.studentName);
        System.out.println("BrithDay: " + new SimpleDateFormat("dd/MM/yyy").format(this.birthDay));
        System.out.println("Address: " + this.address);
        System.out.println("Gender: " + this.gender);
        System.out.println("Phone: " + this.phone);
    }
}

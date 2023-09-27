package ra.run;

import ra.Service.MarkService;
import ra.Service.StudentService;
import ra.Service.SubjectService;
import ra.model.Mark;
import ra.model.Student;
import ra.model.Subject;
import ra.util.InputMethods;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

public class SchoolManagement {
    public static SubjectService subjectService = new SubjectService();
    public static StudentService studentService = new StudentService();

    public static MarkService markService = new MarkService();
    public static void main(String[] args) throws ParseException {
        while (true){
            System.out.println("================SCHOOL-MANAGEMENT================");
            System.out.println("1. Quan ly hoc sinh.\n"
                             + "2. Quan ly mon hoc.\n"
                             + "3. Quan ly diem thi.\n"
                             + "4. Thoat.");
            System.out.println("Nhap lua chon cua ban");
            byte choise = InputMethods.getByte();
            switch (choise){
                case 1:
                    studentManager();
                    break;
                case 2:
                    subjectManager();
                    break;
                case 3:
                    markManager();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Lua chon chua hop ly!");
            }
        }
    }

    // quan ly hoc sinh
    public static void studentManager() throws ParseException {
        while (true){
            System.out.println("================STUDENT-MANAGEMENT================");
            System.out.println("1.Thêm mới học sinh\n"
            +"2.Hiển thị danh sách tất cả học sinh đã lưu trữ\n"
            +"3.Thay đổi thông tin học sinh theo mã id\n"
            +"4.Xóa học sinh theo mã id\n"
            +"5.Thoat");
            System.out.println("Nhap lua chon cua ban");
            byte choise = InputMethods.getByte();
            switch (choise){
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Lua chon chua hop ly!");
            }
            if(choise == 5){
                break;
            }
        }
    }

    // them hoc sinh moi
    public static void addStudent() throws ParseException {
        while (true) {
            Long id = studentService.getNewId();
            Student newStudent = new Student();
            newStudent.setStudentId(id);
            newStudent.inputData();
            while (true) {
                if (!studentService.checkExistPhone(newStudent.getPhone())) {
                    break;
                } else {
                    System.err.println("SDT da ton tai vui long nhap SDT khac");
                    newStudent.setPhone(InputMethods.getString());
                }
            }
            studentService.save(newStudent);
            System.out.println("Add new student successful!");
            if(backToMenu()){
                break;
            }
        }
    }

    // Hien thi danh sach hoc sinh
    public static void displayAllStudent(){
        for (Student s: studentService.getAll()) {
            s.displayData();
            System.out.println("-------------------------");
        }
//        System.out.println("An nut bat ky de quay lai Menu");
//        InputMethods.pressAnyKey();
    }

    // Thay doi thong tin hoc sinh theo ma id
    public static void updateStudent() throws ParseException {
        while (true){
            System.out.println("Nhap Id hoc sinh muon update :");
            Long id = InputMethods.getLong();
            Student updateStudent = studentService.findById(id);
            if(updateStudent != null){
                String prevPhone = updateStudent.getPhone();
                updateStudent.displayData();
                System.out.println("--------------------------");
                updateStudent.inputData();
                while (true){
                    if(prevPhone.equals(updateStudent.getPhone())){
                        break;
                    }
                    if(!studentService.checkExistPhoneInUpdate(updateStudent)){
                        break;
                    } else {
                        System.err.println("SDT da ton tai vui long nhap SDT khac");
                        updateStudent.setPhone(InputMethods.getString());
                    }
                }
                studentService.save(updateStudent);
                System.out.println("Update successful!");
            } else {
                System.err.println("Id khong ton tai!");
            }
            if(backToMenu()){
                break;
            }
        }
    }

    // Xoa hoc sinh theo ma id
    public static void deleteStudent(){
        while (true){
            System.out.println("Nhap Id hoc sinh muon xoa :");
            Long id = InputMethods.getLong();
            if(markService.checkStudentHavePoint(id)){
                System.err.println("Hoc sinh da co diem thi, khong the xoa");
            } else {
                if(studentService.findById(id) != null){
                    studentService.delete(id);
                    System.out.println("Delete successful!");
                } else {
                    System.err.println("Id khong ton tai!");
                }
            }
            if(backToMenu()){
                break;
            }
        }
    }

    // ham back to menu
    public static boolean backToMenu(){
        System.out.println("Ban co muon tiep tuc khong? y/n");
        if(InputMethods.getChar() == 'n'){
            return true;
        }
        return false;
    }

    // quan ly mon hoc
    public static void subjectManager(){
        while (true){
            System.out.println("================SUBJECT-MANAGEMENT================");
            System.out.println("1.Thêm mới mon hoc\n"
                    +"2.Hiển thị danh sách tất cả mon hoc đã lưu trữ\n"
                    +"3.Thay đổi thông tin mon hoc theo mã id\n"
                    +"4.Xóa mon hoc theo mã id\n"
                    +"5.Thoat");
            System.out.println("Nhap lua chon cua ban");
            byte choise = InputMethods.getByte();
            switch (choise){
                case 1:
                    addSubject();
                    break;
                case 2:
                    displayAllSubject();
                    break;
                case 3:
                    updateSubject();
                    break;
                case 4:
                    deleteSubject();
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Lua chon chua hop ly!");
            }
            if(choise == 5){
                break;
            }
        }
    }

    // them mon hoc moi
    public static void addSubject() {
        while (true) {
            Subject newSubject = new Subject();
            newSubject.inputData();
            while (true) {
                if (!subjectService.checkExistSubjectName(newSubject.getSubjectName())) {
                    break;
                } else {
                    System.err.println("Ten mon hoc da ton tai vui long nhap ten khac!");
                    newSubject.setSubjectName(InputMethods.getString());
                }
            }
            subjectService.save(newSubject);
            System.out.println("Add new subject successful!");
            if(backToMenu()){
                break;
            }
        }
    }

    // Hien thi danh sach mon hoc
    public static void displayAllSubject(){
        for (Subject s: subjectService.getAll()) {
            s.displayData();
            System.out.println("-------------------------");
        }
//        System.out.println("An nut bat ky de quay lai Menu");
//        InputMethods.pressAnyKey();
    }

    // Thay doi thong tin hoc sinh theo ma id
    public static void updateSubject() {
        while (true){
            System.out.println("Nhap ma mon hoc muon update :");
            String id = InputMethods.getString();
            Subject updateSubject = subjectService.findById(id);
            if(updateSubject != null){
                String prevName = updateSubject.getSubjectName();
                updateSubject.displayData();
                System.out.println("Nhap ten mon hoc :");
                String name = InputMethods.getString();
                updateSubject.setSubjectName(name);
                while (true){
                    if(prevName.equals(updateSubject.getSubjectName())){
                        break;
                    }
                    if (!subjectService.checkExistSubjectNameByObject(updateSubject)) {
                        break;
                    } else {
                        System.err.println("Ten mon hoc da ton tai vui long nhap ten khac!");
                        updateSubject.setSubjectName(InputMethods.getString());
                    }
                }
                subjectService.save(updateSubject);
                System.out.println("Update successful!");
            } else {
                System.err.println("Ma mon hoc khong ton tai!");
            }
            if(backToMenu()){
                break;
            }
        }
    }

    // Xoa hoc sinh theo ma id
    public static void deleteSubject(){
        while (true){
            System.out.println("Nhap ma muon hoc muon xoa :");
            String id = InputMethods.getString();
            if(markService.checkSubjectHavePoint(id)){
                System.err.println("Mon hoc da co diem thi, khong the xoa");
            } else {
                if(subjectService.findById(id) != null){
                    subjectService.delete(id);
                    System.out.println("Delete successful!");
                } else {
                    System.err.println("Ma mon hoc khong ton tai!");
                }
            }
            if(backToMenu()){
                break;
            }
        }
    }

    // quan ly diem thi
    public static void markManager() throws ParseException {
        while (true){
            System.out.println("================MARK-MANAGEMENT================");
            System.out.println("1.Thêm mới điểm thi cho 1 sinh viên\n"
                    +"2.Hiển thị danh sách tất cả điểm thi theo thứ tự điểm tăng dần\n"
                    +"3.Thay đổi điểm theo mã id\n"
                    +"4.Xóa điểm theo mã id\n"
                    +"5.Hiển thị danh sách điểm thi theo mã môn học\n"
                    +"6.Hiển thị đánh giá học lực của từng học sinh theo mã môn học\n"
                    +"7.Thoat");
            System.out.println("Nhap lua chon cua ban");
            byte choise = InputMethods.getByte();
            switch (choise){
                case 1:
                    addMarkForStudent();
                    break;
                case 2:
                    displayAllList();
                    break;
                case 3:
                    updatePointById();
                    break;
                case 4:
                    deleteMarkById();
                    break;
                case 5:
                    displayAllListBySubjectId();
                    break;
                case 6:
                    displayDanhGia();
                    break;
                case 7:
                    break;
                default:
                    System.err.println("Lua chon chua hop ly!");
            }
            if(choise == 7){
                break;
            }
        }
    }

    // them diem cho 1 sinh vien
    public static void addMarkForStudent() throws ParseException {
        while (true) {
            Mark addMark = new Mark();
            if (studentService.getAll().isEmpty()) {
                System.err.println("Chua co hoc sinh nao trong danh sach! Vui long them hoc sinh!");
                addStudent();
            }
            if (subjectService.getAll().isEmpty()) {
                System.err.println("Chua co mon hoc nao trong danh sach! Vui long them mon hoc!");
                addSubject();
            }
            addMark.setMarkId(markService.getNewId());
            Student addStd;
            while (true){
                displayAllStudent();
                System.out.println("Nhap Id hoc sinh muon them diem");
                Long id = InputMethods.getLong();
                addStd = studentService.findById(id);
                if(addStd == null){
                    System.err.println("Id khong ton tai vui long nhap lai");
                } else {
                    break;
                }
            }
            addMark.setStudent(addStd);
            Subject addSj;
            while (true) {
                displayAllSubject();
                System.out.println("Nhap ma mon muon them diem");
                String mmh = InputMethods.getString();
                addSj = subjectService.findById(mmh);
                if(addSj == null){
                    System.err.println("Ma mon hoc khong ton tai vui long nhap lai");
                } else {
                    break;
                }
            }
            addMark.setSubject(addSj);
            addMark.inputData();
            markService.save(addMark);
            if(backToMenu()){
                break;
            }
        }
    }

    // hien thi list diem theo sap xep tang dan
    public static void displayAllList(){
       List<Mark> listMark = markService.getAll();
       listMark.sort(Comparator.comparing(Mark::getPoint));
        for (Mark m: listMark) {
            m.displayData();
            System.out.println("--------------");
        }
    }

    // update point by id
    public static void updatePointById(){
        while (true){
            System.out.println("Nhap id muon update :");
            Long id = InputMethods.getLong();
            Mark updateMark = markService.findById(id);
            if(updateMark != null){
                updateMark.inputData();
                markService.save(updateMark);
                System.out.println("Update successful!");
            } else {
                System.err.println("Id khong ton tai! Vui long nhap lai!");
            }
            if(backToMenu()){
                break;
            }
        }
    }

    // Xoa point
    public static void deleteMarkById(){
        while (true){
            System.out.println("Nhap Id muon xoa :");
            Long id = InputMethods.getLong();
            Mark updateMark = markService.findById(id);
            if(updateMark != null){
                markService.delete(id);
                System.out.println("Delete successful!");
            } else {
                System.err.println("Id khong ton tai! Vui long nhap lai!");
            }
            if(backToMenu()){
                break;
            }
        }
    }

    // hien thi list diem theo mon hoc
    public static void displayAllListBySubjectId(){
        List<Subject> listSubject = subjectService.getAll();
        List<Mark> listMark = markService.getAll();
        if(listMark.isEmpty()) {
            System.err.println("Danh sach diem dang trong");
        } else if(listSubject.isEmpty()) {
            System.err.println("Danh sach mon hoc dang trong");
        } else {
            for (Subject sj: listSubject) {
                System.out.println("Mon hoc : " + sj.getSubjectName());
                for (Mark m: listMark) {
                    if(m.getSubject().getSubjectId().equals(sj.getSubjectId())){
                        System.out.println("Hoc sinh: " + m.getStudent().getStudentName() + "| Diem: " + m.getPoint());

                    }
                }
                System.out.println("-----------------");
            }
        }
        System.out.println("Nhan nut bat ky de quay Menu");
        InputMethods.pressAnyKey();
    }

    // Hien thi danh gia hoc sinh
    public static void displayDanhGia(){
        List<Subject> listSubject = subjectService.getAll();
        List<Mark> listMark = markService.getAll();
        if(listMark.isEmpty()) {
            System.err.println("Danh sach diem dang trong");
        } else if(listSubject.isEmpty()) {
            System.err.println("Danh sach mon hoc dang trong");
        } else {
            for (Subject sj: listSubject) {
                System.out.println("Mon hoc : " + sj.getSubjectName());
                for (Mark m: listMark) {
                    if(m.getSubject().getSubjectId().equals(sj.getSubjectId())){
                        String danhGia;
                        if(m.getPoint() < 5){
                            danhGia = "Yeu";
                        } else if (m.getPoint() < 6.5){
                            danhGia = "Trung Binh";
                        } else if (m.getPoint() < 8){
                            danhGia = "Kha";
                        } else if (m.getPoint() < 9){
                            danhGia = "Gioi";
                        } else {
                            danhGia = "Xuat Sac";
                        }
                        System.out.println("Hoc sinh: " + m.getStudent().getStudentName() + "| Danh gia: " + danhGia);
                    }
                }
            }
        }
        System.out.println("Nhan nut bat ky de quay Menu");
        InputMethods.pressAnyKey();
    }
}

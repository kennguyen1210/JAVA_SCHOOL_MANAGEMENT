package ra.Service;

import ra.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentService implements IService<Student, Long>{
    private List<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public void delete(Long id) {
            students.remove(findById(id));
    }

    @Override
    public void save(Student student) {
        if(findById(student.getStudentId()) == null){
            students.add(student);
        } else {
            students.set(students.indexOf(findById(student.getStudentId())), student);
        };
    }

    @Override
    public Student findById(Long id) {
        for (Student s: students) {
            if(s.getStudentId().equals(id)){
                return s;
            }
        }
        return null;
    }

    public boolean checkExistPhone(String phone){
        for (Student s: students) {
            if(s.getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public boolean checkExistPhoneInUpdate(Student st){
        for (Student s: students) {
            if(!s.getStudentId().equals(st.getStudentId()) && s.getPhone().equals(st.getPhone())){
                return true;
            }
        }
        return false;
    }
    public Long getNewId(){
        Long maxId = 0L;
        for (Student s: students) {
            if(s.getStudentId() > maxId){
                maxId = s.getStudentId();
            }
        }
        return maxId+1;
    }
}

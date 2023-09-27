package ra.Service;

import ra.model.Student;
import ra.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectService implements IService<Subject, String>{
    private List<Subject> subjects;

    public SubjectService(){
        subjects = new ArrayList<>();
    }
    @Override
    public List<Subject> getAll() {
        return subjects;
    }

    @Override
    public void delete(String id) {
            subjects.remove(findById(id));
    }

    @Override
    public void save(Subject subject) {
        if(findById(subject.getSubjectId()) == null){
            subjects.add(subject);
        } else {
            subjects.set(subjects.indexOf(findById(subject.getSubjectId())), subject);
        }
    }

    @Override
    public Subject findById(String id) {
        for (Subject s: subjects) {
            if(s.getSubjectId().equals(id)){
                return s;
            }
        }
        return null;
    }
    public boolean checkExistSubjectName(String name){
        for (Subject s: subjects) {
            if(s.getSubjectName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean checkExistSubjectNameByObject(Subject sj){
        for (Subject s: subjects) {
            if(!s.getSubjectId().equals(sj.getSubjectId()) && s.getSubjectName().equals(sj.getSubjectName())){
                return true;
            }
        }
        return false;
    }


}

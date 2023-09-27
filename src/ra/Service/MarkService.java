package ra.Service;

import ra.model.Mark;
import ra.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MarkService implements IService<Mark, Long>{
    private List<Mark> marks;

    public MarkService() {
        marks = new ArrayList<>();
    }

    @Override
    public List<Mark> getAll() {
        return marks;
    }

    @Override
    public void delete(Long id) {
        marks.remove(findById(id));

    }

    @Override
    public void save(Mark mark) {
        if(findById(mark.getMarkId()) == null){
            marks.add(mark);
        } else {
            marks.set(marks.indexOf(findById(mark.getMarkId())), mark);
        }
    }

    @Override
    public Mark findById(Long id) {
        for (Mark m: marks) {
            if(m.getMarkId().equals(id)){
                return m;
            }
        }
        return null;
    }

    public Long getNewId(){
        Long maxId = 0L;
        for (Mark s: marks) {
            if(s.getMarkId() > maxId){
                maxId = s.getMarkId();
            }
        }
        return maxId+1;
    }

    public boolean checkStudentHavePoint(Long id){
        for (Mark m: marks) {
            if(m.getStudent().getStudentId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean checkSubjectHavePoint(String id){
        for (Mark m: marks) {
            if(m.getSubject().getSubjectId().equals(id)){
                return true;
            }
        }
        return false;
    }
}

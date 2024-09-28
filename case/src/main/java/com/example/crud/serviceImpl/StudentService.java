package com.example.crud.serviceImpl;

import com.example.crud.model.Student;
import com.example.crud.repository.IStudentRepository;
import com.example.crud.repositoryimpl.StudentRepository;
import com.example.crud.service.IStudentService;

import java.util.Collections;
import java.util.List;

public class StudentService implements IStudentService {
    private final IStudentRepository iStudentRepository = new StudentRepository();

    @Override
    public List<Student> findAll() {
        return iStudentRepository.findAll();
    }

    @Override
    public void addNewStudent(Student student) {
        iStudentRepository.addNewStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        iStudentRepository.updateStudent(student);
    }

    @Override
    public void deleteStudent(int id) {
        iStudentRepository.deleteStudent(id);
    }

    @Override
    public Student findStudentById(int id) {
        return iStudentRepository.findStudentById(id);
    }

    @Override
    public List<Student> searchStudents(String name, String email, Integer clazzId, Double minPoint, Double maxPoint) {
        return iStudentRepository.searchStudents(name, email, clazzId, minPoint, maxPoint);
    }

    @Override
    public List<Student> findStudents(int page, int pageSize) {
        return iStudentRepository.findStudents(page, pageSize);
    }

    @Override
    public int getTotalStudentCount() {
        return iStudentRepository.getTotalStudentCount();
    }

}

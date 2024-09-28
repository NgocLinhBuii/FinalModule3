package com.example.crud.repositoryimpl;

import com.example.crud.model.Clazz;
import com.example.crud.model.Student;
import com.example.crud.repository.BaseRepository;
import com.example.crud.repository.ClazzRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClazzRepositoryImpl implements ClazzRepository {
    private final BaseRepository baseRepository = new BaseRepository();
    private static final String FIND_ALL = "SELECT * FROM clazz";
    private final Connection connection = baseRepository.getConnection();

    @Override
    public List<Clazz> findAll() {
        List<Clazz> clazzList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String clazzName = resultSet.getString("class_name");
                clazzList.add(new Clazz(id, clazzName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clazzList;
    }


    @Override
    public void addNewClazz(Clazz clazz) {

    }

    @Override
    public void updateClazz(Clazz clazz) {

    }

    @Override
    public void deleteClazz(int id) {

    }

    @Override
    public Clazz findClazzById(int id) {
        return null;
    }
}

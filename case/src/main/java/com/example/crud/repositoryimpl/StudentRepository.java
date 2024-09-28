package com.example.crud.repositoryimpl;

import com.example.crud.model.Student;
import com.example.crud.repository.BaseRepository;
import com.example.crud.repository.IStudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository implements IStudentRepository {
    private final BaseRepository baseRepository = new BaseRepository();
    private static final String FIND_ALL = "SELECT * FROM student";
    private static final String ADD_STUDENT = "INSERT INTO student(name, email, clazz_id, point) VALUES (?, ?, ?, ?)";
    private static final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";
    private static final String UPDATE_STUDENT = "UPDATE student SET name = ?, email = ?, clazz_id = ?, point = ? WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM student WHERE id = ?";
    private static final String CHECK = "SELECT COUNT(*) FROM students WHERE email = ? AND id != ?";


    private final Connection connection = baseRepository.getConnection();

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int clazz_id = Integer.parseInt(resultSet.getString("clazz_id"));
                double point = resultSet.getDouble("point");
                studentList.add(new Student(id, name, email, clazz_id, point));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public void addNewStudent(Student student) {
        if (emailExists(student.getEmail())) {
            throw new RuntimeException("Email already exists: " + student.getEmail());
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_STUDENT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getClazz_id()); // Đảm bảo cột này đúng với cơ sở dữ liệu
            preparedStatement.setDouble(4, student.getPoint());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateStudent(Student student) {
//        if (emailExists(student.getEmail())) {
//            throw new RuntimeException("Email already exists: " + student.getEmail());
//        }
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getClazz_id());
            preparedStatement.setDouble(4, student.getPoint());
            preparedStatement.setInt(5, student.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No student found with ID: " + student.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating student.", e);
        }
    }

    @Override
    public void deleteStudent(int id) {

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting student.", e);
        }
    }

    @Override
    public Student findStudentById(int id) {
        Student student = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("clazz_id"),
                            resultSet.getDouble("point")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding student by ID.", e);
        }
        return student;
    }

    @Override
    public List<Student> searchStudents(String name, String email, Integer clazzId, Double minPoint, Double maxPoint) {
        List<Student> students = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM student WHERE 1=1");

        // Thêm điều kiện tìm kiếm theo tên
        if (name != null && !name.isEmpty()) {
            query.append(" AND name LIKE ?");
        }

        // Thêm điều kiện tìm kiếm theo email
        if (email != null && !email.isEmpty()) {
            query.append(" AND email LIKE ?");
        }

        // Thêm điều kiện tìm kiếm theo lớp
        if (clazzId != null) {
            query.append(" AND clazz_id = ?");
        }

        // Thêm điều kiện tìm kiếm theo điểm tối thiểu
        if (minPoint != null) {
            query.append(" AND point >= ?");
        }

        // Thêm điều kiện tìm kiếm theo điểm tối đa
        if (maxPoint != null) {
            query.append(" AND point <= ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;

            // Gán giá trị cho tham số tìm kiếm tên
            if (name != null && !name.isEmpty()) {
                preparedStatement.setString(paramIndex++, "%" + name + "%");
            }

            // Gán giá trị cho tham số tìm kiếm email
            if (email != null && !email.isEmpty()) {
                preparedStatement.setString(paramIndex++, "%" + email + "%");
            }

            // Gán giá trị cho tham số tìm kiếm lớp
            if (clazzId != null) {
                preparedStatement.setInt(paramIndex++, clazzId);
            }

            // Gán giá trị cho tham số điểm tối thiểu
            if (minPoint != null) {
                preparedStatement.setDouble(paramIndex++, minPoint);
            }

            // Gán giá trị cho tham số điểm tối đa
            if (maxPoint != null) {
                preparedStatement.setDouble(paramIndex++, maxPoint);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    students.add(new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("clazz_id"),
                            resultSet.getDouble("point")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching students.", e);
        }

        return students;
    }


    @Override
    public List<Student> findStudents(int page, int pageSize) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Tính toán giá trị OFFSET
            int offset = (page - 1) * pageSize;

            preparedStatement.setInt(1, pageSize);  // Số lượng sinh viên mỗi trang
            preparedStatement.setInt(2, offset);    // Bắt đầu từ sinh viên thứ `offset`

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Lấy dữ liệu và thêm vào danh sách sinh viên
                    students.add(new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("clazz_id"),
                            resultSet.getDouble("point")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching students with pagination.", e);
        }
        return students;
    }


    @Override
    public int getTotalStudentCount() {
        String sql = "SELECT COUNT(*) FROM student";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);  // Lấy tổng số lượng sinh viên
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error counting students.", e);
        }
        return 0;  // Trả về 0 nếu có lỗi
    }

    @Override
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM student WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Nếu có ít nhất một sinh viên với email này
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking email existence.", e);
        }
        return false; // Trả về false nếu có lỗi
    }
}

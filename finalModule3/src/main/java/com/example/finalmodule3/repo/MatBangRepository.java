package com.example.finalmodule3.repo;

import com.example.finalmodule3.model.MatBang;
import com.example.finalmodule3.BaseRepo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatBangRepository implements IMatBangRepo {
    private final BaseRepo baseRepository = new BaseRepo();
    private static final String FIND_ALL = "SELECT * FROM matbang";
    private static final String ADD_MATBANG = "INSERT INTO matbang(maMatBang, trangThai, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_MATBANG = "DELETE FROM matbang WHERE maMatBang = ?";
    private static final String FIND_BY_MA_MATBANG = "SELECT * FROM matbang WHERE maMatBang = ?";
    private static final String SEARCH_MATBANG = "SELECT * FROM matbang WHERE maMatBang LIKE ? OR trangThai LIKE ?";

    @Override
    public List<MatBang> findAll() {
        List<MatBang> matBangList = new ArrayList<>();
        Connection connection = baseRepository.getConnection();
        try (
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery() ){

            while (resultSet.next()) {
                MatBang matBang = new MatBang(
                        resultSet.getString("maMatBang"),
                        resultSet.getString("trangThai"),
                        resultSet.getFloat("dienTich"),
                        resultSet.getInt("tang"),
                        resultSet.getString("loaiMatBang"),
                        resultSet.getFloat("giaTien"),
                        resultSet.getDate("ngayBatDau").toLocalDate(),
                        resultSet.getDate("ngayKetThuc").toLocalDate()
                );
                matBangList.add(matBang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matBangList;
    }

    @Override
    public void save(MatBang matBang) {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_MATBANG)) {

            statement.setString(1, matBang.getMaMatBang());
            statement.setString(2, matBang.getTrangThai());
            statement.setFloat(3, matBang.getDienTich());
            statement.setInt(4, matBang.getTang());
            statement.setString(5, matBang.getLoaiMatBang());
            statement.setFloat(6, matBang.getGiaTien());
            statement.setDate(7, Date.valueOf(matBang.getNgayBatDau()));
            statement.setDate(8, Date.valueOf(matBang.getNgayKetThuc()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String maMatBang) {
        Connection connection = baseRepository.getConnection();
        try (
             PreparedStatement statement = connection.prepareStatement(DELETE_MATBANG)) {

            statement.setString(1, maMatBang);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsByMaMatBang(String maMatBang) {
        return findAll().stream().anyMatch(matBang -> matBang.getMaMatBang().equals(maMatBang));
    }

    @Override
    public MatBang findByMaMatBang(String maMatBang) {
        MatBang matBang = null;
        Connection connection = baseRepository.getConnection();
        try (
             PreparedStatement statement = connection.prepareStatement(FIND_BY_MA_MATBANG)) {

            statement.setString(1, maMatBang);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matBang = new MatBang(
                        resultSet.getString("maMatBang"),
                        resultSet.getString("trangThai"),
                        resultSet.getFloat("dienTich"),
                        resultSet.getInt("tang"),
                        resultSet.getString("loaiMatBang"),
                        resultSet.getFloat("giaTien"),
                        resultSet.getDate("ngayBatDau").toLocalDate(),
                        resultSet.getDate("ngayKetThuc").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matBang;
    }

    @Override
    public List<MatBang> searchWithConditions(String loaiMatBang, Float giaTien, Integer tang, LocalDate startDate, LocalDate endDate) {
        List<MatBang> matBangList = new ArrayList<>();
        Connection connection = baseRepository.getConnection();
        try (
             PreparedStatement statement = connection.prepareStatement(SEARCH_MATBANG)) {

            // Thiết lập các tham số
            statement.setString(1, loaiMatBang);
            statement.setString(2, loaiMatBang);
            statement.setFloat(3, giaTien);
            statement.setFloat(4, giaTien);
            statement.setInt(5, tang);
            statement.setInt(6, tang);
            statement.setDate(7, Date.valueOf(startDate));
            statement.setDate(8, Date.valueOf(endDate));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MatBang matBang = new MatBang(
                        resultSet.getString("maMatBang"),
                        resultSet.getString("trangThai"),
                        resultSet.getFloat("dienTich"),
                        resultSet.getInt("tang"),
                        resultSet.getString("loaiMatBang"),
                        resultSet.getFloat("giaTien"),
                        resultSet.getDate("ngayBatDau").toLocalDate(),
                        resultSet.getDate("ngayKetThuc").toLocalDate()
                );
                matBangList.add(matBang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matBangList;
    }
}

package g56020.atlg4.sorting.model.dao;

import g56020.atlg4.sorting.model.dto.LogsDto;
import g56020.atlg4.sorting.model.repository.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogsDao implements Dao<Integer, LogsDto> {
    private Connection connection;

    private LogsDao() throws RepositoryException {
        connection = DBManager.getInstance().getConnection();
    }

    public static LogsDao getInstance() throws RepositoryException {
        return LogsDaoHolder.getInstance();
    }

    @Override
    public LogsDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Missing key");
        }
        LogsDto dto = null;
        String sql = "SELECT id, timestamp, sort_type, max_size FROM SIMULATION WHERE ID=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new LogsDto(
                        rs.getInt("id"),
                        rs.getTimestamp("timestamp"),
                        rs.getString("sort_type"),
                        rs.getInt("max_size"));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;


    }

    @Override
    public List<LogsDto> selectAll() throws RepositoryException {
        List<LogsDto> logs = new ArrayList<>();
        String sql = "SELECT id, timestamp, sort_type, max_size FROM SIMULATION";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                LogsDto dto = new LogsDto(
                        rs.getInt("id"),
                        rs.getTimestamp("timestamp"),
                        rs.getString("sort_type"),
                        rs.getInt("max_size"));
                logs.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return logs;

    }

    @Override
    public int insert(LogsDto logsDto) throws RepositoryException {
        int result;
        Timestamp timestamp = logsDto.getTimestamp();
        String sortType = logsDto.getSortType();
        int maxSize = logsDto.getMaxSize();

        String sql = "INSERT INTO SIMULATION('timestamp','sort_type','max_size')" +
                "VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, timestamp.toString());
            pstmt.setString(2, sortType);
            pstmt.setInt(3, maxSize);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return result;

    }

    private static class LogsDaoHolder {
        private static LogsDao getInstance() throws RepositoryException {
            return new LogsDao();
        }
    }

}

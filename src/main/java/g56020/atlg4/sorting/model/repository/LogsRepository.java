package g56020.atlg4.sorting.model.repository;

import g56020.atlg4.sorting.model.dao.LogsDao;
import g56020.atlg4.sorting.model.dto.LogsDto;

import java.util.List;

public class LogsRepository implements Repository<Integer, LogsDto> {
    private final LogsDao dao;

    public LogsRepository() throws RepositoryException {
        this.dao = LogsDao.getInstance();
    }

    @Override
    public List<LogsDto> getAll() throws RepositoryException {
        return this.dao.selectAll();
    }

    @Override
    public LogsDto get(Integer key) throws RepositoryException {
        if (contains(key)) {
            return this.dao.select(key);
        }
        return null;
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return this.dao.select(key) != null;
    }

    @Override
    public int insert(LogsDto logsDto) throws RepositoryException {
        if (!contains(logsDto.getKey())) {
            return this.dao.insert(logsDto);
        }
        throw new RepositoryException("A field with the same id already exists");
    }
}

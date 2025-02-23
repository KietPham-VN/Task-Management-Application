package dao.interfaces;

import dto.TaskDTO;

public interface ITaskDAO
{
    boolean add(TaskDTO taskDto);
    boolean update(TaskDTO taskDto);
    boolean delete(TaskDTO taskDto);
}

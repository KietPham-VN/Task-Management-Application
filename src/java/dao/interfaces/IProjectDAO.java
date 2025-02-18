package dao.interfaces;

import dto.ProjectDTO;

public interface IProjectDAO
{
    boolean add(ProjectDTO projectDto);
    boolean update(ProjectDTO projectDto);
}

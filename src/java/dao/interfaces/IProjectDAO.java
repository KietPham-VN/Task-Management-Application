package dao.interfaces;

import dto.ProjectDTO;

public interface IProjectDAO
{
    boolean add(ProjectDTO o);
    boolean update(ProjectDTO o);
    
}

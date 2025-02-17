use master
ALTER DATABASE TaskManagementDB SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DROP DATABASE TaskManagementDB ;
GO

CREATE DATABASE TaskManagementDB
GO

use [TaskManagementDB]
GO

CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY ,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    passwordHash TEXT NOT NULL,
	salt TEXT NOT NULL,
    role VARCHAR(50) CHECK (role IN ('Project Manager', 'Team Member')) NOT NULL,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
GO
CREATE TABLE Projects (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    createdBy INT REFERENCES Users(id) ON DELETE SET NULL,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
GO

CREATE TABLE ProjectMembers (
    id INT IDENTITY(1,1) PRIMARY KEY,
    projectId INT REFERENCES Projects(id) ON DELETE CASCADE,
    userId INT REFERENCES Users(id) ON DELETE CASCADE,
    UNIQUE (projectId, userId)
);
GO

CREATE TABLE Tasks (
    id INT IDENTITY(1,1) PRIMARY KEY,
    projectId INT REFERENCES projects(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    assignedTo INT REFERENCES users(id) ON DELETE SET NULL,
    status VARCHAR(50) CHECK (status IN ('Pending', 'In Progress', 'Completed')) DEFAULT 'Pending',
    priority VARCHAR(50) CHECK (priority IN ('Low', 'Medium', 'High')) DEFAULT 'Medium',
    dueDate DATE,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
GO

CREATE TABLE TaskUpdates (
    id INT IDENTITY(1,1) PRIMARY KEY,
    taskId INT REFERENCES tasks(id) ON DELETE CASCADE,
    updatedBy INT REFERENCES users(id),
    updateNotes TEXT,
    updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
GO

CREATE TABLE Comments (
    id INT IDENTITY(1,1) PRIMARY KEY,
	commentTxt TEXT,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    userId INT REFERENCES users(id) ON DELETE CASCADE,
	taskUpdateId INT REFERENCES TaskUpdates(id) ON DELETE CASCADE
);
GO

CREATE TABLE Labels (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    color VARCHAR(7) NOT NULL CHECK (color LIKE '^#(?:[0-9a-fA-F]{3}){1,2}$')
);
GO

CREATE TABLE TaskLabels (
    taskId INT REFERENCES tasks(id) ON DELETE CASCADE,
    labelId INT REFERENCES labels(id) ON DELETE CASCADE,
    PRIMARY KEY (taskId, labelId)
);
GO

CREATE TRIGGER trg_DeleteTaskUpdatesBeforeUser
ON Users
INSTEAD OF DELETE
AS
BEGIN
    DELETE FROM TaskUpdates
    WHERE updatedBy IN (SELECT id FROM deleted);

    DELETE FROM Users
    WHERE id IN (SELECT id FROM deleted);
END;
GO
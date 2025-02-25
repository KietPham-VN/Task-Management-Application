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
    projectId INT REFERENCES Projects(id),
    userId INT REFERENCES Users(id),
    UNIQUE (projectId, userId)
);
GO

CREATE TABLE Tasks (
    id INT IDENTITY(1,1) PRIMARY KEY,
    projectId INT REFERENCES projects(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    assignedTo INT REFERENCES projectMembers(id) ON DELETE SET NULL,
    status INT CHECK (status BETWEEN 1 AND 3) DEFAULT 1, -- 1: Pending, 2: In Progress, 3: Completed
    priority INT CHECK (priority BETWEEN 1 AND 3) DEFAULT 2, -- 1: Low, 2: Medium, 3: High
    dueDate DATETIME,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
GO

CREATE TABLE TaskUpdates (
    id INT IDENTITY(1,1) PRIMARY KEY,
    taskId INT REFERENCES tasks(id) ON DELETE CASCADE,
    updatedBy INT REFERENCES projectMembers(id) ON DELETE SET NULL,
    updateNotes TEXT,
    updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
GO

CREATE TABLE Comments (
    id INT IDENTITY(1,1) PRIMARY KEY,
	commentTxt TEXT,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    memberId INT REFERENCES projectMembers(id) ON DELETE SET NULL,
	taskUpdateId INT REFERENCES TaskUpdates(id) ON DELETE CASCADE
);
GO

CREATE TABLE Labels (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    color VARCHAR(7) NOT NULL
);
GO

CREATE TABLE TaskLabels (
    taskId INT REFERENCES tasks(id) ON DELETE CASCADE,
    labelId INT REFERENCES labels(id) ON DELETE CASCADE,
    PRIMARY KEY (taskId, labelId)
);
GO

CREATE FUNCTION dbo.fn_CountUniqueProjectMembers(@projectId INT)
RETURNS INT
AS
BEGIN
    DECLARE @memberCount INT;
    SELECT @memberCount = COUNT(DISTINCT userId)
    FROM ProjectMembers
    WHERE projectId = @projectId;

    RETURN @memberCount;
END
GO

-- Insert sample data for Users
INSERT INTO Users (name, email, passwordHash, salt, role) VALUES ('Alice', 'alice@example.com', '94c9eef0fe9b8abaa7d2d6ac16628b61e404d0afcd82de44f8c001bad499518e', '8d274fa624fb4827a4496154b2931bfd', 'Project Manager');
INSERT INTO Users (name, email, passwordHash, salt, role) VALUES ('Bob', 'bob@example.com', '94c9eef0fe9b8abaa7d2d6ac16628b61e404d0afcd82de44f8c001bad499518e', '8d274fa624fb4827a4496154b2931bfd', 'Team Member');
INSERT INTO Users (name, email, passwordHash, salt, role) VALUES ('Charlie', 'charlie@example.com', '94c9eef0fe9b8abaa7d2d6ac16628b61e404d0afcd82de44f8c001bad499518e', '8d274fa624fb4827a4496154b2931bfd', 'Team Member');

-- Insert sample data for Projects
INSERT INTO Projects (name, description, createdBy) VALUES ('Project Alpha', 'Description for Project Alpha', 1);
INSERT INTO Projects (name, description, createdBy) VALUES ('Project Beta', 'Description for Project Beta', 1);

-- Insert sample data for ProjectMembers
INSERT INTO ProjectMembers (projectId, userId) VALUES (1, 1);
INSERT INTO ProjectMembers (projectId, userId) VALUES (1, 2);
INSERT INTO ProjectMembers (projectId, userId) VALUES (2, 3);

-- Insert sample data for Tasks
INSERT INTO Tasks (projectId, name, description, assignedTo, status, priority, dueDate) VALUES (1, 'Task 1', 'Description Task 1', 1, 1, 2, '2025-03-01');
INSERT INTO Tasks (projectId, name, description, assignedTo, status, priority, dueDate) VALUES (2, 'Task 2', 'Description Task 2', 3, 2, 3, '2025-03-10');

-- Insert sample data for TaskUpdates
INSERT INTO TaskUpdates (taskId, updatedBy, updateNotes) VALUES (1, 1, 'Initial update');
INSERT INTO TaskUpdates (taskId, updatedBy, updateNotes) VALUES (2, 3, 'Started progress');

-- Insert sample data for Comments
INSERT INTO Comments (commentTxt, memberId, taskUpdateId) VALUES ('Good progress', 1, 1);
INSERT INTO Comments (commentTxt, memberId, taskUpdateId) VALUES ('Need more details', 3, 2);

-- Insert sample data for Labels
INSERT INTO Labels (name, color) VALUES ('Urgent', '#FF0000');
INSERT INTO Labels (name, color) VALUES ('Bug', '#0000FF');

-- Insert sample data for TaskLabels
INSERT INTO TaskLabels (taskId, labelId) VALUES (1, 1);
INSERT INTO TaskLabels (taskId, labelId) VALUES (2, 2);
GO

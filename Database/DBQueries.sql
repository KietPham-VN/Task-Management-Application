use [master]
GO

CREATE DATABASE TaskManagementDB
GO

use [TaskManagementDB]
GO

CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY ,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    role VARCHAR(50) CHECK (role IN ('Project Manager', 'Team Member')) NOT NULL,
    created_at TIMESTAMP DEFAULT GETDATE()
);
GO
CREATE TABLE projects (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_by INT REFERENCES users(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT GETDATE()
);
GO

CREATE TABLE project_members (
    id INT IDENTITY(1,1) PRIMARY KEY,
    project_id INT REFERENCES projects(id) ON DELETE CASCADE,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE (project_id, user_id)
);
GO

CREATE TABLE tasks (
    id INT IDENTITY(1,1) PRIMARY KEY,
    project_id INT REFERENCES projects(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    assigned_to INT REFERENCES users(id) ON DELETE SET NULL,
    status VARCHAR(50) CHECK (status IN ('Pending', 'In Progress', 'Completed')) DEFAULT 'Pending',
    priority VARCHAR(50) CHECK (priority IN ('Low', 'Medium', 'High')) DEFAULT 'Medium',
    due_date DATE,
    created_at TIMESTAMP DEFAULT GETDATE()
);
GO

CREATE TABLE task_updates (
    id INT IDENTITY(1,1) PRIMARY KEY,
    task_id INT REFERENCES tasks(id) ON DELETE CASCADE,
    updated_by INT REFERENCES users(id) ON DELETE CASCADE,
    status VARCHAR(50) CHECK (status IN ('Pending', 'In Progress', 'Completed')),
    update_notes TEXT,
    updated_at TIMESTAMP DEFAULT GETDATE()
);
GO

CREATE TABLE activity_logs (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    action TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT GETDATE()
);
GO

CREATE TABLE labels (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    color VARCHAR(7) NOT NULL CHECK (color LIKE '^#(?:[0-9a-fA-F]{3}){1,2}$')
);
GO

CREATE TABLE task_labels (
    task_id INT REFERENCES tasks(id) ON DELETE CASCADE,
    label_id INT REFERENCES labels(id) ON DELETE CASCADE,
    PRIMARY KEY (task_id, label_id)
);
GO

CREATE TABLE tasks(
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(2000),
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    
    assignee_id BIGINT,

    due_date TIMESTAMPTZ,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_tasks_project
    FOREIGN KEY (project_id)
    REFERENCES projects(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_tasks_assignee
    FOREIGN KEY (assignee_id)
    REFERENCES users(id)
);
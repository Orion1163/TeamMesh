CREATE TABLE projects (

    id BIGSERIAL PRIMARY KEY,

    workspace_id BIGINT NOT NULL,

    name VARCHAR(150) NOT NULL,

    description VARCHAR(1000),

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_projects_workspace
        FOREIGN KEY (workspace_id)
        REFERENCES workspaces(id)
        ON DELETE CASCADE,

    CONSTRAINT uk_projects_workspace_name
        UNIQUE (workspace_id, name)
);
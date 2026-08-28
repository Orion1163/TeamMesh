CREATE TABLE workspaces (

    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    description VARCHAR(1000),

    owner_id BIGINT NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_workspace_owner
        FOREIGN KEY (owner_id)
        REFERENCES users(id)
);
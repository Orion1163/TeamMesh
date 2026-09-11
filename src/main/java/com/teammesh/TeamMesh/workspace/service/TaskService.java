package com.teammesh.TeamMesh.workspace.service;

import com.teammesh.TeamMesh.common.exception.ResourceNotFoundException;
import com.teammesh.TeamMesh.user.entity.User;
import com.teammesh.TeamMesh.user.repository.UserRepository;
import com.teammesh.TeamMesh.workspace.dto.task.request.CreateTaskRequest;
import com.teammesh.TeamMesh.workspace.dto.task.response.TaskResponse;
import com.teammesh.TeamMesh.workspace.entity.Project;
import com.teammesh.TeamMesh.workspace.model.task.Task;
import com.teammesh.TeamMesh.workspace.model.task.TaskStatus;
import com.teammesh.TeamMesh.workspace.repository.ProjectRepository;
import com.teammesh.TeamMesh.workspace.repository.TaskRepository;
import com.teammesh.TeamMesh.workspace.repository.WorkspaceMemberRepository;
import com.teammesh.TeamMesh.workspace.repository.WorkspaceRepository;

import jakarta.transaction.Transactional;

public class TaskService {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceMemberRepository workspaceMemberRepository;
    private final WorkspaceAuthorizationService workspaceAuthorizationService;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public TaskService(WorkspaceRepository workspaceRepository, UserRepository userRepository, WorkspaceMemberRepository workspaceMemberRepository, WorkspaceAuthorizationService workspaceAuthorizationService, ProjectRepository projectRepository, TaskRepository taskRepository){
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.workspaceMemberRepository = workspaceMemberRepository;
        this.workspaceAuthorizationService = workspaceAuthorizationService;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }


     @Transactional 
    public TaskResponse createTask(Long workspaceId, Long projectId, Long userId, CreateTaskRequest request){

        workspaceAuthorizationService.getMembership(workspaceId, userId);

        Project project = projectRepository.findByIdAndWorkspaceId(projectId, workspaceId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        User assignee = null;

        if(request.getAssigneeId() != null){
            assignee = userRepository.findById(request.getAssigneeId()).orElseThrow(() -> new ResourceNotFoundException("Assignee not found "));
        }

        Task task = new Task(request.getTitle(), request.getDescription(), TaskStatus.TODO, request.getPriority(), project, request.getDueDate());

        task.setAssignee(assignee);

        Task savedTask = taskRepository.save(task);

        return new TaskResponse(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(), savedTask.getStatus(), savedTask.getPriority(), projectId, assignee != null ? assignee.getId() : null, savedTask.getDueDate());
    }
}

package com.teammesh.TeamMesh.workspace.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teammesh.TeamMesh.common.exception.MemberAlreadyExistsException;
import com.teammesh.TeamMesh.common.exception.ResourceNotFoundException;
import com.teammesh.TeamMesh.common.exception.IllegalArgumentException;
import com.teammesh.TeamMesh.common.exception.InvalidOperationException;
import com.teammesh.TeamMesh.user.entity.User;
import com.teammesh.TeamMesh.user.repository.UserRepository;
import com.teammesh.TeamMesh.workspace.dto.request.AddWorkspaceMemberRequest;
import com.teammesh.TeamMesh.workspace.dto.request.CreateProjectRequest;
import com.teammesh.TeamMesh.workspace.dto.request.CreateWorkspaceRequest;
import com.teammesh.TeamMesh.workspace.dto.request.UpdateWorkspaceRequest;
import com.teammesh.TeamMesh.workspace.dto.response.ProjectResponse;
import com.teammesh.TeamMesh.workspace.dto.response.WorkspaceMemberResponse;
import com.teammesh.TeamMesh.workspace.dto.response.WorkspaceResponse;
import com.teammesh.TeamMesh.workspace.entity.Project;
import com.teammesh.TeamMesh.workspace.entity.Workspace;
import com.teammesh.TeamMesh.workspace.entity.WorkspaceMember;
import com.teammesh.TeamMesh.workspace.entity.WorkspaceRole;
import com.teammesh.TeamMesh.workspace.repository.ProjectRepository;
import com.teammesh.TeamMesh.workspace.repository.WorkspaceMemberRepository;
import com.teammesh.TeamMesh.workspace.repository.WorkspaceRepository;
import com.teammesh.TeamMesh.workspace.dto.request.UpdateWorkspaceMemberRoleRequest;

@Service
public class WorkspaceService {
    
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceMemberRepository workspaceMemberRepository;
    private final WorkspaceAuthorizationService workspaceAuthorizationService;
    private final ProjectRepository projectRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository, UserRepository userRepository, WorkspaceMemberRepository workspaceMemberRepository, WorkspaceAuthorizationService workspaceAuthorizationService, ProjectRepository projectRepository){
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.workspaceMemberRepository = workspaceMemberRepository;
        this.workspaceAuthorizationService = workspaceAuthorizationService;
        this.projectRepository = projectRepository;
    }

    public WorkspaceResponse createWorkspace(CreateWorkspaceRequest request, Long userId){

        User owner = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Workspace workspace = new Workspace(request.getName(), request.getDescription(), owner);
 
        Workspace savedWorkspace = workspaceRepository.save(workspace);

        WorkspaceMember ownerMembership = new WorkspaceMember(savedWorkspace, owner, WorkspaceRole.OWNER);

        workspaceMemberRepository.save(ownerMembership);

        return new WorkspaceResponse(savedWorkspace.getId(), savedWorkspace.getName(), savedWorkspace.getDescription(), savedWorkspace.getOwner().getId());
    }

    public List<WorkspaceResponse> getMyWorkspaces(Long userId){
        List<Workspace> workspaces = workspaceRepository.findByOwnerId(userId);

        return workspaces.stream()
                .map(workspace -> new WorkspaceResponse(workspace.getId(), workspace.getName(), workspace.getDescription(), workspace.getOwner().getId())).toList();
    }

    public WorkspaceResponse getWorkspaceById(Long workspaceId, Long userId){

        Workspace workspace = workspaceRepository.findByIdAndOwnerId(workspaceId, userId).orElseThrow(() -> new ResourceNotFoundException("Workspace not Found"));

        return new WorkspaceResponse(workspace.getId(), workspace.getName(), workspace.getDescription(), workspace.getOwner().getId());
    }

    @Transactional
    public WorkspaceResponse updateWorkspace(Long workspaceId, Long userId, UpdateWorkspaceRequest request){

        workspaceAuthorizationService.requireOwner(workspaceId, userId);

        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new ResourceNotFoundException("Workspace not Found"));

        workspace.setName(request.getName());
        workspace.setDescription(request.getDescription());

        return new WorkspaceResponse(workspace.getId(), workspace.getName(),workspace.getDescription(), workspace.getOwner().getId());
        
    }

    @Transactional
    public void deleteWorkspace(Long workspaceId, Long userId){
        Workspace workspace = workspaceRepository.findByIdAndOwnerId(workspaceId, userId).orElseThrow(() -> new ResourceNotFoundException("Workspace not Found"));

        workspaceRepository.delete(workspace);
    }

    @Transactional
    public void addMember(Long workspaceId, Long currentUserId, AddWorkspaceMemberRequest request){

        workspaceAuthorizationService.requireOwner(workspaceId, currentUserId);

        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new ResourceNotFoundException("Workspace Not Found"));

        User userToAdd = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not Found"));

        if (request.getRole() == WorkspaceRole.OWNER){
            throw new IllegalArgumentException("Owner role cannot be assigned to another member");
        }

        boolean alreadyExists = workspaceMemberRepository.existsByWorkspaceIdAndUserId(workspaceId, userToAdd.getId());

        if(alreadyExists){
            throw new MemberAlreadyExistsException("User is already a member of this workspace");
        }

        WorkspaceMember workspaceMember = new WorkspaceMember(workspace, userToAdd, request.getRole());

        workspaceMemberRepository.save(workspaceMember);
    }

    @Transactional(readOnly = true)
    public List<WorkspaceMemberResponse> getWorkspaceMembers(Long workspaceId, Long currentUserId){

        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new ResourceNotFoundException("Workspace Not Found"));

        workspaceAuthorizationService.getMembership(workspaceId, currentUserId);

        return workspaceMemberRepository.findMembersWithUserByWorkspaceId(workspace.getId()).stream().map(member -> new WorkspaceMemberResponse(member.getUser().getId(), member.getUser().getName(), member.getUser().getEmail(), member.getRole())).toList();
    }

    @Transactional
    public void removeMembership(Long workspaceId, Long currentUserId, Long userIdToRemove){
        workspaceAuthorizationService.requireOwner(workspaceId, currentUserId);

        if(currentUserId.equals(userIdToRemove)){
            throw new IllegalArgumentException("Workspace Owner cannot remove themselves");
        }

        WorkspaceMember member = workspaceAuthorizationService.getMembership(workspaceId, userIdToRemove);

        workspaceMemberRepository.delete(member);
    }

    @Transactional
    public void updateMemberRole(Long workspaceId, Long currentUserId, Long userId, UpdateWorkspaceMemberRoleRequest request){

        workspaceAuthorizationService.requireOwner(workspaceId, currentUserId);

        WorkspaceMember member = workspaceAuthorizationService.getMembership(workspaceId, userId);

        if(member.getRole() == WorkspaceRole.OWNER){
            throw new IllegalArgumentException("Workspace owner's role cannot be changed");
        }
        
        if(request.getRole() == WorkspaceRole.OWNER){
            throw new IllegalArgumentException("OWNER role cannot be assigned through this operation");
        }

        member.setRole(request.getRole());
    }

    @Transactional
    public ProjectResponse createProject(Long workspaceId, Long userId, CreateProjectRequest request){
        workspaceAuthorizationService.requireOwnerOrAdmin(workspaceId, userId);

        //find workspace
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

        //prevent duplicate project name
        boolean exists = projectRepository.existsByWorkspaceIdAndName(workspaceId, request.getName());

        if(exists){
            throw new InvalidOperationException("A project with this name already exists.");
        }

        //Create project
        Project project = new Project(request.getName(), request.getDescription(), workspace);
        
        Project savedProject = projectRepository.save(project);

        return new ProjectResponse(savedProject.getId(), savedProject.getName(), savedProject.getDescription(), workspace.getId());
    }

}

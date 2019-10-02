package io.igileintelligence.ppmtool.services;

import io.igileintelligence.ppmtool.domain.Project;
import io.igileintelligence.ppmtool.exceptions.ProjectIdException;
import io.igileintelligence.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId) {

        final Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId.toUpperCase() +
                    "' doesn't exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if (project == null) {
            throw new ProjectIdException("Cannot Project with ID '" + projectId + "'. This project doesn't exists");
        }
        projectRepository.delete(project);
    }
}

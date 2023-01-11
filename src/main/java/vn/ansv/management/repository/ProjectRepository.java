package vn.ansv.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.ansv.management.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}

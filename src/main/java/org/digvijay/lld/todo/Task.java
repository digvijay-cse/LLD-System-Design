// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : Feb 2024
package org.digvijay.lld.todo;

import java.util.*;
import java.time.*;

/**
 * Represents a task in the todo list.
 */
public class Task {
  private static int nextId = 1;

  private int id; // Unique identifier for the task
  private String description; // Description of the task
  private LocalDate deadline; // Deadline for completing the task
  private Set<String> tags; // Tags associated with the task
  private boolean completed; // Flag indicating whether the task is completed

  /**
   * Constructs a new Task.
   *
   * @param description The description of the task.
   * @param deadline    The deadline for completing the task.
   * @param tags        The tags associated with the task.
   */
  public Task(String description, LocalDate deadline, Set<String> tags) {
    this.id = nextId++;
    this.description = description;
    this.deadline = deadline;
    this.tags = tags;
    this.completed = false;
  }

  /**
   * Gets the ID of the task.
   *
   * @return The ID of the task.
   */
  public int getId() {
    return id;
  }

  /**
   * Gets the description of the task.
   *
   * @return The description of the task.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the deadline of the task.
   *
   * @return The deadline of the task.
   */
  public LocalDate getDeadline() {
    return deadline;
  }

  /**
   * Gets the tags associated with the task.
   *
   * @return The tags associated with the task.
   */
  public Set<String> getTags() {
    return tags;
  }

  /**
   * Checks if the task is completed.
   *
   * @return true if the task is completed, false otherwise.
   */
  public boolean isCompleted() {
    return completed;
  }

  /**
   * Marks the task as completed.
   */
  public void markAsCompleted() {
    completed = true;
  }

  @Override
  public String toString() {
    return "ID: " + id + ", Description: " + description + " (Deadline: " + deadline + ", Tags: " + tags + ", Completed: " + completed + ")";
  }
}
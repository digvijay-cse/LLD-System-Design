// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : Feb 2024
package org.digvijay.lld.todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a todo list that manages tasks.
 */
public class TodoList {
  private Map<Integer, Task> tasks; // Map to store tasks with their IDs as keys
  private List<String> activityLog; // List to store activity log messages

  /**
   * Constructs a new TodoList.
   */
  public TodoList() {
    tasks = new HashMap<>();
    activityLog = new ArrayList<>();
  }

  /**
   * Adds a new task to the todo list.
   *
   * @param task The task to be added.
   */
  public void addTask(Task task) {
    tasks.put(task.getId(), task);
    activityLog.add("Task added: " + task.getDescription());
  }

  /**
   * Updates an existing task in the todo list.
   *
   * @param id          The ID of the task to be updated.
   * @param updatedTask The updated task.
   */
  public void updateTask(int id, Task updatedTask) {
    if (tasks.containsKey(id)) {
      tasks.put(id, updatedTask);
      activityLog.add("Task updated: ID " + id);
    } else {
      System.out.println("Task with ID " + id + " not found.");
    }
  }

  /**
   * Removes a task from the todo list.
   *
   * @param id The ID of the task to be removed.
   */
  public void removeTask(int id) {
    if (tasks.containsKey(id)) {
      Task removedTask = tasks.remove(id);
      activityLog.add("Task removed: ID " + id);
    } else {
      System.out.println("Task with ID " + id + " not found.");
    }
  }

  /**
   * Marks a task as completed in the todo list.
   *
   * @param id The ID of the task to be marked as completed.
   */
  public void markTaskAsCompleted(int id) {
    if (tasks.containsKey(id)) {
      Task task = tasks.remove(id);
      task.markAsCompleted();
      activityLog.add("Task completed: ID " + id);
    } else {
      System.out.println("Task with ID " + id + " not found.");
    }
  }

  /**
   * Displays the activity log of the todo list.
   */
  public void displayActivityLog() {
    System.out.println("Activity Log:");
    for (String activity : activityLog) {
      System.out.println(activity);
    }
  }
}
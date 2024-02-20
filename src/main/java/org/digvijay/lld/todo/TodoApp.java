// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : Feb 2024
package org.digvijay.lld.todo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents the main application class for managing a todo list.
 */
public class TodoApp {
  public static void main(String[] args) {
    TodoList todoList = new TodoList();

    // Add sample tasks
    todoList.addTask(new Task("Complete project", LocalDate.of(2022, 3, 15), new HashSet<>(
     Arrays.asList("work", "project"))));
    todoList.addTask(new Task("Buy groceries", LocalDate.of(2022, 2, 20), new HashSet<>(Arrays.asList("home", "shopping"))));
    todoList.addTask(new Task("Call client", LocalDate.of(2022, 2, 25), new HashSet<>(Arrays.asList("work", "client"))));

    // Mark a task as completed
    todoList.markTaskAsCompleted(1);

    // Display activity log
    todoList.displayActivityLog();
  }
}


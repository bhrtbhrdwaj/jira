
Design a system like Jira. It should have the following functionalities :

1) User should be able to create Task of type Story, Feature, Bugs. Each can have their own status.
2) Stories can further have subtracts.
3) Should be able to change the status of any task.
4) User should be able to create any sprint. Should be able to add any task to sprint and remove from it.
5) User can assign task to another user

User should be able to print
6) Delayed task
7) Sprint details
8) Tasks assigned to the user



1) 	Use Case: Create Task  
      Actor: User
      Component: Task {Story, Task, Sub Task, Bug} with status

2)	Use Case: Stories can have tasks
      Actor: User
      Component: Stories, Task

3)	Use Case: Can change status of any task
      Actor: User
      Component:Task

4)	Use Case 4.1: Create Sprint
      Actor:	User
      Component: Sprint

	Use Case 4.2: Add any Task to Sprint
	Actor:	User
	Component: Task, Sprint

	Use Case 4.3: Remove any Task from Sprint
	Actor:	User
	Component: Task, Sprint

5)	Use Case 5: Assign Task to another User
      Actor:	User
      Component: Task, User

6) 	Use Case 6: Get info of delayed Task
      Actor: User
      Component: Task

7) 	Use Case 7: Get Sprint details
      Actor: User
      Component: Sprint

8) 	Use Case 7: Get Task assigned to User
      Actor: User
      Component: Task, User


Scheme Design

schema/ db name: jira

sprint
id, start_date, days(enum 7, 15), end_date(derived), created_at, updated_at

task
id, title, start_date, end_date, type {Story, Feature, Bug}[DTYPE], sprint_id, user_id,  bug_linked_to(FK issue.id), of_story(FK issue.id)

story
status{OPEN, IN_PROGRESS, RELEASED}
task/sub_task
{OPEN, IN_PROGRESS, DEV_DONE, IN_QA, QA_CLOSED, RELEASED}
bug
status{OPEN, IN_PROGRESS, DEV_DONE, CLOSED}


user
id, first_name, last_name, email(UK)


@Entity
public class Sprint {
// ...
}

@Entity
public class User {
private Integer id;
//......
@OneToMany
private List<Task> tasks;
}

@Entity(name="products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
discriminatorType = DiscriminatorType.String)
public class Task {
private Integer id;
//......
@ManyToOne
private User user;

}

@Entity
@DiscriminatorValue("STORY")
public class Story extends Task {
@OneToMany
private List<Feature> feature;

}

@Entity
@DiscriminatorValue("FEATURE")
public class Feature extends Task {
@ManyToOne
private Story story;
@OneToMany
private List<Bug> bugs;
}


@Entity
@DiscriminatorValue("BUG")
public class Bug extends Task {
@ManyToOne
private Feature feature;
}


Class Design

Sprint
public SprintResponse create(SprintRequest);
public void addTask(SprintTaskRequest);
public void removeTask(SprintTaskRequest);


Task
public TaskResponse create(TaskRequest);
public void assign(UserTaskRequest);
public IssueResponse getTaskDetails(taskId);
public boolean updateStatus(TaskStatusUpdateRequest);
public List<TaskResponse> getDelayedTask();
public List<TaskResponse> getUserTask();


Story implements Task
public void addFeature(SubTaskCreateRequest);

Feature implements Task

Bug implements Task
public void linkToATask(...);



User
public List<TaskResponse> getAssignedTasks();






























	
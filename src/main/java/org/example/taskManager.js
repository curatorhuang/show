class Task {
    constructor(description) {
        this.description = description;
        this.id = new Date().getTime(); // Generate a unique id based on timestamp
    }
}

class TaskManager {
    constructor() {
        this.tasks = this.loadTasks();
    }

    addTask(description) {
        const task = new Task(description);
        this.tasks.push(task);
        this.saveTasks();
        return task;
    }

    deleteTask(id) {
        this.tasks = this.tasks.filter(task => task.id !== id);
        this.saveTasks();
    }

    loadTasks() {
        const tasks = localStorage.getItem('tasks');
        return tasks ? JSON.parse(tasks) : [];
    }

    saveTasks() {
        localStorage.setItem('tasks', JSON.stringify(this.tasks));
    }

    getTasks() {
        return this.tasks;
    }
}

const taskManager = new TaskManager();
const taskList = document.getElementById('taskList');
const taskInput = document.getElementById('taskInput');
const addTaskBtn = document.getElementById('addTaskBtn');

function renderTasks() {
}

addTaskBtn.addEventListener('click', () => {
    const description = taskInput.value.trim();
    if (description) {
        taskManager.addTask(description);
        taskInput.value = '';
        renderTasks();
    }
});

function deleteTask(id) {
    taskManager.deleteTask(id);
    renderTasks();
}

// Initial render
renderTasks();

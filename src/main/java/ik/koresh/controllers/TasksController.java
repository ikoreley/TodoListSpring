package ik.koresh.controllers;


import ik.koresh.models.Task;
import ik.koresh.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/todolist")
public class TasksController {

    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("todolist", tasksService.findAll());
        return "todolist/index";
    }

    @GetMapping("/indexTemp")
    public String indexHeap(Model model){
        model.addAttribute("todolist", tasksService.findAll());
        return "todolist/indexTemp";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("task", tasksService.findOne(id));
        return "todolist/show";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task") Task task){
        return "todolist/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("task") @Valid Task task,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "todolist/new";

        tasksService.save(task);
        return "redirect:/todolist";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("task", tasksService.findOne(id));
        return "todolist/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("task") @Valid Task task,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "todolist/edit";

        tasksService.update(id, task);
        return "redirect:/todolist";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        tasksService.delete(id);
        return "redirect:/todolist";
    }

}

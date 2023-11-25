package ik.koresh.controllers;

import ik.koresh.dao.TaskDAO;
import ik.koresh.models.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/todolist")
public class TasksController {

    private final TaskDAO taskDAO;

    public TasksController(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("todolist", taskDAO.index());
        return "todolist/index";
    }

    @GetMapping("/indexTemp")
    public String indexHeap(Model model){
        model.addAttribute("todolist", taskDAO.index());
        return "todolist/indexTemp";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("task", taskDAO.show(id));
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

        taskDAO.save(task);
        return "redirect:/todolist";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("task", taskDAO.show(id));
        return "todolist/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("task") @Valid Task task,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "todolist/edit";

        taskDAO.update(id, task);
        return "redirect:/todolist";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        taskDAO.delete(id);
        return "redirect:/todolist";
    }

}

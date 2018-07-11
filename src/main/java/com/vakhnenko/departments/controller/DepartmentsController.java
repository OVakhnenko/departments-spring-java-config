package com.vakhnenko.departments.controller;

import com.vakhnenko.departments.entity.Department;
import com.vakhnenko.departments.entity.Employee;
import com.vakhnenko.departments.entity.User;
import com.vakhnenko.departments.service.DepartmentService;
import com.vakhnenko.departments.service.EmployeeService;
import com.vakhnenko.departments.service.SecurityService;
import com.vakhnenko.departments.service.UserService;
import com.vakhnenko.departments.validator.PasswordValidator;
import com.vakhnenko.departments.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DepartmentsController {
    private static final Map<String, String> typeEmployee;

    static {
        typeEmployee = new LinkedHashMap<>();
        typeEmployee.put("M", "Manager");
        typeEmployee.put("D", "Developer");
    }

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add/department")
    public String addDepartment(@Valid @ModelAttribute("department") Department department,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("listDepartments", this.departmentService.list());
            return "departments";
        } else {
            this.departmentService.save(department);
            return "redirect:/departments";
        }
    }

    @RequestMapping(value = "/add/department/{dID}/employee/{eID}")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee,
                              BindingResult result, Model model, @PathVariable("dID") int department_id) {

        boolean hasMethOrLangError = employee.hasMethodologyOrLanguageError();
        if (result.hasErrors() || hasMethOrLangError) {
            model.addAttribute("typeEmployee", typeEmployee);
            model.addAttribute("employee", employee);
            model.addAttribute("department", employee.getDepartment());
            model.addAttribute("listEmployees", this.employeeService.list(employee.getDepartment()));
            if (hasMethOrLangError) {
                model.addAttribute("actionError", "Select type of employee and enter methodology for manager or language for developer!");
            }
            return "employees";
        } else {
            this.employeeService.save(employee);
            return "redirect:/department/" + department_id;
        }
    }

    @RequestMapping(value = "/department/{id}")
    public String employees(@PathVariable("id") int id, Model model) {
        Department department = this.departmentService.getById(id);
        Employee employee = new Employee();
        employee.setDepartment(department);

        model.addAttribute("typeEmployee", typeEmployee);
        model.addAttribute("employee", employee);
        model.addAttribute("department", department);
        model.addAttribute("listEmployees", this.employeeService.list(department));
        return "employees";
    }

    @RequestMapping(value = "/department/{dID}/employee/{eID}", method = RequestMethod.GET)
    public String employees(@PathVariable("dID") int department_id, @PathVariable("eID") int employee_id, Model model) {
        Department department = this.departmentService.getById(department_id);
        Employee employee = this.employeeService.getById(employee_id);
        employee.setDepartment(department);

        model.addAttribute("employee", employee);
        model.addAttribute("department", department);
        return "employee";
    }

    @RequestMapping(value = "/edit/department/{id}", method = RequestMethod.GET)
    public String editDepartment(@PathVariable("id") int id, Model model) {
        model.addAttribute("department", this.departmentService.getById(id));
        model.addAttribute("listDepartments", this.departmentService.list());
        return "departments";
    }

    @RequestMapping(value = "/edit/department/{dID}/employee/{eID}", method = RequestMethod.GET)
    public String editEmployee(@PathVariable("dID") int department_id, @PathVariable("eID") int employee_id, Model model) {
        Department department = this.departmentService.getById(department_id);
        Employee employee = this.employeeService.getById(employee_id);
        employee.setDepartment(department);

        model.addAttribute("typeEmployee", typeEmployee);
        model.addAttribute("employee", employee);
        model.addAttribute("department", department);
        model.addAttribute("listEmployees", this.employeeService.list(department));
        return "employees";
    }

    @RequestMapping(value = "/remove/department/{id}", method = RequestMethod.GET)
    public String removeDepartment(@PathVariable("id") int id) {
        this.departmentService.remove(id);
        return "redirect:/departments";
    }

    @RequestMapping(value = "/remove/department/{dID}/employee/{eID}", method = RequestMethod.GET)
    public String removeEmployee(@PathVariable("eID") int employee_id, @PathVariable("dID") int department_id, Model model) {
        this.employeeService.remove(employee_id);

        Department department = this.departmentService.getById(department_id);
        Employee employee = new Employee();
        employee.setDepartment(department);

        model.addAttribute("typeEmployee", typeEmployee);
        model.addAttribute("employee", employee);
        model.addAttribute("department", department);
        model.addAttribute("listEmployees", this.employeeService.list(department));
        return "redirect:/department/" + department_id;
    }

    @RequestMapping(value = "/delete/all", method = RequestMethod.POST)
    public String deleteAll(ModelMap model) {
        this.departmentService.deleteAll();
        model.addAttribute("department", new Department());
        model.addAttribute("actionMessage", "All data is deleted.");
        return "departments";
    }

    @RequestMapping(value = "/demonstration", method = RequestMethod.POST)
    public String Demonstration(ModelMap model) {
        model.addAttribute("listDepartments", this.departmentService.fillDemoData());
        model.addAttribute("department", new Department());
        model.addAttribute("actionMessage", "Demonstartion data is filled.");
        return "departments";
    }

    @RequestMapping(value = "/cancel/department/{dID}/employee", method = RequestMethod.POST)
    public String cancelEmployee(@PathVariable("dID") int department_id, ModelMap model) {
        return "redirect:/department/" + department_id;
    }

    @RequestMapping(value = "/report/all")
    public String reportAll(Model model) {
        List<Department> departments = this.departmentService.list();
        model.addAttribute("listEmployees", this.employeeService.list(departments));
        return "all";
    }

    @RequestMapping(value = "/report/age")
    public String reportAge(Model model) {
        Department department = new Department();
        Employee employee = new Employee();
        employee.setDepartment(department);

        model.addAttribute("typeEmployee", typeEmployee);
        model.addAttribute("mapDepartments", this.departmentService.map());
        model.addAttribute("employee", employee);
        model.addAttribute("listEmployees", null);
        return "age";
    }

    @RequestMapping(value = "/report/age/form")
    public String reportAgeForm(@ModelAttribute("employee") Employee employee, Model model) {
        employee.setDepartment(this.departmentService.getById(employee.getDepartment().getDepartment_id()));
        model.addAttribute("listEmployees", this.employeeService.list(employee));
        model.addAttribute("typeEmployee", typeEmployee);
        model.addAttribute("mapDepartments", this.departmentService.map());
        model.addAttribute("employee", employee);
        return "age";
    }

    @RequestMapping(value = "/report/top")
    public String reportTop(Model model) {
        Department department = new Department();
        Employee employee = new Employee();
        employee.setDepartment(department);

        model.addAttribute("typeEmployee", typeEmployee);
        model.addAttribute("employee", employee);
        model.addAttribute("topEmployees", "<empty>");
        return "top";
    }

    @RequestMapping(value = "/report/top/form")
    public String reportTopForm(@ModelAttribute("employee") Employee employee, Model model) {
        int tmpEmployees;
        int maxEmployees = 0;
        String resultEmployees = "";
        Department topDepartment = null;

        List<Department> departments = this.departmentService.list();
        for (Department department : departments) {
            employee.setDepartment(department);
            tmpEmployees = this.employeeService.getCountOfEmployees(employee);
            if (tmpEmployees > maxEmployees) {
                maxEmployees = tmpEmployees;
                topDepartment = department;
            }
        }

        if (maxEmployees > 0) {
            resultEmployees = "Department \"" + topDepartment.getName() + "\" has " +
                    maxEmployees + " " + employee.typeString() + "('s)";
        } else {
            resultEmployees = "<empty>";
        }

        model.addAttribute("typeEmployee", typeEmployee);
        model.addAttribute("employee", employee);
        model.addAttribute("topEmployees", resultEmployees);

        return "top";
    }

    @RequestMapping(value = "/cancel/department", method = RequestMethod.POST)
    public String cancelDepartment(ModelMap model) {
        return "redirect:/departments";
    }

    @RequestMapping(value = "/departments")
    public String departments1(ModelMap model) {
        return departments(model);
    }

    @RequestMapping(value = "/departments_forward")
    public String departments21(ModelMap model) {
        return "forward:/departments";
    }

    @RequestMapping(value = "/public")
    public String publicPage(Model model) {
        return "public";
    }

    @RequestMapping(value = "/authorized/user", method = RequestMethod.GET)
    public String userPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        User user = new User();
        user.setUsername(userName);
        model.addAttribute("loggedUser", user);
        if ("user".equals(userName) || "admin".equals(userName)) {
            model.addAttribute("noChangeMessage", "You can't change the password for admin & user!");
        }
        return "authorized.user";
    }

    @RequestMapping(value = "/authorized/user", method = RequestMethod.POST)
    public String userPage(@ModelAttribute("loggedUser") User loggedUser, BindingResult bindingResult,
                           HttpServletRequest request, HttpServletResponse response, Model model) {
        passwordValidator.validate(loggedUser, bindingResult);

        if (bindingResult.hasErrors()) {
            return "authorized.user";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            String userName = auth.getName();
            User savedUser = userService.findByUsername(userName);

            loggedUser.setId(savedUser.getId());
            loggedUser.setUsername(savedUser.getUsername());
            loggedUser.setRoles(savedUser.getRoles());

            new SecurityContextLogoutHandler().logout(request, response, auth);
            userService.save(loggedUser);
            securityService.autoLogin(loggedUser.getUsername(), loggedUser.getConfirmPassword());

            model.addAttribute("loggedUser", loggedUser);
        }
        return "authorized.user.password.changed";
    }

    @RequestMapping(value = "/authorized/admin")
    public String adminPage(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());

        return "authorized.admin";
    }

    @RequestMapping(value = "/authorized/admin/change/user/{userId}", method = RequestMethod.GET)
    public String changeUser(@PathVariable("userId") long userId, Model model) {

        User user = userService.getOne(userId);
        user.setPassword("");
        user.setConfirmPassword("");
        model.addAttribute("user", user);
        String userName = user.getUsername();

        if ("user".equals(userName) || "admin".equals(userName)) {
            model.addAttribute("noChangeMessage", "You can't change the password for admin & user!");
        }
        return "authorized.admin.change.user";
    }

    @RequestMapping(value = "/authorized/admin/change/user", method = RequestMethod.POST)
    public String changeUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        passwordValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "authorized.admin.change.user";
        }
        User editedUser = userService.findByUsername(user.getUsername());
        editedUser.setPassword(user.getConfirmPassword());
        userService.save(editedUser);

        model.addAttribute("ChangedMessage",
                "Password for " + user.getUsername() + " changed successfully");
        return "forward:/authorized/admin";
    }

    @RequestMapping(value = "/authorized/admin/delete/user/{id}", method = RequestMethod.GET)
    public String removeUser(@PathVariable("id") long id, Model model) {
        User user = userService.getOne(id);
        String userName = user.getUsername();

        if ("admin".equals(userName) || "user".equals(userName)) {
            model.addAttribute("noDeleteMessage", "You can't delete admin & user!");
            return "forward:/authorized/admin";
        } else {
            userService.delete(id);
            model.addAttribute("DeletedMessage", "User " + userName + " deleted successfully");
            return "redirect:/authorized/admin";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
                model.addAttribute("message", "Logged out successfully.");
                return "departments";
            }
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/departments";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/departments";
    }

    private String departments(ModelMap model) {
        model.addAttribute("listDepartments", this.departmentService.list());
        model.addAttribute("department", new Department());
        return "departments";
    }
}
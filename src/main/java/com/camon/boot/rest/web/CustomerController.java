package com.camon.boot.rest.web;

import com.camon.boot.rest.domain.Customer;
import com.camon.boot.rest.service.CustomerService;
import com.camon.boot.rest.service.LoginUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by jooyong on 2015-11-12.
 */
@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(method = GET)
    String list(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers/list";
    }

    @ModelAttribute
    CustomerForm setUpForm() {
        return new CustomerForm();
    }

    @RequestMapping(value = "create", method = POST)
    String create(@Validated CustomerForm form, BindingResult result, Model model,
                  @AuthenticationPrincipal LoginUserDetails userDetails) {
        if (result.hasErrors()) {
            return list(model);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customerService.create(customer, userDetails.getUser());
        return "redirect:/customers";
    }

    @RequestMapping(value = "edit", params = "form", method = GET)
    String editForm(@RequestParam Integer id, CustomerForm form) {
        Customer customer = customerService.findOne(id);
        BeanUtils.copyProperties(customer, form);
        return "customers/edit";
    }

    @RequestMapping(value = "edit", method = POST)
    String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result,
                @AuthenticationPrincipal LoginUserDetails userDetails) {
        if (result.hasErrors()) {
            return editForm(id, form);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customer.setId(id);
        customerService.update(customer, userDetails.getUser());
        return "redirect:/customers";
    }

    @RequestMapping(value = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/customers";
    }

    @RequestMapping(value = "delete", method = POST)
    String edit(@RequestParam Integer id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}

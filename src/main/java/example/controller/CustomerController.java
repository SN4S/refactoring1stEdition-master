package example.controller;

import example.model.Customer;
import example.service.StatementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final StatementService statementService;

    public CustomerController(StatementService statementService) {
        this.statementService = statementService;
    }

    @PostMapping("/statement")
    public String getStatement(@RequestBody Customer customer) {
        return statementService.generateStatement(customer);
    }
}

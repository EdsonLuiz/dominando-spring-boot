package com.edsonluiz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"v1/customers", "v1/customers/"})
public class CustomerController {
    public static final List<String> NAMES = List.of("Edson", "Luiz", "Ribeiro", "Rodrigues", "Juquinha");
    @GetMapping
    public List<String> list() {
        return NAMES;
    }
    //    http://localhost:8080/v1/customers/filter?name=Edson
    @GetMapping("filter")
    public List<String> filter(@RequestParam String name) {
        return NAMES.stream().filter(n -> n.equalsIgnoreCase(name)).toList();
    }
//    http://localhost:8080/v1/customers/filterDefault?name=Edson
//    http://localhost:8080/v1/customers/filterDefault
    @GetMapping("filterDefault")
    public List<String> filterDefault(@RequestParam(defaultValue = "") String name) {
        return NAMES.stream().filter(n -> n.equalsIgnoreCase(name)).toList();
    }
    //    http://localhost:8080/v1/customers/filterNotRequired
    @GetMapping("filterNotRequired")
    public List<String> filterNotRequired(@RequestParam(required = false) String name) {
        return NAMES.stream().filter(n -> n.equalsIgnoreCase(name)).toList();
    }
    //    http://localhost:8080/v1/customers/filterOptional
    @GetMapping("filterOptional")
    public List<String> filterOptional(@RequestParam Optional<String> name) {
        return NAMES.stream().filter(n -> n.equalsIgnoreCase(name.orElse(""))).toList();
    }
    //    http://localhost:8080/v1/customers/filterList?names=Edson,Rodrigues
    //    http://localhost:8080/v1/customers/filterList?names=Edson&names=Rodrigues
    @GetMapping("filterList")
    public List<String> filterOptional(@RequestParam List<String> names) {
        return NAMES.stream().filter(names::contains).toList();
    }
}

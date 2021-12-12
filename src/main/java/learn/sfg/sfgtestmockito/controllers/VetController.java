package learn.sfg.sfgtestmockito.controllers;

import learn.sfg.sfgtestmockito.fauxspring.Model;
import learn.sfg.sfgtestmockito.services.VetService;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}

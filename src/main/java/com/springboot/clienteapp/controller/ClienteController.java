package com.springboot.clienteapp.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.springboot.clienteapp.entity.Ciudad;
import com.springboot.clienteapp.entity.Cliente;
import com.springboot.clienteapp.service.ICiudadService;
import com.springboot.clienteapp.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("views/clientes")
class ClienteController {
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private ICiudadService ciudadService;


    @GetMapping("/")
    public String listarClientes(Model model){
        List<Cliente> listClients = clienteService.listartodos();

        model.addAttribute("titulo","Listado de Clientes");
        model.addAttribute("clientes",listClients);
     return "views/clientes/listar";
    }


    @RequestMapping("/create")
    public String crear(Model model){
        Cliente c = new Cliente();
        List<Ciudad> listCiudades = ciudadService.listaCiudades();

        model.addAttribute("titulo","Nuevo Cliente");
        model.addAttribute("cliente",c);
        model.addAttribute("ciudades" ,listCiudades);

        return "/views/clientes/create";
    }

    @PostMapping("/save")
    public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result,
                          Model model, RedirectAttributes redirectAttributes){
        List<Ciudad> listCiudades = ciudadService.listaCiudades();
        if (result.hasErrors()){
            model.addAttribute("titulo","Nuevo Cliente");
            model.addAttribute("cliente",cliente);
            model.addAttribute("ciudades" ,listCiudades);
            return "/views/clientes/create";
        }
        clienteService.guardar(cliente);
        redirectAttributes.addFlashAttribute("success",
                "Cliente guardado con exito!!");

        return "redirect:/views/clientes/";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Long id, Model model,
                         RedirectAttributes redirectAttributes){
        Cliente cliente;
        if (id >0){
             cliente = clienteService.buscarporId(id);
             if(cliente==null){
                 redirectAttributes.addFlashAttribute("error",
                         "ID cliente no encontrado");
                 return "redirect:/views/clientes/";
             }
        }else{
            redirectAttributes.addFlashAttribute("error",
                    "Problemas con el ID del cliente");

            return "redirect:/views/clientes/";
        }
        System.out.println(cliente.toString());

        List<Ciudad> listCiudades = ciudadService.listaCiudades();
        model.addAttribute("titulo","Editar Cliente");
        model.addAttribute("cliente",cliente);
        model.addAttribute("ciudades" ,listCiudades);
        redirectAttributes.addFlashAttribute("success",
                "Registrol actualizado con exito");

        return "/views/clientes/create";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        Cliente cliente;
        if (id >0){
            cliente = clienteService.buscarporId(id);
            if(cliente==null){
                redirectAttributes.addFlashAttribute("error",
                        "ID cliente no encontrado");
                return "redirect:/views/clientes/";
            }
        }else{
            redirectAttributes.addFlashAttribute("error",
                    "Problemas con el ID del cliente");
            return "redirect:/views/clientes/";
        }
        clienteService.eliminar(id);
        redirectAttributes.addFlashAttribute("success",
                "Registrol eliminado con exito");
        return "redirect:/views/clientes/";
    }

    @GetMapping("/export")
    public void exportCSV(HttpServletResponse response) throws Exception {

        //set file name and content type
        String filename = "clientes.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<Cliente> writer = new StatefulBeanToCsvBuilder<Cliente>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write(clienteService.listartodos());

    }

}


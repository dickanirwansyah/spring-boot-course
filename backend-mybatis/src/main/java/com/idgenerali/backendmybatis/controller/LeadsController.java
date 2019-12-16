package com.idgenerali.backendmybatis.controller;

import com.idgenerali.backendmybatis.model.HandleLeadsStatus;
import com.idgenerali.backendmybatis.model.Leads;
import com.idgenerali.backendmybatis.service.LeadsService;
import com.idgenerali.backendmybatis.util.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/leads")
public class LeadsController {

    @Autowired
    private LeadsService leadsService;

    @GetMapping(value = "")
    public ResponseEntity<ResponseApi<ArrayList<Leads>>> listLeads(){
        ResponseApi responseApi = new ResponseApi();
        ArrayList<Leads> leads = leadsService.listLeads();
        responseApi.setStatus(HttpStatus.OK.value());
        responseApi.setTimestamp(new Date());
        responseApi.setMessages("success");
        responseApi.setData(leads);
        return ResponseEntity.ok().body(responseApi);
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<ResponseApi<ArrayList<HandleLeadsStatus>>> listLeadsStatus(){
        ResponseApi responseApi = new ResponseApi();
        ArrayList<HandleLeadsStatus> handleLeadsStatuses = leadsService.handleListLeadsStatus();
        responseApi.setStatus(HttpStatus.OK.value());
        responseApi.setTimestamp(new Date());
        responseApi.setMessages("success");
        responseApi.setData(handleLeadsStatuses);
        return ResponseEntity.ok().body(responseApi);
    }

    @GetMapping(value = "/get-leads/{id}")
    public ResponseEntity<ResponseApi<HandleLeadsStatus>> getLeadsStatusId(@PathVariable("id")int id){
        ResponseApi responseApi = new ResponseApi();
        HandleLeadsStatus handleLeadsStatus = leadsService.handleLeadsStatusById(id);
        responseApi.setData(handleLeadsStatus);
        responseApi.setMessages("success");
        responseApi.setTimestamp(new Date());
        responseApi.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(responseApi);
    }

    @GetMapping(value = "/leads-id/{id}")
    public ResponseEntity<ResponseApi<Leads>> getLeadsId(@PathVariable("id")int id){
        Leads leads = leadsService.findById(id);
        ResponseApi responseApi = new ResponseApi();
        responseApi.setData(leads);
        responseApi.setMessages("success");
        responseApi.setTimestamp(new Date());
        responseApi.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(responseApi);
    }

    @PostMapping(value = "/handle-save")
    public ResponseEntity<ResponseApi<Boolean>> createLeads(@RequestBody @Valid Leads leads,
                                                            BindingResult bindingResult){

        ResponseApi responseApi = null;
        boolean valid = false;
        if (bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                responseApi = new ResponseApi();
                responseApi.setTimestamp(new Date());
                responseApi.setStatus(HttpStatus.BAD_REQUEST.value());
                responseApi.setMessages(fieldError.getDefaultMessage());
                responseApi.setData(null);
            }
            return ResponseEntity.badRequest().body(responseApi);
        }


        if (leads.getId() == -1){
            valid = leadsService.saveLeads(leads);
            if (valid == true){
                responseApi = new ResponseApi();
                responseApi.setTimestamp(new Date());
                responseApi.setStatus(HttpStatus.OK.value());
                responseApi.setMessages("success");
                responseApi.setData(valid);
            }
        }else{
            valid = leadsService.updateLeads(leads);
            if (valid == true){
                responseApi = new ResponseApi();
                responseApi.setTimestamp(new Date());
                responseApi.setStatus(HttpStatus.OK.value());
                responseApi.setMessages("success");
                responseApi.setData(valid);
            }
        }

        return ResponseEntity.ok().body(responseApi);
    }
}

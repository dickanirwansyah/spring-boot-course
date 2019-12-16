package com.idgenerali.backendmybatis.controller;

import com.idgenerali.backendmybatis.model.Agent;
import com.idgenerali.backendmybatis.service.AgentService;
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
@RequestMapping(value = "/api/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;


    @GetMapping(value = "")
    public ResponseEntity<ResponseApi<ArrayList<Agent>>> listAllAgent(){
        ArrayList<Agent> agents = agentService.listAllAgent();
        ResponseApi apiResponse = null;
        if (agents.isEmpty()){
            apiResponse = responseApi(agents, HttpStatus.BAD_REQUEST.value(), "not found");
        }else{
            apiResponse = responseApi(agents, HttpStatus.OK.value(), "success");
        }
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseApi<Boolean>> createAgent(@RequestBody @Valid Agent agent,
                                                            BindingResult bindingResult){
        ResponseApi executeResponse = null;
        if (bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                executeResponse = responseApi(false, HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(executeResponse);
        }

        boolean valid = agentService.saveAgent(agent);
        if (valid == true){
            executeResponse = responseApi(valid, HttpStatus.OK.value(), "success");
        }
        return ResponseEntity.ok().body(executeResponse);
    }

    @PostMapping(value = "/update/{agentCode}")
    public ResponseEntity<ResponseApi<Boolean>> updateAgent(@PathVariable("agentCode")String agentCode,
                                                            @RequestBody @Valid Agent agent,
                                                            BindingResult bindingResult){
        ResponseApi executeResponse = null;
        if (bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                executeResponse = responseApi(false, HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(executeResponse);
        }

        agent.setAgentCode(agentCode);
        boolean valid = agentService.updateAgent(agent);
        if (valid == true){
            executeResponse = responseApi(valid, HttpStatus.OK.value(), "success");
        }
        return ResponseEntity.ok().body(executeResponse);
    }

    @PostMapping(value = "/get/{agentCode}")
    public ResponseEntity<ResponseApi<Agent>> getAgentCode(@PathVariable("agentCode")String agentCode){
        Agent agent = agentService.findByAgentCode(agentCode);
        ResponseApi executeResponse = responseApi(agent, HttpStatus.OK.value(), "success");
        return ResponseEntity.ok().body(executeResponse);
    }

    @PostMapping(value = "/agent-status/true")
    public ResponseEntity<ResponseApi<ArrayList<Agent>>> getAgentByStatusTrue(){
        ArrayList<Agent> agents = agentService.listAgentByStatusTrue();
        ResponseApi executeResponse = responseApi(agents, HttpStatus.OK.value(), "success");
        return ResponseEntity.ok().body(executeResponse);
    }

    @PostMapping(value = "/agent-status/false")
    public ResponseEntity<ResponseApi<ArrayList<Agent>>> getAgentByStatusFalse(){
        ArrayList<Agent> agents = agentService.listAgentByStatusFalse();
        ResponseApi executeResponse = responseApi(agents, HttpStatus.OK.value(), "success");
        return ResponseEntity.ok().body(executeResponse);
    }

    private ResponseApi responseApi(Object data, int status, String message){
        ResponseApi responseApi = null;
        switch (status){
            case 200 :
                responseApi = new ResponseApi();
                responseApi.setTimestamp(new Date());
                responseApi.setMessages(message);
                responseApi.setStatus(HttpStatus.OK.value());
                responseApi.setData(data);
                break;
            case 400:
                responseApi = new ResponseApi();
                responseApi.setTimestamp(new Date());
                responseApi.setMessages(message);
                responseApi.setStatus(HttpStatus.BAD_REQUEST.value());
                responseApi.setData(data);
                break;
            default:return null;
        }
        return responseApi;
    }
}

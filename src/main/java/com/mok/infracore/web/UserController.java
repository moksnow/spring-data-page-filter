package com.mok.infracore.web;

import com.google.gson.Gson;
import com.mok.infracore.domain.User;
import com.mok.infracore.domain.pagination.DataTableRequest;
import com.mok.infracore.domain.pagination.DataTableResults;
import com.mok.infracore.domain.pagination.PaginationCriteria;
import com.mok.infracore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/datatable"})
    public String welcome(Map<String, Object> model) {
        return "datatable";
    }

    @RequestMapping(value = "/select_all_users", method = RequestMethod.GET)
    @ResponseBody
    public String selectAllUsers(HttpServletRequest request) {

        DataTableRequest<User> dataTableInRQ = new DataTableRequest<User>(request);
        PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();

        Page<User> allPagination = userService.findAll(pagination, (dataTableInRQ.getStart()) / dataTableInRQ.getLength());

        DataTableResults<User> dataTableResult = new DataTableResults<User>();

        dataTableResult.setDraw(dataTableInRQ.getDraw());
        dataTableResult.setListOfDataObjects(allPagination.getContent());
        dataTableResult.setRecordsFiltered(String.valueOf(allPagination.getTotalElements()));
        dataTableResult.setRecordsTotal(String.valueOf(allPagination.getTotalElements()));


        return new Gson().toJson(dataTableResult);
    }
}

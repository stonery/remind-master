package com.remind.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品专题Controller
 * Created by macro on 2018/6/1.
 */
@Controller
@Api(tags = "UserContentController", description = "商品专题管理")
@RequestMapping("/userContent")
public class UserContentController {

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public Object listAll() {

        return "全部用户文章";
    }

    @ApiOperation(value = "根据专题名称分页获取专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return "分页";
    }
}

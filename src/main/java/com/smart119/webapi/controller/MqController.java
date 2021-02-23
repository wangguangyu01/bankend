package com.smart119.webapi.controller;

import com.smart119.common.utils.R;
import com.smart119.system.mq.RabbitMQClient;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *变成编队管理接
 *
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-28 12:05:01
 */
@Api(value = "消息推送管理", description = "界顶端消息推送管理API")
@Controller
@RequestMapping("/webapi/mq")
public class MqController {

    @Autowired
    private RabbitMQClient rabbitMQClient;

    @ApiOperation("消息推送到queue")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queueName", value = "队列名称", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "message", value = "消息内容", required = true, dataType = "String",paramType = "header")
    })
    @ResponseBody
    @PostMapping("/sendMessageToQueue")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "消息推送成功",response= R.class)})
    public R sendMessageToQueue(String queueName,String message){
        rabbitMQClient.sendMessageToQueue(queueName,message);
        return R.ok();
    }

    @ApiOperation("消息推送到广播exchange")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "exchangeName", value = "路由名称", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "message", value = "消息内容", required = true, dataType = "String",paramType = "header")
    })
    @ResponseBody
    @PostMapping("/sendMessageToExchange")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "消息推送成功",response= R.class)})
    public R sendMessageToExchange(String exchangeName,String message){
        rabbitMQClient.sendMessageToExchange(exchangeName,message);
        return R.ok();
    }


}

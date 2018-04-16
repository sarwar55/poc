package com.jackson.workfront.rest;

import com.jackson.workfront.domain.ConfigBean;
import com.jackson.workfront.services.ConfigBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sarwar on 4/14/18.
 */

@RestController
@RequestMapping("/rest/configbeans/api/v1")
public class ConfigBeanRestController {

    private ConfigBeanService configBeanService;

    @Autowired
    public ConfigBeanRestController(ConfigBeanService configBeanService) {
        this.configBeanService = configBeanService;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Iterable<ConfigBean>> getAllBeans(){
        Iterable<ConfigBean> configBeans = configBeanService.getAllConfigBeans();
        return new ResponseEntity<>(configBeans,HttpStatus.OK);
    }

    @GetMapping(value = "/{configBeanName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ConfigBean> getConfigBean(@PathVariable("configBeanName") String configBeanName){
        ConfigBean configBean = null;
        configBean = this.configBeanService.getConfigBeanByName(configBeanName);
        if (configBean == null) {
            return new ResponseEntity<ConfigBean>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ConfigBean>(configBean, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ConfigBean> addConfigBean(@RequestBody ConfigBean configBean){
        if(this.configBeanService.getConfigBeanByName(configBean.getBeanName()) != null){
            this.configBeanService.saveConfigBean(configBean);
            return new ResponseEntity<ConfigBean>(configBean, HttpStatus.CREATED);
        } else return new ResponseEntity<ConfigBean>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/{configBeanName}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ConfigBean> updateConfigBean(@PathVariable("configBeanName") String configBeanName, @RequestBody ConfigBean configBean){
        ConfigBean currentConfigBean = this.configBeanService.getConfigBeanByName(configBeanName);
        if (currentConfigBean == null) {
            return new ResponseEntity<ConfigBean>(HttpStatus.NOT_FOUND);
        }
        currentConfigBean.setQuery(configBean.getQuery());
        currentConfigBean.setRefreshRate(configBean.getRefreshRate());
        currentConfigBean.setStartDate(configBean.getStartDate());
        this.configBeanService.saveConfigBean(currentConfigBean);
        return new ResponseEntity<ConfigBean>(currentConfigBean, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{configBeanName}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResponseEntity<Void> deleteConfigBean(@PathVariable("configBeanName") String configBeanName) {
        ConfigBean configBean = this.configBeanService.getConfigBeanByName(configBeanName);
        if (configBean == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.configBeanService.deleteConfigBean(configBean);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}

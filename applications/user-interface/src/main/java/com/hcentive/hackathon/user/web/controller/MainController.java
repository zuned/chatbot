/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.user.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Anubhav.Kapoor
 *
 */
@Controller
@RequestMapping("/")
public class MainController {
 
      @RequestMapping(method = RequestMethod.GET)
        public String getIndexPage(Principal user) {
//    	  if(user == null)
            return "index";
//    	  return "redirect:/user";
        }
 
}

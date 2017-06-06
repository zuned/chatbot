/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.user.web.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anubhav.Kapoor
 *
 */
@RequestMapping("/user")
@RestController
public class UserController {

	 @RequestMapping(method = RequestMethod.GET)
     public Object getAuthenticatedUser(Principal user) {
         return user;
     }
}

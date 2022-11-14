/**
 * @author Feng Wang
 *         Security check for get request, error page redirection, initial
 *         version of itemController
 * 
 * @author Alex Sobieraj
 * 
 *         Created Account, AdminHome, and Login Controllers
 * 
 *         Learned how to validate our Objects with the @Valid attribute, and
 *         how to check for
 *         the errors in a bindingresult
 * 
 *         Learned you can attach a request mapping on top of the class
 *         - we originally were incorporating a User and an Admin Account type.
 *         We were using
 *         different Controller classes to handle /admin and /user. We scrapped
 *         this idea because the
 *         only functionality a User had was to search for items, which you can
 *         do with an account
 *         in the Shop app
 * 
 * 
 */

package se452.project.grocery.controllers;
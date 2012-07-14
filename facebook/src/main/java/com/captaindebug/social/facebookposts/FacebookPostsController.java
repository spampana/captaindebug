/**
 * Copyright 2012 Marin Solutions
 */
package com.captaindebug.social.facebookposts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Roger
 * 
 */
@Controller
public class FacebookPostsController {

	private static final Logger logger = LoggerFactory.getLogger(FacebookPostsController.class);

	private final Facebook facebook;

	@Autowired
	public FacebookPostsController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "posts", method = RequestMethod.GET)
	public String showPostsForUser(@RequestParam("id") String userId, Model model) {

		/*
		 * Logic goes something like this
		 * 
		 * if parameter == nil then sign in to get Access Token else already
		 * have access token
		 * 
		 * Use access token to get Facebook data.
		 */

		FeedOperations feedOps = facebook.feedOperations();

		List<Post> posts = feedOps.getPosts();
		logger.debug("Retrieved " + posts.size() + " from the Facebook authenticated user");

		model.addAttribute("posts", posts);
		return "home";
	}

}

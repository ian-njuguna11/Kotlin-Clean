package com.paul.post

import com.paul.AlreadyExistsException
import com.paul.entity.Post
import com.paul.repos.IPostRepository
import java.util.logging.Logger

class CreatePost (
    var post: Post,
    var postRepo: IPostRepository<Post, Int>
){

    var LOG = Logger.getLogger(CreatePost::class.java.name)

    fun execute(): Post
    {
        if (existingPostWithName()) {
            LOG.warning("Post With the name ${post.name} already exists")
            throw AlreadyExistsException("Post With a Similar Name already exists")
        }

        val createdPost = postRepo.create(post)
        LOG.info("created post ${post.toString()}")
        return createdPost as Post
    }

    private fun existingPostWithName(): Boolean
    {
        // checks if there is already a post in the system with a similar name. Since name needs to be unique
        val allPosts = postRepo.findAll()
        var result = false
        loop@ for (p in allPosts){
            if (post.name == p.name){
                result = true
                break@loop
            }
        }
        return result
    }

}
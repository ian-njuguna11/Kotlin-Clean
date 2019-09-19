package com.paul

import com.paul.entity.Post
import com.paul.post.CreatePost
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CreatePostTest{

    var TPostRepo: TPostRepositoryT = TPostRepositoryT()

    @Before
    fun setUp(){
        TPostRepo = TPostRepositoryT()
    }

    @Test
    fun testCreatePost(){
        val post = Post(topic = "post", name = "post")
        val expectedCreatedPost = Post(topic = "post", name = "post", id = 1)
        val createPost = CreatePost(post, TPostRepo)
        val createdPost = createPost.execute()
        assertEquals(createdPost, expectedCreatedPost)
    }

    @Test(expected = AlreadyExistsException::class)
    fun testDuplicatePostCreation(){
        val postOriginal = Post(name = "presidency")
        val postDuplicate = Post(name = "presidency")
        val createOriginalPost = CreatePost(postOriginal, TPostRepo)
        createOriginalPost.execute()
        val createDuplicatePost = CreatePost(postDuplicate, TPostRepo)
        createDuplicatePost.execute()
    }

}
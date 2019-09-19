package com.paul

import com.paul.entity.Post
import com.paul.repos.IPostRepository


class TPostRepositoryT : TBaseRepository<Post, Int>(), IPostRepository<Post, Int>
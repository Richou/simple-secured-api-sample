package com.heanoria.reminders.simplesecuredapisample.persistence.repositories

import com.heanoria.reminders.simplesecuredapisample.persistence.entities.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface ArticleRepository : JpaRepository<ArticleEntity, UUID>
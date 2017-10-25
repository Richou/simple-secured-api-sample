package com.heanoria.reminders.simplesecuredapisample.mappers

import com.heanoria.reminders.simplesecuredapisample.dto.Category
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.CategoryEntity


class CategoryEntityToCategoryMapper {
    fun map(category: CategoryEntity) = Category(category.id, category.key)
}
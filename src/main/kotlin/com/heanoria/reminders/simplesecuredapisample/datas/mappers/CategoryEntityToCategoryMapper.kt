package com.heanoria.reminders.simplesecuredapisample.datas.mappers

import com.heanoria.reminders.simplesecuredapisample.datas.dto.Category
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.CategoryEntity


class CategoryEntityToCategoryMapper {
    fun apply(category: CategoryEntity) = Category(category.id, category.key)
}
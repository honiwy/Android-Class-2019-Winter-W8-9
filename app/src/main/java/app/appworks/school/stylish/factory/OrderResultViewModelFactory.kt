package app.appworks.school.stylish.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.appworks.school.stylish.comment.CommentViewModel
import app.appworks.school.stylish.data.OrderResult
import app.appworks.school.stylish.data.source.StylishRepository

@Suppress("UNCHECKED_CAST")
class OrderResultViewModelFactory(
    private val stylishRepository: StylishRepository,
    private val orderResult: OrderResult
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {

                isAssignableFrom(CommentViewModel::class.java) ->
                    CommentViewModel(stylishRepository, orderResult)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
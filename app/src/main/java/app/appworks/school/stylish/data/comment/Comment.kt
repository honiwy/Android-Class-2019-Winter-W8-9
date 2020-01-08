package app.appworks.school.stylish.data.comment

data class Comment(
    val userId: Int,
    val commentId: Int,
    var star: Int,
    var comment: String
)
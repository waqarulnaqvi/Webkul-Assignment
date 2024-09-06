package com.mysteriouscoder.mobikulassignment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import androidx.navigation.NavHostController
import com.mysteriouscoder.mobikulassignment.data.Comment
import com.mysteriouscoder.mobikulassignment.data.Post
import com.mysteriouscoder.mobikulassignment.data.User
import com.mysteriouscoder.mobikulassignment.navigation.Navigationitems
import com.mysteriouscoder.mobikulassignment.ui.theme.BabyBlue
import com.mysteriouscoder.mobikulassignment.ui.theme.LightGreen
import com.mysteriouscoder.mobikulassignment.ui.theme.RedOrange
import com.mysteriouscoder.mobikulassignment.ui.theme.RedPink
import com.mysteriouscoder.mobikulassignment.ui.theme.Violet


@Composable
fun Heading(
    modifier: Modifier = Modifier,
    title: String = "",
    fontSize: Int = 22,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Text(
        text = title,
        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
        fontSize = fontSize.sp,
        modifier = modifier
            .then(modifier)
            .padding(start = 10.dp),
        textAlign = textAlign,
        color = color,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )

}



@Composable
fun UserItem(
    user: User,
    color: Color,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onclick: () -> Unit={},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .border(
                        border = BorderStroke(
                            3.dp,
                            MaterialTheme.colorScheme.onSurface
                        ),
                        shape = CircleShape
                    )

            )

            Divider(
                modifier = Modifier.width(6.dp),
                color = MaterialTheme.colorScheme.onSurface
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .weight(0.9f)
                        .clickable {
                            onclick()
                        }
                )
                {
                    Canvas(modifier = Modifier.matchParentSize()) {
                        val clipPath = Path().apply {
                            lineTo(size.width - cutCornerSize.toPx(), 0f)
                            lineTo(size.width, cutCornerSize.toPx())
                            lineTo(size.width, size.height)
                            lineTo(0f, size.height)
                            close()
                        }

                        clipPath(clipPath) {
                            drawRoundRect(
                                color = color,
                                size = size,
                                cornerRadius = CornerRadius(cornerRadius.toPx())
                            )
                            drawRoundRect(
                                color = Color(
                                    ColorUtils.blendARGB(color.toArgb(), 0x000000, 0.2f)
                                ),
                                topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                                size = Size(
                                    cutCornerSize.toPx() + 100f,
                                    cutCornerSize.toPx() + 100f
                                ),
                                cornerRadius = CornerRadius(cornerRadius.toPx())
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)

                    ) {
                        UserProfile(
                            heading = "Id:",
                            details = user.id.toString()
                        )

                        UserProfile(
                            heading = "Name:",
                            details = user.name
                        )

                        UserProfile(
                            heading = "Email:",
                            details = user.email
                        )

                        UserProfile(
                            heading = "Phone:",
                            details = user.phone
                        )

                        UserProfile(
                            heading = "Website:",
                            details = user.website
                        )

                        UserProfile(
                            heading = "Address:",
                            details = "${user.address.street}, ${user.address.city}"
                        )

                        UserProfile(
                            heading = "Company:",
                            details = user.company.name
                        )

                    }
                }
                Divider(
                    modifier = Modifier
                        .width(6.dp)
                        .weight(0.1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }

}


@Composable
fun UserProfile(heading: String, details: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.nunito_extrabold))
                )
            ) {
                append(heading)
            }
            append(" $details")
        },
        color = Color.Black,
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic,
    )
}


@Composable
fun CommentsSection(comments: List<Comment>) {
    Heading(title = "Comments:",
        modifier = Modifier.padding(start = 10.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        itemsIndexed(comments) { idx,comment ->
            val colors = when (idx % 5) {
                1 -> LightGreen
                2 -> RedOrange
                3 -> RedPink
                4 -> BabyBlue
                else -> Violet
            }
            Spacer(modifier = Modifier.height(10.dp))
            CommentItem(
                comment,
                colors
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item{
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun CommentItem(comment: Comment,
                color: Color,
                cornerRadius: Dp = 10.dp,
                cutCornerSize: Dp = 30.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(start = 10.dp)
        ,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .weight(0.9f)
            )
            {
                Canvas(modifier = Modifier.matchParentSize()) {
                    val clipPath = Path().apply {
                        lineTo(size.width - cutCornerSize.toPx(), 0f)
                        lineTo(size.width, cutCornerSize.toPx())
                        lineTo(size.width, size.height)
                        lineTo(0f, size.height)
                        close()
                    }

                    clipPath(clipPath) {
                        drawRoundRect(
                            color = color,
                            size = size,
                            cornerRadius = CornerRadius(cornerRadius.toPx())
                        )
                        drawRoundRect(
                            color = Color(
                                ColorUtils.blendARGB(color.toArgb(), 0x000000, 0.2f)
                            ),
                            topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                            size = Size(
                                cutCornerSize.toPx() + 100f,
                                cutCornerSize.toPx() + 100f
                            ),
                            cornerRadius = CornerRadius(cornerRadius.toPx())
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {

                    UserProfile(
                        heading = "Name:",
                        details = comment.name
                    )

                    UserProfile(
                        heading = "Email:",
                        details = comment.email
                    )
                    UserProfile(
                        heading = "Comment:",
                        details = comment.body
                    )

                }
            }

        }
    }
}


@Composable
fun UserPostsScreen(posts: List<Post>,
                    navController: NavHostController
) {
    var searchText by remember { mutableStateOf("") }

    // Filter posts based on search criteria (Post ID or Title)
    val filteredPosts = posts.filter {
        it.title.contains(searchText, ignoreCase = true) || it.id.toString().contains(searchText)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text(text="Search by Post ID or Title",
                color=if (isSystemInDarkTheme()) Color.White else Color.Gray
            ) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
//            placeholder = { Text(text = "Search by Post ID or Title") },
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Spacer(modifier = Modifier.width(20.dp))
            Heading(
                title="User Posts:",
                modifier = Modifier.padding(end=6.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        // If there are no filtered posts, show a message
        if (filteredPosts.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No posts found", style = MaterialTheme.typography.bodyMedium)
            }
        } else {
            // Display the filtered posts in LazyColumn
            LazyColumn(
                modifier=Modifier.fillMaxSize()
            ) {
                itemsIndexed(filteredPosts) { _,post ->
                    val color = when (post.id % 5) {
                        1 -> LightGreen
                        2 -> RedOrange
                        3 -> RedPink
                        4 -> BabyBlue
                        else -> Violet
                    }
                    Spacer(modifier =Modifier.height(5.dp))
                    Post(post = post,
                        color = color
                    ) {
                        navController.navigate(Navigationitems.Screen3.route +  post.id.toString())
                    }
                    Spacer(modifier = Modifier.height(15.dp))


                }
            }
        }
    }
}

@Composable
fun Post(
    post: Post,
    color: Color,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onclick: () -> Unit={}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .border(
                        border = BorderStroke(
                            3.dp,
                            MaterialTheme.colorScheme.onSurface
                        ),
                        shape = CircleShape
                    )

            )

            Divider(
                modifier = Modifier.width(6.dp),
                color = MaterialTheme.colorScheme.onSurface
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .weight(0.9f)
                        .clickable {
                            onclick()
                        }
                )
                {
                    Canvas(modifier = Modifier.matchParentSize()) {
                        val clipPath = Path().apply {
                            lineTo(size.width - cutCornerSize.toPx(), 0f)
                            lineTo(size.width, cutCornerSize.toPx())
                            lineTo(size.width, size.height)
                            lineTo(0f, size.height)
                            close()
                        }

                        clipPath(clipPath) {
                            drawRoundRect(
                                color = color,
                                size = size,
                                cornerRadius = CornerRadius(cornerRadius.toPx())
                            )
                            drawRoundRect(
                                color = Color(
                                    ColorUtils.blendARGB(color.toArgb(), 0x000000, 0.2f)
                                ),
                                topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                                size = Size(
                                    cutCornerSize.toPx() + 100f,
                                    cutCornerSize.toPx() + 100f
                                ),
                                cornerRadius = CornerRadius(cornerRadius.toPx())
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)

                    ) {
                        UserProfile(
                            heading = "PostID:",
                            details = post.id.toString()
                        )

                        UserProfile(
                            heading = "UserID:",
                            details = post.userId.toString()
                        )
                        UserProfile(heading = "Title: " ,
                            details = post.title)

                        UserProfile(heading ="Post: " ,
                            details = post.body)
                    }
                }
                Divider(
                    modifier = Modifier
                        .width(6.dp)
                        .weight(0.1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

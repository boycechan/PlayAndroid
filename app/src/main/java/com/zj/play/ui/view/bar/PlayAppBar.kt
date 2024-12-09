package com.zj.play.ui.view.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlayAppBar(
    title: String,
    showBack: Boolean = true,
    click: (() -> Unit)? = null,
    showRight: Boolean = false,
    rightImg: ImageVector = Icons.Rounded.MoreVert,
    rightClick: (() -> Unit)? = null,
) {
    Column(modifier = Modifier.background(color = MaterialTheme.colors.primary)) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (showBack) {
                IconButton(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start), onClick = click!!
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "back",
                        tint = MaterialTheme.colors.secondary
                    )
                }
            } else {
                IconButton(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start), onClick = {}
                ) {}
            }
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = title,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.secondary
            )
            if (showRight) {
                if (rightClick != null) {
                    IconButton(
                        modifier = Modifier.wrapContentWidth(Alignment.End),
                        onClick = rightClick
                    ) {
                        Icon(
                            imageVector = rightImg,
                            contentDescription = "more",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                }
            } else {
                IconButton(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start), onClick = {}
                ) {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayAppBarPreview() {
    PlayAppBar("测试")
}
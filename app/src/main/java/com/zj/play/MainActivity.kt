package com.zj.play

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zj.model.ArticleModel
import com.zj.play.ui.main.NavGraph
import com.zj.play.ui.theme.GrayAppAdapter
import com.zj.play.ui.theme.PlayAndroidTheme
import com.zj.play.ui.theme.getCurrentColors
import com.zj.play.widget.ArticleListWidgetGlance
import com.zj.utils.cancelToast
import com.zj.utils.setAndroidNativeLightStatusBar
import com.zj.utils.transparentStatusBar
import dagger.hilt.android.AndroidEntryPoint

const val CHANGED_THEME = "CHANGED_THEME"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var articleModel: ArticleModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        setAndroidNativeLightStatusBar()
        disposeIntent(intent)
        setContent {
            val colors = getCurrentColors()
            PlayAndroidTheme(colors) {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                    systemUiController.setNavigationBarColor(
                        colors.primary,
                        darkIcons = useDarkIcons
                    )
                }
                // 清明、七月十五或者是国家公祭日的时候展示黑白化效果
                GrayAppAdapter {
                    NavGraph(articleModel = articleModel)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        disposeIntent(intent)
    }

    private fun disposeIntent(intent: Intent?) {
        try {
            articleModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getParcelableExtra(
                    ArticleListWidgetGlance.ARTICLE_DATA,
                    ArticleModel::class.java
                )
            } else {
                intent?.getParcelableExtra(ArticleListWidgetGlance.ARTICLE_DATA)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 当 Activity 结束时表明整个项目已经结束，将 Toast 取消显示。
        cancelToast()
    }

}
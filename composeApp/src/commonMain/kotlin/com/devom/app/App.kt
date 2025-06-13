package com.devom.app

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import co.touchlab.kermit.Logger
import com.devom.app.theme.AppTheme
import com.devom.app.theme.greenColor
import com.devom.app.ui.components.AppContainer
import com.devom.app.ui.components.ProgressLoader
import com.devom.app.ui.components.ShowSnackBar
import com.devom.app.ui.navigation.NavigationHost
import com.devom.app.ui.navigation.Screens
import com.devom.app.ui.providers.LoadingCompositionProvider
import com.devom.models.payment.WalletTransaction
import com.devom.network.NetworkClient
import com.devom.utils.Application.isLoggedIn
import com.devom.utils.Application.loaderState
import com.devom.utils.Application.loginState
import com.devom.utils.date.convertIsoToDate
import com.devom.utils.date.toLocalDateTime
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import network.chaintech.cmpcharts.axis.AxisData
import network.chaintech.cmpcharts.axis.DataCategoryOptions
import network.chaintech.cmpcharts.common.model.Point
import network.chaintech.cmpcharts.ui.barchart.BarChart
import network.chaintech.cmpcharts.ui.barchart.models.BarChartData
import network.chaintech.cmpcharts.ui.barchart.models.BarData
import network.chaintech.cmpcharts.ui.barchart.models.BarStyle
import kotlin.math.roundToInt

val settings = Settings()

@Composable
internal fun App() = AppTheme {
    var accessKey by remember { mutableStateOf(settings.get<String>(ACCESS_TOKEN_KEY)) }
    var refreshToken by remember { mutableStateOf(settings.get<String>(ACCESS_TOKEN_KEY)) }
    var uuid by remember { mutableStateOf(settings.get<String>(UUID_KEY)) }

    val isLoggedIn by loginState.collectAsState()
    var initialized by remember { mutableStateOf(false) }

    LaunchedEffect(isLoggedIn){
        if (isLoggedIn.not()) {
            settings.remove(ACCESS_TOKEN_KEY)
            settings.remove(ACCESS_TOKEN_KEY)
            settings.remove(UUID_KEY)
        } else {
            accessKey = settings.get<String>(ACCESS_TOKEN_KEY)
            refreshToken = settings.get<String>(ACCESS_TOKEN_KEY)
            uuid = settings.get<String>(UUID_KEY)
        }
    }

    LaunchedEffect(isLoggedIn) {
        val loggedIn = accessKey?.isNotEmpty() == true && refreshToken?.isNotEmpty() == true && uuid?.isNotEmpty() == true
        isLoggedIn(loggedIn)
        NetworkClient.configure {
            setTokens(access = accessKey.orEmpty(), refresh = refreshToken.orEmpty())
            baseUrl = BASE_URL
            onLogOut = {
                Logger.d("ON_LOGOUT") { "user has been logged out" }
                isLoggedIn(false)
            }
            addHeaders {
                append(UUID_KEY, uuid.orEmpty())
            }
        }
        initialized = true
    }

    if (initialized) MainScreen(isLoggedIn)
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(isLoggedIn: Boolean) {
    val navController = rememberNavController()
    LoadingCompositionProvider(state = loaderState.collectAsStateWithLifecycle().value) {
        AppContainer {
            Scaffold(snackbarHost = { ShowSnackBar() }, content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    AnimatedContent(
                        targetState = isLoggedIn, transitionSpec = {
                            fadeIn(animationSpec = tween(300)) with fadeOut(
                                animationSpec = tween(
                                    300
                                )
                            )
                        }, label = "Auth/Dashboard Transition"
                    ) { target ->
                        NavigationHost(
                            navController = navController,
                            startDestination = if (target) Screens.Dashboard.path else Screens.Login.path
                        )
                    }

                    ProgressLoader()
                }
            })
        }
    }
}



@Composable
fun BarChartExample(transactions: List<WalletTransaction>) {
    val barData = getEarningBarData(transactions, DataCategoryOptions())

    if (barData.isNotEmpty()) {
        val maxY = barData.maxOf { it.point.y }
        val roundedMaxY = getRoundedMaxY(maxY)
        val yStepSize = roundedMaxY / 5

        val xAxisData = AxisData.Builder()
            .axisStepSize(30.dp)
            .steps((barData.size - 1).coerceAtLeast(1))
            .bottomPadding(40.dp)
            .axisLabelAngle(20f)
            .shouldDrawAxisLineTillEnd(false)
            .startDrawPadding(48.dp)
            .labelData { index ->
                barData.getOrNull(index)?.label.orEmpty()
            }
            .build()

        val yAxisData = AxisData.Builder()
            .steps(5)
            .labelData { ((it * yStepSize).toInt()).formatWithSuffix() }
            .shouldDrawAxisLineTillEnd(false)
            .axisLineColor(Color.Transparent)
            .labelAndAxisLinePadding(20.dp)
            .axisOffset(20.dp)
            .build()

        val barChartData = BarChartData(
            chartData = barData,
            xAxisData = xAxisData,
            yAxisData = yAxisData,
            horizontalExtraSpace = 10.dp,
            barStyle = BarStyle(
                cornerRadius = 5.dp,
                barWidth = 25.dp,
                paddingBetweenBars = 40.dp,
                selectionHighlightData = null
            ),
        )

        BarChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            barChartData = barChartData
        )
    }
}

fun getRoundedMaxY(max: Float): Int {
    return when {
        max <= 1000 -> 1000
        max <= 5000 -> 5000
        max <= 10000 -> 10000
        max <= 15000 -> 15000
        max <= 20000 -> 20000
        max <= 30000 -> 30000
        max <= 50000 -> 50000
        else -> ((max / 10000).toInt() + 1) * 10000
    }
}

fun Int.formatWithSuffix(): String {
    return when {
        this >= 1_000_000 -> "${this / 1_000_000 }M"
        this >= 1000 -> "${this / 1000} K"
        else -> "$this"
    }
}

fun getEarningBarData(
    transactions: List<WalletTransaction>,
    options: DataCategoryOptions,
): List<BarData> {

    val earningsByDate =
        transactions.filter { it.type.equals("credit", ignoreCase = true) }.groupBy {
                val date = runCatching {
                    it.createdAt.convertIsoToDate()?.toLocalDateTime()?.date
                }.getOrNull()
                date?.toString() ?: "Unknown"
            }.mapValues { value ->
                value.value.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
            }

    return earningsByDate.entries.mapIndexed { index, (date, totalAmount) ->
        BarData(
            point = Point(index.toFloat(), totalAmount.toFloat()),
            color = greenColor,
            label = date,
            gradientColorList = listOf(Color(0xFF66BB6A), Color(0xFF2E7D32)),
            dataCategoryOptions = options,
            description = "Earned ₹${totalAmount.roundToInt()} on $date"
        )
    }
}

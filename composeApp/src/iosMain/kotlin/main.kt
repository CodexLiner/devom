import androidx.compose.ui.window.ComposeUIViewController
import com.devom.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }

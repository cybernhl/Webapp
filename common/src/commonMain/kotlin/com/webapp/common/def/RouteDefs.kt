package com.webapp.common.def

import androidx.compose.runtime.Composable
import com.webapp.common.ui.main.MainPage
import com.webapp.common.ui.splash.SplashPage
import com.webapp.common.ui.sample.BasicWebViewWithHTMLSample
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

public class Route(
    public val route: String,
    public val page: (@Composable (BackStackEntry) -> Unit)
) {

    public fun registerRoute(builder: RouteBuilder) {
        builder.scene(route, content = page)
    }

}

public val SPLASH: Route = Route("splash") {
    SplashPage()
}

public val MAIN: Route = Route("main") {
    MainPage()
}

public val TEST: Route = Route("test") {
    BasicWebViewWithHTMLSample()
}


public object Router {
    private var navigator: Navigator? = null

    public fun initNavigation(navigator: Navigator) {
        this.navigator = navigator
    }

    public fun navigateTo(route: Route, navOptions: NavOptions? = null) {
        val nav = navigator ?: return
        val routeStr = route.route
        nav.navigate(routeStr, navOptions)
    }

    public fun popback() {
        navigator?.popBackStack()
    }
}
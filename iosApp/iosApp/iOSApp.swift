import UIKit
import common
import SwiftUI
@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    override init(){
        print("init is override")
        Koin_iosKt.doInitKoin_ios()
    }
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        ///抓取info.plist的key value
        if let hn = getInfoPlistValue(forKey: "HostName"){
            print(hn)
            ///由import common來的
            AppConfig().HOST = hn
        }
        if let token = getInfoPlistValue(forKey: "AdjustToken") {
            print("ios adjust token : \(token)")
            AppConfig().ADJUSTTOEKN = token
        }
        print("print ios host : \(AppConfig().HOST)")
        print("print ios adjustToken : \(AppConfig().ADJUSTTOEKN)")

        PlatformKt.debugBuild()
        ///使用swiftUI
        let contentView = ContentView()
        ///確認UI有init再顯示
        if let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene {
            let window = UIWindow(windowScene: windowScene)
            window.rootViewController = UIHostingController(rootView: contentView)
            self.window = window
            window.makeKeyAndVisible()
        }
        return true
    }

    func getInfoPlistValue(forKey key: String) -> String? {
        let res = Bundle.main.object(forInfoDictionaryKey: key) as? String
        return res
    }
}

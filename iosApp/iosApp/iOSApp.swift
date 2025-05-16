import SwiftUI

// Configurando a chave da api
@main
struct iOSApp: App {
    init() {
        GMSServices.provideAPIKey("MAPS_API_KEY")
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
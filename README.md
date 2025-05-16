# Integração do Google Maps em Projeto KMP (Kotlin Multiplatform)

Este guia descreve os passos necessários para integrar o Google Maps em um projeto Kotlin Multiplatform, garantindo compatibilidade com Android e iOS.

Baseado em: [link github](https://github.com/areebmomin/Google-Maps-CMP/tree/main/iosApp/iosApp)
---

## 1. Adicionar dependências no `libs.versions.toml`

Inclua as bibliotecas necessárias no arquivo `libs.versions.toml`, conforme exigido para o suporte ao Google Maps e ao Compose Multiplatform.

---

## 2. Criar a função `expect` para o componente de mapa

No módulo `commonMain`, crie o arquivo `MapComponent.android.kt` com a seguinte definição:

```kotlin
@Composable
expect fun MapComponent()
```

Devido à natureza da função expect, será necessário implementar a função actual separadamente para cada plataforma:

- androidMain/MapComponent.android.kt
- iosMain/MapComponent.ios.kt

---

## 3. Configuração do lado Android
### 3.1 Adicionar dependência no `composeApp/build.gradle.kts`:

```kotlin
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}
```

```kotlin
 androidMain.dependencies {
    implementation(compose.preview)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.google.maps)
}
```
### 3.2 Adicionar a chave da API no `AndroidManifest.xml` dentro da tag <application>:

```kotlin
<meta-data
android:name="com.google.android.geo.API_KEY"
android:value="${MAPS_API_KEY}" />
```

---

## 4. Configuração do lado Ios
### 4.1. Adicionar MapViewController em `composabeApp/iosMain/MainViewController.kt`:

```kotlin
fun MainViewController(
    mapUIViewController: () -> UIViewController
) = ComposeUIViewController {
    mapViewController = mapUIViewController
    App()
}

lateinit var mapViewController: () -> UIViewController
```

### 4.2. Implementar a função actual composable em para o lado iOS:
No módulo `iosMain`, crie o arquivo `MapComponent.ios.kt`

```kotlin
@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MapComponent() {
    UIKitViewController(
        factory = mapViewController,
        modifier = Modifier.fillMaxSize(),
    )
}
```

### 4.3. Adicionar a chave da API no `iosApp/iOSApp.swift`:

```swift
import SwiftUI
import GoogleMaps

@main
struct iOSApp: App {
    init() {
        GMSServices.provideAPIKey("YOUR_API_KEY")
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
```




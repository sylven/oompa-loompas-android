# Oompa Loompas - Android

Android app with Master/Detail flow written in Kotlin.

API Endpoints:  
https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas?page=1  
https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/1


## Software used
- Android Studio Arctic Fox | 2020.3.1 Canary 3
- Android Gradle Plugin Version: 4.1.0
- Gradle Version: 6.5

## Libraries used
- Moshi - Parsing JSON data
- Retrofit2 - Make network requests
- Okhttp3 - Creating HTTP requests with all the proper headers
- Kotlin Coroutines - Making non-blocking (main thread) network requests.
- Coil - Downloading an image from the internet and setting it into an ImageView.
- Paging library - To help implement inifinitely loading lists. It includes several core classes to help creating progressively loading lists. These classes abstract the logic that determines when to load new items. The Paging library also provides tie-ins to other core architecture components. These include the LiveData streaming library and the Room storage library.
- Koin - Dependency Injection
- Navigation component
- JUnit for testing

### Why use Kotlin Coroutines?
As you may know, Android developers today have many async tools at hand. These include RxJava/Kotlin/Android, AsyncTasks, Jobs, Threads, and more. So why would you need another tool in the toolbox, something else to learn?
If you’ve worked with Rx, then you know it takes a lot of effort to get to know it enough, to be able to use it safely. On the other hand, AsyncTasks and Threads can easily introduce leaks and memory overhead. Finally, relying on all these APIs, which use callbacks, can introduce a ton of code. Not only that, but the code can become unreadable, as you introduce more callbacks.

### Other
- Convention for property names for backing properties - https://kotlinlang.org/docs/reference/coding-conventions.html#property-names

### Networking API calls in Android using Retrofit2 and Kotlin Coroutines
1. Android Networking in 2019 — Retrofit with Kotlin’s Coroutines - https://android.jlelse.eu/android-networking-in-2019-retrofit-with-kotlins-coroutines-aefe82c4d777
2. Coroutines, Retrofit, and a nice way to handle Responses - https://levelup.gitconnected.com/coroutines-retrofit-and-a-nice-way-to-handle-responses-769e013ee6ef

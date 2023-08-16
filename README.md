# Github-Trends

Android App that demonstrates trending Github repositories using public GitHub API. This app follows **MVVM** layered architecture.
The main motivation behind developing this app is to demonstrate TDD practices. I have used the **Chicago School of TDD** or as said **Inside-Out** TDD Approach.

## Why TDD

Because there are tons of benefits when we follow TDD:

* **Higher Code Quality**: TDD promotes writing tests before writing code, which ensures that the code is designed to meet specific requirements. This results in code that is well-tested, less error-prone, and less likely to contain defects.

* **Faster Debugging**: When a test fails, it's often easier to pinpoint the cause of the issue because the scope of the code changes since the last passing test is limited. This speeds up the debugging process.

* **Early Feedback**: TDD provides immediate feedback on the correctness of the code. As soon as you write a test, you know if your code behaves as expected. This helps catch issues early in the development process, reducing the cost of fixing them later.

* **Confidence in Refactoring**: Since the tests ensure that your code behaves correctly, you can refactor your code with confidence, knowing that you'll catch any potential regressions through the test suite.

* **Continuous Integration and Deployment (CI/CD)**: TDD aligns well with CI/CD practices. Automated tests ensure that new code changes are thoroughly tested before being deployed, reducing the risk of breaking the production environment.

Overall, Test-Driven Development promotes a disciplined and iterative approach to software development that focuses on delivering high-quality code that meets the requirements of the software project.

# CICD Stack:

To ensure proper feedback loop for strict TDD I have used the following CICD stack:

* Github Actions.
* Firebase Test-Lab for UI Tests Execution.
* Fastlane for .aab deployment to Google Play Console.
* Google Play Console for internal testing.

<p align="center">
    <img width="600" src="https://github.com/warisdgk/Github-Trends/blob/5f8ffe274b3c2a45583c39352e87a96bdaaeec5d/docs/CICD%20Explained.png" alt="CICD Flow Explained">
</p>

# Firebase TestLab Limitations:

I have integrated Firebase Test Lab for UI test executions, Currently, the  command that executes UI tests is commented out due to the Firebase Test Lab limitations for Free Tier. If you hit the limitations any subsequent jobs will fail stating the used of free quota limits.

### Following are the limitations:

Test Lab's testing quota is measured by the number of test runs per day:
**Spark plan (no-cost)**: The resource limits are listed for up to 15 test runs per day in total:

* 10 test runs per day on virtual devices
* 5 test runs per day on physical devices

## Testing

Most data layer components are defined as interfaces.
Then, concrete implementations (with various dependencies) are bound to provide those interfaces to
other components in the app.
In tests, **Github Trends** does _not_ use any mocking libraries.
Instead, the production implementations can be replaced with test doubles using testing APIs
(or via manual constructor injection for `ViewModel` tests).

These test doubles implement the same interface as the production implementations and generally
provide a simplified (but still realistic) implementation with additional testing hooks.
This results in less brittle tests that may exercise more production code, instead of just verifying
specific calls against mocks.

## Libraries Used

* [Jetpack-Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s recommended modern toolkit for building native UI.
* [LeakCanary](https://square.github.io/leakcanary/) - LeakCanary is a memory leak detection library for Android.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Newly introduced library on top of Dagger two for [Depencency Inversion](https://developer.android.com/training/dependency-injection)
* [Coil](https://github.com/coil-kt/coil) - An image loading library for Android backed by Kotlin Coroutines.
* [Chucker](https://github.com/ChuckerTeam/chucker) - Chucker simplifies the inspection of HTTP(S) requests/responses fired by your Android App.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android to perfrom Network Requests.
* [Kotlin-Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Helps in writing the code to perform asynchronous operations.
* [Espresso](https://developer.android.com/training/testing/espresso) - Library that helps to write concise, beautiful, and reliable Android UI tests.


## App Directory Structure

```
app/
|- base/
   |- viewmodel/
|- data/
   |- entities
   |- exceptions
   |- remote
   |- repositories
|- di/
|- testdoubles/
|- ui/
|- utils/
-MainApplication
```

#### - Folder Structure Explained

1. **base** - This folder will hold all the base classes we have used in the app including the ones for viewmodels.
2. **Data** - This folder will hold all the data related classes in it. This data can either be from remote API.
3. **Entities** - This folder will have all the Entities of the app as the DB models.
4. **exceptions** - This folder will have all custom made exceptions if any.
5. **remote** - This folder will have all the related classes for Remote data source and remote service that will fetch the data.
6. **repositories** - This folder will have all the repositories of different Screens in a specific app. A class one level below the ViewModels to delegate the data fetching work.
7. **testdoubles** - This folder will have TestDoubles or classes implementing the production-level interfaces to mock Repositories, ViewModels, Network State etc,
8. **di** - This folder will have Dependency Injection related Classes.
9. **ui** - UI will hold all the UI app components majorly composables.
10. **utils** - A space for utilities that will be used by all over the application.

## Github Public API Used

Public Github Api used so far : https://api.github.com/search/repositories?q=language=+sort:stars

## Author
* Muhammad waris
* Staff Engineer
* Portfolio: [Muhammad waris](http://mwaris.dev/)

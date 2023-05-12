# Mote: Note Taking Made Easy
Mote is a comprehensive note-taking app built on Clean Architecture and MVVM. The app allows users to create, edit, and manage their notes in an intuitive and efficient way. From simple text-based notes to image attachments, Mote provides a platform for users to capture their thoughts, ideas, and tasks. The app also includes search functionality to easily find specific notes. Comprehensive Unit Tests over Data, Domain, and UI layers ensure the reliability and robustness of the application.
### All requirements for a feature-rich note taking app are fulfilled with user experience at the core
# Screenshots
<body>
  <img src="https://github.com/onuralpavci/Mote/assets/63224890/302661c9-36de-46fb-a876-348f7f0fdab4" style="width: 45%;">
  <img src="https://github.com/onuralpavci/Mote/assets/63224890/db160b3a-8a72-4443-9234-22c410ec30d6" style="width: 45%;">
  <br>
  
  <img src="https://github.com/onuralpavci/Mote/assets/63224890/24eb2261-1075-4f88-9e96-e5c3119abacb" style="width: 45%;">
   <img src="https://github.com/onuralpavci/Mote/assets/63224890/41cbe074-1642-4997-8bc5-9ee3da7dfb43" style="width: 45%;">
  <br>
  
  <img src="https://github.com/onuralpavci/Mote/assets/63224890/f5bc7ef0-1709-44db-ae1b-a17beb929e85" style="width: 45%;">
  <img src="https://github.com/onuralpavci/Mote/assets/63224890/e4d8c3eb-3050-4214-8a27-75a9ba6fa08e" style="width: 45%;">
  <br>
  
  <img src="https://github.com/onuralpavci/Mote/assets/63224890/05280931-c455-4991-8d9e-dc7921b2099a" style="width: 45%;">
  <img src="https://github.com/onuralpavci/Mote/assets/63224890/3ca25ff2-c38e-4d1b-966e-5739f218fd65" style="width: 45%;">
  <br>

</body>

### Data Layer:
The Data Layer encapsulates the application's data and its related business logic. It includes components such as APIs, Data Transfer Objects (DTOs), Repositories, and Mappers. This layer interacts directly with the data sources (like databases and network requests), performing data operations and transformations to provide a clean and usable data format for the upper layers.

### Domain Layer:
The Domain Layer, also known as the business logic layer, is the heart of the application where the core business logic resides. It acts as a bridge between the Data Layer and the UI Layer. This layer is typically composed of Use Cases, Repository Interfaces, and Entities. The Use Cases define the operations which can be performed in the application. The Repository Interfaces provide an abstraction over the Data Layer. And the Entities are the fundamental data objects on which the operations are performed.

### Ui Layer:
The UI Layer is responsible for displaying data to the user and handling user interactions. It contains all UI-related components such as ViewModels, Fragments, Activities, and UI-specific state objects. The UI Layer communicates with the Domain Layer to consume the data and operations defined in the use cases. The data is then formatted for presentation and delivered to the user.

## Importance of Mapping Data Between Layers
In a well-architected application, each layer has its own data model. This strategy is crucial to maintaining the separation of concerns principle. Data mapping between layers allows us to adapt the data models to the specific needs of each layer. For instance, the Data Layer may require a model that accommodates a specific database schema, while the UI Layer needs a model tailored for display purposes. By maintaining separate models per layer, we can modify one without affecting the others, making the codebase more scalable and maintainable. Additionally, it prevents high coupling between the models and the specific details of each layer, promoting better adherence to the Clean Architecture principles.

# Tech Stack & Open Source Libraries
- Minimum SDK level 24
- %100 [Kotlin](https://kotlinlang.org/) based
- [Use Cases](https://developer.android.com/topic/architecture/domain-layer)' purpose is to request data from repositories and turn them ready to use for the Ui layer.
- [Repository](https://developer.android.com/topic/architecture/data-layer) pattern is a design pattern that isolates the data layer from the rest of the app.
- [Coroutines](https://developer.android.com/kotlin/coroutines) for asynchronous programming on Android. 
- [Flow](https://developer.android.com/kotlin/flow) is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value In coroutines
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) class is a business logic or screen level state holder. It exposes state to the UI and encapsulates related business logic
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) is a class that holds the information about the lifecycle state of a component (like an activity or a fragment) and allows other objects to observe this state.
- [Navigation Component](https://developer.android.com/guide/navigation) refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within app
- [Dagger Hilt](https://dagger.dev/hilt/) Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding) is a feature that allows you to more easily write code that interacts with views.
- [Extension Functions](https://kotlinlang.org/docs/extensions.html) Kotlin provides the ability to extend a class or an interface with new functionality without having to inherit from the class or use design patterns 

# Unit Testing
- [JUnit](https://junit.org/junit5/) JUnit is a simple, open-source framework to write repeatable tests. It forms the backbone of our unit tests, offering a robust and user-friendly environment to define test cases and assert conditions.
- [Room Testing](https://developer.android.com/training/data-storage/room/testing-db) Room provides a testing framework for testing DAOs (Data Access Objects) and database migrations. We've used this to ensure the correct operation of our database interactions and integrity of migrations.

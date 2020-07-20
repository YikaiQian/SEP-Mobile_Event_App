# SEP - Mobile Event App Project
This mobile event app project is my first android application. During
the process of learning and development, I noticed that android development
covered large variety of knowledge including app architecture, 
UI design, data management and etc. It is really hard to build
a good application in such a short time. 

So, I will keep update and improve this project on this repo.

## Currently Working On
Welcome for advice or feedback on this project. Leave message in the repo or reach out to me by other means.
- UI design
- Comments
- Web server API and Admins side

## Update History
- UI update: 7.20
  - Redesign the UI of 'general event info' page using `NestedScrollView`. 
  Contents of event info are added to the fragment programmatically since the number of weeks and
  the number of sessions per week is not fixed. 
  - Customize the Color Theme: Material design palette
  - Add Snackbar messages to improve user experience


## About the project
This project is focused on the functionality and architecture and. The
app UI is more or less primitive which will be improved next.

### Functional Requirements
Most functional requirements are met. Filter function is finished. 
The chatting and admin functions will be implemented next. 

## Application Details
The app has one main activity which contains multiple fragment that
user can navigate to view the general event info, list of sessions,
and the to-attend list. Those three main fragments are organized with
the Buttom Navigation bar. 

Organizing app screens in one activity with multiple fragments facilitates
the ability of Navigation Component which simplifies the logic.   

### Data Management
- A Local Json File `res.raw.all_sessions` provides raw stubbed data

- Repository pattern is adopted to hide the the data source from the
rest of the application.

- A Repository class is responsible for fetching the data. So, it is 
easy to migrate the local data source APIs to Web Service APIs.
Only the Repository class is going to be changed.

- SQLite Database: User data such as to-attend sessions are stored in the
database. 

If the Web Service API is provided, those user info can be stored on
the server and later fetched from different endpoints. That's more efficient
than load all data at once. Large files such as images can also be cached at
local storage to speed up the app.

#### ViewModel
A ViewModel is used to manage the data shared by multiple fragments.
The ViewModel contains Mutable LiveData that publish updated data to 
those fragments when the data change. 

 
### Navigation
- Navigation from login activity to main activity is realized by Intent.
- Navigation between different fragments in main activity uses Navigation
Architecture Component.


### Display Data
- Add UI components and layout programmatically
- ListView & RecyclerView
- Adapter
- Data Binding

The UI of general event info fragment is constructed programmatically
so that it can display nested lists of contents and add customized filters to the data. i.e. display sessions by 
week and display multiple weeks of data

ListView and RecyclerView are used in the project in order to display a list of sessions in a fragment and handle the navigation 
to the detail fragment when one of the session is selected.

Adapter classes specify how each item is displayed in a ListView or RecyclerView.
It also provides an interface to handle the click event and pass the data of
selected item to the main fragment.

Data Binding + viewModel = Display data directly in XML file


## External Libraries Used
- Room: Manage Android SQLite Databases
- Moshi: Parse the Json file
- Glide: Display images from url
- Material UI Components: Unified and powerful theming (button, card, font)


## Next Step
- Optimize the UI design

- Dive deep into basic concepts of Android such as view, context, Fragment and
architecture components.

- Setup a Web Server and implement the admin function

- Refactor the code and add some necessary comments


## Some References
- Kotlin Bootcamp: https://codelabs.developers.google.com/codelabs/kotlin-bootcamp-welcome/#0

- LinkedIn Learning: 
   - Kotlin Essential Training
   - Android Development Essential Training: Your First App with Kotlin
   - Android Development Essential Training: The User Interface with Kotlin
   - Android Development Essential Training: App Architecture with Kotlin
   - Android Development Essential Training: Manage Data with Kotlin
   - Android Development: The Navigation Architecture Component

- Material Design code lab: https://codelabs.developers.google.com/codelabs/mdc-101-kotlin/#0

- Room Database: https://developer.android.com/training/data-storage/room

- Android ListView Tutorial: https://www.raywenderlich.com/155-android-listview-tutorial-with-kotlin

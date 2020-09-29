# Bookstore

Bookstore android app assignment for my 3rd year in university.

### <p align="center">![App screens](https://raw.githubusercontent.com/CrazyStevenz/bookstore/master/bookstore.png)</p>

## Structure

The MVVM paradigm was used to create the application wherever possible.
An attempt was also made to follow the [Android Code Style Guide](https://source.android.com/devices/architecture/hidl/code-style).

The application is using DrawerLayout and has a shopping cart.
There are 2 activities in the application, LoginActivity and MainActivity.
LoginActivity appears in the application load.
Login transitions to MainActivity where we remain for all other screen switches.

The pages of the application are in Fragment format.
The alternation between them is done with the corresponding alternation of Fragments.
Each Fragment (View) is accompanied by a ViewModel file, which is responsible for logic,
data management and communication with Repositories.

Repositories are responsible for communicating with Daos as well as the logic that
data needs to undergo both before and after communicating with them.

Daos use the Room API to send queries to the database and retrieve data from it, which
can be either primitives, Entities, or LiveData (data that is updated by changing the
database without any further action other than using observe).

Entities are representations of the data of a database with Java objects whose
variables correspond to the fields of the database. Any constraints the database has
are declared directly in the Entities.

Finally, the AppDatabase file contains the instance of the database on which all
operations are performed. It also fills the database with dummy data for demonstration 
purposes.

## Demonstration

Video (in greek): https://youtu.be/5-BDIiOYfew

The video did not show the invalid credential check that has been implemented.
Additionally, on the Sale List page it was discovered that the number shown in the video
was not the number of products on sale but the number of products in the Store.
The problem was fixed after the recording of this video.

## Database

The database consists of three tables:

- Products
- Customers
- Sales

The connection to the database was made using the Data Access Object.

## Code

The code is mostly annotated, except for lines I may have missed or where the
explanation is obvious.

## Changes

**AndroidManifest:** LoginActivity was added and became the screen the application
starts on.

**Gradle:** To be able to use VectorDrawableCompat, the option
"vectorDrawables.useSupportLibrary = true" has been added to defaultConfig.
The necessary libraries for the use of "lifecycle" and the "room api" were also added.

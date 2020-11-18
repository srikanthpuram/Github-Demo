# Github-Demo
Github Demo is an Android sample app to use the Github Apis and display the Github users information in the UI, used the below components for this project

Kotlin, Programing language

Kotlin Coroutines

MVVM, Architectural pattern

Koin, Dependency injection framework

Retrofit, HTTP client platform

Android Jetpack's Paging library

Glide, Image loading framework

Github Api Demo: is used to retrieve. the. user commits and also the gitusers,
able to fetch github users (https://api.github.com/search/users?q=repos%3A%3E1&page=2&per_page=15) successfully, since the API was returning an object.
remote data is like this:   {"total_count":14439199,"incomplete_results":false,"items":[{"login":"torvalds","id"

But for commits (/repos/{owner}/{repo}/commits)  remote api was returning an Array, but our model object/Json parser only reads like an object at the root.
so even though we get the data (visible in console), we are getting exception parsing the data 
"  java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $. "
Attached are all the screenshots and the logs files in the screenshot folder in the project.
Checked in blogs and forums but could'nt the help, tried Gson Converter and also Moshi converter, but no luck.

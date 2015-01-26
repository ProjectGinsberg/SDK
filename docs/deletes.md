## Delete data

The final common method is Delete for deleting a single identified record of users data from the system as done via single calls to the SDK. If logged in and successful, the system will send back confirmation to the `Comment` method. If a fault occurs, then a call to `CommentError` will be made.

The calls to delete the required data all have a single parameter of the Int ID value of the required record to delete and are as follows:
* **Activity -** Delete a particular record of users activity.

    ```obj-c
    //Obj-c
    [[GAPI.Instance] DeleteActivity:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteActivity(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteActivity(12345);
    ```
    
GAPI.Instance().DeleteNotifications(ei);
GAPI.Instance().DeleteActivity(ei);
GAPI.Instance().DeleteAlcohol(ei);
GAPI.Instance().DeleteNutrition(ei);
GAPI.Instance().DeleteSleep(ei);
GAPI.Instance().DeleteBody(ei);
GAPI.Instance().DeleteCaffeine(ei);
GAPI.Instance().DeleteSmoking(ei);
GAPI.Instance().DeleteStepcount(ei);
GAPI.Instance().DeleteExercise(ei);
GAPI.Instance().DeleteSocial(ei);
GAPI.Instance().DeleteEvents(ei);
GAPI.Instance().DeleteWellbeing(ei);

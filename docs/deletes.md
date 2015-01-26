## Delete data


## Delete Data

The final common method is Delete for deleting a single identified record of users data from the system as done via single calls to the SDK. If logged in and successful, the system will send back confirmation to the `Comment` method. If a fault occurs, then a call to `CommentError` will be made.

- [Activity](#activity)
- [Alcohol](#alcohol)
- [Body](#body)
- [Caffeine](#caffeine)
- [Events](#events)
- [Exercise](#exercise)
- [Notifications](#notifictions)
- [Nutrition](#nutrition)
- [Sleep](#sleep)
- [Smoking](#smoking)
- [Social](#social)
- [Step Count](#step-count)
- [Wellbeing](#wellbeing) 



The calls to delete the required data all have a single parameter of the Int ID value of the required record to delete and are as follows:
* #####Activity
    Delete a particular record of users activity.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteActivity:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteActivity(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteActivity(12345);
    ```

* #####Alcohol
    Delete a particular record of users alcohol intake.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteAlcohol:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteAlcohol(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteAlcohol(12345);
    ```

* #####Body
    Delete a particular record of users body data.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteBody:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteBody(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteBody(12345);
    ```
  
* #####Caffeine
    Delete a particular record of users caffeine in.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteCaffeine:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteCaffeine(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteCaffeine(12345);
    ```
  
* #####Events
    Delete a particular record of users events.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteEvents:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteEvents(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteEvents(12345);
    ```
  
* #####Exercise
    Delete a particular record of users exercise.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteExercise:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteExercise(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteExercise(12345);
    ```
  
* #####Notification
    Delete a particular record of users notification.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteNotification:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteNotification(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteNotification(12345);
    ```
  
* #####Nutrition
    Delete a particular record of users nutrition.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteNutrition:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteNutrition(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteNutrition(12345);
    ```
      
* #####Sleep
    Delete a particular record of users sleep.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteSleep:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteSleep(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteSleep(12345);
    ```
    
* #####Smoking
    Delete a particular record of users smoking.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteSmoking:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteSmoking(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteSmoking(12345);
    ```
   
* #####Social
    Delete a particular record of users social.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteSocial:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteSocial(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteSocial(12345);
    ```
 
* #####Step Count
    Delete a particular record of users step count.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteStepcount:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteStepcount(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteStepcount(12345);
    ```
  
* #####Wellbeing
    Delete a particular record of users wellbeing.

    ```obj-c
    //Obj-c
    [[GAPI Instance] DeleteWellbeing:12345];
    ```
    ```swift
    //Swift
    GAPI.Instance().DeleteWellbeing(12345)
    ```
    ```java
    //Android
    GAPI.Instance().DeleteWellbeing(12345);
    ```
  
    

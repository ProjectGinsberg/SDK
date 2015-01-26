###Post Data

As with getting data, posting users data to the system is done via single calls to the SDK, which, if logged in and successful, will send back confirmation to the `Comment` method. If a fault occurs, then a call to `CommentError` will be made.

The calls to post the required data are as follows:
* **Activity -** Post the users details of a given activity.

    ```obj-c
    //Obj-c
    [[GAPI.Instance] PostActivity:timeStamp start:timeStart end:timeEnd dist:distance cal:calories steps:Int32(stepCount)];
    ```
    ```swift
    //Swift
    GAPI.Instance().PostActivity(timeStamp, start:timeStart, end:timeEnd, dist:distance, cal:calories, steps:Int32(stepCount));
    ```
    ```java
    //Android
    GAPI.Instance().PostActivity(timeStart, timeEnd, distance, calories, stepCount, timeStamp);
    ```


GAPI.Instance().PostBody(weight, fat, timeStamp);
GAPI.Instance().PostCaffeine(ef, timeStamp);
GAPI.Instance().PostSmoking(ei, timeStamp);
GAPI.Instance().PostStepcount(timeStart, timeEnd, distance, calories, stepCount, timeStamp);
GAPI.Instance().PostSocial(timeStamp);
GAPI.Instance().PostNutrition(calories, carbohydrates, fat, fiber, protein, sugar, timeStamp);
GAPI.Instance().PostActivity(timeStart, timeEnd, distance, calories, stepCount, timeStamp);
GAPI.Instance().PostAlcohol(timeStamp, ef);
GAPI.Instance().PostEvents(timeStamp, "Todays event string", GAPI.Instance().todaysEventID );
GAPI.Instance().PostSleep(timeStamp, timesAwoken, awake, lightSleep, remSleep, deepSleep, totalSleep, 5);

GAPI.Instance().PostExercise(timeStart, timeEnd, distance, calories, stepCount, timeStamp);
GAPI.Instance().PostWellbeing(ei, timeStamp, "I've been interested in new things", 10/*wellbeingType*/);
GAPI.Instance().PostProfile("Bill", "Ben", "12345", 1); }

        
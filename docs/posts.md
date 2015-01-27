###Post Data

As with getting data, posting users data to the system is done via single calls to the SDK, which, if logged in and successful, will send back confirmation to the `Comment` method. If a fault occurs, then a call to `CommentError` will be made.

The calls to post the required data are as follows:

- [Activity](#activity)
- [Alcohol](#alcohol)
- [Body](#body)
- [Caffeine](#caffeine)
- [Daily Summary](#dailysummary)
- [Events](#events)
- [Exercise](#exercise)
- [Nutrition](#nutrition)
- [Profile](#profile)
- [Sleep](#sleep)
- [Smoking](#smoking)
- [Social](#social)
- [Step Count](#step-count)
- [Wellbeing](#wellbeing)


* #####Activity
	Post the users details of a given activity.

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


* #####Alcohol
	GAPI.Instance().PostAlcohol(timeStamp, ef);

* #####Body
	GAPI.Instance().PostBody(weight, fat, timeStamp);

* #####Caffeine
	GAPI.Instance().PostCaffeine(ef, timeStamp);

* #####Events
	GAPI.Instance().PostEvents(timeStamp, "Todays event string", GAPI.Instance().todaysEventID );

* #####Exercise
	GAPI.Instance().PostExercise(timeStart, timeEnd, distance, calories, stepCount, timeStamp);

* #####Nutrition
	GAPI.Instance().PostNutrition(calories, carbohydrates, fat, fiber, protein, sugar, timeStamp);

* #####Profile
	GAPI.Instance().PostProfile("Bill", "Ben", "12345", 1); }

* #####Sleep
	GAPI.Instance().PostSleep(timeStamp, timesAwoken, awake, lightSleep, remSleep, deepSleep, totalSleep, 5);

* #####Smoking
	GAPI.Instance().PostSmoking(ei, timeStamp);

* #####Social
	GAPI.Instance().PostSocial(timeStamp);

* #####Step Count
	GAPI.Instance().PostStepcount(timeStart, timeEnd, distance, calories, stepCount, timeStamp);

* #####Wellbeing
	GAPI.Instance().PostWellbeing(ei, timeStamp, "I've been interested in new things", 10/*wellbeingType*/);

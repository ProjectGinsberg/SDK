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
    [[GAPI.Instance] PostActivity:@"2015-01-27T11:50:16+0000" start:@"2015-01-26T11:50:16+0000" end:@"2015-01-26T12:50:16+0000" dist:5.0 cal:1000.0 steps:10000];
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
	Post the users details of a given alcohol.

    ```obj-c
    //Obj-c
    [[GAPI.Instance] PostAlcohol:@"2015-01-27T11:50:16+0000" units:5.0];
    ```
    
	GAPI.Instance().PostAlcohol(timeStamp, ef);

* #####Body
	Post the users body details.

    GAPI.Instance().PostBody(weight, fat, timeStamp);

* #####Caffeine
	Post the users caffeine intake.

    GAPI.Instance().PostCaffeine(ef, timeStamp);

* #####Events
	Post the users details of a given event.

    GAPI.Instance().PostEvents(timeStamp, "Todays event string", GAPI.Instance().todaysEventID );

* #####Exercise
	Post the users details of a given exercise.

    GAPI.Instance().PostExercise(timeStart, timeEnd, distance, calories, stepCount, timeStamp);

* #####Nutrition
	Post the users details of a given nutrition intake.

	GAPI.Instance().PostNutrition(calories, carbohydrates, fat, fiber, protein, sugar, timeStamp);

* #####Profile
	Post the users profile details.

	GAPI.Instance().PostProfile("Bill", "Ben", "12345", 1); }

* #####Sleep
	Post the users details of a given sleep period.

	GAPI.Instance().PostSleep(timeStamp, timesAwoken, awake, lightSleep, remSleep, deepSleep, totalSleep, 5);

* #####Smoking
	Post a users smoking period.

	GAPI.Instance().PostSmoking(ei, timeStamp);

* #####Social
	Post a users social activity.

	GAPI.Instance().PostSocial(timeStamp);

* #####Step Count
	Post the users details of a given steps activity.

	GAPI.Instance().PostStepcount(timeStart, timeEnd, distance, calories, stepCount, timeStamp);

* #####Wellbeing
	Post the users details of a particular wellbeing data capture.

	GAPI.Instance().PostWellbeing(ei, timeStamp, "I've been interested in new things", 10/*wellbeingType*/);

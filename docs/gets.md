##Get Data

Getting users data from the system is done via single calls to the SDK, which, if logged in and successful, will send data back to the app via the callbacks `DataReceived` method. The get methods follow a standard format, with a set of parameters to define for what range of values to get back, of the type of data requested.  If a fault occurs, then a call to `CommentError` will be made. These values are as follows:

 *  **String Range -**  Range to get data for. Is one of: "All":Get all available data, "ID":For particular wellbeing entry given by the ID param, "From":From the given typeFrom param, "To":Upto the given typeTo param, "FromTo":Between the given typeFrom/typeTo params.
 *  **String TypeFrom -** Type of range to start getting data from. Is one of: "Yesterday", "Lastweek", "Lastyear", "Date"
 *  **String DateFrom -** If using typeFrom of "Date", the string date to get data from
 *  **String TypeTo -** Type of range to end getting data upto. Is one of: "Yesterday", "Lastweek", "Lastyear", "Date"
 *  **String DateTo -** If using typeTo of "Date", the string date to get data upto
 *  **Int ID -** If using range of "ID", this is the id of the particular entry to get

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetActivity:@"All" typeFrom:@"Yesterday" dateFrom:nil typeTo:@"Yesterday" dateTo:nil ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetActivity("All",typeFrom:"Yesterday",dateFrom:nil,typeTo:"Yesterday",dateTo:nil,ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetActivity("All", "Yesterday", null, "Yesterday", null, ID);
    ```

The calls to get the required data are as follows:

- [Activity](#activity)
- [Alcohol](#alcohol)
- [Body](#body)
- [Caffeine](#caffeine)
- [Correlations](#correlations)
- [Daily Summary](#dailysummary)
- [Events](#events)
- [Exercise](#exercise)
- [Measures](#measures)
- [Notifications](#notifictions)
- [Nutrition](#nutrition)
- [Profile](#profile)
- [Sleep](#sleep)
- [Smoking](#smoking)
- [Social](#social)
- [Step Count](#step-count)
- [Wellbeing](#wellbeing) 



* #####Activity
	Get the users activity for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetActivity:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetActivity(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetActivity(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Alcohol
	Get the users alcohol for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetAlcohol:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetAlcohol(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetAlcohol(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Body
	Get the users body info for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetBody:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetBody(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetBody(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Caffeine
	Get the users caffeine for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetCaffeine:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetCaffeine(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetCaffeine(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Correlations
	Get the users correlations.

    ```obj-c
    //Obj-c
    [[GAPI Instance] Get];
    ```
    ```swift
    //Swift
    GAPI.Instance().Get();
    ```
    ```java
    //Android
    GAPI.Instance().Get();
    ```
    
* #####Daily Summary
	Get the users daily summaries for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetDailySummary:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetDailySummary(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo);
    ```
    ```java
    //Android
    GAPI.Instance().GetDailySummary(range, typeFrom, dateFrom, typeTo, dateTo);
    ```
    
* #####Events
	Get the users events for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetEvents:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetEvents(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetEvents(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Exercise
	Get the users exercise for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetExercise:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetExercise(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetExercise(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Measures
	Get the users measures for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetMeasures:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetMeasures(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetMeasures(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Notifications
	Get the users notifications for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetNotifications:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetNotifications(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetNotifications(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Nutrition
	Get the users nutrition for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetNutrition:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetNutrition(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetNutrition(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Profile
	Get the users profile.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetProfile];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetProfile();
    ```
    ```java
    //Android
    GAPI.Instance().GetProfile();
    ```
    
* #####Sleep
	Get the sleep users for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetSleep:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetSleep(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetSleep(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Smoking
	Get the users smoking for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetSmoking:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetSmoking(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetSmoking(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Social
	Get the users social for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetSocial:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetSocial(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetSocial(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Step Count
	Get the users step count for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetStepcount:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetStepcount(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetStepcount(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Survey
	Get the users survey for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetSurvey:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetSurvey(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetSurvey(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Tags
	Get the users tags for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetTag:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetTag(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetTag(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
* #####Wellbeing
	Get the users wellbeing for a given range.

    ```obj-c
    //Obj-c
    [[GAPI Instance] GetWellbeing:range typeFrom:typeFrom dateFrom:dateFrom typeTo:typeTo dateTo:dateTo ID:ID];
    ```
    ```swift
    //Swift
    GAPI.Instance().GetWellbeing(range, typeFrom:typeFrom, dateFrom:dateFrom, typeTo:typeTo, dateTo:dateTo, ID:Int(ID));
    ```
    ```java
    //Android
    GAPI.Instance().GetWellbeing(range, typeFrom, dateFrom, typeTo, dateTo, ID);
    ```
    
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

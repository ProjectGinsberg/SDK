Ginsberg.io Mobile SDK and Example Apps
=======================================

The Ginsberg Mobile SDK makes it easy to add Ginsberg data access to mobile apps.

## Contents

- [Requirements](#requirements)
- [Add the SDK to Your Project](#add-the-sdk-to-your-project)
- [Credentials](#credentials)
- [Implementation Overview](#implementation-overview)
- [Signup](#signup)
- [Login](#login)
- [Get Data](#get-data)
- [Post Data](#post-data)
- [Delete Data](#delete-data)
- [Connections](#connections)
- [Example App](#example-app) 

## Requirements

### iOS
* Xcode 6 and iOS SDK 8
* iOS 6.0+ target deployment
* armv7, armv7s, and arm64 devices, and the simulator (not armv6)
* iPhone and iPad of all sizes and resolutions

### Android
* Android 2.2 or later
* Phone or tablet

## Add the SDK to Your Project
### iOS
1. Clone or download the SDK, which consists of header files, release notes, and a static library. It also includes an example app.
2. Add the `libs/iOS` directory (containing GAPI.h and libGAPI.a) to your Xcode project.
3. Implement a class that uses GAPIProtocol. This will provide the callbacks from calls to the SDK. See example app for reference, as well as reading the rest of this document.
4. During the usual program run, as long as you have the right credentials and valid account as mentioned below, you will pass your GAPIProtocol instance to a setup call of the GAPI singleton, as in 'Login' below, then you can start using the SDK for pushing/getting/deletes.

### Android
1. Clone or download the SDK, which consists of header files, release notes, and a static library. It also includes an example app.
2. Add the `libs/Android/GAPI.aar` library to your Android project.
3. Implement a class that uses IGAPICallbacks. This will provide the callbacks from calls to the SDK. See example app for reference, as well as reading the rest of this document.
4. During the usual program run, as long as you have the right credentials and valid account as mentioned below, you will pass your GAPIProtocol instance to a setup call of the GAPI singleton, as in 'Login' below, then you can start using the SDK for pushing/getting/deletes.


## Credentials

Your will require different `client_id` and `client_secret` values for each application you develop using the SDK. You can obtain these Ginsberg API credentials by visiting the [Applications page on the Ginsberg Developer site](https://platform.ginsberg.io/app) and logging in with your Ginsberg account. Register as a developer, if not done already, and select new app. The page will then show you the client_id and client_secret strings to embed into your application.

##Implementation Overview
To setup getting data in and out of Ginsberg, the user must first create an account, if not already done through the website. Then they must go through the SDKs login process once, before the SDK can then be used for posting and getting data. The SDK can also be used to delete individual records of the user. To get data back from the SDK server calls, implement the GAPIProtocol interface and pass an instance off it to the SDK.

###Signup

1. Signup can either be done via a popover webview, else via a single api call. For the popover webview call the `SignUpWeb` method as below. For the single api call call the `SignUp` method as below, with something for a first name, last name, password, confirmation of password, email address, country code, and selected wellbeing questions. Only passwords and email address are required to be valid, as shown in the example, to reduce initial signup time. The other data can be updated at a later stage once the user is logged in. 

    ```obj-c
    //Obj-c
    [[GAPI Instance] SignUpWeb];
    ```
    ```obj-c
    //Obj-c
    [[GAPI Instance] SignUp:@"Please" lastName:@"Replace" password:@"password" cpassword:@"password" email:@"john@example.com" countryID:1 wbIDs:nil];
    ```
    ```swift
    //Swift
    GAPI.Instance().SignUpWeb();
    ```
    ```swift
    //Swift
    GAPI.Instance().SignUp("Please", lastName:"Replace", password:"password", cpassword:"password", email:"john@example.com", countryID:1, wbIDs:nil);
    ```
    ```java
    //Android
    GAPI.Instance().SignUpWeb();
    ```
    ```java
    //Android
    GAPI.Instance().SignUp("Please", "Replace", "password", "password", "john@example.com", 1, null);
    ```
    
###Login

1. Call the setup method with the a valid client id, client secret and GAPIProtocol instance. On android there is an extra first parameter of current activity.
    ```obj-c
    //Obj-c
    [[GAPI Instance] Setup:CLIENT_ID, secret:CLIENT_SECRET, callbacks:self];
    ```
    ```swift
    //Swift
    GAPI.Instance()!.Setup(CLIENT_ID, secret:CLIENT_SECRET, callbacks:self);
    ```
    ```java
    //Android
    GAPI.Instance().Setup(this, CLIENT_ID, CLIENT_SECRET, this);
    ```
    
2. If the system alreasy has a valid user login GAPIProtocols `GainedAccess` will be called from which point you can start getting/posting data. If there is not a valid login GAPIProtocols `NeedLogin` will be called instead.

3. If login is required. call the login method to run the login procedure. Once a valid login process has finished, `GainedAccess` will be called as in the previous step.
    ```obj-c
    //Obj-c
    [[GAPI Instance] Login];
    ```
    ```swift
    //Swift
    GAPI.Instance().Login();
    ```
    ```java
    //Android
    GAPI.Instance().Login();
    ```
    
###Get Data

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

The calls to get the required data are  [listed here](docs/gets.md) with the following as an example:
* **Activity -** Get the users activity for a given period.

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
    
    
###Post Data

As with getting data, posting users data to the system is done via single calls to the SDK, which, if logged in and successful, will send back confirmation to the `Comment` method. If a fault occurs, then a call to `CommentError` will be made.

The calls to post the required data are [listed here](docs/posts.md) with the following as an example:
* **Activity -** Post the users details of a given activity.

    ```obj-c
    //Obj-c
    [[GAPI Instance] PostActivity:timeStamp start:timeStart end:timeEnd dist:distance cal:calories steps:Int32(stepCount)];
    ```
    ```swift
    //Swift
    GAPI.Instance().PostActivity(timeStamp, start:timeStart, end:timeEnd, dist:distance, cal:calories, steps:Int32(stepCount));
    ```
    ```java
    //Android
    GAPI.Instance().PostActivity(timeStart, timeEnd, distance, calories, stepCount, timeStamp);
    ```

    
###Delete Data

The final common method is Delete for deleting a single identified record of users data from the system as done via single calls to the SDK. If logged in and successful, the system will send back confirmation to the `Comment` method. If a fault occurs, then a call to `CommentError` will be made.

The calls to delete the required data all have a single parameter of the Int ID value of the required record to delete and are [listed here](docs/deletes.md) with the following as an example:
* **Activity -** Delete a particular record of users activity.

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
    
###Connections

1. To connect the users Ginsberg account to external sources, such as fitbit and runkeeper, a popover webview is used for the user to sign into the respective services. This is activated with a single call to the SDK, once the user is logged into the server. The call has a single parameter of an optional background image. For iOS this is UIImage, and Android an image ID. It looks as follows:

    ```obj-c
    //Obj-c
    [[GAPI Instance] ConnetionsWeb:bgUIImage];
    ```
    ```swift
    //Swift
    GAPI.Instance().ConnectionsWeb(bgUIImage);
    ```
    ```java
    //Android
    GAPI.Instance().ConnectionsWeb(bgImageID);
    ```

##Example App

To show the sdk in practice, the repository contains an example app in the **ExampleApp** folder. It is broken down into the following views
* **Login -** Login process 
* **Signup -** Custom signup process, without webview
* **Post -** Get/Post/Delete calls of users data
* **Profile -** Update users profile info such as name, phone number, etc
* **Connetions -** Show the web view connections page for 3rd party data
* **Charts -** IN DEVELOPMENT - Test of showing data via cross platform chart system (in js)


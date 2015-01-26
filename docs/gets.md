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

The calls to get the required data are as follows:
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
    
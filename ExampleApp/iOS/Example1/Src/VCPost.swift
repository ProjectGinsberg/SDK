//
//  ViewController.swift
//  Example1
//
//  Created by CD on 22/07/2014.
//  Copyright (c) 2014 Ginsberg. All rights reserved.
//

import UIKit


//
// Main handler of demonstrating getting and posting of users Ginsberg data
//

class VCPost: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource, UITextFieldDelegate, GAPIProtocol
{
    //
    // MARK: Outlets
    //
    
    @IBOutlet weak var aiBusy: UIView!
    @IBOutlet weak var vMain: UIView!
    @IBOutlet weak var lbText: UILabel!
    @IBOutlet weak var pkType: UIPickerView!
    @IBOutlet weak var pkGetPeriod: UIPickerView!
    @IBOutlet weak var pkGetFrom: UIPickerView!
    @IBOutlet weak var pkGetTo: UIPickerView!
    @IBOutlet weak var lbGetID: UITextField!
    @IBOutlet weak var lbGetFrom: UITextField!
    @IBOutlet weak var lbGetTo: UITextField!
    @IBOutlet weak var lbDeleteID: UITextField!
    @IBOutlet weak var svSendData: UIScrollView!
    @IBOutlet weak var svOutput: UIScrollView!
    
    @IBOutlet weak var btGet: UIButton!
    @IBOutlet weak var btPost: UIButton!
    @IBOutlet weak var btDelete: UIButton!
    
    @IBOutlet weak var vFromTo: UIView!
    @IBOutlet weak var vPost: UIView!
    
    
    //
    // MARK: Onscreen items
    //
    
    let Types = [/*"Aggregate",*/ "Correlations", "Daily Summary", "Notifications", "Profile", "Tag", "Activity" , "Alcohol", "Body" , "Caffeine", "Exercise", "Events", "Measures", "Nutrition", "Sleep", "Smoking", "Social", "Stepcount", "Survey", "Wellbeing", "Mood", "Happy", "Uneasy", "Well", "Sad"];
    let Range = ["All", "ID", "FromBelow", "ToBelow", "FromToBelow"];
    let Time = ["Yesterday", "Lastweek", "Lastyear", "Date"];
    var dataRequested:Bool = false;
    
    
    //
    // MARK: View Controller
    //
    
    override func viewDidLoad()
    {
        super.viewDidLoad();
        
        //Set callbacks to this instance
        GAPI.Instance().SetCallbacks(self);
        
        //Hide busy indicator
        SetBusy(false);
        
        //Set onscreen dates to now
        pressedSetTimeNow(nil);
        
        //Set text colour for when buttons are disabled
        btPost.setTitleColor(UIColor.grayColor(), forState: UIControlState.Disabled);
        btDelete.setTitleColor(UIColor.grayColor(), forState: UIControlState.Disabled);
        
        //Set initial picker selection
        self.pickerView(pkType, didSelectRow: 0, inComponent: 0);
    }
    
    
    override func didReceiveMemoryWarning()
    {
        super.didReceiveMemoryWarning()
    }
    
    
    override func viewWillAppear(animated: Bool)
    {
        super.viewWillAppear(animated)
    }
    
    
    override func viewDidAppear(animated: Bool)
    {
        super.viewDidAppear(animated);
        
        //Make pickers a bit smaller
        pkType.transform = CGAffineTransformMakeScale(0.6, 0.4);
        pkGetPeriod.transform = CGAffineTransformMakeScale(0.6, 0.4);
        pkGetFrom.transform = CGAffineTransformMakeScale(0.6, 0.4);
        pkGetTo.transform = CGAffineTransformMakeScale(0.6, 0.4);
        
        //Configure send data entries
        svSendData.contentSize = CGSizeMake(svSendData.contentSize.width, 620.0);
        svOutput.contentSize = CGSizeMake(svOutput.contentSize.width, 2000.0);
        
        //Space out send data
        for subView in svSendData.subviews
        {
            var obj:UIView = subView as UIView;
            var i = obj.tag;
            if(i != 0)
            {
                obj.frame = CGRectMake(obj.frame.origin.x, CGFloat(30*((i-1)/2)), obj.frame.size.width, obj.frame.size.height);
            }
        }
    }
    
    
    //
    // MARK: TextField
    //
    
    func textFieldShouldReturn(textField: UITextField) -> Bool
    {
        //Hide keyboard on return press
        textField.resignFirstResponder();
        return false;
    }
    
    
    //
    // MARK: Pickerview
    //
    
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int
    {
        return 1;
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int
    {
        if(pickerView == pkType)
        {
            return Types.count;
        }
        else
        if(pickerView == pkGetPeriod)
        {
            return Range.count;
        }
        else
        if(pickerView == pkGetFrom)
        {
            return Time.count;
        }
        else
        if(pickerView == pkGetTo)
        {
            return Time.count;
        }
        
        return 0;
    }
    
    func pickerView(pickerView: UIPickerView!, titleForRow row: Int, forComponent component: Int) -> String!
    {
        if(pickerView == pkType)
        {
            return Types[row];
        }
        else
        if(pickerView == pkGetPeriod)
        {
            return Range[row];
        }
        else
        if(pickerView == pkGetFrom)
        {
            return Time[row];
        }
        else
        if(pickerView == pkGetTo)
        {
            return Time[row];
        }
    
        return "";
    }
    
    
    func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int)
    {
        //Format view per selection
        var selection:String = Types[pkType.selectedRowInComponent(0)];
        
        switch(selection)
        {
            //case "Aggregate":Int(ID)); break;
        case "Correlations": EnableViews(false, postData:false, deleteData:false, valueType:nil); break;
        case "Daily Summary":EnableViews(true, postData:false, deleteData:false, valueType:nil); break;
        case "Tag":          EnableViews(true, postData:false, deleteData:false, valueType:nil); break;
        case "Profile":      EnableViews(false, postData:false, deleteData:false, valueType:nil); break;
        case "Activity":     EnableViews(true, postData:true, deleteData:true, valueType:nil, variables:"timeStart","timeEnd","distance","calories","steps","timeStamp"); break;
        case "Alcohol":      EnableViews(true, postData:true, deleteData:true, valueType:"Units", variables:"timeStamp"); break;
        case "Body":         EnableViews(true, postData:true, deleteData:true, valueType:nil,
                                         variables:"weight", "fat", "timeStamp"); break;
        case "Caffeine":     EnableViews(true, postData:true, deleteData:true, valueType:"Caffeine", variables:"timeStamp"); break;
        case "Events":       EnableViews(true, postData:true, deleteData:true, valueType:nil, variables:"timeStamp"); break;
        case "Exercise":     EnableViews(true, postData:true, deleteData:true, valueType:"Distance", variables:"timeStamp"); break;
        case "Measures":     EnableViews(false, postData:false, deleteData:false, valueType:nil); break;
        case "Notifications": EnableViews(true, postData:false, deleteData:false, valueType:nil); break;
        case "Nutrition":    EnableViews(true, postData:true, deleteData:true, valueType:nil, variables:"calories","carbohydrates","fat","fiber","protein","sugar","timeStamp"); break;
        case "Sleep":        EnableViews(true, postData:true, deleteData:true, valueType:nil,
                                         variables:"timesAwoken","awake","lightSleep","remSleep","deepSleep","totalSleep","sleepQuality","timeStamp"); break;
        case "Smoking":      EnableViews(true, postData:true, deleteData:true, valueType:"Quantity", variables:"timeStamp"); break;
        case "Social":       EnableViews(true, postData:true, deleteData:true, valueType:nil, variables:"timeStamp"); break;
        case "Stepcount":    EnableViews(true, postData:true, deleteData:true, valueType:nil,
                                         variables:"timeStart","timeEnd","distance","calories","steps","timeStamp"); break;
        case "Survey":       EnableViews(false, postData:false, deleteData:false, valueType:nil); break;
        case "Wellbeing":    EnableViews(true, postData:true, deleteData:true, valueType:nil); break;
        
        case "Mood":         EnableViews(true, postData:true, deleteData:true, valueType:"Value"); break;
        case "Happy":        EnableViews(true, postData:true, deleteData:true, valueType:"Value"); break;
        case "Uneasy":       EnableViews(true, postData:true, deleteData:true, valueType:"Value"); break;
        case "Well":         EnableViews(true, postData:true, deleteData:true, valueType:"Value"); break;
        case "Sad":          EnableViews(true, postData:true, deleteData:true, valueType:"Value"); break;
            
        default: break;
        }
    }
    
    
    // Customise view
    func EnableViews(getPeriod:Bool, postData:Bool, deleteData:Bool, valueType:String?, variables:String...)
    {
        //Show/hide used/unused views
        UIView.animateWithDuration(0.5)
        {
            self.vFromTo.userInteractionEnabled = !getPeriod;
            self.vPost.userInteractionEnabled = !postData;
            
            if(getPeriod)
            {
                self.vFromTo.alpha = 0.0;
            }
            else
            {
                self.vFromTo.alpha = 1.0;
            }
            
            if(postData)
            {
                self.vPost.alpha = 0.0;
            }
            else
            {
                self.vPost.alpha = 1.0;
            }
        }
        
        //Enable buttons
        btPost.enabled = postData;
        btDelete.enabled = deleteData;
        lbDeleteID.enabled = deleteData;
        
        if(deleteData)
        {
            lbDeleteID.textColor = UIColor.blackColor();
        }
        else
        {
            lbDeleteID.textColor = UIColor.grayColor();
        }
        
        //Hide all posting items
        for subView in svSendData.subviews
        {
            var obj:UIView = subView as UIView;
            obj.hidden = true;
        }
        svSendData.setContentOffset(CGPoint(x:0.0,y:0.0),animated:true);
        
        var i = 0;
        
        //If using the value entry posting box then enable it and put at top of posting panel
        if(valueType != nil)
        {
            RepositionView(7, index:i++);
            (svSendData.viewWithTag(7) as UILabel).text = valueType;
        }
        
        //Enable data entry items listed in the passed variables list
        for variable in variables
        {
            switch(variable)
            {
            case "timeStamp": RepositionView(1, index:i); break;
            case "weight": RepositionView(13, index:i); break;
            case "fat": RepositionView(15, index:i); break;
            case "timeStart": RepositionView(3, index:i); break;
            case "timeEnd": RepositionView(5, index:i); break;
            case "distance": RepositionView(9, index:i); break;
            case "calories": RepositionView(11, index:i); break;
            case "steps": RepositionView(37, index:i); break;
                
            case "carbohydrates": RepositionView(17, index:i); break;
            case "fiber": RepositionView(19, index:i); break;
            case "protein": RepositionView(21, index:i); break;
            case "sugar": RepositionView(23, index:i); break;
            case "timesAwoken": RepositionView(35, index:i); break;
            case "awake": RepositionView(33, index:i); break;
            case "lightSleep": RepositionView(31, index:i); break;
            case "remSleep": RepositionView(29, index:i); break;
            case "deepSleep": RepositionView(27, index:i); break;
            case "totalSleep": RepositionView(25, index:i); break;
            case "sleepQuality": RepositionView(125, index:i); break;
                
            default: break;
            }
            ++i;
        }
    }
    
    
    //Reposition and enable passed item
    func RepositionView(tag:Int, index:Int)
    {
        var viewTitle:UIView! = svSendData.viewWithTag(tag);
        var viewValue:UIView! = svSendData.viewWithTag(tag+1);
        
        viewTitle.hidden = false;
        viewValue.hidden = false;
        
        viewTitle.frame = CGRectMake(viewTitle.frame.origin.x, CGFloat(10+35*index), viewTitle.frame.size.width, viewTitle.frame.size.height);
        viewValue.frame = CGRectMake(viewValue.frame.origin.x, CGFloat(10+35*index), viewValue.frame.size.width, viewValue.frame.size.height);
    }
    
    
    //
    // MARK: Actions
    //
    
    @IBAction func pressedPostData(sender: UIButton)
    {
        SetBusy(true);
        
        // Get all potential data from view
        var ev:String = "1";
        var selection:String=Types[pkType.selectedRowInComponent(0)];
        
        var timeStamp:String = (svSendData.viewWithTag(2) as UITextField).text; //"2014-07-23T14:38:58";
        
        var ed:Double = ((svSendData.viewWithTag(8) as UITextField).text as NSString).doubleValue;
        var ei:Int = Int(ed);
        
        var timeStart:String = (svSendData.viewWithTag(4) as UITextField).text;
        var timeEnd:String = (svSendData.viewWithTag(6) as UITextField).text;
        
        var distance:Double = ((svSendData.viewWithTag(10) as UITextField).text as NSString).doubleValue;
        var calories:Double = ((svSendData.viewWithTag(12) as UITextField).text as NSString).doubleValue;
        var weight:Double = ((svSendData.viewWithTag(14) as UITextField).text as NSString).doubleValue;
        var fat:Double = ((svSendData.viewWithTag(16) as UITextField).text as NSString).doubleValue;
        var carbohydrates:Double = ((svSendData.viewWithTag(18) as UITextField).text as NSString).doubleValue;
        var fiber:Double = ((svSendData.viewWithTag(20) as UITextField).text as NSString).doubleValue;
        var protein:Double = ((svSendData.viewWithTag(22) as UITextField).text as NSString).doubleValue;
        var sugar:Double = ((svSendData.viewWithTag(24) as UITextField).text as NSString).doubleValue;
        var totalSleep:Double = ((svSendData.viewWithTag(26) as UITextField).text as NSString).doubleValue;
        var deepSleep:Double = ((svSendData.viewWithTag(28) as UITextField).text as NSString).doubleValue;
        var remSleep:Double = ((svSendData.viewWithTag(30) as UITextField).text as NSString).doubleValue;
        var lightSleep:Double = ((svSendData.viewWithTag(32) as UITextField).text as NSString).doubleValue;
        var awake:Double = ((svSendData.viewWithTag(34) as UITextField).text as NSString).doubleValue;
        var timesAwoken:Int = ((svSendData.viewWithTag(36) as UITextField).text as NSString).integerValue;
        var stepCount:Int = ((svSendData.viewWithTag(38) as UITextField).text as NSString).integerValue;
        var wellbeingType:Int = ((svSendData.viewWithTag(40) as UITextField).text as NSString).integerValue;
        
        var wellbeingQues:String = "I've been interested in new things";
        
        //Post data with the respective SDK call
        switch(selection)
        {
            case "Body": GAPI.Instance().PostBody(timeStamp, weight:weight, fat:fat); break;
            case "Caffeine": GAPI.Instance().PostCaffeine(timeStamp, amount:ed); break;
            case "Smoking": GAPI.Instance().PostSmoking(timeStamp, amount:Int32(ei)); break;
            case "Stepcount": GAPI.Instance().PostStepcount(timeStamp, timeStart:timeStart, timeEnd:timeEnd, distance:distance, calories:calories, steps:Int32(stepCount)); break;
            case "Social": GAPI.Instance().PostSocial(timeStamp); break;
            case "Nutrition": GAPI.Instance().PostNutrition(timeStamp, calories:calories, carbohydrates:carbohydrates, fat:fat, fiber:fiber, protein:protein, sugar:sugar); break;
            case "Activity": GAPI.Instance().PostActivity(timeStamp, start:timeStart, end:timeEnd, dist:distance, cal:calories, steps:Int32(stepCount)); break;
            case "Alcohol": GAPI.Instance().PostAlcohol(timeStamp, units:ed); break;
            case "Events": GAPI.Instance().PostEvent(timeStamp, event:"Todays event string", ID:GAPI.Instance().todaysEventID); break;
            case "Sleep": GAPI.Instance().PostSleep(timeStamp, timesAwoken:Int32(timesAwoken), awake:Double(awake), lightSleep:Double(lightSleep), remSleep:Double(remSleep), deepSleep:Double(deepSleep), totalSleep:Double(totalSleep), quality:Int32(10)); break;
            //case "Survey": GAPI.Instance().PostSurvey(1234); break;
            
                //To finish
            case "Exercise": GAPI.Instance().PostExercise(timeStamp, activity:"Jogging", distance:ed); break;
            case "Wellbeing": GAPI.Instance().PostWellbeing(timeStamp, value:Int32(5), wbques:"I've been interested in new things", wbtype:Int32(10)); break;
            case "Profile": GAPI.Instance().PostProfile("Chris", lastName:"Brown", phoneNumber:"12345", country:Int32(0+1)); break;
            
            case "Mood":   GAPI.Instance().PostMood(timeStamp, value:Int32(ei)); break;
            case "Happy":  GAPI.Instance().PostHappy(timeStamp, value:Int32(ei)); break;
            case "Uneasy": GAPI.Instance().PostUneasy(timeStamp, value:Int32(ei)); break;
            case "Well":   GAPI.Instance().PostWell(timeStamp, value:Int32(ei)); break;
            case "Sad":    GAPI.Instance().PostSad(timeStamp, value:Int32(ei)); break;
            
                //Done other than input values
            //case "Notifications": GAPI.Instance().PostNotifications(timeStamp, value:Int32(ei));
            default: break;
        }
    }


    @IBAction func pressedGetData(sender: UIButton)
    {
        SetBusy(true);
        dataRequested = true;

        var selection:String = Types[pkType.selectedRowInComponent(0)];
        var range:String = Range[pkGetPeriod.selectedRowInComponent(0)];
        var typeFrom:String = Time[pkGetFrom.selectedRowInComponent(0)];
        var typeTo:String = Time[pkGetTo.selectedRowInComponent(0)];
        var dateFrom:String = lbGetFrom.text;
        var dateTo:String = lbGetTo.text;
        
        var ID:Int! = lbGetID.text.toInt();
        
        //Get data with the respective SDK call
        switch(selection)
        {
            //case "Aggregate":     GAPI.Instance().GetAggregate(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Correlations":  GAPI.Instance().GetCorrelations(); break;
            case "Daily Summary": GAPI.Instance().GetDailySummary(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo); break;
            case "Profile":       GAPI.Instance().GetProfile(); break;
            case "Tag":           GAPI.Instance().GetTag(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo); break;
            case "Activity":      GAPI.Instance().GetActivity(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Alcohol":       GAPI.Instance().GetAlcohol(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Body":          GAPI.Instance().GetBody(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Caffeine":      GAPI.Instance().GetCaffeine(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Events":        GAPI.Instance().GetEvent(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Exercise":      GAPI.Instance().GetExercise(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            //case "Measures":      GAPI.Instance().GetMeasures(Int(ID)); break;
            case "Notifications": GAPI.Instance().GetNotifications(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Nutrition":     GAPI.Instance().GetNutrition(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Sleep":         GAPI.Instance().GetSleep(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Smoking":       GAPI.Instance().GetSmoking(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Social":        GAPI.Instance().GetSocial(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Stepcount":     GAPI.Instance().GetStepcount(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            //case "Survey":        GAPI.Instance().GetSurvey(); break;
            case "Wellbeing":     GAPI.Instance().GetWellbeing(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Mood":          GAPI.Instance().GetMood(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Happy":         GAPI.Instance().GetHappy(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Uneasy":        GAPI.Instance().GetUneasy(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Well":          GAPI.Instance().GetWell(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            case "Sad":           GAPI.Instance().GetSad(range,typeFrom:typeFrom,dateFrom:dateFrom,typeTo:typeTo,dateTo:dateTo,ID:Int(ID)); break;
            default: break;
        }
    }
    
    
    @IBAction func pressedDelete(sender: UIButton)
    {
        SetBusy(true);
        
        var selection:String = Types[pkType.selectedRowInComponent(0)];
        var ev:String = lbDeleteID.text;
        var ei:Int! = ev.toInt();

        //Delete data record with the respective SDK call
        switch(selection)
        {
            case "Activity":      GAPI.Instance().DeleteActivity(Int32(ei));  break;
            case "Alcohol":       GAPI.Instance().DeleteAlcohol(Int32(ei));  break;
            case "Body":          GAPI.Instance().DeleteBody(Int32(ei));  break;
            case "Caffeine":      GAPI.Instance().DeleteCaffeine(Int32(ei));  break;
            case "Events":        GAPI.Instance().DeleteEvent(Int32(ei));  break;
            case "Exercise":      GAPI.Instance().DeleteExercise(Int32(ei));  break;
            //case "Measures":      GAPI.Instance().DeleteMeasures(Int32(ei));  break;
            //case "Notifications": GAPI.Instance().DeleteNotifications(Int32(ei));  break;
            case "Nutrition":     GAPI.Instance().DeleteNutrition(Int32(ei));  break;
            case "Sleep":         GAPI.Instance().DeleteSleep(Int32(ei));  break;
            case "Smoking":       GAPI.Instance().DeleteSmoking(Int32(ei));  break;
            case "Social":        GAPI.Instance().DeleteSocial(Int32(ei));  break;
            case "Stepcount":     GAPI.Instance().DeleteStepcount(Int32(ei));  break;
            //case "Survey":        GAPI.Instance().DeleteSurvey(Int32(ei));  break;
            case "Wellbeing":     GAPI.Instance().DeleteWellbeing(Int32(ei));  break;
            case "Mood":          GAPI.Instance().DeleteWellbeing(Int32(ei));  break;
            case "Happy":         GAPI.Instance().DeleteWellbeing(Int32(ei));  break;
            case "Uneasy":        GAPI.Instance().DeleteWellbeing(Int32(ei));  break;
            case "Well":          GAPI.Instance().DeleteWellbeing(Int32(ei));  break;
            case "Sad":           GAPI.Instance().DeleteWellbeing(Int32(ei));  break;
            default: break;
        }
    }
    
    
    // Clear output view at top of screen
    @IBAction func pressedClear(sender: UIButton)
    {
        lbText.text = "";
        lbText.sizeToFit();
        lbText.frame = CGRectMake(lbText.frame.origin.x, lbText.frame.origin.y, 300, lbText.frame.size.height);
        svOutput.setContentOffset(CGPoint(x:0.0,y:0.0),animated:true);
    }
    
    
    // Clear user login token, resulting in current/different user having to log in again
    @IBAction func pressedClearToken(sender: UIButton)
    {
        GAPI.Instance().ClearToken();
        self.dismissViewControllerAnimated(true, completion: nil);
    }
    
    
    //Update views time entries to current date
    @IBAction func pressedSetTimeNow(sender: UIButton?)
    {
        var date = GAPI.GetDateShort(0);
        var now = GAPI.GetDateTime(0);
        
        lbGetFrom.text = date;
        lbGetTo.text = date;
        
        //Time now
        (svSendData.viewWithTag(2) as UITextField).text = now;
        
        //Start time
        (svSendData.viewWithTag(4) as UITextField).text = now;
        
        //End time
        (svSendData.viewWithTag(6) as UITextField).text = now;
    }
    
    
    //
    // MARK: Callbacks
    //
    
    func SetBusy(truth: Bool)
    {
        aiBusy.hidden = !truth;
    }
    
    
    func Comment(text: String)
    {
        //Add comment to top of view
        lbText.text = text + "\n" + lbText.text!;
        lbText.sizeToFit();
        lbText.frame = CGRectMake(lbText.frame.origin.x, lbText.frame.origin.y, 300, lbText.frame.size.height);
    }
    
    
    func CommentError(text: String)
    {
        Comment(text);
    }
    
    
    func CommentResult(text: String)
    {
    
    }
    
    
    func CommentSystem(text: String)
    {
        
    }
    
    
    func DataReceived(endPoint:String, withData:NSDictionary?, andString:String)
    {
        //Is data recieved not from default login process, display at top of view, as data apps user requested directly
        if( dataRequested ||
            (  endPoint != "/defaults.json" && endPoint != "/account/signupmetadata"
            && endPoint != "/v1/me" && endPoint != "/v1/wellbeing"
            && endPoint != "/v1/o/events" && endPoint != "/v1/tags") )
        {
            Comment("Got data from '\(endPoint)' of '\(andString)'");
            SetBusy(false);
            dataRequested = false;
        }
    }
}


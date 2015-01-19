//
//  ViewController.swift
//  Example1
//
//  Created by CD on 22/07/2014.
//  Copyright (c) 2014 Ginsberg. All rights reserved.
//

import UIKit


//
// Handle updating the users profile information
//

class VCProfile: UIViewController, GAPIProtocol, UIPickerViewDelegate, UIPickerViewDataSource, UITextFieldDelegate
{
    //
    // MARK: Outlets
    //
    
    @IBOutlet weak var aiBusy: UIView!
    @IBOutlet weak var vMain: UIView!
    @IBOutlet weak var btCancel: UIButton!
    @IBOutlet weak var btUpdate: UIButton!
  
    @IBOutlet weak var pkCountry: UIPickerView!
    @IBOutlet weak var tfFirstName: UITextField!
    @IBOutlet weak var tfLastName: UITextField!
    @IBOutlet weak var tfPhoneNumber: UITextField!
    
    //Truth of is user has changed values, before originals obtained from server
    var firstNameChanged:Bool = false;
    var lastNameChanged:Bool = false;
    var phoneNumberChanged:Bool = false;
    var countryChanged:Bool = false;
    
    
    //
    // MARK: View Controller
    //
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        SetBusy(false);
    
        //Make buttons look nice
        btCancel.layer.borderWidth = 0.0;
        btCancel.layer.cornerRadius = 15;
        btUpdate.layer.borderWidth = 0.0;
        btUpdate.layer.cornerRadius = 15;
    }
    
    
    override func viewWillAppear(animated: Bool)
    {
        super.viewWillAppear(animated)
        
        //Set callbacks to this instance
        GAPI.Instance()?.SetCallbacks(self);
        
        //Update screen with stored user details
        UpdateDetails();
    }
    
    
    //
    // MARK: TextField
    //
    
    func textFieldShouldReturn(textField: UITextField) -> Bool
    {
        //Hide keyboard on return
        textField.resignFirstResponder();
        return false;
    }
    
    
    func textFieldDidBeginEditing(textField: UITextField)
    {
        //Flag when user has updated a field
        switch(textField)
        {
        case tfFirstName: firstNameChanged = true; break;
        case tfLastName: lastNameChanged = true; break;
        case tfPhoneNumber: phoneNumberChanged = true; break;
        default: break;
        }
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
        if(pickerView == pkCountry)
        {
            if(GAPI.Instance()?.countries != nil && GAPI.Instance()?.countries.count > 200 && !countryChanged)
            {
                return GAPI.Instance()!.countries.count;
            }
        }
        
        return 1;
    }
    
    
    func pickerView(pickerView: UIPickerView!, titleForRow row: Int, forComponent component: Int) -> String!
    {
        if(pickerView == pkCountry)
        {
            //Show country name else 'Downloading...' if still downloading from server
            if(GAPI.Instance()?.countries != nil && GAPI.Instance()?.countries.count > 200 && !countryChanged)
            {
                return GAPI.Instance()!.countries[row] as NSString;
            }
            else
            {
                return "Downloading...";
            }
        }
        
        return "";
    }
    
    
    //
    // MARK: Actions
    //
    
    @IBAction func pressedUpdate(sender: UIButton)
    {
        // Grab user entered data
        var firstName:NSString = tfFirstName.text;
        var lastName = tfLastName.text;
        var phoneNumber = tfPhoneNumber.text;
        var countryID = pkCountry.selectedRowInComponent(0);
        var countryName = "Scotland";
        
        if(GAPI.Instance()?.countries != nil && GAPI.Instance()?.countries.count > 200)
        {
            countryName = GAPI.Instance()?.countries[countryID] as NSString;
        }
     
        // If user profile info changed then send to user
        if(firstName != GAPI.Instance()!.userFirstName || lastName != GAPI.Instance()!.userLastName  ||
           phoneNumber != GAPI.Instance()!.userPhoneNumber || countryName != GAPI.Instance()!.userCountry )
        {
            GAPI.Instance()?.PostProfile(firstName, lastName:lastName, phoneNumber:phoneNumber, country:Int32(countryID+1));
        }
        else
        {
            let alert = UIAlertView(title:"No Updates", message:"No data has changed.", delegate:nil, cancelButtonTitle:"OK");
            alert.show();
        }
    }
    
    
    @IBAction func pressedCancel(sender: UIButton)
    {
        self.dismissViewControllerAnimated(true, completion: nil);
    }
    
    
    //
    // MARK: Methods
    //
    
    //Update onscreen user details if SDK has valid values for
    func UpdateDetails()
    {
        if(GAPI.Instance()?.userFirstName != nil && !firstNameChanged)
        {
            tfFirstName.text = GAPI.Instance()?.userFirstName;
        }
        if(GAPI.Instance()?.userLastName != nil && !lastNameChanged)
        {
            tfLastName.text = GAPI.Instance()?.userLastName;
        }
        if(GAPI.Instance()?.userPhoneNumber != nil && !phoneNumberChanged)
        {
            tfPhoneNumber.text = GAPI.Instance()?.userPhoneNumber;
        }
        if(GAPI.Instance()?.countries != nil && GAPI.Instance()?.countries.count > 200 && !countryChanged)
        {
            pkCountry.reloadAllComponents();
            var id:Int = Int(GAPI.Instance()!.GetUserCountryID())-1;
            pkCountry.selectRow(id, inComponent: 0, animated: true);
        }
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
        let alert = UIAlertController(title: "Updated", message: text, preferredStyle:UIAlertControllerStyle.Alert)
            
        let doSomethingAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.Default) { _ in
            self.dismissViewControllerAnimated(true, completion: nil);
        }
        
        alert.addAction(doSomethingAction)
        self.presentViewController(alert, animated: true, completion: nil)
    }
    
    
    func CommentError(text: String)
    {
        let alert = UIAlertView();
        alert.title = "Connection Error"
        alert.message = "Please check internet connection."
        alert.addButtonWithTitle("OK")
        alert.show();
    }
    
    
    func CommentResult(text: String)
    {
        
    }
    
    
    func CommentSystem(text: String)
    {
        
    }
    
    
    func DataReceived(endPoint:String, withData:NSDictionary?, andString:String)
    {
        //If data has been recieved back from server then update screen with
        UpdateDetails();
    }
}


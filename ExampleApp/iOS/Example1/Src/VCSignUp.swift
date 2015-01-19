//
//  ViewController.swift
//  Example1
//
//  Created by CD on 22/07/2014.
//  Copyright (c) 2014 Ginsberg. All rights reserved.
//

import UIKit


//
// Handle signing up to Ginsberg, with minimal information needed from user
//

class VCSignUp: UIViewController, GAPIProtocol, UITextFieldDelegate
{
    //
    // MARK: Outlets
    //
    
    @IBOutlet weak var aiBusy: UIView!
    @IBOutlet weak var tfEmail: UITextField!
    @IBOutlet weak var tfPassword: UITextField!
    @IBOutlet weak var tfCPassword: UITextField!
    @IBOutlet weak var btSignUp: UIButton!
    @IBOutlet weak var btBack: UIButton!
    
    
    //
    // MARK: View Controller
    //
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        
        //Set callbacks to this instance
        GAPI.Instance()?.SetCallbacks(self);
        
        //Hide busy indicator
        SetBusy(false);
        
        //Make buttons look nice
        btSignUp.layer.borderWidth = 0.0;
        btSignUp.layer.cornerRadius = 15;
        btBack.layer.borderWidth = 0.0;
        btBack.layer.cornerRadius = 15;
    }
    
    
    override func viewWillAppear(animated: Bool)
    {
        super.viewWillAppear(animated)
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
    // MARK: Actions
    //
    
    @IBAction func pressedSignUp(sender: UIButton)
    {
        //Call signup with entered values, using defaults where possible to reduce initial user inputs
        var wbIDs = [1,2,3];
        GAPI.Instance()!.SignUp("Please", lastName:"Replace", password:tfPassword.text, cpassword:tfCPassword.text, email:tfEmail.text, countryID:1, wbIDs:nil);
    }

    
    @IBAction func pressedBack(sender: UIButton)
    {
        self.dismissViewControllerAnimated(true, completion: nil);
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
        //If message is of signup success, rather than default initial posting reponse, tell user of successful signup
        if(text != "Post Success!")
        {
            let alert = UIAlertController(title: "Updated", message: text, preferredStyle:UIAlertControllerStyle.Alert)
            
            let doSomethingAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.Default) { _ in
                self.dismissViewControllerAnimated(true, completion: nil);
            }
            
            alert.addAction(doSomethingAction)
            self.presentViewController(alert, animated: true, completion: nil)
        }
    }
    
    
    func CommentError(text: String)
    {
        let alert = UIAlertView();
        alert.title = "Signup error"
        alert.message = text;
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
        
    }
}


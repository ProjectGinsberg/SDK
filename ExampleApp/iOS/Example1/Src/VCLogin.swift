//
//  ViewController.swift
//  Example1
//
//  Created by CD on 22/07/2014.
//  Copyright (c) 2014 Ginsberg. All rights reserved.
//

import UIKit


//
// Initial screen, for starting signup for user, else logging into system
//

class VCLogin: UIViewController, GAPIProtocol
{
    //
    // MARK: Outlets
    //
    
    @IBOutlet weak var aiBusy: UIView!
    @IBOutlet weak var vMain: UIView!
    @IBOutlet weak var btSignupNative: UIButton!
    @IBOutlet weak var btSignupWeb: UIButton!
    @IBOutlet weak var btLogin: UIButton!
    @IBOutlet weak var lbLogin: UILabel!
    
    
    //Example apps ID
    let CLIENT_ID : String =     "8583BBDA47ECF4E9DC136FFE50C26B89B646FCD0"
    let CLIENT_SECRET : String = "859E451F0E65245E242E3FCA96271BE2EB0B6AB4"
    
    
    //
    // MARK: View Controller
    //
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
    
        //Setting up buttons
        btSignupNative.layer.borderWidth = 0.0;
        btSignupNative.layer.cornerRadius = 15;
        btSignupWeb.layer.borderWidth = 0.0;
        btSignupWeb.layer.cornerRadius = 15;
        btLogin.layer.borderWidth = 0.0;
        btLogin.layer.cornerRadius = 15;
        
        //Hiding login button until needed
        btLogin.hidden = true;
        lbLogin.hidden = true;
    }
    
    
    override func viewWillAppear(animated: Bool)
    {
        super.viewWillAppear(animated)
    
        //Initial SDK setup
        GAPI.Instance()!.Setup(CLIENT_ID, secret:CLIENT_SECRET, callbacks:self);
    }
    
    
    func MoveOn()
    {
        //Move onto post screen
        self.performSegueWithIdentifier("segueToPost", sender: self);
    }
    
    
    //
    // MARK: Actions
    //
    
    @IBAction func pressedSignupWeb(sender: UIButton)
    {
        //Start web signin popover
        GAPI.Instance()!.SignUpWeb();
    }
    
    
    @IBAction func pressedLogin(sender: UIButton)
    {
        //Start SDK login process
        GAPI.Instance()!.Login();
    }
    
    
    //
    // MARK: Callbacks
    //
    
    //Login is required so setup screen for
    func NeedLogin()
    {
        //Show login buttons as login needed
        btLogin.hidden = false;
        lbLogin.hidden = false;
    }
    
    
    //Access gained so move into main part of app
    func GainedAccess()
    {
        //Login done so hide login button
        btLogin.hidden = true;
        lbLogin.hidden = true;
        
        //Move onto next view
        MoveOn();
    }
    
    
    func SetBusy(truth: Bool)
    {
    }
    
    
    func Comment(text: String)
    {
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
        
    }
}


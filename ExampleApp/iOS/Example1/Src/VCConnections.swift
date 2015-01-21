//
//  ViewController.swift
//  Example1
//
//  Created by CD on 22/07/2014.
//  Copyright (c) 2014 Ginsberg. All rights reserved.
//

import UIKit


//
// Show connections to third party products, such as Fitbit, RunKeeper, Jawbone
//

class VCConnections: UIViewController, GAPIProtocol
{
    //
    // MARK: Outlets
    //
    
    @IBOutlet weak var vMain: UIView!
    @IBOutlet weak var btCancel: UIButton!
    
    
    //
    // MARK: View Controller
    //
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
    
        //Make button look nice
        btCancel.layer.borderWidth = 0.0;
        btCancel.layer.cornerRadius = 15;
    }
    
    
    override func viewWillAppear(animated: Bool)
    {
        super.viewWillAppear(animated);
        
        //Set callbacks to this instance
        GAPI.Instance().SetCallbacks(self);
        
        //Start connections web popover, with views default background
        GAPI.Instance().ConnectionsWeb(UIImage(named: "bg-temp.png"));
    }
    
    
    override func viewDidAppear(animated: Bool)
    {
        super.viewDidAppear(animated)
    }
    
    
    //
    // MARK: Actions
    //
    
    @IBAction func pressedCancel(sender: UIButton)
    {
        self.dismissViewControllerAnimated(true, completion: nil);
    }
    
    
    //
    // MARK: Callbacks
    //
    
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


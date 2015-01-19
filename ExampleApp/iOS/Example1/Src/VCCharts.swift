//
//  ViewController.swift
//  Example1
//
//  Created by CD on 22/07/2014.
//  Copyright (c) 2014 Ginsberg. All rights reserved.
//

import UIKit

//
// Initial charting tests. Place holder for now
//

class VCCharts: UIViewController, GAPIProtocol, UIWebViewDelegate
{
    //
    // MARK: Outlets
    //
    
    @IBOutlet weak var vMain: UIView!
    @IBOutlet weak var btCancel: UIButton!
    @IBOutlet weak var wvChart1: UIWebView!
    @IBOutlet weak var wvChart2: UIWebView!
    @IBOutlet weak var wvChart3: UIWebView!
    @IBOutlet weak var svCharts: UIScrollView!
    
    
    //
    // MARK: View Controller
    //
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        
        //Make buttons nice
        btCancel.layer.borderWidth = 0.0;
        btCancel.layer.cornerRadius = 15;
        
        //Make scroll area just nice and big for now
        svCharts.contentSize = CGSizeMake(svCharts.contentSize.width, 2000.0);
    }
    
    
    override func viewWillAppear(animated: Bool)
    {
        super.viewWillAppear(animated)
        GAPI.Instance()?.SetCallbacks(self);
        
        //Setup example chart
        var baseUrl  = NSURL.fileURLWithPath(NSBundle.mainBundle().bundlePath);
        let pathhtml = NSBundle.mainBundle().pathForResource("index", ofType: "html");
        var c:String = String(contentsOfFile:pathhtml!, encoding:NSUTF8StringEncoding, error: nil)!;
        wvChart1.loadHTMLString(c, baseURL: baseUrl)
        
        //Add example values
        c.replaceRange(c.rangeOfString("series1=")!.endIndex...c.rangeOfString(";//End")!.startIndex, with: "[{x:1, y:5},{x:2, y:4},{x:3, y:3},{x:4, y:2},{x:5, y:1}]");
        wvChart2.loadHTMLString(c, baseURL: baseUrl)
        wvChart3.loadHTMLString(c, baseURL: baseUrl)
    }
    
    
    override func viewDidAppear(animated: Bool)
    {
        super.viewDidAppear(animated)
    }
    
    
    //WebView - To convert
    /*
    - (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType
    {
    return YES;
    }
    
    
    - (void)webViewDidStartLoad:(UIWebView *)webView
    {
    }
    
    
    - (void)webViewDidFinishLoad:(UIWebView *)webView
    {
    }
    
    
    - (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error
    {
    }
    */
    
    
    //
    // MARK: Actions
    //
    
    @IBAction func pressedUpdate(sender: UIButton)
    {
    }
    
    
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


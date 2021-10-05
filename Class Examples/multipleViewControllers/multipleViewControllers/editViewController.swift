//
//  editViewController.swift
//  multipleViewControllers
//
//  Created by Kai Schuyler on 9/30/21.
//

import UIKit

class editViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var userBook: UITextField!
    @IBOutlet weak var userAuthor: UITextField!
    
    override func viewDidLoad() {
        userBook.delegate = self
        userAuthor.delegate = self
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "favEdit" {
            let scene1ViewController = segue.destination as! ViewController
            if userBook.text?.isEmpty == false {
                scene1ViewController.user.favBook = userBook.text
            }
            if userAuthor.text?.isEmpty == false {
                scene1ViewController.user.favAuthor = userAuthor.text
            }
        }
    }
}

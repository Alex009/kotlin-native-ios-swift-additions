//
//  SwiftAddition.swift
//  SwiftAddition
//
//  Created by Aleksey Mikhailov on 02.06.2021.
//

import Foundation
import UIKit

@objc
public class SwiftAddition: NSObject {
  @objc
  public func getValue() -> Int {
    return 9
  }
}

public class MPLLiveData<T> {
  public init(value: T) {
    
  }
  
  func addObserverObserver(block: (T) -> Void) {
    
  }
}

public extension UILabel {
  func bindLabel(liveData: MPLLiveData<NSString>) {
    liveData.addObserverObserver { text in
      if let text = text as String? {
        self.text = text
      } else {
        self.text = "null observer"
      }
    }
  }
}

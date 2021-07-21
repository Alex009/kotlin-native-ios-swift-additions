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
  
  @objc
  public static var bindLiveData: ((Any) -> Void)? = nil
  
  @objc
  public static func setBindLiveData(block: @escaping ((Any) -> Void)) {
    SwiftAddition.bindLiveData = block
  }
}

//@objc
//public class MPLLiveData<T>: NSObject {
//  public init(value: T) {
//
//  }
//
//  func addObserverObserver(block: (T) -> Void) {
//
//  }
//}

@objc
public class MyLiveData<T> {
  
}

public extension UILabel {
  func bindLabel(liveData: MyLiveData<NSString>) {
    SwiftAddition.bindLiveData?(liveData)
//    liveData.addObserverObserver { text in
//      if let text = text as String? {
//        self.text = text
//      } else {
//        self.text = "null observer"
//      }
//    }
  }
}
